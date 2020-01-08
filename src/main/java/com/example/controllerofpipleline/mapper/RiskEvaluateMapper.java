package com.example.controllerofpipleline.mapper;
import com.example.controllerofpipleline.model.ChangeModule;
import com.example.controllerofpipleline.model.Evaluate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author WellCode
 */
@Repository
public interface RiskEvaluateMapper {
    /**
     * 查询一个父节点其下的下一级所有子节点
     * @param parentId 父节点Id
     * @return
     */
    List<Evaluate> selectModuleList(int parentId);

    /**
     * 查询单个模块根据主键moudleId
     * @param moduleId
     * 为了方便算法getTree或者delateTree@return List<Evaluate>
     */
    List<Evaluate> selectOneModule(int moduleId);

    /**
     * 根据主键删除一个模块
     * @param moduleId
     * @return
     */
    int delateOneModule(int moduleId);

    int addOneModule(Evaluate evaluate);

    int changeOneModule(ChangeModule changeModule);
}
