package com.example.controllerofpipleline.mapper;

import com.example.controllerofpipleline.domin.PipleLineInfo;
import com.example.controllerofpipleline.domin.PiplelineSection;
import com.example.controllerofpipleline.model.ChangeSectionRisk;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WellCode
 */
@Repository
public interface PiplelineSectionMapper {

//    List<PipleLineInfo> seleAllPipleSection();


    /**
     * 添加一条管段信息
     * @param piplelineSection
     * @return int
     */
    int addPiplelineSection(PiplelineSection piplelineSection);

    /**
     * 查询出所有的管段信息，此管段具体风险等级
     * @return List<></>
     */
    List<PiplelineSection> seleAllSection();

    /**
     * 根据管段的段Id进行更新管段的风险情况
     * @param changeSectionRisk
     * @return
     */
    int updateSectionRisk(ChangeSectionRisk changeSectionRisk);

    /**
     * 根据桩号查询其属于的管段，以此判断更新此桩号是否合法
     * @param Stake
     * @return
     */
    PiplelineSection isStakeLegal(int Stake);



}
