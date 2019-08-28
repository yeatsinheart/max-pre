package com.max.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 分类
 *
 * @author zane
 * @since 2019-08-28
 */
@Data
@ApiModel(value = "CategoryGame对象", description = "分类")
public class CategoryGame implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "游戏分类ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "游戏分类名称")
    private String categoryName;


}
