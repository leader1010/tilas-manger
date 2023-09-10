package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    public Result getDepts(){

        List<Dept> list = deptService.list();
        return Result.success(list);
    }

    @DeleteMapping("/depts/{id}")
    public Result deleteDepts(@PathVariable Integer id){

        deptService.delete(id);
        return Result.success();
    }

    @PostMapping ("/depts")
    public Result deleteDepts(@RequestBody Dept dept){

        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result getDepts(@PathVariable Integer id){

        Dept dept = deptService.queryDeptById(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result updateDepts(@RequestBody Dept dept){
        deptService.update(dept);
        return Result.success(dept);
    }


}
