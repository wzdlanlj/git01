package xyz.xkun.crm.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import xyz.xkun.crm.annotation.RequestPermission;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.utils.AssertUtil;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: TODO
 * @author: fkun
 * @date: 2019/6/12 21:59
 */
@Component
@Aspect
public class permissionAdaptor {

    @Autowired
    private HttpSession session;

    //切面拦截
    @Pointcut("@annotation(xyz.xkun.crm.annotation.RequestPermission)")
    public void cut() {

    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        Object result = null;

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        RequestPermission permission = method.getAnnotation(RequestPermission.class);
        String aclValue = permission.aclValue();
        /**
         * 1.获取目标方法的权限码
         * 2.获取当前用户的权限列表
         */
        List<String> permissions = (List<String>) session.getAttribute(CrmConstant.USER_PERMISSIONS);

        AssertUtil.isTrue(CollectionUtils.isEmpty(permissions) || permissions.contains(aclValue), "没有权限访问");


        result = pjp.proceed();

        return result;
    }

}
