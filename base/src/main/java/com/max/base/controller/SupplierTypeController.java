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

import com.max.base.service.SupplierTypeService;
import com.max.base.entity.SupplierType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 服务提供类型 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/supplierType")
@Slf4j
@Api(value = "服务提供类型", tags = {"服务提供类型控制器"})
    public class SupplierTypeController {
@Autowired
private SupplierTypeService supplierTypeService;

/**
* 获取数据列表
*/
@GetMapping("/listSupplierType/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listSupplierType( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<SupplierType> page= new Page(pageIndex,size);
QueryWrapper<SupplierType> queryWrapper = new QueryWrapper<SupplierType>();
//queryWrapper.lambda().eq(SupplierType::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(SupplierType::getName, "1"));;
return Response.success(supplierTypeService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allSupplierType")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allSupplierType(){
List<SupplierType> models = supplierTypeService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getSupplierType")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getSupplierType(@PathVariable Long id){
SupplierType supplierType = supplierTypeService.getById(id);
if(supplierType==null){
return Response.error("尚未查询到此ID");
}
return Response.success(supplierType);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findSupplierType")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findSupplierType(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<SupplierType> supplierTypeList = supplierTypeService.listByMap(columnMap);
return Response.success(supplierTypeList);
}


/**
* 添加数据
*/
@PostMapping("/addSupplierType")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addSupplierType(@RequestBody SupplierType supplierType){
boolean isOk = supplierTypeService.save(supplierType);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateSupplierType")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateSupplierType(@RequestBody SupplierType supplierType){
boolean isOk = supplierTypeService.updateById(supplierType);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delSupplierType")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delSupplierType(@RequestParam("ids") List <Long> ids){
    boolean isOk = supplierTypeService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


