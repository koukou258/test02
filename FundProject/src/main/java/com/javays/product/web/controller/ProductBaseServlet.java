package com.javays.product.web.controller;

import com.javays.base.bean.PageInfo;
import com.javays.base.bean.ReturnEntity;
import com.javays.base.util.ServletUtils;
import com.javays.base.web.controller.BaseServlet;
import com.javays.product.bean.proBaseInfo;
import com.javays.product.bean.proJingzhi;
import com.javays.product.service.impl.proJingzhiServiceImpl;
import com.javays.product.service.impl.productBaseServiceImpl;
import com.javays.product.service.proJingzhiService;
import com.javays.product.service.productBaseService;

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

@WebServlet(name = "ProductBaseServlet", value = "/basics.do")
public class ProductBaseServlet extends BaseServlet {
    productBaseService productBaseService = new productBaseServiceImpl();
    proJingzhiService proJingzhiService = new proJingzhiServiceImpl();

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @return
     */
    public ReturnEntity<PageInfo> getProductBaseByPage(HttpServletRequest request, HttpServletResponse response) {
//        String a = null;
//        System.out.println(a.equals(""));
        // 获取 当前页、每页显示几条 值 【分页条件】
        String currentPage = request.getParameter("nowPage");
        String pageSize = request.getParameter("pageSize");

        // 获取 产品姓名 [查询条件]
        String productname = request.getParameter("productname");
        // 获取 产品类型 [查询条件]
        String productType = request.getParameter("productType");

        //获取 审核状态[查询条件]
        String examinestate = request.getParameter("examinestate");

        // 存储所有的查询条件
        proBaseInfo proBaseInfo = new proBaseInfo();
        proBaseInfo.setProductname(productname);
        proBaseInfo.setProductType(productType);
        proBaseInfo.setExaminestate(examinestate);
        // 调用 service中分页查询方法
        ReturnEntity<PageInfo> returnEntity = productBaseService.showProductBaseByPage(currentPage, pageSize, proBaseInfo);
        // 返回 ReturnEntity 对象，在BaseServlet类中统一响应
        return returnEntity;
    }

    /**
     * 添加
     *
     * @param request
     * @param response
     * @return
     */
    public ReturnEntity saveProductBase(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        // 获取产品系列的数据值[页面]
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 调用servletUtils类方法实现 map转实体类
        proBaseInfo proBaseInfo = ServletUtils.mapToEntityObject(parameterMap, proBaseInfo.class);
        // 调用service中添加方法，实现产品系列数据更新
        ReturnEntity returnEntity = productBaseService.saveProductBase(proBaseInfo);
        return returnEntity;
    }

    /**
     * 修改
     *
     * @param request
     * @param response
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ReturnEntity updateProductBase(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        // 获取所有的请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 将map集合转为实体类
        proBaseInfo proBaseInfo = ServletUtils.mapToEntityObject(parameterMap, proBaseInfo.class);
        // 调用serivce中修改的方法

        ReturnEntity returnEntity = productBaseService.updateProductBase(proBaseInfo);
        return returnEntity;
    }

    /**
     * 通过id净值回显数据
     * @param request
     * @param response
     * @return
     */
    public ReturnEntity getNetWorth(HttpServletRequest request, HttpServletResponse response) {
        String productIdStr = request.getParameter("productId");
        Integer productId = productIdStr != null && !"".equals(productIdStr) ? Integer.valueOf(productIdStr) : null;
        ReturnEntity<proJingzhi> proJingzhiReturnEntity = proJingzhiService.selectNetWorthByProductId(productId);
        return proJingzhiReturnEntity;
    }

    /**
     * 净值新增
     * @param request
     * @param response
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ReturnEntity saveNetWorth(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 将map集合转为实体类
        proJingzhi proJingzhi = ServletUtils.mapToEntityObject(parameterMap, proJingzhi.class);
        ReturnEntity returnEntity = proJingzhiService.saveNetWorth(proJingzhi);
        return returnEntity;

    }

    /**净值修改
     *
     * @param request
     * @param response
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ReturnEntity updateNetWorth(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
    Map<String, String[]> parameterMap = request.getParameterMap();
    // 将map集合转为实体类
    proJingzhi proJingzhi = ServletUtils.mapToEntityObject(parameterMap, proJingzhi.class);
    ReturnEntity returnEntity = proJingzhiService.updateNetWorth(proJingzhi);
    return returnEntity;

}
}
