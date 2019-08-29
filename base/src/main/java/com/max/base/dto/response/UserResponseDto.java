package com.max.base.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.max.base.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @author zane
 * @since 2019-08-29
 */
@Data

@ApiModel(value = "UserResponseDto响应对象", description = "用户表")
public class UserResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名，手机号")
    private String name;

    @ApiModelProperty(value = "密码")
    private String passwd;

    @ApiModelProperty(value = "昵称")
    private String nick;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "钱包余额，字符元角分厘")
    private String balance;

    @ApiModelProperty(value = "账号状态 0 正常 ")
    private Integer status;

    @ApiModelProperty(value = "资金密码")
    private String withdrawPasswd;

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setPasswd(this.passwd);
        user.setNick(this.nick);
        user.setCreateTime(this.createTime);
        user.setBalance(this.balance);
        user.setStatus(this.status);
        user.setWithdrawPasswd(this.withdrawPasswd);
        return user;
    }
}
