package com.example.controllerofpipleline.service;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.model.ChangeModule;
import com.example.controllerofpipleline.model.Evaluate;

import java.util.List;

/**
 * @author WellCode
 */
public interface IRiskEvaluateService {

    /**
     * 遍历所有树，并返回
     * @return
     */
    List<Evaluate> selectAllModule();

    /**
     * 删除一个模块，同时删除其下的所有模块
     * @param moduleId
     * @return
     */
    Result delateOneMoudle(int moduleId);

    /**
     * 查询一个模块及其子模块
     * @param moduleId
     * @return
     */
    List<Evaluate> selectOneModule(int moduleId);

    Result addOneModule(Evaluate evaluate);

    Result changeOneModule(ChangeModule changeModule);
}
