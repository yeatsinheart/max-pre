package com.max.base.dto.req;
import com.max.base.entity.LogUser;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* 用户操作记录
* @author zane
* @since 2019-08-28
*/
    @Data
    @ApiModel(value="LogUserReqDto请求对象", description="用户操作记录")
    public class LogUserReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

public LogUser toLogUser(){
LogUser logUser = new LogUser();
    logUser.setId(this.id);
return logUser;
}
}
