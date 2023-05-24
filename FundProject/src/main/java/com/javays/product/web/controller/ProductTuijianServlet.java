package com.javays.product.web.controller;

/**
 * @Author：黄宇航
 * @Version：1.0
 * @Date：2023/4/28-11:01
 * @Since：jdk1.8
 * @Description：
 */

import com.javays.base.bean.PageInfo;
import com.javays.base.bean.ReturnEntity;
import com.javays.base.util.ServletUtils;
import com.javays.base.web.controller.BaseServlet;
import com.javays.product.bean.ProTJInfo;
import com.javays.product.service.ProductTuijianService;
import com.javays.product.service.impl.ProductTuijianServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * BaseServlet 类的使用规则：
 * 1.继承与BaseServlet类
 * 2.自己的servlet类不需要重写 doGet、doPost 方法
 * 3.自己的servlet类的方法名不要有重名
 * 4.自己的servlet类的 参数：HttpServletRequest、HttpServletResponse
 * 5.客户端发送的请求 type=调用方法的方法名
 */

@WebServlet(name = "ProductTuijianServlet", value = "/recommend.do")
public class ProductTuijianServlet extends BaseServlet {
    ProductTuijianService productTuijianService = new ProductTuijianServiceImpl();

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @return
     */
    public ReturnEntity<PageInfo> getProductTJByPage(HttpServletRequest request, HttpServletResponse response) {
        // 获取 当前页、每页显示几条 值 【分页条件】
        String currentPage = request.getParameter("nowPage");
        String pageSize = request.getParameter("pageSize");
        // 获取 产品姓名 [查询条件]
        String productname = request.getParameter("productname");
        // 存储所有的查询条件
        ProTJInfo proTJInfo = new ProTJInfo();
        proTJInfo.setProductname(productname);
        // 调用 service中分页查询方法
        ReturnEntity<PageInfo> returnEntity = productTuijianService.showProductTJByPage(currentPage, pageSize, proTJInfo);
        // 返回 ReturnEntity 对象，在BaseServlet类中统一响应
        return returnEntity;
    }

    /**
     * 新增
     * @param request
     * @param response
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ReturnEntity saveProductTJ(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        // 获取产品系列的数据值[页面]
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 调用servletUtils类方法实现 map转实体类
        ProTJInfo proTJInfo = ServletUtils.mapToEntityObject(parameterMap, ProTJInfo.class);
        // 调用service中添加方法，实现产品系列数据更新
        ReturnEntity returnEntity = productTuijianService.saveProductBase(proTJInfo);
        return returnEntity;
    }

    /**修改
     *
     * @param request
     * @param response
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ReturnEntity updateProductTJ(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        // 获取产品系列的数据值[页面]
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 调用servletUtils类方法实现 map转实体类
        ProTJInfo proTJInfo = ServletUtils.mapToEntityObject(parameterMap, ProTJInfo.class);
        // 调用service中添加方法，实现产品系列数据更新
        ReturnEntity returnEntity = productTuijianService.updateProductTJ(proTJInfo);
        return returnEntity;
    }
}
