package com.example.test.service;

import com.example.test.bean.AdminBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminService {
//    AdminBean loginIn(String name, String password);
    @Select("SELECT * FROM admin WHERE adminname = #{adminname} AND adminpassword = #{adminpassword}")
    AdminBean loginIn(@Param("adminname")String adminname, @Param("adminpassword")String adminpassword);

    @Update("update admin set adminbalance=#{adminbalance} where adminname='admin'")
    int addbalance(@Param("adminbalance")String adminbalance);
}
