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

import com.max.base.service.WalletOrderService;
import com.max.base.entity.WalletOrder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 订单 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/walletOrder")
@Slf4j
@Api(value = "订单", tags = {"订单控制器"})
    public class WalletOrderController {
@Autowired
private WalletOrderService walletOrderService;

/**
* 获取数据列表
*/
@GetMapping("/listWalletOrder/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listWalletOrder( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<WalletOrder> page= new Page(pageIndex,size);
QueryWrapper<WalletOrder> queryWrapper = new QueryWrapper<WalletOrder>();
//queryWrapper.lambda().eq(WalletOrder::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(WalletOrder::getName, "1"));;
return Response.success(walletOrderService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allWalletOrder")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allWalletOrder(){
List<WalletOrder> models = walletOrderService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getWalletOrder")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getWalletOrder(@PathVariable Long id){
WalletOrder walletOrder = walletOrderService.getById(id);
if(walletOrder==null){
return Response.error("尚未查询到此ID");
}
return Response.success(walletOrder);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findWalletOrder")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findWalletOrder(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<WalletOrder> walletOrderList = walletOrderService.listByMap(columnMap);
return Response.success(walletOrderList);
}


/**
* 添加数据
*/
@PostMapping("/addWalletOrder")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addWalletOrder(@RequestBody WalletOrder walletOrder){
boolean isOk = walletOrderService.save(walletOrder);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateWalletOrder")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateWalletOrder(@RequestBody WalletOrder walletOrder){
boolean isOk = walletOrderService.updateById(walletOrder);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delWalletOrder")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delWalletOrder(@RequestParam("ids") List <Long> ids){
    boolean isOk = walletOrderService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


