package com.example.controllerofpipleline.domin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class PiplelineRisk {
    @ApiModelProperty(required = false ,hidden = true)
    private int riskId;
    @ApiModelProperty(value = "管道最小单位的起始桩位",required = true)
    private int startStackId;
    @ApiModelProperty(value = "风险等级",required = true)
    private int riskLevel;
    @ApiModelProperty(hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date riskTime;
    @ApiModelProperty(value = "距离起始桩位的距离（请手动填写）",required = false)
    private int distanceStack;
}
