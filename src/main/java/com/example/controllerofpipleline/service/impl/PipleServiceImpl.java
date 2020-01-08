package com.example.controllerofpipleline.service.impl;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.enums.ResultEnum;
import com.example.controllerofpipleline.exception.ResultException;
import com.example.controllerofpipleline.mapper.PiplelineRiskMapper;
import com.example.controllerofpipleline.mapper.PiplelineinfoMapper;
import com.example.controllerofpipleline.model.ChangePipleInfo;
import com.example.controllerofpipleline.model.ChangeRisk;
import com.example.controllerofpipleline.model.CompletePipleInfo;
import com.example.controllerofpipleline.model.StakeInfo;
import com.example.controllerofpipleline.service.IPipleService;
import com.example.controllerofpipleline.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CompletePipleInfo SeleOnePipleInfoById(int id) {
       CompletePipleInfo completePipleInfo = piplelineinfoMapper.SeleOnePipleInfoById(id);
       if (completePipleInfo==null){
           throw new ResultException(ResultEnum.NULLPIPLEIFO);
       }else {
           return completePipleInfo;
       }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<StakeInfo> seleAllStakeInfo() {
        List<StakeInfo> stakeInfos = piplelineinfoMapper.seleAllStakeInfo();
        if (stakeInfos == null){
            throw new ResultException(ResultEnum.ERROFSELE);
        }
        return stakeInfos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updataPiplelineRisk(ChangeRisk changeRisk) {
        int isUpdata = piplelineRiskMapper.updataPiplelineRisk(changeRisk);
        if (isUpdata==1){
            return ResultUtil.success();
        }else {
            return ResultUtil.error();
        }
    }


    @Override
    public Result updataOnePiplelineInfo(ChangePipleInfo changePipleInfo) {
        int isUp = piplelineinfoMapper.updataOnePiplelineInfo(changePipleInfo);
        if (isUp!=1){
            throw new ResultException(ResultEnum.NORMOALERROR);
        }
        return ResultUtil.success(changePipleInfo);
    }
}
