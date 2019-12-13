package com.example.controllerofpipleline.service;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.model.StakeInfo;

import java.text.ParseException;
import java.util.List;

/**
 * @author WellCode
 */
public interface IPipleService {

    /**
     * 添加一个风险
     * @return Result<></>
     */
    Result addRiskForPipleLine(PiplelineRisk piplelineRisk);



    /**
     * 通过id查询一个管道最小单位的信息
     * @param stakeId
     * @return
     */
    PipleLineInfo SeleOnePipleInfoById(int stakeId);

    /**
     *
     * @return所有桩号的经纬度及相关信息
     */
    List<StakeInfo> seleAllStakeInfo();
}
