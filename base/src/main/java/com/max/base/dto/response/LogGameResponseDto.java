package com.max.base.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.max.base.entity.LogGame;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 游戏记录
 *
 * @author zane
 * @since 2019-08-28
 */
@Data

@ApiModel(value = "LogGameResponseDto响应对象", description = "游戏记录")
public class LogGameResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    public LogGame toLogGame() {
        LogGame logGame = new LogGame();
        logGame.setId(this.id);
        return logGame;
    }
}
