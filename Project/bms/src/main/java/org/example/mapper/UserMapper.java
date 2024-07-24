package org.example.mapper;

import org.example.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity org.example.entity.User
 */
public interface UserMapper extends BaseMapper<User> {
    User selectByUserName(String userName);
}




