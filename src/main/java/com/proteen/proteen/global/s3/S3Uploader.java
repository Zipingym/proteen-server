package com.proteen.proteen.global.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadVideos(MultipartFile file) {
        try {
            String originName = UUID.randomUUID().toString();
            long size = file.getSize();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(size);

            amazonS3Client.putObject(
                    new PutObjectRequest(bucket, originName, file.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
            log.info(amazonS3Client.getUrl(bucket, originName).toString());
            return amazonS3Client.getUrl(bucket, originName).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
