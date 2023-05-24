package com.javays.product.web.controller;

import com.javays.base.bean.PageInfo;
import com.javays.base.bean.ReturnEntity;
import com.javays.base.util.ServletUtils;
import com.javays.base.web.controller.BaseServlet;
import com.javays.product.bean.ProRemittanceInfo;
import com.javays.product.bean.ProductLineInfo;
import com.javays.product.service.ProRemittanceService;
import com.javays.product.service.ProductLineService;
import com.javays.product.service.impl.ProRemittanceServiceImpl;
import com.javays.product.service.impl.ProductLineServiceImpl;

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

@WebServlet(name = "ProductLineServlet", value = "/series.do")
public class ProductLineServlet extends BaseServlet {
    ProductLineService productLineService = new ProductLineServiceImpl();
    ProRemittanceService proRemittanceService = new ProRemittanceServiceImpl();

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @return
     */
    public ReturnEntity<PageInfo> getProductLineByPage(HttpServletRequest request, HttpServletResponse response) {
//        String a = null;
//        System.out.println(a.equals(""));
        // 获取 当前页、每页显示几条 值 【分页条件】
        String currentPage = request.getParameter("nowPage");
        String pageSize = request.getParameter("pageSize");

        // 获取 产品系列中文名 [查询条件]
        String productLineChineseName = request.getParameter("productLineChineseName"); // 获取 产品系列中文名 [查询条件]
        // 获取 产品系列英文 [查询条件]
        String productlineEnglishName = request.getParameter("productlineEnglishName");
        // 存储所有的查询条件
        ProductLineInfo productLineInfo = new ProductLineInfo();
        productLineInfo.setProductlineChineseName(productLineChineseName);
        productLineInfo.setProductlineEnglishName(productlineEnglishName);
        // 调用 service中分页查询方法
        ReturnEntity<PageInfo> returnEntity = productLineService.showProductLineByPage(currentPage, pageSize, productLineInfo);
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
    public ReturnEntity saveProductLine(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        // 获取产品系列的数据值[页面]
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 调用servletUtils类方法实现 map转实体类
        ProductLineInfo productLineInfo = ServletUtils.mapToEntityObject(parameterMap, ProductLineInfo.class);
        // 调用service中添加方法，实现产品系列数据更新
        ReturnEntity returnEntity = productLineService.saveProductLine(productLineInfo);
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
    public ReturnEntity updateProductLine(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        // 获取所有的请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 将map集合转为实体类
        ProductLineInfo productLineInfo = ServletUtils.mapToEntityObject(parameterMap, ProductLineInfo.class);
        // 调用serivce中修改的方法

        ReturnEntity returnEntity = productLineService.updateProductLine(productLineInfo);
        return returnEntity;
    }

    /**
     * 汇款信息回显数据
     */

    public ReturnEntity getRemittance(HttpServletRequest request, HttpServletResponse response) {
        String productlineIdStr = request.getParameter("productlineId");
        Integer productlineId = productlineIdStr != null && !"".equals(productlineIdStr) ? Integer.valueOf(productlineIdStr) : null;
        ReturnEntity<ProRemittanceInfo> proRemittanceInfoReturnEntity = proRemittanceService.selectProRemittanceInfoByProductlineId(productlineId);
        return proRemittanceInfoReturnEntity;
    }


    public ReturnEntity saveRemittance(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 将map集合转为实体类
        ProRemittanceInfo proRemittanceInfo = ServletUtils.mapToEntityObject(parameterMap, ProRemittanceInfo.class);
        ReturnEntity returnEntity = proRemittanceService.saveRemittance(proRemittanceInfo);
        return returnEntity;

    }

    public ReturnEntity updateRemittance(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 将map集合转为实体类
        ProRemittanceInfo proRemittanceInfo = ServletUtils.mapToEntityObject(parameterMap, ProRemittanceInfo.class);
        ReturnEntity returnEntity = proRemittanceService.updateRemittance(proRemittanceInfo);
        return returnEntity;

    }
}
