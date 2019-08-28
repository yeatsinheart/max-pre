package com.max.base.dto.response;
import com.max.base.entity.WalletSeries;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* 钱包流水号
* @author zane
* @since 2019-08-28
*/
    @Data

    @ApiModel(value="WalletSeriesResponseDto响应对象", description="钱包流水号")
    public class WalletSeriesResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "用户ID")
    private Integer userId;

            @ApiModelProperty(value = "钱包流水号")
    private String series;

            @ApiModelProperty(value = "1 充值 2 提现 3 转入游戏 4 转出游戏")
    private Integer type;

            @ApiModelProperty(value = "所处状态 0 处理中 1已用完")
    private Integer status;

public WalletSeries toWalletSeries(){
WalletSeries walletSeries = new WalletSeries();
    walletSeries.setId(this.id);
    walletSeries.setUserId(this.userId);
    walletSeries.setSeries(this.series);
    walletSeries.setType(this.type);
    walletSeries.setStatus(this.status);
return walletSeries;
}
}
