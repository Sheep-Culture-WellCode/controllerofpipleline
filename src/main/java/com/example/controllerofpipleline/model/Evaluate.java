package com.example.controllerofpipleline.model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
public class Evaluate {
    @ApiModelProperty(value = "父节点Id,如果加入模块为算法模块名称（最顶层）那么此应该为0",required = true)
    private int parentId;
    @ApiModelProperty(hidden = true)
    private int moduleId;
    @ApiModelProperty(value = "模块名称",required = true)
    private String moduleName;
    @ApiModelProperty(value = "模块类型：(算法)最顶层类型为-2，必须填入；（选项）最底层选项为-1，必须填入；（也是最底层）输入框类型为-3，必须填入；其余时候不用填入")
    private int moduleType;
    @ApiModelProperty(value="模块分数：当moduleType为-1时，必须填入；其余时候不用填入")
    private int moduleScore;
    @ApiModelProperty(value="模块权重",required = true)
    private float moduleWeight;
    @ApiModelProperty(hidden = true)
    private List<Evaluate> evaluateList;
}
