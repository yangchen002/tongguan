package com.fwkt.jiuwanzi.entity.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ApiModel("用户信息")
@Table(name="sys_file")
@EntityListeners(AuditingEntityListener.class)
public class Sysfile {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ApiModelProperty(value = "关联的ID",hidden = true)
    private String relateId;

    @ApiModelProperty(value = "附件名字")
    private String name;

    @ApiModelProperty(value = "附件地址")
    private String url;

    @CreatedDate
    @JsonIgnore
    @Column(updatable = false, nullable = false,name="create_time")
    @ApiModelProperty(value = "创建时间",hidden = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "创建人ID")
    @JsonIgnore
    private String createUser;

    @ApiModelProperty(value = "附件后缀")
    private String suffix;

    @Column(name = "file_type")
    @JsonIgnore
    @ApiModelProperty(value = "附件类型 1：投保附件 2：退款附件 3: 投标须知")
    private Integer fileType;
}