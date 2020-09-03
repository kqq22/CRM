package com.crm.controller;

import com.crm.entity.BaseDict;
import com.crm.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * 查询所有数据字典信息（跳转到数据字典管理页面）
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
     * 模糊查询（跳转到数据字典管理页面）
     * @param m
     * @return
     */
    @RequestMapping("/findBaseDictByExample")
    public String findBaseDictByExample(BaseDict baseDict, Model m){
        //获取参数
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
        //判断是否能编辑 0为不能编辑，1为不能编辑
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
     * @param id 数据字典id
     * @throws IOException
     */
    @RequestMapping("/delBaseDict")
    public void delBaseDict(HttpServletResponse response, Integer id) throws IOException {
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
    public void updateBaseDict(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取参数
        String type = request.getParameter("type");
        String item = request.getParameter("item");
        String value = request.getParameter("value");
        int id = Integer.parseInt(request.getParameter("id"));
        String [] checkbox = request.getParameterValues("checkbox");
        int editable;
        //判断是否能编辑 0为不能编辑，1为不能编辑
        if(checkbox==null){
            editable = 0;
        }else{
            editable = 1;
        }
        BaseDict baseDict = new BaseDict(id,type,item,value,editable);
        //调用修改方法
        int row = baseDictService.updateBaseDict(baseDict);
        response.sendRedirect("/findBaseDictAll");

    }

    /**
     * 根据id查询单个BaseDict（跳转到数据字典条目编辑页面）
     * @param id 数据字典id
     * @param m
     * @return
     */
    @RequestMapping("/findBaseDictById")
    public String findBaseDictById(Integer id, Model m){
        BaseDict baseDict = baseDictService.findBaseDictById(id);
        m.addAttribute("baseDict",baseDict);
        return "/Manager/DataEdit";
    }
}