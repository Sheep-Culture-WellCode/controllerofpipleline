package com.example.controllerofpipleline.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CompletePipleInfo {
    private int stakeId;
    private String stakeName;
    private String x;
    private String y;
    private String piplelineName;
    public String patrolId;
    private String patrolName;
    private String layMode;
    private String collectData;
    private String areaLevel;
    private int riskLevel;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date riskTime;
    private int distanceStack;

}
