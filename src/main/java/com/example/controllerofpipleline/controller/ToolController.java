package com.example.controllerofpipleline.controller;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineRisk;
import com.example.controllerofpipleline.mapper.PiplelineinfoMapper;
import com.example.controllerofpipleline.service.IPipleService;
import com.example.controllerofpipleline.service.impl.PipleService_test;
import com.example.controllerofpipleline.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Api(value = "工具管理接口",tags = {"工具管理"})
@RestController
@RequestMapping("tool")
public class ToolController {
    @Autowired
    PipleService_test pipleService_test;
    @Autowired
    PiplelineinfoMapper piplelineinfoMapper;
    @Autowired
    IPipleService pipleService;

    @ApiOperation("桩号信息Excel导入测试")
    @PostMapping("pipleInfoExcelInto")
    public Result pipleInfoExcelInto(MultipartFile file, HttpServletRequest request){
        try{
            List<PipleLineInfo> pipleLineInfos = new ArrayList<PipleLineInfo>();
            System.out.println("开始");
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet hssfSheet = workbook.getSheetAt(0);
            for (Row row : hssfSheet){
                PipleLineInfo pipleLineInfo = new PipleLineInfo();
                int rowNum = row.getRowNum();
                if (rowNum == 0){
                    continue;
                }
                if (row.getCell(1)!=null){
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    pipleLineInfo.setStakeName(row.getCell(1).getStringCellValue());
                }
                if (row.getCell(2)!=null){
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    pipleLineInfo.setX(row.getCell(2).getStringCellValue());
                }
                if(row.getCell(3)!=null){
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    pipleLineInfo.setY(row.getCell(3).getStringCellValue());
                }
                if(row.getCell(4)!=null){
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    pipleLineInfo.setPiplelineName(row.getCell(4).getStringCellValue());
                }
                if(row.getCell(5)!=null){
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    pipleLineInfo.setPatrolId(row.getCell(5).getStringCellValue());
                }
                if(row.getCell(6)!=null){
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    pipleLineInfo.setLayMode(row.getCell(6).getStringCellValue());
                }
                if(row.getCell(7)!=null){
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    pipleLineInfo.setCollectData(row.getCell(7).getStringCellValue());
                }
                if(row.getCell(8)!=null){
                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                    pipleLineInfo.setAreaLevel(row.getCell(8).getStringCellValue());
                }
                pipleLineInfos.add(pipleLineInfo);
            }
            return pipleService_test.saveExcelList(pipleLineInfos);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error();
        }
    }

//    @ApiOperation("风险信息Excel导入")
//    @PostMapping("pipleRiskExcelInto")
//    public Result pipleRiskExcelInto(MultipartFile file, HttpServletRequest request){
//        try{
//            List<PiplelineRisk> pipleLineInfos = new ArrayList<PiplelineRisk>();
//            System.out.println("开始");
//            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
//            XSSFSheet hssfSheet = workbook.getSheetAt(0);
//            for (Row row : hssfSheet){
//                PiplelineRisk pipleLineInfo = new PiplelineRisk();
//                int rowNum = row.getRowNum();
//                if (rowNum == 0){
//                    continue;
//                }
//                if (row.getCell(1)!=null){
//                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
//                    pipleLineInfo.setStartStackId(row.getCell(1).getStringCellValue());
//                }
//                if (row.getCell(2)!=null){
//                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
//                    pipleLineInfo.setRiskLevel(row.getCell(2).getStringCellValue());
//                }
//                if(row.getCell(3)!=null){
//                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
//                    pipleLineInfo.setRiskTime(row.getCell(3).getDateCellValue());
//                }
//                if(row.getCell(4)!=null){
//                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
//                    pipleLineInfo.setDistanceStack(row.getCell(4).getStringCellValue());
//                }
//
//                pipleLineInfos.add(pipleLineInfo);
//            }
//            return pipleService.saveRiskExcelList(pipleLineInfos);
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResultUtil.error();
//        }
//    }

    @ApiOperation("为每个桩号添加风险信息")
    @PostMapping("AddPipleRisk")
    public Result AddPipleRisk(int startStakeId , int endStakeId){
        PiplelineRisk piplelineRisk = new PiplelineRisk();
        for (int i =startStakeId;i<=endStakeId;i++){
            piplelineRisk.setStartStackId(i);
            piplelineRisk.setRiskLevel(1);
            piplelineRisk.setDistanceStack(20);
            Result result = pipleService.addRiskForPipleLine(piplelineRisk);
            if (result.getCode()==601){
                return ResultUtil.error();
            }
        }
        return ResultUtil.success();
    }
}
