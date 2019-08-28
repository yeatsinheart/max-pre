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

import com.max.base.service.LogBankService;
import com.max.base.entity.LogBank;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 用户银行卡记录 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/logBank")
@Slf4j
@Api(value = "用户银行卡记录", tags = {"用户银行卡记录控制器"})
    public class LogBankController {
@Autowired
private LogBankService logBankService;

/**
* 获取数据列表
*/
@GetMapping("/listLogBank/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listLogBank( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<LogBank> page= new Page(pageIndex,size);
QueryWrapper<LogBank> queryWrapper = new QueryWrapper<LogBank>();
//queryWrapper.lambda().eq(LogBank::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(LogBank::getName, "1"));;
return Response.success(logBankService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allLogBank")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allLogBank(){
List<LogBank> models = logBankService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getLogBank")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getLogBank(@PathVariable Long id){
LogBank logBank = logBankService.getById(id);
if(logBank==null){
return Response.error("尚未查询到此ID");
}
return Response.success(logBank);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findLogBank")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findLogBank(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<LogBank> logBankList = logBankService.listByMap(columnMap);
return Response.success(logBankList);
}


/**
* 添加数据
*/
@PostMapping("/addLogBank")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addLogBank(@RequestBody LogBank logBank){
boolean isOk = logBankService.save(logBank);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateLogBank")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateLogBank(@RequestBody LogBank logBank){
boolean isOk = logBankService.updateById(logBank);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delLogBank")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delLogBank(@RequestParam("ids") List <Long> ids){
    boolean isOk = logBankService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


