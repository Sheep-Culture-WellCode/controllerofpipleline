package com.example.controllerofpipleline.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StakeInfo {

    private int stakeId;
    private String stakeName;
    private String x;
    private String y;
    private int riskLevel;
    private String piplelineName;
    public String patrolId;
    private String patrolName;
    private String layMode;
    private String collectData;
    private String areaLevel;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date riskTime;
    private int distanceStack;
}
