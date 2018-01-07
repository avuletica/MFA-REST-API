package com.fesb.mfa.user.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class BackupCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Lob
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    private ApplicationUser user;

    public BackupCode() {

    }

    public BackupCode(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
