package com.tuvarna.geo.service.storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tuvarna.geo.config.storage.AWSConfig;
import com.tuvarna.geo.service.dto.user.request.LoggerDTO;

@Component
public class S3Service {
    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    private final Logger logger = LogManager.getLogger(S3Service.class.getName());

    @Autowired
    private AWSConfig awsConfig;

    public void readLogs() {
        LoggerDTO loadedStudent = awsConfig.s3Template().read(bucket, "logs.xml", LoggerDTO.class);
        logger.info(loadedStudent);
    }

    public void store(LoggerDTO loggerDTO) {
        awsConfig.s3Template().store(bucket, "logs.xml", loggerDTO);
    }
}