package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {


    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    void update(Dept dept);

    Dept queryDeptById(Integer id);
}
