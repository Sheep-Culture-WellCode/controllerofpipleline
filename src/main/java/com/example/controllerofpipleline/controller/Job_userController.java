package com.example.controllerofpipleline.controller;

import com.example.controllerofpipleline.Bean.Result;
import com.example.controllerofpipleline.domin.Job_user;
import com.example.controllerofpipleline.service.impl.Job_userServiceImpl;
import com.example.controllerofpipleline.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "test",tags = {"test"})
public class Job_userController {
    @Autowired
    Job_userServiceImpl userService;
    @ApiOperation(value = "用户登录", notes = "这是登录测试")
    @PostMapping("userLogin")
    public Result userLogin(Job_user user) {
        if(user.getId() == null || user.getPassword() == null) {
           return ResultUtil.error(1,"账号或密码为空");
        }
        Job_user ruser = userService.findJob_userByid(user.getId());
        if (ruser != null) {
            if (ruser.getPassword() == user.getPassword()) {
               return ResultUtil.success();
            } else {
               return ResultUtil.error(2,"密码错误");
            }
        } else {
           return ResultUtil.error(3,"账号错误");
        }
    }
    @ApiOperation(value = "修改用户")
    @ResponseBody
    @RequestMapping(value = "/updateJob_user",method = RequestMethod.POST)
    public Result update(Job_user user){
        int rs =userService.updateJob_user(user);
        if(rs>=1){
            return ResultUtil.success();
        }else{
            return ResultUtil.error();
        }
    }
    @ApiOperation(value = "注册用户")
    @ResponseBody
    @RequestMapping(value = "/addJob_user",method = RequestMethod.POST)
    public Result insert(Job_user user){
        int rs=userService.addJob_user(user);
        if(rs>=1){
            return ResultUtil.success();
        }else{
            return ResultUtil.error();
        }
    }
    @ApiOperation(value = "通过id查询用户")
    @ResponseBody
    @RequestMapping(value = "/findJob_userByid",method = RequestMethod.POST)
    public Job_user findJob_userByid(String id){
        return userService.findJob_userByid(id);
    }
}
