package com.example.test.serviceImpl;

import com.example.test.bean.AdminBean;
import com.example.test.mapper.AdminMapper;
import com.example.test.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired(required=false)
    private AdminMapper adminMapper;

    @Override
    public AdminBean loginIn(String adminname, String adminpassword) {
        return adminMapper.loginIn(adminname,adminpassword);
    }

    @Override
    public int addbalance(String adminbalance) {
        return adminMapper.addbalance(adminbalance);
    }
}
