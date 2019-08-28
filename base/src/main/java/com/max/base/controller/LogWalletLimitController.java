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

import com.max.base.service.LogWalletLimitService;
import com.max.base.entity.LogWalletLimit;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 钱包变动记录 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/logWalletLimit")
@Slf4j
@Api(value = "钱包变动记录", tags = {"钱包变动记录控制器"})
    public class LogWalletLimitController {
@Autowired
private LogWalletLimitService logWalletLimitService;

/**
* 获取数据列表
*/
@GetMapping("/listLogWalletLimit/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listLogWalletLimit( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<LogWalletLimit> page= new Page(pageIndex,size);
QueryWrapper<LogWalletLimit> queryWrapper = new QueryWrapper<LogWalletLimit>();
//queryWrapper.lambda().eq(LogWalletLimit::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(LogWalletLimit::getName, "1"));;
return Response.success(logWalletLimitService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allLogWalletLimit")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allLogWalletLimit(){
List<LogWalletLimit> models = logWalletLimitService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getLogWalletLimit")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getLogWalletLimit(@PathVariable Long id){
LogWalletLimit logWalletLimit = logWalletLimitService.getById(id);
if(logWalletLimit==null){
return Response.error("尚未查询到此ID");
}
return Response.success(logWalletLimit);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findLogWalletLimit")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findLogWalletLimit(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<LogWalletLimit> logWalletLimitList = logWalletLimitService.listByMap(columnMap);
return Response.success(logWalletLimitList);
}


/**
* 添加数据
*/
@PostMapping("/addLogWalletLimit")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addLogWalletLimit(@RequestBody LogWalletLimit logWalletLimit){
boolean isOk = logWalletLimitService.save(logWalletLimit);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateLogWalletLimit")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateLogWalletLimit(@RequestBody LogWalletLimit logWalletLimit){
boolean isOk = logWalletLimitService.updateById(logWalletLimit);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delLogWalletLimit")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delLogWalletLimit(@RequestParam("ids") List <Long> ids){
    boolean isOk = logWalletLimitService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


