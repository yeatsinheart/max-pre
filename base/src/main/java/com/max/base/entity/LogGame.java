package com.max.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


/**
 * 游戏记录
 *
 * @author zane
 * @since 2019-08-29
 */
@Data
@ApiModel(value = "LogGame对象", description = "游戏记录")
public class LogGame implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


}
