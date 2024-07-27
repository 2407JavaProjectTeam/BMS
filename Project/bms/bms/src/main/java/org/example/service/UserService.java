package org.example.service;

import cn.dev33.satoken.util.SaResult;
import org.example.dto.UserAddDto;
import org.example.dto.UserLoginDto;
import org.example.dto.UserQueryDto;
import org.example.dto.UserUpdateDto;
import org.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {
    SaResult login(UserLoginDto userLoginDto);

    SaResult queryUserInfos(UserQueryDto userQueryDto);

    SaResult removeUser(Integer userId);

    SaResult removeUsers(Integer[] userIds);

    SaResult queryUserNameIsExist(String userName);

    SaResult addUser(UserAddDto userAddDto);

    SaResult updateUser(UserUpdateDto userUpdateDto);
}
