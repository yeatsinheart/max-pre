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

import com.max.base.service.LogGameService;
import com.max.base.entity.LogGame;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 游戏记录 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/logGame")
@Slf4j
@Api(value = "游戏记录", tags = {"游戏记录控制器"})
    public class LogGameController {
@Autowired
private LogGameService logGameService;

/**
* 获取数据列表
*/
@GetMapping("/listLogGame/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listLogGame( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<LogGame> page= new Page(pageIndex,size);
QueryWrapper<LogGame> queryWrapper = new QueryWrapper<LogGame>();
//queryWrapper.lambda().eq(LogGame::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(LogGame::getName, "1"));;
return Response.success(logGameService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allLogGame")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allLogGame(){
List<LogGame> models = logGameService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getLogGame")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getLogGame(@PathVariable Long id){
LogGame logGame = logGameService.getById(id);
if(logGame==null){
return Response.error("尚未查询到此ID");
}
return Response.success(logGame);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findLogGame")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findLogGame(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<LogGame> logGameList = logGameService.listByMap(columnMap);
return Response.success(logGameList);
}


/**
* 添加数据
*/
@PostMapping("/addLogGame")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addLogGame(@RequestBody LogGame logGame){
boolean isOk = logGameService.save(logGame);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateLogGame")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateLogGame(@RequestBody LogGame logGame){
boolean isOk = logGameService.updateById(logGame);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delLogGame")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delLogGame(@RequestParam("ids") List <Long> ids){
    boolean isOk = logGameService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


