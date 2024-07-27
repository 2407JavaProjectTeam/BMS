package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserAddDto {
    private String userName;

    private String userPassword;

    private String userRname;

    private String userGender;

    private String userTel;

    private String userEmail;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date registrationDate;

    private String roleName;
}
