package com.openbanking.platform.repo;

import com.openbanking.platform.model.AuthEntity;

import com.openbanking.platform.storage.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthRepo extends JpaRepository<AuthEntity, Long> {

    List<AuthEntity> findByStatus(Status verified);
}
