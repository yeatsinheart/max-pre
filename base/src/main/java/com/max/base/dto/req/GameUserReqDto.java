package com.max.base.dto.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.max.base.entity.GameUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 游戏角色信息
 *
 * @author zane
 * @since 2019-08-29
 */
@Data
@ApiModel(value = "GameUserReqDto请求对象", description = "游戏角色信息")
public class GameUserReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务提供客户信息ID")
    @TableId(value = "game_user_id", type = IdType.AUTO)
    private Integer gameUserId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "服务提供客户名")
    private String gameUserName;

    @ApiModelProperty(value = "服务提供客户密码")
    private String gameUserPasswd;

    @ApiModelProperty(value = "余额")
    private String gameBalance;

    @ApiModelProperty(value = "服务提供ID")
    private Integer supplierId;

    public GameUser toGameUser() {
        GameUser gameUser = new GameUser();
        gameUser.setGameUserId(this.gameUserId);
        gameUser.setUserId(this.userId);
        gameUser.setGameUserName(this.gameUserName);
        gameUser.setGameUserPasswd(this.gameUserPasswd);
        gameUser.setGameBalance(this.gameBalance);
        gameUser.setSupplierId(this.supplierId);
        return gameUser;
    }
}
