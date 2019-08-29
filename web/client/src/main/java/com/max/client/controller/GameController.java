package com.max.client.controller;

import com.max.core.redis.RedisService;
import com.max.core.result.Result;
import com.max.core.result.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//todo 游戏界面视图 及 功能
public class GameController {
    @Autowired
    private RedisService redisService;
    //@Autowired
    //private GameViewService gameViewrService;
    //@Autowired
    // private GameItemViewService gameItemViewrService;


    @ApiOperation(value = "/game", tags = {"游戏视图"})
    @PostMapping("/game")
    public Result game() {
        /*
        * SELECT  * FROM category_game a
        LEFT JOIN relation_category_view b ON a.id = b.category_id
        LEFT JOIN view c ON b.view_id = c.view_id AND c.view_type = 1 AND c.status = 0
        * */
        //List<GameItemViewDto> viewDtos =  gameItemViewrService.game();
        return ResultGenerator.genSuccessResult(null);
    }
    /*额度转入游戏*//*
    @PostMapping("/transferin")
    public Result transferin(GameDto game) {
        //事务：比较钱包余额足够，扣除钱包余额，生成游戏转入单
        //事务：转入失败，返回钱包余额
        //事务：转入成功，记录钱包增减日志
        return ResultGenerator.genSuccessResult();
    }

    *//*额度转出游戏*//*
    @PostMapping("/transferout")
    public Result transferout(GameDto game) {
        //事务：比较游戏余额足够，生成游戏转出单，发起游戏余额转出
        //事务：转出失败
        //事务：转出成功，记录钱包增减日志，钱包增加相应额度
        return ResultGenerator.genSuccessResult();
    }

    *//*进入游戏*//*
    @PostMapping("/enter")
    public Result enter(GameDto game) {
        //用户是否有效
        //创建游戏账号
        //额度转入游戏
        //登录游戏
        return ResultGenerator.genSuccessResult();
    }

    *//*查看投注情况*//*
    @PostMapping("/gamerecord")
    public Result gamerecord(GameDto recordPage) {
        //用户是否有效
        //获取筛选的投注记录。最大一个月
        return ResultGenerator.genSuccessResult();
    }*/
}
