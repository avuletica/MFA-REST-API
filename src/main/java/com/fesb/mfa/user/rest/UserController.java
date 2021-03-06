package com.fesb.mfa.user.rest;

import com.fesb.mfa.user.domain.ApplicationUser;
import com.fesb.mfa.user.domain.ApplicationUserRepository;
import com.fesb.mfa.user.domain.BackupCode;
import com.fesb.mfa.user.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/security")
public class UserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }

    @GetMapping("/user/{userName}")
    public ApplicationUser getUserInformation(@PathVariable String userName) {
        return applicationUserRepository.findByUsername(userName);
    }

    @GetMapping("/backupCodes/{userName}")
    public Set<BackupCode> getUserBackupCodes(@PathVariable String userName) {
        return applicationUserRepository.findByUsername(userName).getBackupCodes();
    }

    @GetMapping("/backupCodes/generate/{userName}")
    public ApplicationUser generateUserBackupCodes(@PathVariable String userName) {
        return applicationUserService.generateBackupCodes(userName);
    }

    @GetMapping("/validateBackupCode/{code}/{userName}")
    public Boolean validateBackupCode(@PathVariable String userName, @PathVariable String code) {
        return applicationUserService.validateBackupCode(userName, code);
    }

    @GetMapping("/validateSecretQuestionAnswer/{answer}/{userName}")
    public Boolean validateSecretQuestionAnswer(@PathVariable String userName, @PathVariable String answer) {
        return applicationUserService.validateSecretQuestionAnswer(userName, answer);
    }

    @GetMapping("/twoFactorAuthenticationActiveState/{userName}")
    public Boolean twoFactorAuthenticationActiveState(@PathVariable String userName) {
        return applicationUserService.getTwoFactorAuthenticationActiveState(userName);
    }

    @PostMapping("/updateTwoFactorAuthenticationActiveState/{state}/{userName}")
    public ApplicationUser updateTwoFactorAuthenticationActiveState(@PathVariable String userName, @PathVariable Boolean state) {
        return applicationUserService.updateTwoFactorAuthenticationActiveState(userName, state);
    }

}