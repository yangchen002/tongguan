package com.fwkt.jiuwanzi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="web_financecompanyinterface")
@ApiModel("金融机构接口")
@EntityListeners(AuditingEntityListener.class)
public class WebFinanceCompanyInterface implements Serializable {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private String fciId;
  @ApiModelProperty(value="金融机构ID",example="0")
  @Column(name = "fci_fc_id")
  private Integer fciFcId;
  @ApiModelProperty("名称")
  private String fciName;
  @ApiModelProperty("类型")
  private String fciType;
  @ApiModelProperty("地址")
  private String fciUrl;
  @ApiModelProperty("输入参数")
  private String fciInputparameter;
  @ApiModelProperty("输出参数")
  private String fciOutparameter;
  @ApiModelProperty("状态")
  private String fciState;
  @ApiModelProperty("说明")
  private String fciExplain;

  @CreatedDate
  @Column(updatable = false, nullable = false,name="fci_createdt")
  @ApiModelProperty("创建时间")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date fciCreatedt;

  @LastModifiedDate
  @Column(name = "fci_updatedt")
  @ApiModelProperty("修改时间")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date fciUpdatedt;

}
