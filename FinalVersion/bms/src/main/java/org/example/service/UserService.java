package org.example.service;

import cn.dev33.satoken.util.SaResult;
import org.example.dto.*;
import org.example.entity.Result;
import org.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {
    SaResult login(UserLoginDto userLoginDto);

    SaResult register(UserRegisterDto userRegisterDto);

    SaResult queryUserInfos(UserQueryDto userQueryDto);

    SaResult removeUser(Integer userId);

    SaResult removeUsers(Integer[] userIds);

    SaResult queryUserNameIsExist(String userName);

    SaResult addUser(UserAddDto userAddDto);

    SaResult updateUser(UserUpdateDto userUpdateDto);

    SaResult updateUserPwd(UserPasswordDto userPasswordDto);

    SaResult checkUserPwd(UserPasswordDto userPasswordDto);

    int updateUserImg(String userId, String imgUrl);
}
