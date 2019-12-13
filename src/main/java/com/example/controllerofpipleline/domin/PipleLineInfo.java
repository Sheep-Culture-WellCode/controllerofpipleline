package com.example.controllerofpipleline.domin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PipleLineInfo {
    @ApiModelProperty(hidden = true)
    private int stakeId;
    private String stakeName;
    private String x;
    private String y;
    private String piplelineName;
    private String patrolId;
    private String layMode;
    private String collectData;
    private String areaLevel;
}
