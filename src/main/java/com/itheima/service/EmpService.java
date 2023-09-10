package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    PageBean page(Integer page, Integer pageSize);

    Emp login(Emp emp);
}
