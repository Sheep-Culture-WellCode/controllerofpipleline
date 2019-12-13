package com.example.controllerofpipleline.service.impl;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.enums.ResultEnum;
import com.example.controllerofpipleline.exception.ResultException;
import com.example.controllerofpipleline.mapper.PiplelineRiskMapper;
import com.example.controllerofpipleline.mapper.PiplelineinfoMapper;
import com.example.controllerofpipleline.model.StakeInfo;
import com.example.controllerofpipleline.service.IPipleService;
import com.example.controllerofpipleline.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author WellCode
 */
@Service
public class PipleServiceImpl implements IPipleService {

    @Autowired
    PiplelineRiskMapper piplelineRiskMapper;

    @Autowired
    PiplelineinfoMapper piplelineinfoMapper;

    @Override
    public Result addRiskForPipleLine(PiplelineRisk piplelineRisk) {
        try {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringDate = df.format(date);
            date = df.parse(stringDate);
            piplelineRisk.setRiskTime(date);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(601,e.getMessage());
        }
        int i = piplelineRiskMapper.addRiskForPipleLine(piplelineRisk);
        if (i == 1){
            return ResultUtil.success();
        }
        else {
            return ResultUtil.error();
        }
    }

    @Override
    public PipleLineInfo SeleOnePipleInfoById(int id) {
       PipleLineInfo pipleLineInfo = piplelineinfoMapper.SeleOnePipleInfoById(id);
       if (pipleLineInfo==null){
           throw new ResultException(ResultEnum.NULLPIPLEIFO);
       }else {
           return pipleLineInfo;
       }
    }

    @Override
    public List<StakeInfo> seleAllStakeInfo() {
        List<StakeInfo> stakeInfos = piplelineinfoMapper.seleAllStakeInfo();
        if (stakeInfos == null){
            throw new ResultException(ResultEnum.ERROFSELE);
        }
        return stakeInfos;
    }

}
