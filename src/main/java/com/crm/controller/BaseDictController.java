package com.crm.controller;

import com.crm.entity.BaseDict;
import com.crm.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/**
 * 数据字典管理分控制器
 */
@Controller
public class BaseDictController {
    @Autowired
    private BaseDictService baseDictService;
    @RequestMapping("/findBaseDictAll")
    public String findBaseDictAll(Model m){
        List<BaseDict> list = baseDictService.findBaseDictAll();
        m.addAttribute("list",list);
        return "Manager/DirectionData";
    }
}
