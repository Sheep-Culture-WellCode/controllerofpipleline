package com.example.controllerofpipleline.domin;

import lombok.Data;

@Data
public class PiplelineSection {

    private int sectionId;
    private int startStackId;
    private int endStackId;
    private int sectionRiskLevel;

}
