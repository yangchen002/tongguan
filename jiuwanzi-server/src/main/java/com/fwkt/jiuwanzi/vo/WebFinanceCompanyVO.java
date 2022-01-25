package com.fwkt.jiuwanzi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author yangchen
 * @version 1.0
 * @date 2022-01-17 17:13
 */
@Data
@ApiModel("金融机构")
public class WebFinanceCompanyVO {

    @ApiModelProperty("金融机构id")
    private String fcId;

    @ApiModelProperty("机构全称")
    private String fcName;

    @ApiModelProperty("机构简称")
    private String fcAbbreviation;

    @ApiModelProperty(value="金融机构类型 1保险公司 2银行 3担保机构",example="0")
    private Integer fcType;

    @ApiModelProperty("状态  正常 禁用")
    private String fcState;

    @ApiModelProperty(value="是否认证 1是 0不是 ",example="1")
    private Integer fcAuthentication = 1;

    @ApiModelProperty(value="排序",example="0")
    private Integer fcOrder;

    @ApiModelProperty("机构logo图片路径")
    private String fcLogourl;

    @ApiModelProperty("金融机构h5图片")
    private String fcH5Url;

    @ApiModelProperty("跳转金融机构首页地址")
    private String fcIndexurl;

    @ApiModelProperty("机构简介")
    private String fcSynopsis;

    @ApiModelProperty("详情")
    private String fcDetail;

    @ApiModelProperty("金融机构保函查验地址")
    private String fcSearchurl;
}
