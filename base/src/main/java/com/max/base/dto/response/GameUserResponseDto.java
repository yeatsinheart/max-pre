package com.max.base.dto.response;
import com.max.base.entity.GameUser;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* 游戏角色信息
* @author zane
* @since 2019-08-28
*/
    @Data

    @ApiModel(value="GameUserResponseDto响应对象", description="游戏角色信息")
    public class GameUserResponseDto implements Serializable {

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

public GameUser toGameUser(){
GameUser gameUser = new GameUser();
    gameUser.setGameUserId(this.gameUserId);
    gameUser.setUserId(this.userId);
    gameUser.setGameUserName(this.gameUserName);
    gameUser.setGameUserPasswd(this.gameUserPasswd);
    gameUser.setGameBalance(this.gameBalance);
return gameUser;
}
}
