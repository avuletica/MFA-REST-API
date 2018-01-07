package com.fesb.mfa.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BackupCodeRepository extends JpaRepository<BackupCode, Long> {
}
