package com.example.controllerofpipleline.mapper;

import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.model.CDChangeInMysql;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author WellCode
 */
@Repository
public interface PiplelineinfoMapper {
    int test(PipleLineInfo pipleLineInfo);
    List<PipleLineInfo> SeleAllPipleInfo();
    int test5(CDChangeInMysql cdChangeInMysql);
    PipleLineInfo SeleOnePipleInfoById(int id);
}