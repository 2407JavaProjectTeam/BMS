package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Title: UserUpdateDto
 * @Author Huan
 * @Date 2024/7/25 17:20
 * @description:
 */
@Data
public class UserUpdateDto {
    private Integer userId;

    private String userName;

    private String userRname;

    private String userGender;

    private String userTel;

    private String userEmail;

    private String userStatus;

    private Integer maxNum;

    private Integer borrowNum;

    private String roleName;
}
