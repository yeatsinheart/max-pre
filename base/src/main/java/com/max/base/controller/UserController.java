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

import com.max.base.service.UserService;
import com.max.base.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 用户表 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "用户表", tags = {"用户表控制器"})
    public class UserController {
@Autowired
private UserService userService;

/**
* 获取数据列表
*/
@GetMapping("/listUser/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listUser( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<User> page= new Page(pageIndex,size);
QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//queryWrapper.lambda().eq(User::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(User::getName, "1"));;
return Response.success(userService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allUser")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allUser(){
List<User> models = userService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getUser")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getUser(@PathVariable Long id){
User user = userService.getById(id);
if(user==null){
return Response.error("尚未查询到此ID");
}
return Response.success(user);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findUser")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findUser(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<User> userList = userService.listByMap(columnMap);
return Response.success(userList);
}


/**
* 添加数据
*/
@PostMapping("/addUser")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addUser(@RequestBody User user){
boolean isOk = userService.save(user);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateUser")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateUser(@RequestBody User user){
boolean isOk = userService.updateById(user);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delUser")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delUser(@RequestParam("ids") List <Long> ids){
    boolean isOk = userService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


