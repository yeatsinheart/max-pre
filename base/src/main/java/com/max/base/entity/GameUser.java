package com.max.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 游戏角色信息
 *
 * @author zane
 * @since 2019-08-28
 */
@Data
@ApiModel(value = "GameUser对象", description = "游戏角色信息")
public class GameUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务提供客户信息ID")
    @TableId(value = "game_user_id", type = IdType.AUTO)
    private Integer gameUserId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "服务提供客户名")
    private String gameUserName;

    @ApiModelProperty(value = "服务提供客户密码")
    private String gameUserPasswd;

    @ApiModelProperty(value = "余额")
    private String gameBalance;


}
