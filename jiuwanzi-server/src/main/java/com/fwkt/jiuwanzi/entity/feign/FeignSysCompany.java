package com.fwkt.jiuwanzi.entity.feign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangQiang
 * @version 1.0
 * @date 2020/5/19 18:08
 */
@Data
@ApiModel("金融机构数据模型")
public class FeignSysCompany implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "机构名称")
    private String fcName;

    @ApiModelProperty(value = "机构简称")
    private String fcAbbreviation;

    @ApiModelProperty(value = "业务类型 1:保险公司 2:银行 3:担保机构")
    private String fcType;

    @ApiModelProperty(value = "状态 0: 启用  1:禁用")
    private String fcState;

    @ApiModelProperty(value = "金融机构的图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "金融机构详情（富文本）")
    private String fcDetail;

    @ApiModelProperty(value = "是否认证 1: 是 0：否")
    private Integer fcAuthentication;

    @ApiModelProperty(value = "机构简介(富文本)")
    private String fcSynopsis;

    @ApiModelProperty(value = "投标须知 pdf附件")
    private String fcNfipufUrl;

    @ApiModelProperty(value = "排序")
    private Integer fcOrder;

    @ApiModelProperty(value = "金融机构访问主页")
    private String fcIndexurl;

    @ApiModelProperty(value = "金融机构h5页面地址")
    private String fcH5url;

    @ApiModelProperty(value = "投保须知（富文本）")
    private String fcNotice;

    @ApiModelProperty(value = "投标人声明")
    private String fcStatement;

    @ApiModelProperty(value = "退保须知")
    private String surrenderNotice;

    @ApiModelProperty(value = "金融机构枚举别名")
    private String fcAlias;
}
