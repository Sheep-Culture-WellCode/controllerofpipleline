package com.example.controllerofpipleline.service.impl;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineSection;
import com.example.controllerofpipleline.enums.ResultEnum;
import com.example.controllerofpipleline.exception.ResultException;
import com.example.controllerofpipleline.mapper.PiplelineSectionMapper;
import com.example.controllerofpipleline.mapper.PiplelineinfoMapper;
import com.example.controllerofpipleline.model.ChangeSectionRisk;
import com.example.controllerofpipleline.model.CompletePipleInfo;
import com.example.controllerofpipleline.service.IPipleSectionService;
import com.example.controllerofpipleline.util.ArithmeticUtil;
import com.example.controllerofpipleline.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PipleSectionServiceImpl implements IPipleSectionService{
    @Autowired
    PiplelineSectionMapper piplelineSectionMapper;
    @Autowired
    PiplelineinfoMapper piplelineinfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addPiplelineIntoSection(PiplelineSection piplelineSection) {
        for (int i = piplelineSection.getStartStakeId();i<=piplelineSection.getEndStakeId();i++){
            CompletePipleInfo completePipleInfo = piplelineinfoMapper.SeleOnePipleInfoById(i);
            if (completePipleInfo == null){
                throw new ResultException(ResultEnum.ERROFSELE);
            }
          PiplelineSection piplelineSection1 = piplelineSectionMapper.isStakeLegal(i);
          if (piplelineSection1!=null) {
              throw new ResultException(ResultEnum.STAKEIDEXISTSECTION);
          }
        }
        int flag = piplelineSectionMapper.addPiplelineSection(piplelineSection);
        if (flag==1){
            return ResultUtil.success(piplelineSection);
        }
        return ResultUtil.error();
    }

    @Override
    public Result updateOnePipleSectionInfo(int stakeId) {
        PiplelineSection piplelineSection = piplelineSectionMapper.isStakeLegal(stakeId);
        if(piplelineSection == null){
            return ResultUtil.error();
        }
        int lowRisk =0;
        int normalRisk=0;
        int biggerRisk=0;
        int biggestRisk=0;
        int startStakeId = piplelineSection.getStartStakeId();
        int endStakeId = piplelineSection.getEndStakeId();
            for (int i = startStakeId ; i <=endStakeId ; i++) {
                CompletePipleInfo completePipleInfo = piplelineinfoMapper.SeleOnePipleInfoById(i);
                if (completePipleInfo.getRiskLevel() == 1) {
                    lowRisk++;
                }
                if (completePipleInfo.getRiskLevel() == 2) {
                    normalRisk++;
                }
                if (completePipleInfo.getRiskLevel() == 3) {
                    biggerRisk++;
                }
                if (completePipleInfo.getRiskLevel() == 4) {
                    biggestRisk++;
                }
            }
            Boolean isLowRisk = ArithmeticUtil.isBiggest(lowRisk,normalRisk,biggerRisk,biggestRisk);
            Boolean isNormalRisk = ArithmeticUtil.isBiggest(normalRisk,lowRisk,biggerRisk,biggestRisk);
            Boolean isBiggerRisk = ArithmeticUtil.isBiggest(biggerRisk,lowRisk,normalRisk,biggestRisk);
            Boolean isBiggestRisk = ArithmeticUtil.isBiggest(biggestRisk,lowRisk,normalRisk,biggerRisk);
//            System.out.println(lowRisk);
//            System.out.println(normalRisk);
//            System.out.println(biggerRisk);
//            System.out.println(biggestRisk);
//            System.out.println(isBiggestRisk.toString());
            if (isBiggestRisk == true){
                ChangeSectionRisk changeSectionRisk = new ChangeSectionRisk();
                changeSectionRisk.setId(piplelineSection.getSectionId());
                changeSectionRisk.setRiskLevel(4);
                int flag = piplelineSectionMapper.updateSectionRisk(changeSectionRisk);
                if (flag!=1){
                    throw new ResultException(ResultEnum.ERROUPDATESECTIONRISK);
                }
            }else if (isBiggerRisk==true){
                ChangeSectionRisk changeSectionRisk = new ChangeSectionRisk();
                changeSectionRisk.setId(piplelineSection.getSectionId());
                changeSectionRisk.setRiskLevel(3);
                int flag = piplelineSectionMapper.updateSectionRisk(changeSectionRisk);
                if (flag!=1){
                    throw new ResultException(ResultEnum.ERROUPDATESECTIONRISK);
                }
            }else if (isNormalRisk==true){
                ChangeSectionRisk changeSectionRisk = new ChangeSectionRisk();
                changeSectionRisk.setId(piplelineSection.getSectionId());
                changeSectionRisk.setRiskLevel(2);
                int flag = piplelineSectionMapper.updateSectionRisk(changeSectionRisk);
                if (flag!=1){
                    throw new ResultException(ResultEnum.ERROUPDATESECTIONRISK);
                }
            }else if (isLowRisk==true){
                ChangeSectionRisk changeSectionRisk = new ChangeSectionRisk();
                changeSectionRisk.setId(piplelineSection.getSectionId());
                changeSectionRisk.setRiskLevel(1);
                int flag = piplelineSectionMapper.updateSectionRisk(changeSectionRisk);
                if (flag!=1){
                    throw new ResultException(ResultEnum.ERROUPDATESECTIONRISK);
                }
            }
        return  ResultUtil.success();
    }

    @Override
    public Result UpadatePipleSectionInfo() {
        List<PiplelineSection> piplelineSectionList = piplelineSectionMapper.seleAllSection();
        if(piplelineSectionList == null){
            return ResultUtil.error();
        }
        for(PiplelineSection piplelineSection : piplelineSectionList){
            int lowRisk =0;
            int normalRisk=0;
            int biggerRisk=0;
            int biggestRisk=0;
            int startStakeId = piplelineSection.getStartStakeId();
            int endStakeId = piplelineSection.getEndStakeId();
            for (int i = startStakeId ; i <=endStakeId ; i++){
                CompletePipleInfo completePipleInfo = piplelineinfoMapper.SeleOnePipleInfoById(i);
                if (completePipleInfo.getRiskLevel() == 1){
                    lowRisk++;
                }
                if (completePipleInfo.getRiskLevel() == 2){
                    normalRisk++;
                }
                if (completePipleInfo.getRiskLevel() == 3){
                    biggerRisk++;
                }
                if (completePipleInfo.getRiskLevel() == 4){
                    biggestRisk++;
                }
            }
            Boolean isLowRisk = ArithmeticUtil.isBiggest(lowRisk,normalRisk,biggerRisk,biggestRisk);
            Boolean isNormalRisk = ArithmeticUtil.isBiggest(normalRisk,lowRisk,biggerRisk,biggestRisk);
            Boolean isBiggerRisk = ArithmeticUtil.isBiggest(biggerRisk,lowRisk,normalRisk,biggestRisk);
            Boolean isBiggestRisk = ArithmeticUtil.isBiggest(biggestRisk,lowRisk,normalRisk,biggerRisk);
//            System.out.println(lowRisk);
//            System.out.println(normalRisk);
//            System.out.println(biggerRisk);
//            System.out.println(biggestRisk);
//            System.out.println(isBiggestRisk.toString());
            if (isBiggestRisk == true){
                ChangeSectionRisk changeSectionRisk = new ChangeSectionRisk();
                changeSectionRisk.setId(piplelineSection.getSectionId());
                changeSectionRisk.setRiskLevel(4);
                int flag = piplelineSectionMapper.updateSectionRisk(changeSectionRisk);
                if (flag!=1){
                    throw new ResultException(ResultEnum.ERROUPDATESECTIONRISK);
                }
            }else if (isBiggerRisk==true){
                ChangeSectionRisk changeSectionRisk = new ChangeSectionRisk();
                changeSectionRisk.setId(piplelineSection.getSectionId());
                changeSectionRisk.setRiskLevel(3);
                int flag = piplelineSectionMapper.updateSectionRisk(changeSectionRisk);
                if (flag!=1){
                    throw new ResultException(ResultEnum.ERROUPDATESECTIONRISK);
                }
            }else if (isNormalRisk==true){
                ChangeSectionRisk changeSectionRisk = new ChangeSectionRisk();
                changeSectionRisk.setId(piplelineSection.getSectionId());
                changeSectionRisk.setRiskLevel(2);
                int flag = piplelineSectionMapper.updateSectionRisk(changeSectionRisk);
                if (flag!=1){
                    throw new ResultException(ResultEnum.ERROUPDATESECTIONRISK);
                }
            }else if (isLowRisk==true){
                ChangeSectionRisk changeSectionRisk = new ChangeSectionRisk();
                changeSectionRisk.setId(piplelineSection.getSectionId());
                changeSectionRisk.setRiskLevel(1);
                int flag = piplelineSectionMapper.updateSectionRisk(changeSectionRisk);
                if (flag!=1){
                    throw new ResultException(ResultEnum.ERROUPDATESECTIONRISK);
                }
            }
        }
        return  ResultUtil.success();
    }

    @Override
    public List<PiplelineSection> selectAllPipleSection() {
        return piplelineSectionMapper.seleAllSection();
    }
}
