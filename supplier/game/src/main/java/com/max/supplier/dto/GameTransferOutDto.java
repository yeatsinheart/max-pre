package com.max.supplier.dto;

import com.max.base.entity.GameUser;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GameTransferOutDto {
    private BigDecimal money;
    private GameUser user;
}
