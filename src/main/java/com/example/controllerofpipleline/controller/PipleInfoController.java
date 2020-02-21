package com.example.controllerofpipleline.controller;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PatrolTeam;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.domin.PiplelineSection;
import com.example.controllerofpipleline.enums.ResultEnum;
import com.example.controllerofpipleline.exception.ResultException;
import com.example.controllerofpipleline.mapper.PatrolTeamMapper;
import com.example.controllerofpipleline.model.ChangePipleInfo;
import com.example.controllerofpipleline.model.ChangeRisk;
import com.example.controllerofpipleline.model.CompletePipleInfo;
import com.example.controllerofpipleline.model.StakeInfo;
import com.example.controllerofpipleline.service.IPipleSectionService;
import com.example.controllerofpipleline.service.IPipleService;
import com.example.controllerofpipleline.service.impl.PipleServiceImpl;
import com.example.controllerofpipleline.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    IPipleSectionService pipleSectionService;
    @Autowired
    PatrolTeamMapper patrolTeamMapper;

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("返回所有桩号经纬度及颜色")
    @PostMapping("seleAllStakeInfo")
    public  List<StakeInfo> seleAllStakeInfo(){
        return pipleService.seleAllStakeInfo();
    }

    @Transactional
    @ApiOperation("查询一个管道最小单位所有信息")
    @PostMapping("SeleOnePipleInfoById")
    public Result SeleOnePipleInfoById(String stakeId){
        if (stakeId==null){
            throw new ResultException(ResultEnum.ERROARGUMENT);
        }
        int intStakeId = Integer.valueOf(stakeId);
        CompletePipleInfo completePipleInfo = pipleService.SeleOnePipleInfoById(intStakeId);
        return ResultUtil.success(completePipleInfo);
    }

    @ApiOperation("改变管道最小单位风险（不支持多选）")
    @PostMapping("updataOnePiplelineRisk")
    public Result updataOnePiplelineRisk(String startStakeId,String riskLevel,String distanceRisk){
        if (startStakeId==null||distanceRisk==null||riskLevel==null){
            throw new ResultException(ResultEnum.ERROARGUMENT);
        }
        int intStartStakeId = Integer.valueOf(startStakeId);
        int intRiskLevel = Integer.valueOf(riskLevel);
        int intDistance = Integer.valueOf(distanceRisk);
        try {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringDate = df.format(date);
            date = df.parse(stringDate);
            CompletePipleInfo completePipleInfo = pipleService.SeleOnePipleInfoById(intStartStakeId);
            if (completePipleInfo == null){
                throw new ResultException(ResultEnum.NORMOALERROR);
            }
            ChangeRisk changeRisk = new ChangeRisk(intStartStakeId,intRiskLevel,date,intDistance);
            Result result  = pipleService.updataPiplelineRisk(changeRisk);
            if (result.getCode() != 600){
                throw new ResultException(ResultEnum.NULLPIPLEIFO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Result result = pipleSectionService.updateOnePipleSectionInfo(intStartStakeId);
        System.out.println(result.toString());
        if (result.getCode()==600) {
            return ResultUtil.success();
        }else if (result.getCode()==601) {
            return ResultUtil.success("修改管道最小单位风险成功，但是没有管段信息可以更改");
        }
        return ResultUtil.error();
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("改变管道最小单位风险(支持多选)")
    @PostMapping("updataPiplelineRisk")
    public Result updataPiplelineRisk(String startStakeId,String endStakeId,String riskLevel) throws Exception{
        if (startStakeId==null||endStakeId==null||riskLevel==null){
            throw new ResultException(ResultEnum.ERROARGUMENT);
        }
        int intStartStakeId = Integer.valueOf(startStakeId);
        int intEndStakeId = Integer.valueOf(endStakeId);
        int intRiskLevel = Integer.valueOf(riskLevel);
        int intDistance = -1;
        try {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stringDate = df.format(date);
            date = df.parse(stringDate);
            for(int i = intStartStakeId;i<=intEndStakeId-1;i++){
                ChangeRisk changeRisk = new ChangeRisk(i,intRiskLevel,date,intDistance);
                CompletePipleInfo completePipleInfo = pipleService.SeleOnePipleInfoById(i);
                if (completePipleInfo == null){
                    throw new ResultException(ResultEnum.NORMOALERROR);
                }
                Result result  = pipleService.updataPiplelineRisk(changeRisk);
                if (result.getCode() != 600){
                    throw new ResultException(ResultEnum.NULLPIPLEIFO);
                }
            }
        }catch (Exception e){
            throw e;
        }
        Result result = pipleSectionService.UpadatePipleSectionInfo();
        if (result.getCode()==600) {
            return ResultUtil.success();
        }else if (result.getCode()==601) {
            return ResultUtil.success("修改管道最小单位风险成功，但是没有管段信息可以更改");
        }
        return ResultUtil.error();
    }

    @Transactional(rollbackFor=Exception.class)
    @ApiOperation("将管道最小单位归入一条管段")
    @PostMapping("addPiplelineIntoSection")
    public Result addPiplelineIntoSection(String startStakeId,String endStakeId){
        if (startStakeId==null||endStakeId==null){
            throw new ResultException(ResultEnum.ERROARGUMENT);
        }
        if (Integer.valueOf(endStakeId)<=Integer.valueOf(startStakeId)){
            throw new ResultException(ResultEnum.STAKEIDEXISTSECTION);
        }
        PiplelineSection piplelineSection = new PiplelineSection(Integer.valueOf(startStakeId),Integer.valueOf(endStakeId)-1);
        return pipleSectionService.addPiplelineIntoSection(piplelineSection);
    }

    @ApiOperation("查询所有管段的信息及风险等级")
    @PostMapping("selectAllPipleSection")
    public List<PiplelineSection> selectAllPipleSection(){
        return pipleSectionService.selectAllPipleSection();
    }

    @ApiOperation("修改管道信息")
    @PostMapping("updataOnePiplelineInfo")
    public Result updataOnePiplelineInfo(ChangePipleInfo changePipleInfo){
        return pipleService.updataOnePiplelineInfo(changePipleInfo);
    }

    @ApiOperation("返回所有巡线队信息")
    @PostMapping("seleAllPatrolInfo")
    public Result seleAllPatrolInfo(){
        List<PatrolTeam> patrolTeams = patrolTeamMapper.seleAllPatrolInfo();
        return ResultUtil.success(patrolTeams);
    }
//    @ApiOperation("更新管段")
//    @PostMapping("updateAPipleInfo")
//    public Result updateAPipleInfo(int startStakeId){
//        return ResultUtil.success();
//    }
//
//    @ApiOperation("删除管段")
//    @PostMapping("deletePipleInfo")
//    public Result deletePipleInfo (int startStakeId,int endStakeId){
//        return ResultUtil.success();
//    }
}
