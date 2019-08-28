package com.max.base.dto;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
import com.max.base.entity.LogGame;
/**
* 游戏记录
* @author zane
* @since 2019-08-28
*/
    @Data
    @ApiModel(value="LogGameDto对象", description="游戏记录")
    public class LogGameDto implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

public LogGame toLogGame(){
LogGame logGame = new LogGame();
    logGame.setId(this.id);
return logGame;
}

}
