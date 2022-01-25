package com.fwkt.jiuwanzi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fwkt.jiuwanzi.entity.feign.FeignSysCompany;
import com.fwkt.jiuwanzi.entity.sys.Sysfile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="web_productinfo")
@ApiModel("产品信息")
@EntityListeners(AuditingEntityListener.class)
@Component
public class WebProductInfo implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private String piId;
  @ApiModelProperty("名称")
  private String piName;
  @ApiModelProperty("简述")
  private String piInfo;
  @ApiModelProperty("状态   正常 禁用")
  private String piState;
  @ApiModelProperty("产品详情")
  private String piDetails;
  @ApiModelProperty("投保须知")
  private String piNotice;
  @ApiModelProperty("理赔服务")
  private String piServe;
  @ApiModelProperty("常见问题")
  private String piProblem;
  @ApiModelProperty("保险条款")
  private String piInsurance;

  @ApiModelProperty("logo图片")
  private String piLogo;

  @ApiModelProperty(value="排序",example="0")
  private Integer piOrder;

  @CreatedDate
  @Column(updatable = false, nullable = false,name="pi_createdt")
  @ApiModelProperty("创建时间")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date piCreatedt;

  @LastModifiedDate
  @Column(name = "pi_updatedt")
  @ApiModelProperty("修改时间")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date piUpdatedt;




//  @ManyToMany(fetch = FetchType.LAZY,mappedBy = "productList")
//  @JsonIgnoreProperties(value = { "financeCompanyList" })
//  @ApiModelProperty(value = "金融机构")
//  @JsonIgnore
//  private List<WebFinanceCompany> financeCompanyList;
  @Transient
  @ApiModelProperty(value = "金融机构")
  private List<LinkedHashMap<Object,Object>> financeCompanys = new ArrayList<>();
  @Transient
  @ApiModelProperty(value = "附件")
  private List<Sysfile> sysfiles;

  @Transient
  @ApiModelProperty(value = "金融机构")
  private List<FeignSysCompany> finances = new ArrayList<>();

  @Transient
  @ApiModelProperty(value = "是否绑定")
  private Integer isCheck;

}
