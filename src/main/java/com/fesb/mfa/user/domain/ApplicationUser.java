package com.fesb.mfa.user.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private Boolean twoFactorAuthenticationActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Set<BackupCode> backupCodes = new HashSet<>();

    public ApplicationUser() {

    }

    public ApplicationUser(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.twoFactorAuthenticationActive = false;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<BackupCode> getBackupCodes() {
        return backupCodes;
    }

    public void setBackupCodes(Set<BackupCode> backupCodes) {
        this.backupCodes = backupCodes;
    }

    public Boolean getTwoFactorAuthenticationActive() {
        return twoFactorAuthenticationActive;
    }

    public void setTwoFactorAuthenticationActive(Boolean twoFactorAuthenticationActive) {
        this.twoFactorAuthenticationActive = twoFactorAuthenticationActive;
    }
}