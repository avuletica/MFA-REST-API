package com.fesb.mfa.user.service;

import com.fesb.mfa.user.domain.ApplicationUser;

public interface ApplicationUserService {
    ApplicationUser generateBackupCodes(String username);

    ApplicationUser updateTwoFactorAuthenticationActiveState(String username, Boolean state);

    Boolean validateBackupCode(String username, String code);

    Boolean getTwoFactorAuthenticationActiveState(String username);
}

