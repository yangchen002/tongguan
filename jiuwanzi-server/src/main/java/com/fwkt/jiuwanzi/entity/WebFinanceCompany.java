package com.fwkt.jiuwanzi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 金融机构
 */
@Data
@Entity
@Table(name="web_financecompany")
@ApiModel("金融机构")
@EntityListeners(AuditingEntityListener.class)
@Component
public class WebFinanceCompany implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private String fcId;

  @ApiModelProperty("名称")
  private String fcName;

  @ApiModelProperty("简称")
  private String fcAbbreviation;

  @ApiModelProperty("入住时间")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date fcJoindate;

  @ApiModelProperty(value="好评率",example="0")
  private Integer fcPraise;

  @ApiModelProperty(value="是否认证 1是 0不是 ",example="0")
  private Integer fcAuthentication = 1;

  @ApiModelProperty("logo路径")
  private String fcLogourl;

  @ApiModelProperty("机构简介")
  private String fcSynopsis;

  @ApiModelProperty("联系人")
  private String fcContacts;

  @ApiModelProperty("联系电话")
  private String fcContactnum;

  @ApiModelProperty("联系邮箱")
  private String fcContactmailbox;

  @ApiModelProperty("状态  正常 禁用")
  private String fcState;

  @ApiModelProperty(value="排序",example="0")
  private Integer fcOrder;

  @CreatedDate
  @Column(updatable = false, nullable = false,name="fc_createdt")
  @ApiModelProperty("创建时间")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date fcCreatedt;

  @LastModifiedDate
  @Column(name = "fc_updatedt")
  @ApiModelProperty("修改时间")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date fcUpdatedt;

  @ApiModelProperty(value="金融机构类型 1保险公司 2银行 3担保机构",example="0")
  private Integer fcType;

  @ManyToMany(fetch = FetchType.EAGER)
  @JsonIgnoreProperties(value = { "productList" })
  @JoinTable(name = "web_financecompanyandproduct",joinColumns = {@JoinColumn(name="fcp_fc_id")},inverseJoinColumns ={@JoinColumn(name="fcp_pi_id")} )
  @ApiModelProperty(value = "产品信息")
  private List<WebProductInfo> productList;

  @OneToMany(targetEntity= WebFinanceCompanyInterface.class, cascade= CascadeType.ALL)
  @JoinColumn(name="fci_fc_id")
  @ApiModelProperty(value = "金融机构接口")
  private List<WebFinanceCompanyInterface> webFinanceCompanyInterfaceList;

  @ApiModelProperty("详情")
  private String fcDetail;

  @ApiModelProperty("跳转金融机构首页地址")
  private String fcIndexurl;

  @Column(name = "fc_h5url")
  @ApiModelProperty("h5金融机构图片")
  private String fcH5Url;

  @ApiModelProperty("金融机构保函查验地址")
  private String fcSearchurl;

  @Transient
  @ApiModelProperty("金融机构类型")
  private String finaryType;

}
