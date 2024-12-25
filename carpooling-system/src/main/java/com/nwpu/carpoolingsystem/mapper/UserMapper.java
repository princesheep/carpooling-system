package com.nwpu.carpoolingsystem.mapper;

import com.nwpu.carpoolingsystem.entity.Carpool;
import com.nwpu.carpoolingsystem.entity.User;
import com.nwpu.carpoolingsystem.entity.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    // 插入用户
    @Insert("INSERT INTO user (username, password, email, phone, create_time, update_time) " +
            "VALUES (#{username}, #{password}, #{email}, #{phone}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    List<String> findRoleByUserId(Long id);

    void insertUserRole(UserRole userRole);

    Long findIdByUserName(String username);

}
