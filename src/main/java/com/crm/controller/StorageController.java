package com.crm.controller;

import com.crm.entity.Storage;
import com.crm.service.StorageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 库存分控制器
 */
@Controller
public class StorageController {
    @Autowired
    private StorageService storageService;

    /**
     * 分页查询+模糊查询
     * @param pageNum 页码
     * @param name
     * @param warehouse
     * @return
     */
    @RequestMapping(value="/getStorageAll",method= RequestMethod.GET)
    @ResponseBody
    public PageInfo<Storage> getStorageAll(@RequestParam(defaultValue="1",required=true,value="pageNum") Integer pageNum, String name, String warehouse){
        //必须放在list前面
        PageHelper.startPage(pageNum,3);
        List<Storage> list;
        if(name==""&&warehouse==""){
            //查询所有库存
            list = storageService.findStorageAll();
        }else {
            //根据条件进行模糊查询
            list = storageService.findStorageByExample("%"+name+"%","%"+warehouse+"%");
        }
        PageInfo<Storage> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
