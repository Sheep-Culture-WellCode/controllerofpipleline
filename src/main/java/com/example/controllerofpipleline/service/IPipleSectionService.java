package com.example.controllerofpipleline.service;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.PiplelineSection;

import java.util.List;

public interface IPipleSectionService {

    /**
     * 根据起始桩号划分一个管道段
     * @param piplelineSection
     * @return
     */
    Result addPiplelineIntoSection(PiplelineSection piplelineSection);

    /**
     * 更新单条管段所有信息
     * @return
     */
    Result updateOnePipleSectionInfo(int stakeId);

    /**
     * 更新所有管道段的所有信息
     * @return
     */
    Result UpadatePipleSectionInfo();

    /**
     * 查询出所有管段信息
     * @return
     */
    List<PiplelineSection> selectAllPipleSection();


}
