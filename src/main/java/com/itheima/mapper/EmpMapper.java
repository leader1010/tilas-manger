package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    @Select("select * from emp limit #{pageSize} offset #{offest};")
    List<Emp> page(Integer offest, Integer pageSize);

    //获取总记录数
    @Select("select count(*) from emp")
    public Long count();

    @Select("select * from emp where username=#{username} and password=#{password};")
    public Emp getByUsernameAndPassword(Emp emp);
}
