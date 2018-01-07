package com.fesb.mfa;

import com.fesb.mfa.user.domain.ApplicationUser;
import com.fesb.mfa.user.domain.ApplicationUserRepository;
import com.fesb.mfa.user.domain.BackupCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final int MAXIMUM_NUMBER = 999999;
        final int MINIMUM_NUMBER = 100000;

        ApplicationUser user = new ApplicationUser("test", "test");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        for (int i = 0; i < 10; i++) {
            Random rn = new Random();
            int range = MAXIMUM_NUMBER - MINIMUM_NUMBER + 1;
            int randomNum = rn.nextInt(range) + MINIMUM_NUMBER;

            BackupCode backupCode = new BackupCode(Integer.toString(randomNum));

            backupCode.setCode(backupCode.getCode());
            user.getBackupCodes().add(backupCode);
        }

        userRepository.save(user);

    }
}
