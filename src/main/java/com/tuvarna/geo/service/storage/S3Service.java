package com.tuvarna.geo.service.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tuvarna.geo.config.storage.AWSConfig;
import com.tuvarna.geo.service.dto.user.request.LoggerDTO;

import io.awspring.cloud.s3.S3Exception;
import io.awspring.cloud.s3.S3Resource;

@Component
public class S3Service {
    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;
    @Autowired
    private AWSConfig awsConfig;

    private final Logger logger = LogManager.getLogger(S3Service.class.getName());
    private LocalDate currentDate = LocalDate.now();
    private String logPath = currentDate.getYear() + "/" + currentDate.getMonth() + "/" + currentDate.getDayOfMonth()
            + "/logs.json";

    public void store(LoggerDTO loggerDTO) {
        List<LoggerDTO> existingLogs = getLog(logPath);
        existingLogs.add(loggerDTO);

        awsConfig.s3Template().store(bucket,
                logPath,
                existingLogs);
    }

    @SuppressWarnings("unchecked")
    private List<LoggerDTO> getLog(String objectPath) {
        try {
            return awsConfig.s3Template().read(bucket, objectPath, List.class);
        } catch (S3Exception err) {
            logger.warn("S3 path: {} doesn't exist. Now creating a new directory...", logPath);
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public List<LoggerDTO> readLogs() {
        List<LoggerDTO> logs = new ArrayList<>();
        try {
            for (S3Resource s3Resource : awsConfig.s3Template().listObjects(bucket, "")) {
                logger.info("Retrieved object from s3: {} ", s3Resource.getFilename());
                List<LoggerDTO> fileLog = awsConfig.s3Template().read(bucket, s3Resource.getFilename(), List.class);

                logs.addAll(fileLog);
            }
        } catch (S3Exception err) {
            logger.warn("No S3 objects found! Moving on...");
            return new ArrayList<>();
        }
        return logs;
    }

}