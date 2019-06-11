package xyz.xkun.crm.interceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.service.UserService;
import xyz.xkun.crm.utils.AssertUtil;
import xyz.xkun.crm.utils.LoginUserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/5 16:12
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        /**
         * 判断用户是否登录
         * 非法请求拦截
         */
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        AssertUtil.isTrue(null==userId||null==userService.queryById(userId), CrmConstant.USER_NOT_LOGIN_MSG);
        return true;
    }
}
