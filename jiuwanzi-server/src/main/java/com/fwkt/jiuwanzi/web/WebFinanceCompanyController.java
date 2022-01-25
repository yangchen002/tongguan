package com.fwkt.jiuwanzi.web;

import com.fwkt.commonutils.WebResponse;
import com.fwkt.jiuwanzi.vo.WebFinanceCompanyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author yangchen
 * @version 1.0
 * @date 2022-01-17 17:12
 */
@RestController
@RequestMapping("webFinanceCompany")
@Api(tags = "门户管理-金融机构")
public class WebFinanceCompanyController {

    @PostMapping("operateCompany")
    @ApiOperation(value = "新增/修改金融机构", tags = {"金融机构管理", "机构管理"})
    public WebResponse addType(@RequestBody(required = true) @ApiParam(value = "vo类") WebFinanceCompanyVO webFinanceCompanyVO) {
        return null;
    }
}
