package com.example.controllerofpipleline.service;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;

import java.text.ParseException;

/**
 * @author WellCode
 */
public interface IPipleService {

    /**
     * 添加一个风险
     * @return Result<></>
     */
    Result addRiskForPipleLine(PiplelineRisk piplelineRisk);
    PipleLineInfo SeleOnePipleInfoById(int id);
}
