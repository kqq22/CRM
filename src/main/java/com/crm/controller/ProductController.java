package com.crm.controller;

import com.crm.entity.Product;
import com.crm.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * 产品信息分控制器
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 分页查询
     * @param pageNum
     * @return
     */
    @RequestMapping(value="/getProductAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<Product> getProductAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum,String name,String type,String batch){
        int pageNum1 = pageNum==null?1:pageNum;
        //必须放在list前面
        PageHelper.startPage(pageNum1,3);
        //调用业务类查询方法
        List<Product> list;
        if(name==""&&type==""&&batch==""){
            list = productService.findProductAll();
        }else {
            Product product = new Product("%"+name+"%","%"+type+"%","%"+batch+"%");
            list = productService.findProductByExample(product);
        }
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
