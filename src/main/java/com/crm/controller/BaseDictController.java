package com.crm.controller;

import com.crm.entity.BaseDict;
import com.crm.service.BaseDictService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * 数据字典管理分控制器
 */
@Controller
public class BaseDictController {
    //注入Service接口
    @Autowired
    private BaseDictService baseDictService;

    /**
     * 查询所有数据字典信息
     * @param m
     * @return
     */
    @RequestMapping("/findBaseDictAll")
    public String findBaseDictAll(Model m){
        List<BaseDict> list = baseDictService.findBaseDictAll();
        m.addAttribute("list",list);
        return "/Manager/DirectionData";
    }

    /**
     * 分页查询
     * @param pageNum 页码
     * @param pageSize
     * @return
     */
    @RequestMapping("/getDirectionData")
    @ResponseBody//返回Json数据
    public PageInfo<BaseDict> getDirectionData(Integer pageNum, Integer pageSize) {
        //必须放在list查询的前面，设置页码和最大显示条数
        PageHelper.startPage(pageNum, pageSize);
        //调用查询方法
        List<BaseDict> list = baseDictService.findBaseDictAll();
        PageInfo<BaseDict> pageInfo = new PageInfo<BaseDict>(list);
        return pageInfo;
    }

    @RequestMapping("/findBaseDictAlltest")
    public String DirectionData(){
        return "Manager/DirectionData";
    }

    /**
     * 模糊查询
     * @param m
     * @return
     */
    @RequestMapping("/findBaseDictByExample")
    public String findBaseDictByExample(BaseDict baseDict,Model m){
        String dictType = "%"+baseDict.getDictType()+"%";
        String dictItem = "%"+baseDict.getDictItem()+"%";
        String dictValue = "%"+baseDict.getDictValue()+"%";
        baseDict = new BaseDict(dictType,dictItem,dictValue);
        //调用查询方法
        List<BaseDict> list = baseDictService.findBaseDictByExample(baseDict);
        m.addAttribute("list",list);
        return "/Manager/DirectionData";
    }

    /**
     * 添加数据字典记录
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addBaseDict")
    public void addBaseDict(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        String item = request.getParameter("item");
        String value = request.getParameter("value");
        String [] checkbox = request.getParameterValues("checkbox");
        int editable;
        if(checkbox==null){
            editable = 0;
        }else{
            editable = 1;
        }
        BaseDict baseDict = new BaseDict(type,item,value,editable);
        int row = baseDictService.addBaseDict(baseDict);
        if(row==1){
            response.sendRedirect("/findBaseDictAll");
        }
    }

    /**
     * 删除数据字典记录
     * @param response
     * @param request
     * @param id
     * @throws IOException
     */
    @RequestMapping("/delBaseDict")
    public void delBaseDict(HttpServletResponse response,HttpServletRequest request,Integer id) throws IOException {
        int row = baseDictService.delBaseDict(id);
        if(row==1){
            response.sendRedirect("/findBaseDictAll");
        }else {
            response.sendRedirect("/index.jsp");
        }
    }

    /**
     * 修改数据字典信息
     * @param request
     * @param response
     */
    @RequestMapping("/updateBaseDict")
    public void updateBaseDict(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取参数
        String type = request.getParameter("type");
        String item = request.getParameter("item");
        String value = request.getParameter("value");
        int id = Integer.parseInt(request.getParameter("id"));
        String [] checkbox = request.getParameterValues("checkbox");
        int editable;
        if(checkbox==null){
            editable = 0;
        }else{
            editable = 1;
        }
        BaseDict baseDict = new BaseDict(id,type,item,value,editable);
        int row = baseDictService.updateBaseDict(baseDict);
        if(row==1){
            response.sendRedirect("/findBaseDictAll");
        }else {
            response.sendRedirect("/index.jsp");
        }
    }

    /**
     * 根据id查询单个BaseDict
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findBaseDictById")
    public String findBaseDictById(Integer id,Model m){
        BaseDict baseDict = baseDictService.findBaseDictById(id);
        m.addAttribute("baseDict",baseDict);
        return "/Manager/DataEdit";
    }
}
