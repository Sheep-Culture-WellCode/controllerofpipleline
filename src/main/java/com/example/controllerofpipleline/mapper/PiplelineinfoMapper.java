package com.example.controllerofpipleline.mapper;

import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.model.CDChangeInMysql;
import com.example.controllerofpipleline.model.ChangePipleInfo;
import com.example.controllerofpipleline.model.CompletePipleInfo;
import com.example.controllerofpipleline.model.StakeInfo;
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
    CompletePipleInfo SeleOnePipleInfoById(int id);
    List<StakeInfo> seleAllStakeInfo();
    int updataOnePiplelineInfo(ChangePipleInfo changePipleInfo);


}
