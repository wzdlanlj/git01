package xyz.xkun.crm.exceptions;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import xyz.xkun.crm.constants.CrmConstant;
import xyz.xkun.crm.model.ResultInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @title: wzdlanlj@163.com
 * @projectName: shsxt_srm
 * @description: DONE
 * @author: fkun
 * @date: 2019/6/5 9:40
 */
@Component
public class GolbaExcptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception e) {
        ModelAndView modelAndView = creatDefaultModelAndView(request, e);

        //遇到
        if (e instanceof LoginException){
            modelAndView.addObject("errorMsg", CrmConstant.USER_NOT_LOGIN_MSG);
            modelAndView.setViewName("error");
            return modelAndView;
        }


        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
            if (null == responseBody) {
                //普通页面请求
                if (e instanceof ParamsException) {
                    ParamsException paramsException = (ParamsException) e;
                    modelAndView.addObject("errorMsg", paramsException.getMsg());
                }
            } else {
                //json请求
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统繁忙");

                if (e instanceof ParamsException) {
                    ParamsException paramsException = (ParamsException) e;
                    resultInfo.setMsg(paramsException.getMsg());
                }
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                //输出到页面
                PrintWriter printWriter = null;
                try {
                    printWriter = response.getWriter();
                    printWriter.write(JSON.toJSONString(resultInfo));
                    printWriter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    printWriter.close();
                }
                return null;
            }
        }
        return modelAndView;
    }

    private ModelAndView creatDefaultModelAndView(HttpServletRequest request, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");//错误页面
        modelAndView.addObject("errorMsg", e.getMessage() + "系统繁忙");//错误信息
        modelAndView.addObject("ctx", request.getContextPath());
        modelAndView.addObject("uri",request.getRequestURI());
        return modelAndView;
    }
}
