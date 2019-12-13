package com.example.controllerofpipleline.mapper;

import com.example.controllerofpipleline.domin.PiplelineRisk;
import org.springframework.stereotype.Repository;

/**
 * @author WellCode
 */
@Repository
public interface PiplelineRiskMapper {
    int addRiskForPipleLine(PiplelineRisk piplelineRisk);
}
