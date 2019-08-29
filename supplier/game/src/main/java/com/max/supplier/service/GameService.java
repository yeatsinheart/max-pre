package com.max.supplier.service;

import com.max.base.dto.GameUserDto;
import com.max.base.dto.LogGameDto;
import com.max.base.entity.GameUser;
import com.max.base.entity.User;
import com.max.supplier.dto.GameLoginResponse;
import com.max.supplier.dto.GameTransferInDto;
import com.max.supplier.dto.GameTransferOutDto;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {
    /**
     * 创建游戏账号
     */
    GameUserDto createUser(User user);

    /**
     * 登录 返回链接还是返回表单
     */
    GameLoginResponse login(GameUser user);

    /**
     * 额度转换 单一钱包此处则将投注/中奖/撤单作为额度转换操作看待
     */
    boolean transferIn(GameTransferInDto user);

    boolean transferOut(GameTransferOutDto user);

    /**
     * 查询余额 如果出错也返回0吧？？？？？
     */
    BigDecimal balance(GameUser balanceRequest);

    /**
     * 投注记录
     */
    List<LogGameDto> history(GameUser historyRequest);

}
