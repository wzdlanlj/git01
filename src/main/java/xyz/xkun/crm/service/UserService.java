package xyz.xkun.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import xyz.xkun.crm.base.BaseQuery;
import xyz.xkun.crm.base.BaseService;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.dao.UserMapper;
import xyz.xkun.crm.dao.UserRoleMapper;
import xyz.xkun.crm.dto.UserDto;
import xyz.xkun.crm.model.UserInfo;
import xyz.xkun.crm.po.User;
import xyz.xkun.crm.po.UserRole;
import xyz.xkun.crm.query.UserQuery;
import xyz.xkun.crm.utils.AssertUtil;
import xyz.xkun.crm.utils.Md5Util;
import xyz.xkun.crm.utils.UserIDBase64;

import java.util.*;
import java.util.stream.Stream;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/4 10:32
 */
@Service
public class UserService extends BaseService<UserDto> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public UserInfo login(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空", 300);
        AssertUtil.isTrue(StringUtils.isBlank(userPwd), "密码不能为空", 300);
        User user = userMapper.queryUserByName(userName);
        AssertUtil.isTrue(null == user, "用户不存在", 300);
        AssertUtil.isTrue(!Md5Util.encode(userPwd).equals(user.getUserPwd()), "用户名或密码不正确!", 300);

        return createUserInfo(user);
    }

    private UserInfo createUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userInfo.setUserName(user.getUserName());
        userInfo.setRealName(user.getTrueName());
        return userInfo;
    }

    /**
     * 密码更改
     *
     * @param oldPwd
     * @param newPwd
     * @param confirmPwd
     * @param userId
     */
    public void updateUserPwd(String oldPwd, String newPwd, String confirmPwd, int userId) {
        checkOldNewPwd(oldPwd, newPwd, confirmPwd);
        User user = userMapper.queryById(userId);
        AssertUtil.isTrue(null == user, "用户不存在或者已注销");
        AssertUtil.isTrue(!Md5Util.encode(oldPwd).equals(user.getUserPwd()), "旧密码不正确");
        AssertUtil.isTrue(userMapper.updateUserPwd(Md5Util.encode(newPwd), userId) < 1, "密码更新失败");
    }

    private void checkOldNewPwd(String oldPwd, String newPwd, String confirmPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd), "旧密码为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPwd), "新密码为空");
        AssertUtil.isTrue(!newPwd.equals(confirmPwd), "两次密码不一致");
    }

    public Integer save(UserDto user) {
        System.out.println(user);
        return userMapper.save(user);
    }

    //保存或更新用户信息
    public void saveOrUpdateUser(UserDto user, Integer[] roleIds) {
//        return userMapper.saveOrUpdateUser();
        checkUserParams(user);
        user.setUpdateDate(new Date());
        Integer id = user.getId();
        if (null == id) {
            AssertUtil.isTrue(null != userMapper.queryUserByName(user.getUserName()), "用户名已存在");
            //添加
            /**
             * 初始密码:123456
             * 存时加密字符串
             */
            user.setUserPwd(Md5Util.encode("123456"));
            user.setIsValid(1);
            user.setCreateDate(new Date());
            AssertUtil.isTrue(userMapper.save(user) < 1, CrmConstant.OPS_FAILED_MSG);
        } else {
            /**
             * 更新
             * 1.用户名不允许修改
             * 2.更新角色
             */
            AssertUtil.isTrue(!user.getUserName().equals(userMapper.queryById(id).getUserName()), "用户名不允许修改");
            AssertUtil.isTrue(userMapper.update(user) < 1, CrmConstant.OPS_FAILED_MSG);

            /**
             * 角色:先查询用户是否有角色,如果有进行删除再添加,如果没有直接添加
             */
            Integer roleNum = userRoleMapper.queryUserRolesByUserId(id);
            if (roleNum > 0) {
                //删除
                AssertUtil.isTrue(userRoleMapper.deleteUserRolesByUserId(id) < roleNum, CrmConstant.OPS_FAILED_MSG);
            }
        }
        /**
         * 角色添加
         * 获取用id
         */
        if (null != roleIds && roleIds.length > 0) {
            Integer userId = user.getId();
            List<UserRole> roleList = new ArrayList<>();
            for (Integer roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                roleList.add(userRole);
            }
            AssertUtil.isTrue(userRoleMapper.saveBatch(roleList) < roleList.size(), CrmConstant.OPS_FAILED_MSG);
        }
    }

    //User校验
    private void checkUserParams(User user) {

        String userName = user.getUserName();
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名为空!");
        AssertUtil.isTrue(StringUtils.isBlank(user.getTrueName()), "真实姓名为空!");
        AssertUtil.isTrue(StringUtils.isBlank(user.getEmail()), "邮箱为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getPhone()), "手机号为空");
    }

    //重写分页操作
    /*public PageInfo<UserDto> queryByParams(UserQuery userQuery) throws DataAccessException {
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        List<UserDto> users = userMapper.queryByParams(userQuery);
        */

    /**
     * 把字符串1,2,3 变成[1,2,3]
     *//*
        for (UserDto userDto : users) {
            String[] roleIdArr = userDto.getRoleIdsStr().split(",");
            List<Integer> roleIdList = new ArrayList<>();
            for (String roleId : roleIdArr) {
                roleIdList.add(Integer.valueOf(roleId));
            }
            userDto.setRoleIds(roleIdList);
        }
        return new PageInfo<UserDto>(users);
    }*/
/*    public Map<String, Object> queryForPage(UserQuery userQuery) throws DataAccessException {
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        List<UserDto> userDtos = userMapper.queryByParams(userQuery);
        PageInfo<UserDto> pageInfo = new PageInfo<UserDto>(userDtos);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        userDtos = pageInfo.getList();
        */

    /**
     * 把字符串1,2,3 变成[1,2,3]
     *//*
        for (UserDto userDto : userDtos) {
            String rolerIdsStr = userDto.getRoleIdsStr();
            if (null != rolerIdsStr) {
                String[] roleIdArr = userDto.getRoleIdsStr().split(",");
                List<Integer> roleIdList = new ArrayList<>();
                for (String roleId : roleIdArr) {
                    roleIdList.add(Integer.valueOf(roleId));
                }
                userDto.setRoleIds(roleIdList);
            }

        }
        return map;
    }*/

    //批量删除用户
    public void deleteUsers(Integer[] ids) {
        if (null != ids && ids.length > 0) {
            for (Integer userId : ids) {
                /**
                 * 删除用户(假删除)
                 * 删除用户的所属角色
                 */
                AssertUtil.isTrue(userMapper.fakeDelete(userId) < 1, "用户删除失败");
                Integer rolesNum = userRoleMapper.queryUserRolesByUserId(userId);
                if (rolesNum > 0) {
                    //真删除中间表
                    AssertUtil.isTrue(userRoleMapper.deleteUserRolesByUserId(userId) < rolesNum, CrmConstant.OPS_FAILED_MSG);
                }
            }
        }
    }
    /**
     * 两条执行sql语句时,可能两条语句中如果出现错误,就会出现前者删除后者无更改,数据不一致
     */
}










