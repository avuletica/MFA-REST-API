package com.fesb.mfa.user.service;

import com.fesb.mfa.user.domain.ApplicationUser;
import com.fesb.mfa.user.domain.ApplicationUserRepository;
import com.fesb.mfa.user.domain.BackupCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {
    @Autowired
    private ApplicationUserRepository userRepository;

    @Override
    public ApplicationUser generateBackupCodes(String username) {
        final int MAXIMUM_NUMBER = 999999;
        final int MINIMUM_NUMBER = 100000;
        ApplicationUser user = userRepository.findByUsername(username);

        user.getBackupCodes().clear();

        for (int i = 0; i < 10; i++) {
            Random rn = new Random();
            int range = MAXIMUM_NUMBER - MINIMUM_NUMBER + 1;
            int randomNum = rn.nextInt(range) + MINIMUM_NUMBER;

            BackupCode backupCode = new BackupCode(Integer.toString(randomNum));

            backupCode.setCode(backupCode.getCode());
            user.getBackupCodes().add(backupCode);
        }

        userRepository.save(user);

        return user;

    }

    @Override
    public Boolean getTwoFactorAuthenticationActiveState(String username) {
        ApplicationUser user = userRepository.findByUsername(username);
        return user.getTwoFactorAuthenticationActive();
    }

    @Override
    public ApplicationUser updateTwoFactorAuthenticationActiveState(String username, Boolean state) {
        ApplicationUser user = userRepository.findByUsername(username);

        user.setTwoFactorAuthenticationActive(state);

        userRepository.save(user);

        return user;
    }

    @Override
    public Boolean validateBackupCode(String username, String code) {
        ApplicationUser user = userRepository.findByUsername(username);
        Set<BackupCode> backupCodes = user.getBackupCodes();
        for (BackupCode backupCode : backupCodes) {
            if (backupCode.getCode().equals(code)) {
                user.getBackupCodes().remove(backupCode);
                userRepository.save(user);
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean validateSecretQuestionAnswer(String username, String answer) {
        ApplicationUser user = userRepository.findByUsername(username);
        return user.getSecretQuestionAnswer().equals(answer);
    }

}
