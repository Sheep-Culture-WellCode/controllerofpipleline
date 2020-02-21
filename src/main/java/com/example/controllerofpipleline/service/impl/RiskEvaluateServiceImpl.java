package com.example.controllerofpipleline.service.impl;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.enums.ResultEnum;
import com.example.controllerofpipleline.exception.ResultException;
import com.example.controllerofpipleline.mapper.RiskEvaluateMapper;
import com.example.controllerofpipleline.model.ChangeModule;
import com.example.controllerofpipleline.model.Evaluate;
import com.example.controllerofpipleline.service.IRiskEvaluateService;
import com.example.controllerofpipleline.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskEvaluateServiceImpl implements IRiskEvaluateService {
    @Autowired
    RiskEvaluateMapper riskEvaluateMapper;
    @Override
    public List<Evaluate> selectAllModule() {
        List<Evaluate> evaluateListA = riskEvaluateMapper.selectModuleList(0);
        return getTree(evaluateListA);
    }

    @Override
    public List<Evaluate> selectOneModule(int moduleId){
        List<Evaluate> evaluateListA = riskEvaluateMapper.selectOneModule(moduleId);
        return getTree(evaluateListA);
    }

    @Override
    public Result delateOneMoudle(int moudleId) {
        List<Evaluate> evaluateListA = riskEvaluateMapper.selectOneModule(moudleId);
        if (evaluateListA.size()==0){
            throw new ResultException(ResultEnum.NULLMOUDLE);
        }
        boolean isDelate = delateTree(evaluateListA);
        if (isDelate == false){
            throw new ResultException(ResultEnum.ERRDELATEMOUDLE);
        }
        return ResultUtil.success(evaluateListA);
    }

    @Override
    public Result addOneModule(Evaluate evaluate) {
        int INPUT=-1;
        int TOP_MODULE =-2;
        int OPTION = -3;
        if (evaluate.getParentId()==0){
            if (evaluate.getModuleType()!= TOP_MODULE){
                return ResultUtil.error(601, "您增加模块的格式并不规范");
            }
        }else {
            if (evaluate.getModuleType()==TOP_MODULE){
                return ResultUtil.error(601, "您增加模块的格式并不规范");
            }
            List<Evaluate> evaluateList = riskEvaluateMapper.selectOneModule(evaluate.getParentId());
            if (evaluateList.size()==0){
                return ResultUtil.error(601, "您所增加模块的父模块不存在");
            }
            Evaluate parentEvaluate = evaluateList.get(0);
            if (parentEvaluate.getModuleType() == TOP_MODULE){
                evaluate.setModuleType(0);
            }else if (parentEvaluate.getModuleType()==INPUT||parentEvaluate.getModuleType()==OPTION){
                return ResultUtil.error(601, "您增加模块的格式并不规范");
            } else if (evaluate.getModuleType()!=OPTION && evaluate.getModuleType()!=INPUT){
                evaluate.setModuleType(parentEvaluate.getModuleType()+1);
            }
        }
        int isAdd = riskEvaluateMapper.addOneModule(evaluate);
        if (isAdd!=1){
            return ResultUtil.error(601, "增加模块数据库发生了错误");
        }
        return ResultUtil.success(evaluate);
    }

    @Override
    public Result changeOneModule(ChangeModule changeModule) {
        List<Evaluate> evaluate = riskEvaluateMapper.selectOneModule(changeModule.getModuleId());
        if (evaluate.size()==0){
            return ResultUtil.error(601, "您所增加模块不存在");
        }
        int isUp = riskEvaluateMapper.changeOneModule(changeModule);
        if (isUp!=1){
            return ResultUtil.error(601, "增加模块数据库发生了错误");
        }
        return ResultUtil.success();
    }

    public List<Evaluate> getTree(List<Evaluate> evaluates){
        for (Evaluate evaluate : evaluates){
            List<Evaluate> evaluateListA = riskEvaluateMapper.selectModuleList(evaluate.getModuleId());
            evaluate.setEvaluateList(evaluateListA);
            if (evaluateListA.size()!=0){
                getTree(evaluateListA);
            }
        }
        return evaluates;
    }

    public Boolean delateTree(List<Evaluate> evaluates){
        for (Evaluate evaluate : evaluates){
            int isDelate = riskEvaluateMapper.delateOneModule(evaluate.getModuleId());
            if (isDelate!=1){
                return false;
            }
            List<Evaluate> evaluateListA = riskEvaluateMapper.selectModuleList(evaluate.getModuleId());
            evaluate.setEvaluateList(evaluateListA);
            if (evaluateListA.size()!=0){
                delateTree(evaluateListA);
            }
        }
        return true;
    }
}