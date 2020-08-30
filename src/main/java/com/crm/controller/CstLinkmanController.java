package com.crm.controller;import com.crm.entity.CstLinkman;
import com.crm.service.CstLinkmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 联系人分控制器类
 */
@Controller
public class CstLinkmanController {
    @Autowired
    private CstLinkmanService cstLinkmanService;

    /**
     * 根据客户信息管理编号查询联系人
     * @param no
     * @param m
     * @return
     */
    @RequestMapping("/findCstLinkmanByNo")
    public String findCstLinkmanByNo(String no, Model m, HttpServletRequest request){
        no = request.getParameter("no");
        List<CstLinkman> linkmanList = cstLinkmanService.findCstLinkmanByNo(no);
        m.addAttribute("linkmanList",linkmanList);
        return "Customer/LinkManPage";
    }


    /**
     * 根据id查询联系人
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findCstLinkmanByid")
    public String findCstLinkmanByid(Integer id, Model m){
        CstLinkman cstLinkman = cstLinkmanService.findCstLinkmanById(id);
        m.addAttribute("cstLinkman",cstLinkman);
        return "Customer/LinkManEdit";
    }

    /**
     * 根据id查询联系人（跳转到新建页面）
     *
     * @param m
     * @return
     */
    @RequestMapping("/findCstLinkmanBylkmId")
    public String findCstLinkmanAdd(Integer lkmId, Model m){
        CstLinkman linkman = cstLinkmanService.findCstLinkmanById(lkmId);
        m.addAttribute("linkman",linkman);
        return "Customer/LinkManAdd";
    }

    /**
     * 添加联系人
     * @param cstLinkman
     * @return
     */
    @RequestMapping("/addCstLinkman")
    public void addCstLinkman(CstLinkman cstLinkman,Model m,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getLkmCustNo:"+cstLinkman.getLkmCustNo());
        int row = cstLinkmanService.addCstLinkman(cstLinkman);
        if (row==1) {
            request.getRequestDispatcher("/findCstLinkmanByNo?no="+cstLinkman.getLkmCustNo()).forward(request,response);
        }
    }

    /**
     * 修改联系人信息
     * @param cstLinkman
     * @return
     */
    @RequestMapping("/updateCstLinkman")
    public void updateCstLinkman(CstLinkman cstLinkman, Model m, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int row = cstLinkmanService.updateLinkman(cstLinkman);
        String no = cstLinkman.getLkmCustNo();
        request.setAttribute("no",no);
        request.getRequestDispatcher("/findCstLinkmanByNo?no="+no).forward(request,response);
    }

    /**
     * 删除联系人
     * @param lkmId
     * @param lkmcustNo
     * @param request
     * @param response
     */
    @RequestMapping("/delCstLinkman")
    public void delCstLinkman(Integer lkmId,String lkmcustNo,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int row = cstLinkmanService.delLinkman(lkmId);
        request.getRequestDispatcher("/findCstLinkmanByNo?no="+lkmcustNo).forward(request,response);

    }
}

