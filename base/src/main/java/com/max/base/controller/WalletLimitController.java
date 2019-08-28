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

import com.max.base.service.WalletLimitService;
import com.max.base.entity.WalletLimit;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 系统级别提现设置 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/walletLimit")
@Slf4j
@Api(value = "系统级别提现设置", tags = {"系统级别提现设置控制器"})
    public class WalletLimitController {
@Autowired
private WalletLimitService walletLimitService;

/**
* 获取数据列表
*/
@GetMapping("/listWalletLimit/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listWalletLimit( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<WalletLimit> page= new Page(pageIndex,size);
QueryWrapper<WalletLimit> queryWrapper = new QueryWrapper<WalletLimit>();
//queryWrapper.lambda().eq(WalletLimit::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(WalletLimit::getName, "1"));;
return Response.success(walletLimitService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allWalletLimit")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allWalletLimit(){
List<WalletLimit> models = walletLimitService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getWalletLimit")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getWalletLimit(@PathVariable Long id){
WalletLimit walletLimit = walletLimitService.getById(id);
if(walletLimit==null){
return Response.error("尚未查询到此ID");
}
return Response.success(walletLimit);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findWalletLimit")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findWalletLimit(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<WalletLimit> walletLimitList = walletLimitService.listByMap(columnMap);
return Response.success(walletLimitList);
}


/**
* 添加数据
*/
@PostMapping("/addWalletLimit")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addWalletLimit(@RequestBody WalletLimit walletLimit){
boolean isOk = walletLimitService.save(walletLimit);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateWalletLimit")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateWalletLimit(@RequestBody WalletLimit walletLimit){
boolean isOk = walletLimitService.updateById(walletLimit);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delWalletLimit")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delWalletLimit(@RequestParam("ids") List <Long> ids){
    boolean isOk = walletLimitService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


