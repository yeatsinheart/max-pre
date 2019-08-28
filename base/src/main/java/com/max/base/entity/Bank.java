package com.max.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 支持的银行
 *
 * @author zane
 * @since 2019-08-28
 */
@Data
@ApiModel(value = "Bank对象", description = "支持的银行")
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "支持的银行名称")
    private String bankName;

    @ApiModelProperty(value = "银行图标")
    private String ico;


}
