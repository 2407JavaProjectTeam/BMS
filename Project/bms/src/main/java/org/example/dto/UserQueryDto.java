package org.example.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserQueryDto extends BaseQueryParamsDto{
    private String userName;
    private String userRname;
    private String roleName;
    private Byte userStatus;
}
