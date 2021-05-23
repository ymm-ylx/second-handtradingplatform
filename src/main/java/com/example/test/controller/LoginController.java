package com.example.test.controller;

import com.example.test.bean.AdminBean;
import com.example.test.bean.MerchantBean;
import com.example.test.bean.UserBean;
import com.example.test.service.AdminService;
import com.example.test.service.MerchantService;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@ResponseBody
public class LoginController {


    //将Service注入Web层
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    MerchantService merchantService;


    @CrossOrigin
    @RequestMapping ("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserBean userBean = userService.loginIn(username,password);
        AdminBean adminBean = adminService.loginIn(username,password);
        MerchantBean merchantBean=merchantService.loginIn(username,password);
        if(userBean!=null){
            return "2";
        }else if(adminBean!=null){
            return "1";
        }else if(merchantBean!=null){
            return "3";
        }else {
            return "-1";
        }
    }

    @CrossOrigin
    @RequestMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value="merchantname",required = false) String merchantname,
                           @RequestParam(value="merchantpassword",required = false) String merchantpassword,
                           @RequestParam(value="merchantbankcard",required = false) String merchantbankcard,
                           @RequestParam(value="merchantrealname",required = false) String merchantrealname,
                           @RequestParam(value="merchantsex",required = false) String merchantsex,
                           @RequestParam(value="merchantphone",required = false) String merchantphone,
                           @RequestParam(value="businesslicense",required = false) MultipartFile[] businesslicense,
                           @RequestParam(value="identitycard",required = false) MultipartFile[] identitycard) throws Exception {
        request.setCharacterEncoding("utf-8");
        String type=request.getParameter("type");
        if(type.equals("user")){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String usersex = request.getParameter("usersex");
            String userphone = request.getParameter("userphone");
            String email = request.getParameter("email");
            String userbankcard = request.getParameter("userbankcard");
            String usercity = request.getParameter("usercity");
            UserBean userBean = userService.loginIn(username,password);
            AdminBean adminBean = adminService.loginIn(username,password);
            MerchantBean merchantBean=merchantService.loginIn(username,password);
            if(userBean!=null || adminBean!=null || merchantBean!=null){
                return "0";//用户名和密码已被占用
            }else {
                int resultc = userService.saveUser(username, password,name,usersex,userphone,email,userbankcard,usercity);
                return String.valueOf(resultc);
            }
        }else{
            if(businesslicense.length!=1)return "2";
            if(identitycard.length!=2)return "3";
            UUID randomUUID = UUID.randomUUID();
            businesslicense[0].transferTo(new File("D:\\secondhandplatformpics/"+ randomUUID));
            String businesslicensepath="D:\\secondhandplatformpics/"+ randomUUID;
            UUID randomUUID1 = UUID.randomUUID();
            UUID randomUUID2 = UUID.randomUUID();
            String fidentitycardpath=null;
            String zidentitycardpath=null;
            if (identitycard != null && identitycard.length == 2) {
                identitycard[0].transferTo(new File("D:\\secondhandplatformpics/"+ randomUUID1));
                zidentitycardpath="D:\\secondhandplatformpics/"+ randomUUID1;
                identitycard[1].transferTo(new File("D:\\secondhandplatformpics/"+ randomUUID2));
                fidentitycardpath="D:\\secondhandplatformpics/"+ randomUUID2;
            }
            else{
                zidentitycardpath=null;
                fidentitycardpath=null;
            }
            UserBean userBean = userService.loginIn(merchantname,merchantpassword);
            AdminBean adminBean = adminService.loginIn(merchantname,merchantpassword);
            MerchantBean merchantBean = merchantService.loginIn(merchantname,merchantpassword);
            if(userBean!=null || adminBean!=null || merchantBean!=null){
                return "0";//商家名和密码已被占用
            }else {
                int merchantresultc = merchantService.saveMerchant(merchantname, merchantpassword,merchantbankcard,merchantrealname,merchantsex,merchantphone);
                int merchantresultc1 = merchantService.saveMerchantpicpath(merchantname,businesslicensepath,zidentitycardpath,fidentitycardpath);
                return "1";
                //返回2：没有营业执照 3：身份证传两张

            }
        }
    }





}

