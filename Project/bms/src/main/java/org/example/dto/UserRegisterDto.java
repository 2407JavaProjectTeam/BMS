package org.example.dto;
import lombok.Data;

@Data
public class UserRegisterDto {
    private String userName;
    private String userPassword;
    private String confirmPassword;
    private int roleId;
}
