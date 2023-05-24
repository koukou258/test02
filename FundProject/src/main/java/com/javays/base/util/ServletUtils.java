package com.javays.base.util;

import com.alibaba.fastjson2.JSONObject;
import com.javays.base.bean.ReturnEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Map;

public class ServletUtils {

    /**
     * 编码格式
     *
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void processEncoding(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
    }

    /**
     * 跨域访问
     *
     * @param response
     */
    public static void processCors(HttpServletResponse response) {
        /* 允许跨域的主机地址 */
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response.setHeader("Access-Control-Max-Age", "4200");
        /* 允许跨域的请求头 */
        response.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    /**
     * 响应数据到页面
     *
     * @param response
     * @param returnEntity
     * @throws IOException
     */
    public static void sendDataToClient(HttpServletResponse response, ReturnEntity returnEntity) throws IOException {
        PrintWriter out = response.getWriter();
        String jsonString = JSONObject.toJSONString(returnEntity);
        out.write(jsonString);
        out.close();
    }
    /**
     * 将 map集合转为实体类对象
     *
     * @param requestParameterMap 请求数据
     * @param entityClass         实体类的Class
     * @param <T>
     * @return
     */
    public static <T> T mapToEntityObject(Map<String, String[]> requestParameterMap, Class<T> entityClass) throws InstantiationException, IllegalAccessException {
        T entity = null;

        // requestParameterMap 中有数据时，需要将map中的数据存储在实体类对象
        if (!requestParameterMap.isEmpty()) {
            // 获取实体类中所有的成员变量
            Field[] declaredFields = entityClass.getDeclaredFields();

            // 实例化 实体类
            entity = entityClass.newInstance();

            //循环获取到每一个成员变量
            for (Field declaredField : declaredFields) {
                // 获取实体类中成员变量的变量名
                String name = declaredField.getName();
                // 通过变量名到map集合中获取数据
                String[] valueArray = requestParameterMap.get(name);

                if (valueArray != null) {
                    // 暴力破解
                    declaredField.setAccessible(true);
                    if (valueArray.length == 1) {// 如果只有一个数据，表单元素不是checkbox
                        String valueStr = valueArray[0];// 获取请求参数
                        // 判断 declaredField 成员变量的数据类型，将 数据转为对应类型的数据
                        // Integer.class.getName():获取Integer类的路径
                        // declaredField.getType():获取成员变量的数据类型
                        // declaredField.getType().getName:获取成员变量的数据类型的路径
                        if (Integer.class.getName().equals(declaredField.getType().getName())) {
                            Integer value = valueStr != null && !"".equals(valueStr) ? Integer.valueOf(valueStr) : null;
                            declaredField.set(entity, value);
                        } else if (Float.class.getName().equals(declaredField.getType().getName())) {
                            Float value = valueStr != null && !"".equals(valueStr) ? Float.valueOf(valueStr) : null;
                            declaredField.set(entity, value);
                        } else if (String.class.getName().equals(declaredField.getType().getName())) {
                            declaredField.set(entity, valueStr);
                        }
                    } else {
                        String value = "";
                        for (int i = 0; i < valueArray.length; i++) {
                            value += valueArray[i];
                            if (i < valueArray.length - 1) {
                                value += ",";
                            }
                        }
                        declaredField.set(entity, value);
                    }
                }
            }
        }
        return entity;
    }

}
