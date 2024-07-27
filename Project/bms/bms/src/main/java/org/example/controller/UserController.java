package org.example.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.example.dto.UserAddDto;
import org.example.dto.UserLoginDto;
import org.example.dto.UserQueryDto;
import org.example.dto.UserUpdateDto;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public SaResult login(@RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }

    @GetMapping("/logout")
    public SaResult logout(){
        StpUtil.logout();
        return SaResult.ok("注销成功！");
    }
    @GetMapping("/user")
    public SaResult queryUserInfo(UserQueryDto userQueryDto){
        return userService.queryUserInfos(userQueryDto);
    }

    @DeleteMapping("/user/{userId}")
    public SaResult removeUser(@PathVariable("userId") Integer userId){
        return userService.removeUser(userId);
    }

    @DeleteMapping("/users/{ids}")
    public SaResult removeUsers(@PathVariable Integer[] ids){
        return userService.removeUsers(ids);
    }

    @GetMapping("/user/{userName}")
    public SaResult queryUserByUserName(@PathVariable("userName") String userName){
        return userService.queryUserNameIsExist(userName);
    }

    @PostMapping("/user")
    public SaResult addUser(@RequestBody UserAddDto userAddDto){
        return userService.addUser(userAddDto);
    }

    @PutMapping("/user")
    public SaResult updateUser(@RequestBody UserUpdateDto userUpdateDto){
        return userService.updateUser(userUpdateDto);
    }
}
