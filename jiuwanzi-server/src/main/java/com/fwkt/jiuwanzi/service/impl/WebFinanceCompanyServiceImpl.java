package com.fwkt.jiuwanzi.service.impl;

import com.fwkt.jiuwanzi.entity.WebFinanceCompany;
import com.fwkt.jiuwanzi.repository.WebFinanceCompanyRepository;
import com.fwkt.jiuwanzi.service.WebFinanceCompanyService;
import com.fwkt.jiuwanzi.vo.WebFinanceCompanyVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * TODO
 *
 * @author yangchen
 * @version 1.0
 * @date 2022-01-17 17:25
 */
@Service
public class WebFinanceCompanyServiceImpl implements WebFinanceCompanyService {

    @Autowired
    private WebFinanceCompanyRepository webFinanceCompanyRepository;

    @Override
    public void saveWebFc(WebFinanceCompanyVO webFinanceCompanyVO) {
        if(StringUtils.isBlank(webFinanceCompanyVO.getFcId())) {
            /*新增*/
            WebFinanceCompany company = new WebFinanceCompany();
            BeanUtils.copyProperties(webFinanceCompanyVO, company);
            company.setFcCreatedt(new Date());
            company.setFcUpdatedt(new Date());
            company.setFcAuthentication(1);
            webFinanceCompanyRepository.save(company);
        } else {
            try {
                /*修改*/
                WebFinanceCompany company = webFinanceCompanyRepository.findById(webFinanceCompanyVO.getFcId()).get();
                BeanUtils.copyProperties(webFinanceCompanyVO, company);
                company.setFcUpdatedt(new Date());
                company.setFcAuthentication(1);
                webFinanceCompanyRepository.save(company);
            } catch (Exception e) {
                throw new RuntimeException("当前机构不存在");
            }
        }
    }
}
