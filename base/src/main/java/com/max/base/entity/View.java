package com.max.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 界面元素视图
 *
 * @author zane
 * @since 2019-08-29
 */
@Data
@ApiModel(value = "View对象", description = "界面元素视图")
public class View implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "view_id", type = IdType.AUTO)
    private Integer viewId;

    @ApiModelProperty(value = "界面元素类型：1 公告 2 轮播图 3 弹窗 4游戏 5活动 6充值方式")
    private Integer viewType;

    @ApiModelProperty(value = "标题")
    private String viewTitle;

    @ApiModelProperty(value = "标题类型 0 文本 1图片链接")
    private Integer viewTitleType;

    @ApiModelProperty(value = "内容")
    private String viewContent;

    @ApiModelProperty(value = "内容类型 0文本 1图片链接")
    private Integer viewContentType;

    @ApiModelProperty(value = "跳转内容")
    private String viewJump;

    @ApiModelProperty(value = "跳转类型 0无跳转 1站内 2链接")
    private Integer viewJumpType;

    @ApiModelProperty(value = "置顶:0普通 1置顶")
    private Integer viewTopping;

    @ApiModelProperty(value = "顺序位置")
    private Integer viewOrder;

    @ApiModelProperty(value = "状态：0开启 1关闭")
    private Integer viewStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
