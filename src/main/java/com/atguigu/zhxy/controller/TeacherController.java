package com.atguigu.zhxy.controller;

import com.atguigu.zhxy.pojo.Admin;
import com.atguigu.zhxy.pojo.Teacher;
import com.atguigu.zhxy.service.TeacherService;
import com.atguigu.zhxy.util.MD5;
import com.atguigu.zhxy.util.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "教师控制器")
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * GET  sms/teacherController/getTeachers/1/3
     *  请求数据
     *  响应Result data = 分页
     */
    @ApiOperation("分页获取教师信息，带搜索条件")
    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(
            @ApiParam("页码数") @PathVariable Integer pageNo,
            @ApiParam("页大小") @PathVariable Integer pageSize,
            @ApiParam("查询条件") Teacher teacher
    ){
        Page<Teacher> pageParam = new Page<>(pageNo, pageSize);
        IPage<Teacher> page =  teacherService.getTeachersByOpr(pageParam,teacher);
        return Result.ok(page);
    }

    /**
     * Post sms/teacherController/saveOrUpdateTeacher
     *      请求数据 Teacher
     *      响应Result data= null OK
     * */
    @ApiOperation("新增或修改grade，有id属性是修改，没有则是增加")
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(
            @ApiParam("JSON格式的Teacher对象") @RequestBody Teacher teacher
    ){
        Integer id = teacher.getId();
        if (id == null || 0 == id){
            teacher.setPassword(MD5.encrypt(teacher.getPassword()));
        }
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }

    /**
     * DELETE sms/teacherController/deleteTeacher
     *   请求的数据 JSON 数组 [1,2,3]
     *   响应Result data =null  OK
     *
     * */
    @ApiOperation("删除单个或者多个老师信息")
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(
            @ApiParam("要删除的老师的多个ID的JSON集合") @RequestBody List<Integer> ids
    ){
        teacherService.removeByIds(ids);
        return Result.ok();
    }
}
