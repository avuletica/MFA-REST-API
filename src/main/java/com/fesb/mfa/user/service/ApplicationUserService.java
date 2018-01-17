package com.fesb.mfa.user.service;

import com.fesb.mfa.user.domain.ApplicationUser;

public interface ApplicationUserService {
     ApplicationUser generateBackupCodes(String username);

     ApplicationUser updateBackupCodeActiveState(String username, Boolean state);

     ApplicationUser deleteBackupCode(String username, String code);
}

