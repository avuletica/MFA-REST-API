package com.fesb.mfa.user.service;

import com.fesb.mfa.user.domain.ApplicationUser;

public interface ApplicationUserService {
     ApplicationUser generateBackupCodes(String username);
}

