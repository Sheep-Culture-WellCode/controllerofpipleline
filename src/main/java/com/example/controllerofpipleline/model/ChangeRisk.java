package com.example.controllerofpipleline.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author WellCode
 */
@Data
public class ChangeRisk {
    private int startStakeId;
    private int riskLevel;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date riskTime;
    private int distanceStake;
    public ChangeRisk(int startStakeId, int riskLevel, Date riskTime, int distanceStake) {
        this.startStakeId = startStakeId;
        this.riskLevel = riskLevel;
        this.riskTime = riskTime;
        this.distanceStake = distanceStake;
    }
}
