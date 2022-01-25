package com.fwkt.jiuwanzi.service;

import com.fwkt.jiuwanzi.vo.WebFinanceCompanyVO;

public interface WebFinanceCompanyService {

    /**
     * 新增/修改金融机构
     * @param webFinanceCompanyVO
     */
    void saveWebFc(WebFinanceCompanyVO webFinanceCompanyVO);
}
