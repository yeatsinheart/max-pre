package com.max.base.dto.response;
import com.max.base.entity.CategoryGame;
    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;

    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* 分类
* @author zane
* @since 2019-08-28
*/
    @Data

    @ApiModel(value="CategoryGameResponseDto响应对象", description="分类")
    public class CategoryGameResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "游戏分类ID")
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            @ApiModelProperty(value = "游戏分类名称")
    private String categoryName;

public CategoryGame toCategoryGame(){
CategoryGame categoryGame = new CategoryGame();
    categoryGame.setId(this.id);
    categoryGame.setCategoryName(this.categoryName);
return categoryGame;
}
}
