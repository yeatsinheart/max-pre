package com.max.base.dto;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
import com.max.base.entity.Bank;
/**
* 支持的银行
* @author zane
* @since 2019-08-28
*/
    @Data
    @ApiModel(value="BankDto对象", description="支持的银行")
    public class BankDto implements Serializable {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "支持的银行名称")
    private String bankName;

            @ApiModelProperty(value = "银行图标")
    private String ico;

public Bank toBank(){
Bank bank = new Bank();
    bank.setId(this.id);
    bank.setBankName(this.bankName);
    bank.setIco(this.ico);
return bank;
}

}
