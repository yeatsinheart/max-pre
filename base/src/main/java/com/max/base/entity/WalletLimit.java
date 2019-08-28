package com.max.base.entity;

    import java.io.Serializable;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;


/**
* 系统级别提现设置
* @author zane
* @since 2019-08-28
*/
    @Data
    @ApiModel(value="WalletLimit对象", description="系统级别提现设置")
    public class WalletLimit implements Serializable {

private static final long serialVersionUID = 1L;

    private Integer walletLimitidId;

            @ApiModelProperty(value = "限制方式")
    private String walletLimitWay;

            @ApiModelProperty(value = "限制值")
    private String walletLimitValue;


}
