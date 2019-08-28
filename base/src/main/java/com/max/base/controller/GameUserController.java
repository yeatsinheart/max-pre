package com.max.base.controller;


import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.work.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import com.max.base.service.GameUserService;
import com.max.base.entity.GameUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 游戏角色信息 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/gameUser")
@Slf4j
@Api(value = "游戏角色信息", tags = {"游戏角色信息控制器"})
    public class GameUserController {
@Autowired
private GameUserService gameUserService;

/**
* 获取数据列表
*/
@GetMapping("/listGameUser/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listGameUser( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<GameUser> page= new Page(pageIndex,size);
QueryWrapper<GameUser> queryWrapper = new QueryWrapper<GameUser>();
//queryWrapper.lambda().eq(GameUser::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(GameUser::getName, "1"));;
return Response.success(gameUserService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allGameUser")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allGameUser(){
List<GameUser> models = gameUserService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getGameUser")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getGameUser(@PathVariable Long id){
GameUser gameUser = gameUserService.getById(id);
if(gameUser==null){
return Response.error("尚未查询到此ID");
}
return Response.success(gameUser);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findGameUser")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findGameUser(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<GameUser> gameUserList = gameUserService.listByMap(columnMap);
return Response.success(gameUserList);
}


/**
* 添加数据
*/
@PostMapping("/addGameUser")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addGameUser(@RequestBody GameUser gameUser){
boolean isOk = gameUserService.save(gameUser);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateGameUser")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateGameUser(@RequestBody GameUser gameUser){
boolean isOk = gameUserService.updateById(gameUser);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delGameUser")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delGameUser(@RequestParam("ids") List <Long> ids){
    boolean isOk = gameUserService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


