package com.example.controllerofpipleline.controller;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.model.ChangeModule;
import com.example.controllerofpipleline.model.Evaluate;
import com.example.controllerofpipleline.service.IRiskEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(value = "风险评价接口",tags = {"风险评价接口"})
@RestController
@RequestMapping("riskEvaluate")
public class RiskEvaluateController {
    @Autowired
    IRiskEvaluateService riskEvaluateService;

    @ApiOperation("遍历评价表所有树，并返回")
    @PostMapping("selectAllModule")
    public List<Evaluate> selectAllModule(){
        return riskEvaluateService.selectAllModule();
    }


    @ApiOperation("删除一个模块")
    @PostMapping("delateOneMoudle")
    public Result delateOneMoudle(int moudleId){
        return riskEvaluateService.delateOneMoudle(moudleId);
    }

    @ApiOperation("增加一个模块")
    @PostMapping("addOneModule")
    public Result addOneModule(Evaluate evaluate){
        return riskEvaluateService.addOneModule(evaluate);
    }

    @ApiOperation("修改一个模块")
    @PostMapping("changeOneModule")
    public Result changeOneModule(ChangeModule changeModule){
        return riskEvaluateService.changeOneModule(changeModule);
    }

    @ApiOperation("计算风险等级")
    @PostMapping("countRiskEvaluate")
    public Result countRiskEvaluate(){
        return null;
    }
}
