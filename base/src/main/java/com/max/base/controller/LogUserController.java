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

import com.max.base.service.LogUserService;
import com.max.base.entity.LogUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 用户操作记录 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/logUser")
@Slf4j
@Api(value = "用户操作记录", tags = {"用户操作记录控制器"})
    public class LogUserController {
@Autowired
private LogUserService logUserService;

/**
* 获取数据列表
*/
@GetMapping("/listLogUser/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listLogUser( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<LogUser> page= new Page(pageIndex,size);
QueryWrapper<LogUser> queryWrapper = new QueryWrapper<LogUser>();
//queryWrapper.lambda().eq(LogUser::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(LogUser::getName, "1"));;
return Response.success(logUserService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allLogUser")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allLogUser(){
List<LogUser> models = logUserService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getLogUser")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getLogUser(@PathVariable Long id){
LogUser logUser = logUserService.getById(id);
if(logUser==null){
return Response.error("尚未查询到此ID");
}
return Response.success(logUser);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findLogUser")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findLogUser(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<LogUser> logUserList = logUserService.listByMap(columnMap);
return Response.success(logUserList);
}


/**
* 添加数据
*/
@PostMapping("/addLogUser")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addLogUser(@RequestBody LogUser logUser){
boolean isOk = logUserService.save(logUser);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateLogUser")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateLogUser(@RequestBody LogUser logUser){
boolean isOk = logUserService.updateById(logUser);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delLogUser")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delLogUser(@RequestParam("ids") List <Long> ids){
    boolean isOk = logUserService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


