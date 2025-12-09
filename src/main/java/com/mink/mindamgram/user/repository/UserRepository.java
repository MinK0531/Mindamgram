package com.mink.mindamgram.user.repository;

import com.mink.mindamgram.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
    public int insertUser(
            @Param("signinId") String signinId
            , @Param("password") String password
            , @Param("name") String name
            , @Param("email") String email);

    public int countBySigninId(@Param("signinId") String signinId);

    public User selectUser(
            @Param("signinId") String signinId
            , @Param("password") String password);
}
