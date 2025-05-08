package com.tecnologiaefinancas.authify.io;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {

    @NotBlank(message = "New password is required")
    private String newPassword;
    @NotBlank(message = "OTP is required")
    private String otp;
    @NotBlank(message = "E-mail is required")
    private String email;
}
