package com.max.base.entity;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.time.LocalDateTime;
    import java.io.Serializable;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;


/**
* 界面元素视图
* @author zane
* @since 2019-08-29
*/
    @Data
    @ApiModel(value="View对象", description="界面元素视图")
    public class View implements Serializable {

private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "界面元素类型：ViewTypeEnum")
    private Integer type;

            @ApiModelProperty(value = "标题")
    private String title;

            @ApiModelProperty(value = "标题类型 0 文本 1图片链接")
    private Integer titleType;

            @ApiModelProperty(value = "内容")
    private String content;

            @ApiModelProperty(value = "内容类型 0文本 1图片链接")
    private Integer contentType;

            @ApiModelProperty(value = "跳转内容")
    private String action;

            @ApiModelProperty(value = "跳转类型 0无跳转 1站内 2链接")
    private Integer actionType;

            @ApiModelProperty(value = "置顶:0普通 1置顶")
    private Integer topping;

            @ApiModelProperty(value = "顺序位置")
    private Integer order;

            @ApiModelProperty(value = "状态：0开启 1关闭")
    private Integer status;

            @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

            @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;


}
