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

import com.max.base.service.RelationCategoryViewService;
import com.max.base.entity.RelationCategoryView;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
* 分类与视图关系 前端控制器
* @author zane
* @since 2019-08-28
*/

    @RestController
@RequestMapping("/relationCategoryView")
@Slf4j
@Api(value = "分类与视图关系", tags = {"分类与视图关系控制器"})
    public class RelationCategoryViewController {
@Autowired
private RelationCategoryViewService relationCategoryViewService;

/**
* 获取数据列表
*/
@GetMapping("/listRelationCategoryView/{pageIndex}/{pageSize}")
@ResponseBody
@ApiOperation(value = "获取数据列表")
public Response listRelationCategoryView( @PathVariable(name = "pageIndex") int pageIndex, @PathVariable(name = "pageSize") int size){
Page<RelationCategoryView> page= new Page(pageIndex,size);
QueryWrapper<RelationCategoryView> queryWrapper = new QueryWrapper<RelationCategoryView>();
//queryWrapper.lambda().eq(RelationCategoryView::getChangeTypeStatus, "05").or(obj1 -> obj1.eq(Student::getName, "冯文议")).or(obj2 -> obj2.eq(RelationCategoryView::getName, "1"));;
return Response.success(relationCategoryViewService.page(page,queryWrapper));

}


/**
* 获取全部数据
*/
@GetMapping("/allRelationCategoryView")
@ResponseBody
@ApiOperation(value = "获取全部数据")
public Response allRelationCategoryView(){
List<RelationCategoryView> models = relationCategoryViewService.list(null);
return Response.success(models);
}


/**
* 根据ID查找数据
*/
@GetMapping("/getRelationCategoryView")
@ResponseBody
@ApiOperation(value = "根据ID查找数据")
public Response getRelationCategoryView(@PathVariable Long id){
RelationCategoryView relationCategoryView = relationCategoryViewService.getById(id);
if(relationCategoryView==null){
return Response.error("尚未查询到此ID");
}
return Response.success(relationCategoryView);
}


/**
* 根据条件查找数据
*/
@PostMapping("/findRelationCategoryView")
@ResponseBody
@ApiOperation(value = "根据条件查找数据")
public Response findRelationCategoryView(Map columnMap){
columnMap.put("last_name","东方不败");//写表中的列名
columnMap.put("gender","1");
Collection<RelationCategoryView> relationCategoryViewList = relationCategoryViewService.listByMap(columnMap);
return Response.success(relationCategoryViewList);
}


/**
* 添加数据
*/
@PostMapping("/addRelationCategoryView")
@ResponseBody
@ApiOperation(value = "添加数据")
public Response addRelationCategoryView(@RequestBody RelationCategoryView relationCategoryView){
boolean isOk = relationCategoryViewService.save(relationCategoryView);
if(isOk){
return Response.success("数据添加成功！");
}
return Response.error("数据添加失败");
}


/**
* 更新数据
*/
@PostMapping("/updateRelationCategoryView")
@ResponseBody
@ApiOperation(value = "更新数据")
public Response updateRelationCategoryView(@RequestBody RelationCategoryView relationCategoryView){
boolean isOk = relationCategoryViewService.updateById(relationCategoryView);
if(isOk){
return Response.success("数据更改成功！");
}
return Response.error("数据更改失败");
}


/**
* 删除数据
*/
@PostMapping("/delRelationCategoryView")
@ResponseBody
@ApiOperation(value = "删除数据")
public Response delRelationCategoryView(@RequestParam("ids") List <Long> ids){
    boolean isOk = relationCategoryViewService.removeByIds(ids);
    if(isOk){
    return Response.success("数据删除成功！");
    }
    return Response.error("数据删除失败");
    }
    }


