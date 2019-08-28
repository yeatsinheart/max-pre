package com.max.base.dto;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
import com.max.base.entity.LogUser;
/**
* 用户操作记录
* @author zane
* @since 2019-08-28
*/
    @Data
    @ApiModel(value="LogUserDto对象", description="用户操作记录")
    public class LogUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

public LogUser toLogUser(){
LogUser logUser = new LogUser();
    logUser.setId(this.id);
return logUser;
}

}
