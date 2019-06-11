package xyz.xkun.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.xkun.crm.base.BaseDao;
import xyz.xkun.crm.dto.UserDto;
import xyz.xkun.crm.po.User;
import xyz.xkun.crm.query.UserQuery;

import java.util.List;

@Repository
public interface UserMapper extends BaseDao<UserDto> {

    public User queryUserByName(String userName);

    public Integer updateUserPwd(@Param("userPwd") String userPwd, @Param("id") Integer id);

    public User queryById(int userId);

    public Integer fakeDelete(Integer userId);

}