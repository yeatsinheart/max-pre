package com.max.supplier.service;

import com.max.base.dto.GameUserDto;
import com.max.base.dto.LogGameDto;
import com.max.base.entity.GameUser;
import com.max.base.entity.User;
import com.max.supplier.dto.GameLoginResponse;
import com.max.supplier.dto.GameTransferInDto;
import com.max.supplier.dto.GameTransferOutDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("test")
public class TestGameService implements GameService {
    /**
     * 创建游戏账号
     *
     * @param user
     */
    @Override
    public GameUserDto createUser(User user) {
        return null;
    }

    /**
     * 登录
     *
     * @param user
     */
    @Override
    public GameLoginResponse login(GameUser user) {
        return null;
    }

    /**
     * 额度转换 单一钱包此处则将投注/中奖/撤单作为额度转换操作看待
     *
     * @param user
     */
    @Override
    public boolean transferIn(GameTransferInDto user) {
        return false;
    }

    @Override
    public boolean transferOut(GameTransferOutDto user) {
        return false;
    }

    /**
     * 查询余额
     *
     * @param balanceRequest
     */
    @Override
    public BigDecimal balance(GameUser balanceRequest) {
        return null;
    }

    /**
     * 投注记录
     *
     * @param historyRequest
     */
    @Override
    public List<LogGameDto> history(GameUser historyRequest) {
        return null;
    }
}
