package com.javays.base.web.controller;


import com.javays.base.bean.ReturnEntity;
import com.javays.base.util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * BaseServlet 类的使用规则：
 * 1.继承与BaseServlet类
 * 2.自己的servlet类不需要重写 doGet、doPost 方法
 * 3.自己的servlet类的方法名不要有重名
 * 4.自己的servlet类的 参数：HttpServletRequest、HttpServletResponse
 * 5.客户端发送的请求 type=调用方法的方法名
 * <p>
 * 如果当前方法不会再被调用到【tomcat除外】，在当前方法中处理 try-catch
 * 如果当前方法会被调用，抛出
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReturnEntity returnEntity = null;
        try {
            // 获取 type 值，type=调用servlet类中方法的方法名
            String type = request.getParameter("type");
            // 获取子类中所有的方法
            Class<? extends BaseServlet> sonClass = this.getClass();// 获取子类的class
            Method[] declaredMethods = sonClass.getDeclaredMethods();// 获取子类中所有的方法【共有、私有、受保护、默认】
            Method method = null;
            // 循环 declaredMethods
            for (Method declaredMethod : declaredMethods) {
                // 判断type和方法名是否相同
                if (declaredMethod.getName().equals(type)) {
                    method = declaredMethod;
                    break;
                }
            }
            if (method != null) {
                // 如果通过type找到Method，调用Method
                Object returnObject = method.invoke(this, request, response);// returnObject:servlet类中返回值
                if (returnObject != null) {
                    if (returnObject instanceof ReturnEntity) {
                        returnEntity = (ReturnEntity) returnObject;
                    }
                } else {
                    returnEntity = null;
                }
            } else {
                returnEntity = new ReturnEntity();
                returnEntity.setCode(404);
                returnEntity.setMessage("通过type未找servlet类中的方法");
            }
        } catch (NullPointerException e) {
            // 项目所有功能都是些完成之后，在换成这中，开发阶段使用：抛出异常
//            returnEntity = new ReturnEntity();
//            returnEntity.setCode(500);
//            returnEntity.setMessage("空指针异常");
            // 错误信息 输出
//            e.printStackTrace();
            throw new RuntimeException("空指针异常", e);
        } catch (Exception e) {
            // 项目所有功能都是些完成之后，在换成这中，开发阶段使用：抛出异常
//            returnEntity = new ReturnEntity();
//            returnEntity.setCode(500);
//            returnEntity.setMessage(e.getMessage());
            // 错误信息 输出
//            e.printStackTrace();
            // 项目都完成之后，修改异常处理方式抛出运行时异常
            throw new RuntimeException(e);
        } finally {
            if (returnEntity != null) {
                ServletUtils.sendDataToClient(response, returnEntity);
            }
        }
    }
}
