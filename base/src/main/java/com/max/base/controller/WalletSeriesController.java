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

import com.max.base.service.WalletSeriesService;
import com.max.base.entity.WalletSeries;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 钱包流水号 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/walletSeries")
@Slf4j
@Api(value = "钱包流水号", tags = {"钱包流水号控制器"})
    public class WalletSeriesController {
@Autowired
private WalletSeriesService walletSeriesService;

/**
* 获取数据列表
*/
@GetMapping("/listWalletSeries/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listWalletSeries( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<WalletSeries> page= new Page(pageIndex,size);
QueryWrapper<WalletSeries> queryWrapper = new QueryWrapper<WalletSeries>();
//queryWrapper.lambda().eq(WalletSeries::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(WalletSeries::getName, "1"));;
return Response.success(walletSeriesService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allWalletSeries")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allWalletSeries(){
List<WalletSeries> models = walletSeriesService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getWalletSeries")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getWalletSeries(@PathVariable Long id){
WalletSeries walletSeries = walletSeriesService.getById(id);
if(walletSeries==null){
return Response.error("尚未查询到此ID");
}
return Response.success(walletSeries);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findWalletSeries")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findWalletSeries(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<WalletSeries> walletSeriesList = walletSeriesService.listByMap(columnMap);
return Response.success(walletSeriesList);
}


/**
* 添加数据
*/
@PostMapping("/addWalletSeries")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addWalletSeries(@RequestBody WalletSeries walletSeries){
boolean isOk = walletSeriesService.save(walletSeries);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateWalletSeries")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateWalletSeries(@RequestBody WalletSeries walletSeries){
boolean isOk = walletSeriesService.updateById(walletSeries);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delWalletSeries")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delWalletSeries(@RequestParam("ids") List <Long> ids){
    boolean isOk = walletSeriesService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


