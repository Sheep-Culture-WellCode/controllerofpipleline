package com.example.controllerofpipleline.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChangeModule {
    @ApiModelProperty(value = "模块Id",required = true)
    private int moduleId;
    @ApiModelProperty(value = "模块名称", required = true)
    private String moduleName;
    @ApiModelProperty(value = "模块分数：当moduleType为-1时，必须填入；其余时候不用填入")
    private int moduleScore;
    @ApiModelProperty(value = "模块权重", required = true)
    private float moduleWeight;
}
