package xyz.xkun.crm.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import xyz.xkun.crm.model.ResultInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/4 21:00
 */
public class BaseController {

    //前置处理方法
    @ModelAttribute
    public void PreHandle(HttpServletRequest request){
        request.setAttribute("ctx",request.getContextPath());//获取当前项目路径,因为页面
    }
    public ResultInfo success(Integer code,String msg,Object result){
        return new ResultInfo(code,msg,result);
    }
    public ResultInfo success(Integer code,String msg){
        return new ResultInfo(code,msg);
    }
}
