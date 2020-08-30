package com.crm.service;

import com.crm.entity.CstLinkman;

import java.util.List;

/**
 * 联系人业务逻辑接口类
 */
public interface CstLinkmanService {
    /**
     * 根据客户信息编号查询
     * @param no
     * @return 返回CstLinkman集合
     */
    public List<CstLinkman> findCstLinkmanByNo(String no);

    /**
     * 根据id查询联系人
     * @param id
     * @return
     */
    public CstLinkman findCstLinkmanById(Integer id);

    /**
     * 添加联系人
     * @param cstLinkman
     * @return
     */
    public int addCstLinkman(CstLinkman cstLinkman);

    /**
     * 修改联系人信息
     * @return
     */
    public int updateLinkman(CstLinkman cstLinkman);

    /**
     * 删除联系人
     * @param id
     * @return
     */
    public int delLinkman(Integer id);
}
