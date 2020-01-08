package com.example.controllerofpipleline.mapper;

import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.model.ChangePipleInfo;
import com.example.controllerofpipleline.model.ChangeRisk;
import org.springframework.stereotype.Repository;

/**
 * @author WellCode
 */
@Repository
public interface PiplelineRiskMapper {
    int addRiskForPipleLine(PiplelineRisk piplelineRisk);
    int updataPiplelineRisk(ChangeRisk changeRisk);
}
