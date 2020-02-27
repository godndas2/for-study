package com.forstudy.oauth2.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class VerificationForm {

    @NotEmpty
    @Email
    private String email;
}
