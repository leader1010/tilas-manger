package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        List<Emp> list = empMapper.list(name, gender, begin, end, page, pageSize);

        return list;
    }

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        //1、获取总记录数
        Long count = empMapper.count();

        Integer offest = (page - 1) * pageSize;
        List<Emp> list = empMapper.page(offest, pageSize);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(count);
        pageBean.setRows(list);
        return pageBean;
    }

    @Override
    public Emp login(Emp emp) {

        return empMapper.getByUsernameAndPassword(emp);
    }


}
