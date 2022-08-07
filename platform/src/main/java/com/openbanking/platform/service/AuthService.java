package com.openbanking.platform.service;

import com.openbanking.platform.storage.CloudinaryClient;
import com.openbanking.platform.storage.Status;
import com.openbanking.platform.model.AuthEntity;
import com.openbanking.platform.repo.AuthRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthRepo authRepo;
    private final CloudinaryClient cloudinaryClient;
    @Scheduled(initialDelay = 300L, fixedDelay = 8000L)
    public void saveRecord() throws IOException {
        log.info("Video uplading is started");
        File directoryPath = new File("/Users/shamistanhuseynov/Desktop/evolution-open-banking-platform/platform/src/main/resources/videos");

        for(File file : Objects.requireNonNull(directoryPath.listFiles())) {
            String fileAbsolutePath = file.getAbsolutePath();
            if (!fileAbsolutePath.endsWith("avi")){
                continue;
            }
            String url = cloudinaryClient.uploadVideo(fileAbsolutePath);
            getAndSaveEntity(url);
            boolean deleted = file.delete();
            if (deleted){
                log.info("Operation is successful for "+ fileAbsolutePath);
            }
        }
    }

    public void getAndSaveEntity(String url){
        AuthEntity authEntity = new AuthEntity();
        authEntity.setVideoURL(url);
        authEntity.setStatus(Status.UNVERIFIED);
        authRepo.save(authEntity);
    }

}
