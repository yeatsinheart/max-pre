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

import com.max.base.service.BankService;
import com.max.base.entity.Bank;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 支持的银行 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/bank")
@Slf4j
@Api(value = "支持的银行", tags = {"支持的银行控制器"})
    public class BankController {
@Autowired
private BankService bankService;

/**
* 获取数据列表
*/
@GetMapping("/listBank/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listBank( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<Bank> page= new Page(pageIndex,size);
QueryWrapper<Bank> queryWrapper = new QueryWrapper<Bank>();
//queryWrapper.lambda().eq(Bank::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(Bank::getName, "1"));;
return Response.success(bankService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allBank")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allBank(){
List<Bank> models = bankService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getBank")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getBank(@PathVariable Long id){
Bank bank = bankService.getById(id);
if(bank==null){
return Response.error("尚未查询到此ID");
}
return Response.success(bank);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findBank")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findBank(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<Bank> bankList = bankService.listByMap(columnMap);
return Response.success(bankList);
}


/**
* 添加数据
*/
@PostMapping("/addBank")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addBank(@RequestBody Bank bank){
boolean isOk = bankService.save(bank);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateBank")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateBank(@RequestBody Bank bank){
boolean isOk = bankService.updateById(bank);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delBank")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delBank(@RequestParam("ids") List <Long> ids){
    boolean isOk = bankService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


