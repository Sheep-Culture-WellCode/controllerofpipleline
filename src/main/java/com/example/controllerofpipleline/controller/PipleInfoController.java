package com.example.controllerofpipleline.controller;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.enums.ResultEnum;
import com.example.controllerofpipleline.exception.ResultException;
import com.example.controllerofpipleline.model.StakeInfo;
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

import java.text.ParseException;
import java.util.List;

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


    @ApiOperation("返回所有桩号经纬度及颜色")
    @PostMapping("seleAllStakeInfo")
    public List<StakeInfo> seleAllStakeInfo(){
        return pipleService.seleAllStakeInfo();
    }


    @ApiOperation("查询一个管段最小单位所有信息")
    @PostMapping("SeleOnePipleInfoById")
    public PipleLineInfo SeleOnePipleInfoById(String stakeId){
        if (stakeId==null){
            throw new ResultException(ResultEnum.ERROARGUMENT);
        }
        int intStakeId = Integer.valueOf(stakeId);
        return pipleService.SeleOnePipleInfoById(intStakeId);
    }

//    @ApiOperation("更新管段")
//    @PostMapping("updateAPipleInfo")
//    public R esult updateAPipleInfo(int startStakeId){
//        return ResultUtil.success();
//    }
//
//    @ApiOperation("删除管段")
//    @PostMapping("deletePipleInfo")
//    public Result deletePipleInfo (int startStakeId,int endStakeId){
//        return ResultUtil.success();
//    }





}
