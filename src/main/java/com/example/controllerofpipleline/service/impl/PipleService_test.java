package com.example.controllerofpipleline.service.impl;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.mapper.PiplelineRiskMapper;
import com.example.controllerofpipleline.mapper.PiplelineinfoMapper;
import com.example.controllerofpipleline.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PipleService_test {
    @Autowired
    PiplelineinfoMapper piplelineinfoMapper;
    @Autowired
    PiplelineRiskMapper piplelineRiskMapper;
    public Result test(PipleLineInfo pipleLineInfo){
        int i = piplelineinfoMapper.test(pipleLineInfo);

        if (i == 1){
            return ResultUtil.success();
        }
        return ResultUtil.error();
    }

    public Result saveExcelList(List<PipleLineInfo> pipleLineInfos){
        for (PipleLineInfo type : pipleLineInfos){
            int i =piplelineinfoMapper.test(type);
            if (i != 1){
                return ResultUtil.error();
            }
        }
        return ResultUtil.success();
    }

    public Result saveRiskExcelList(List<PiplelineRisk> piplelineRisks){
        for (PiplelineRisk type : piplelineRisks){
            int i =piplelineRiskMapper.addRiskForPipleLine(type);
            if (i != 1){
                return ResultUtil.error();
            }
        }
        return ResultUtil.success();
    }
}
