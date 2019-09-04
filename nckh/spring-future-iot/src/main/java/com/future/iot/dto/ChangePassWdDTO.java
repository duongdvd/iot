package com.future.iot.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePassWdDTO {
    @NotNull
    @NotBlank
    @Size(max = 15, min = 6)
    private String passWd;

    @NotNull
    @NotBlank
    @Size(max = 15, min = 6)
    private String passWdNew;

    @NotNull
    @NotBlank
    @Size(max = 15, min = 6)
    private String passWdNew1;

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public String getPassWdNew() {
        return passWdNew;
    }

    public void setPassWdNew(String passWdNew) {
        this.passWdNew = passWdNew;
    }

    public String getPassWdNew1() {
        return passWdNew1;
    }

    public void setPassWdNew1(String passWdNew1) {
        this.passWdNew1 = passWdNew1;
    }
}
