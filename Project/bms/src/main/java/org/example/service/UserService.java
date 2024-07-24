package org.example.service;

import cn.dev33.satoken.util.SaResult;
import org.example.dto.UserLoginDto;
import org.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserService extends IService<User> {
    SaResult login(UserLoginDto userLoginDto);
}
