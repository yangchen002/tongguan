package com.fwkt.jiuwanzi.repository;


import com.fwkt.jiuwanzi.entity.WebFinanceCompany;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface WebFinanceCompanyRepository extends JpaRepository<WebFinanceCompany, String>, JpaSpecificationExecutor<WebFinanceCompany> {
    //List<WebFinanceCompany> findByFcStateAndFcAuthenticationOrderByFcOrderDesc(String status,Integer code);
    List<WebFinanceCompany> findByFcStateAndFcAuthenticationAndFcType(String status, Integer code, int fcType, Sort sort);

    List<WebFinanceCompany> findByFcStateAndFcAuthentication(String status, Integer code, Sort sort);
    @Query(value = "delete from web_financecompany where fc_id in (:list)",nativeQuery = true)
    @Modifying
    void deletes(@Param("list") List<Integer> list);


    @Query(value = "SELECT wfc.fc_id fcId,wfc.fc_name fcName,wfc.fc_h5url fcH5Url,wfc.fc_logourl fcLogourl, fcp.fcp_pi_insurance fcpPiInsurance,fcp.refund_insurance refundInsurance,fcp.fcp_id fcpId FROM web_financecompanyandproduct fcp INNER JOIN web_financecompany wfc ON fcp.fcp_fc_id = wfc.fc_id  where fcp.fcp_pi_id = :piId and fcp.fcp_virtual = 1 AND wfc.fc_state = '正常'  ORDER BY wfc.fc_order desc,wfc.fc_createdt desc",nativeQuery = true)
    List<Map<String,Object>> selectByProductId(@Param("piId") String piId);
}
