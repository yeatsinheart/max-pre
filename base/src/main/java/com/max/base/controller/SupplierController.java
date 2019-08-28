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

import com.max.base.service.SupplierService;
import com.max.base.entity.Supplier;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 服务提供 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/supplier")
@Slf4j
@Api(value = "服务提供", tags = {"服务提供控制器"})
    public class SupplierController {
@Autowired
private SupplierService supplierService;

/**
* 获取数据列表
*/
@GetMapping("/listSupplier/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listSupplier( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<Supplier> page= new Page(pageIndex,size);
QueryWrapper<Supplier> queryWrapper = new QueryWrapper<Supplier>();
//queryWrapper.lambda().eq(Supplier::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(Supplier::getName, "1"));;
return Response.success(supplierService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allSupplier")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allSupplier(){
List<Supplier> models = supplierService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getSupplier")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getSupplier(@PathVariable Long id){
Supplier supplier = supplierService.getById(id);
if(supplier==null){
return Response.error("尚未查询到此ID");
}
return Response.success(supplier);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findSupplier")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findSupplier(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<Supplier> supplierList = supplierService.listByMap(columnMap);
return Response.success(supplierList);
}


/**
* 添加数据
*/
@PostMapping("/addSupplier")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addSupplier(@RequestBody Supplier supplier){
boolean isOk = supplierService.save(supplier);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateSupplier")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateSupplier(@RequestBody Supplier supplier){
boolean isOk = supplierService.updateById(supplier);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delSupplier")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delSupplier(@RequestParam("ids") List <Long> ids){
    boolean isOk = supplierService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


