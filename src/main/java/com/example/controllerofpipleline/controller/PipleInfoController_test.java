package com.example.controllerofpipleline.controller;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.mapper.PiplelineinfoMapper;
import com.example.controllerofpipleline.model.CDChange;
import com.example.controllerofpipleline.model.CDChangeInMysql;
import com.example.controllerofpipleline.service.impl.PipleService_test;
import com.example.controllerofpipleline.util.CoordinatesChangeUtil;
import com.example.controllerofpipleline.util.HttpUtil;
import com.example.controllerofpipleline.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Api(value = "管道接口(测试)",tags = {"管道接口（测试）"})
@RestController
@RequestMapping("PipleLinetest")
public class PipleInfoController_test {

    @Autowired
    PipleService_test pipleService;
    @Autowired
    PiplelineinfoMapper piplelineinfoMapper;

    @ApiOperation("数据库测试")
    @PostMapping("test")
    public Result test(PipleLineInfo pipleLineInfo){
        return pipleService.test(pipleLineInfo);
    }


    @ApiOperation("查询所有桩号及信息")
    @PostMapping("SeleAllPipleInfo")
    public List<PipleLineInfo> SeleAllPipleInfo(){
        return piplelineinfoMapper.SeleAllPipleInfo();
    }

    @ApiOperation("坐标转化测试")
    @PostMapping("test4")
    public CDChange test4(String x ,String y){
        return CoordinatesChangeUtil.changetest(x,y);
    }

    @ApiOperation("数据库坐标转化测试")
    @PostMapping("test5")
    public Result test5(){
        List<PipleLineInfo> pipleLineInfos = piplelineinfoMapper.SeleAllPipleInfo();
        CDChangeInMysql cdChangeInMysql = new CDChangeInMysql();
        CDChange cdChange = new CDChange();
        for (PipleLineInfo pipleLineInfo : pipleLineInfos){
            cdChange = CoordinatesChangeUtil.changetest(pipleLineInfo.getX(),pipleLineInfo.getY());
            cdChangeInMysql.setId(pipleLineInfo.getStakeId());
            cdChangeInMysql.setX(cdChange.getX());
            cdChangeInMysql.setY(cdChange.getY());
            int i = piplelineinfoMapper.test5(cdChangeInMysql);
            if (i!=1){
                return ResultUtil.error();
            }
        }
        return ResultUtil.success();
    }

    @ApiOperation("调用Api")
    @PostMapping("ApiTest")
    public String ApiTest(){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("address","北京市朝阳区阜通东大街6号");
        params.add("output","JSON");
        params.add("key","b32db3386641bd9d50166f6b40bd5f2e");
        HttpHeaders httpHeaders = new HttpHeaders();
        return HttpUtil.sendGet("https://restapi.amap.com/v3/geocode/geo","address=北京市朝阳区阜通东大街6号&output=JSON&key=b32db3386641bd9d50166f6b40bd5f2e");
    }
}
