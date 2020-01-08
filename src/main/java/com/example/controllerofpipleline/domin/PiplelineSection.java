package com.example.controllerofpipleline.domin;

import lombok.Data;

@Data
public class PiplelineSection {

    private int sectionId;
    private int startStakeId;
    private int endStakeId;
    private int sectionRiskLevel;

    public PiplelineSection(int startStackId, int endStackId) {
        this.startStakeId = startStackId;
        this.endStakeId = endStackId;
        this.sectionId = 0;
        this.sectionRiskLevel = 0;
    }
}
