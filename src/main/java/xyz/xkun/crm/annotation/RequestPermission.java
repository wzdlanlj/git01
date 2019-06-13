package xyz.xkun.crm.annotation;

import java.lang.annotation.*;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/12 21:56
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestPermission {
    String aclValue() default "";
}
