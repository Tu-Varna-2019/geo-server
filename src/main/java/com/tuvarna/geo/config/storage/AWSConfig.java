package com.tuvarna.geo.config.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.awspring.cloud.s3.InMemoryBufferingS3OutputStreamProvider;
import io.awspring.cloud.s3.Jackson2JsonS3ObjectConverter;
import io.awspring.cloud.s3.S3Template;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Component
public class AWSConfig {

    @Value("${access.key.id}")
    private String accessKeyId;
    @Value("${access.key.secret}")
    private String accessKeySecret;

    @Bean
    public S3Client s3Client() {

        return S3Client.builder()
                .region(Region.EU_WEST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, accessKeySecret)))
                .build();
    }

    @Bean
    public S3Template s3Template() {
        return new S3Template(s3Client(), new InMemoryBufferingS3OutputStreamProvider(s3Client(), null),
                new Jackson2JsonS3ObjectConverter(new ObjectMapper()), S3Presigner.create());
    }
}
