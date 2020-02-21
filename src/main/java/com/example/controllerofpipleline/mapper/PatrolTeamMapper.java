package com.example.controllerofpipleline.mapper;

import com.example.controllerofpipleline.domin.PatrolTeam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatrolTeamMapper {
    List<PatrolTeam> seleAllPatrolInfo();
}
