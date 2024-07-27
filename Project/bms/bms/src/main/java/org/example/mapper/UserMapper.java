package org.example.mapper;

import org.example.dto.UserQueryDto;
import org.example.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity org.example.entity.User
 */
public interface UserMapper extends BaseMapper<User> {
    User selectByUserName(String userName);

    List<User> selectUsers(UserQueryDto userQueryDto);

    Integer selectUsersCount(UserQueryDto userQueryDto);
}




