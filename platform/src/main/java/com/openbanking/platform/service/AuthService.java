package com.openbanking.platform.service;

import com.openbanking.platform.storage.Status;
import com.openbanking.platform.model.AuthEntity;
import com.openbanking.platform.repo.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepo authRepo;
    public void saveRecord(String url){
        AuthEntity authEntity = new AuthEntity();
        authEntity.setVideoURL(url);
        authEntity.setStatus(Status.UNVERIFIED);
        authRepo.save(authEntity);
    }
}
