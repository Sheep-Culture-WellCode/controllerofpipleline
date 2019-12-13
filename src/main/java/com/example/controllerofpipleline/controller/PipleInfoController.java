package com.example.controllerofpipleline.controller;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.service.IPipleService;
import com.example.controllerofpipleline.service.impl.PipleServiceImpl;
import com.example.controllerofpipleline.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author WellCode
 */
@Slf4j
@Api(value = "管道接口",tags = {"管道接口"})
@RestController
@RequestMapping("pipleLine")
public class PipleInfoController {
    @Autowired
    IPipleService pipleService;
    
    @ApiOperation("为管道最小单位添加风险等级")
    @PostMapping("addRiskForPipleLine")
    public Result addRiskForPipleLine(int startStakeId,int endStakeId){
        return ResultUtil.success();
    }

//    @ApiOperation("查询管段所有信息")
//    @PostMapping("seleAPipleAllInfo")
////
//
    @ApiOperation("更新管段")
    @PostMapping("updateAPipleInfo")
    public Result updateAPipleInfo(int startStakeId){
        return ResultUtil.success();
    }

//
//    @ApiOperation("删除管段")




}
