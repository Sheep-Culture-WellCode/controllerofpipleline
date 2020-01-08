package com.example.controllerofpipleline.service;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.model.ChangePipleInfo;
import com.example.controllerofpipleline.model.ChangeRisk;
import com.example.controllerofpipleline.model.CompletePipleInfo;
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
     * @param stakeId: 查询管道最小单位的桩号
     * @return:返回最小单位完整的信息
     */
    CompletePipleInfo SeleOnePipleInfoById(int stakeId);

    /**
     *
     * @return:所有桩号的经纬度及相关信息
     */
    List<StakeInfo> seleAllStakeInfo();


    /**
     * 更新一段管段最小单位的风险属性
     * @param changeRisk：更改风险的BEAN
     * @return Result
     */
    Result updataPiplelineRisk(ChangeRisk changeRisk);

    Result updataOnePiplelineInfo(ChangePipleInfo changePipleInfo);
}
