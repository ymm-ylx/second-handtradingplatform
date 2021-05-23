package com.example.test.controller;

import com.example.test.bean.*;
import com.example.test.service.AdminService;
import com.example.test.service.GoodsService;
import com.example.test.service.MerchantService;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class AdminController {

    public String businesslicensepath;
    public String zidentitycardpath;
    public String fidentitycardpath;
    public int rename;

    //将Service注入Web层
    @Autowired
    UserService userService;
    @Autowired
    MerchantService merchantService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    AdminService adminService;

    //用户审核
    @CrossOrigin
    @RequestMapping("/userverify")
    public List<UserBean> findall(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<UserBean> users = userService.findAll();
        return users;
    }
    @CrossOrigin
    @RequestMapping("/userverify/agree")
    public String agree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userid = request.getParameter("userid");
        int resultc = userService.agreeUser(userid);
        List<UserBean> usersagree = userService.findAll();
        if(resultc!=0){
            return "ok";}
        else{
            return "no";
        }
    }
    @CrossOrigin
    @RequestMapping("/userverify/disagree")
    public String disagree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userid = request.getParameter("userid");
        int resultc = userService.disagreeUser(userid);
        List<UserBean> usersdisagree = userService.findAll();
//        m.addAttribute("user",users);
        if(resultc!=0){
            return "ok";}
        else{
            return null;
        }
    }

    @CrossOrigin
    @RequestMapping("/merchantverify")
    public List<MerchantBean> findallmerchant(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<MerchantBean> merchants = merchantService.findAll();
        return merchants;
    }

    @CrossOrigin
    @RequestMapping("/merchantverify/agree")
    public String merchantagree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantid = request.getParameter("merchantid");
        int resultc = merchantService.agreeMerchant(merchantid);
        List<MerchantBean> merchantsagree = merchantService.findAll();
        if(resultc!=0){
            return "ok";}
        else{
            return "no";
        }
    }
    @CrossOrigin
    @RequestMapping("/merchantverify/disagree")
    public String merchantdisagree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantid = request.getParameter("merchantid");
        int resultc = merchantService.disagreeMerchant(merchantid);
        List<MerchantBean> mrechantsdisagree = merchantService.findAll();
        if(resultc!=0){
            return "ok";}
        else{
            return null;
        }
    }

    @CrossOrigin
    @RequestMapping("/merchantverify/getpics")
    public String[] getpics(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname = request.getParameter("merchantname");
        //根据商家名找到图片存储路径
        MerchantBean merchant = merchantService.findpathbymerchantname(merchantname);
        String businesslicense=merchant.getBusinesslicensepath();
        businesslicense=businesslicense.replace( "/",    "\\");
        this.businesslicensepath=businesslicense.replaceAll( "\\\\",    "\\\\\\\\");
        String zidentitycardpath=merchant.getZidentitycardpath();
        zidentitycardpath=zidentitycardpath.replace( "/",    "\\");
        this.zidentitycardpath=zidentitycardpath.replaceAll( "\\\\",    "\\\\\\\\");
        String fidentitycardpath=merchant.getFidentitycardpath();
        fidentitycardpath=fidentitycardpath.replace( "/",    "\\");
        this.fidentitycardpath=fidentitycardpath.replaceAll( "\\\\",    "\\\\\\\\");
        this.rename+=1;
        String addurl=String.valueOf(rename);
        String[] picsurl={"http://127.0.0.1:8888/businesslicense/"+addurl,"http://127.0.0.1:8888/zidentitycard/"+addurl,
                            "http://127.0.0.1:8888/fidentitycard/"+addurl};
        return picsurl;
    }

    @GetMapping (value = "/businesslicense/*",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] pushbusinesslicense() throws Exception {
        File file = new File(this.businesslicensepath);
        FileInputStream inputstream = new FileInputStream(file);
        byte[] bytes = new byte[inputstream.available()];
        inputstream.read(bytes, 0, inputstream.available());
        return bytes;
    }

    @GetMapping (value = "/zidentitycard/*",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] pushzidentitycardpath() throws Exception {
        File file = new File(this.zidentitycardpath);
        FileInputStream inputstream = new FileInputStream(file);
        byte[] bytes = new byte[inputstream.available()];
        inputstream.read(bytes, 0, inputstream.available());
        return bytes;
    }

    @GetMapping (value = "/fidentitycard/*",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] pushfidentitycardpath() throws Exception {
        File file = new File(this.fidentitycardpath);
        FileInputStream inputstream = new FileInputStream(file);
        byte[] bytes = new byte[inputstream.available()];
        inputstream.read(bytes, 0, inputstream.available());
        return bytes;
    }

    @CrossOrigin
    @RequestMapping("/usermanagement")
    public List<UserBean> findallusered(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<UserBean> users = userService.findallusered();
        return users;
    }

    @CrossOrigin
    @RequestMapping("/usermanagement/deleteuser")
    public String deleteuser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userid = request.getParameter("userid");
        int resultc = userService.disagreeUser(userid);
        List<UserBean> usersdisagree = userService.findAll();
//        m.addAttribute("user",users);
        if(resultc!=0){
            return "ok";}
        else{
            return "error";
        }
    }

    @CrossOrigin
    @RequestMapping("/usermanagement/updateuser")
    public String updateuser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userid = request.getParameter("userid");
        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String usersex = request.getParameter("usersex");
        String userphone = request.getParameter("userphone");
        String email = request.getParameter("email");
        String userbankcard = request.getParameter("userbankcard");
        String usercity = request.getParameter("usercity");
//        int userbalance = Integer.parseInt(request.getParameter("userbalance"));
//        int userpoints = Integer.parseInt(request.getParameter("userpoints"));
        int resultc = userService.updateUser(userid,username,name,usersex,userphone,email,userbankcard,usercity);
        List<UserBean> usersdisagree = userService.findAll();
//        m.addAttribute("user",users);
        if(resultc!=0){
            return "ok";}
        else{
            return "error";
        }
    }

    @CrossOrigin
    @RequestMapping("/usermanagement/addbalance")
    public String addbalance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String userid = request.getParameter("userid");
        double userbalance1 = Double.parseDouble(request.getParameter("userbalance"));
        double userbalance2=Double.parseDouble(userService.getuserbalance(userid));
        String userbalance=String.valueOf(userbalance1+userbalance2);
        BigDecimal bd = new BigDecimal(userbalance);
        userbalance = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
        int resultc = userService.addbalance(userid,userbalance);
        List<UserBean> usersdisagree = userService.findAll();
        if(resultc!=0){
            return "ok";}
        else{
            return "error";
        }
    }

    @CrossOrigin
    @RequestMapping("/merchantmanagement")
    public List<MerchantBean> findallmerchanted(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<MerchantBean> merchants = merchantService.findallmerchanted();
        for(int i=0;i<merchants.size();i++){
            int num=0;
            Double sum=0.0;
            MerchantBean merchantBean=merchants.get(i);
            String merchantid=String.valueOf(merchantBean.getMerchantid());
            StoreBean storeBean=merchantService.findstorebymerchantid(merchantid);
            if(storeBean==null){
                merchantBean.setStorename("暂无商铺");//加上商铺名字
                merchantBean.setTradenum("未交易");//加上交易数量
                merchantBean.setTradecountsum("未交易");//加上总金额
            }else {
                String storename = storeBean.getStorename();
                merchantBean.setStorename(storename);//加上商铺名字
                List<GoodsBean> goodsBeans = goodsService.checkGoodsbuymerchantid(merchantid);
                for (int j = 0; j < goodsBeans.size(); j++) {
                    GoodsBean goodsBean = goodsBeans.get(j);
                    String goodsid = String.valueOf(goodsBean.getGoodsid());
                    Double price = Double.parseDouble(goodsBean.getGoodsoriginalprice()) * (Double.parseDouble(goodsBean.getGoodsdiscount()) / 10);
//                    System.out.print("现价"+price+"\n");
                    List<BuyRelation> buyRelations = goodsService.findfinishbygoodsid(goodsid);
                    num += buyRelations.size();
                    for (int k = 0; k < buyRelations.size(); k++) {
                        BuyRelation buyRelation = buyRelations.get(k);
                        String buynum = buyRelation.getBuynum();
//                        System.out.print("数量"+buynum+"\n");
                        sum += Double.parseDouble(buynum) * price;
//                        System.out.print("总额"+sum+"\n");
                    }

                }
                merchantBean.setTradenum(String.valueOf(num));//加上交易数量
                String str = String.valueOf(sum);
                BigDecimal bd = new BigDecimal(str);
                str = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                merchantBean.setTradecountsum(str);//加上总金额
            }

        }

        return merchants;
    }

    @CrossOrigin
    @RequestMapping("/merchantmanagement/deletemerchant")
    public String deletemerchant(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantid = request.getParameter("merchantid");
        int resultc = merchantService.disagreeMerchant(merchantid);
        List<MerchantBean> mrechantsdisagree = merchantService.findAll();
        if(resultc!=0){
            return "ok";}
        else{
            return "error";
        }
    }

    @CrossOrigin
    @RequestMapping("/merchantmanagement/getpics")
    public String[] getpictures(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname = request.getParameter("merchantname");
        //根据商家名找到图片存储路径
        MerchantBean merchant = merchantService.findpathbymerchantname(merchantname);
        String businesslicense=merchant.getBusinesslicensepath();
        businesslicense=businesslicense.replace( "/",    "\\");
        this.businesslicensepath=businesslicense.replaceAll( "\\\\",    "\\\\\\\\");
        String zidentitycardpath=merchant.getZidentitycardpath();
        zidentitycardpath=zidentitycardpath.replace( "/",    "\\");
        this.zidentitycardpath=zidentitycardpath.replaceAll( "\\\\",    "\\\\\\\\");
        String fidentitycardpath=merchant.getFidentitycardpath();
        fidentitycardpath=fidentitycardpath.replace( "/",    "\\");
        this.fidentitycardpath=fidentitycardpath.replaceAll( "\\\\",    "\\\\\\\\");
        this.rename+=1;
        String addurl=String.valueOf(rename);
        String[] picsurl={"http://127.0.0.1:8888/businesslicense/"+addurl,"http://127.0.0.1:8888/zidentitycard/"+addurl,
                "http://127.0.0.1:8888/fidentitycard/"+addurl};
        return picsurl;
    }

    @CrossOrigin
    @RequestMapping("/merchantmanagement/updatemerchant")
    public String updatemerchant(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value="merchantid",required = false) String merchantid,
                                 @RequestParam(value="merchantname",required = false) String merchantname,
                                 @RequestParam(value="merchantbankcard",required = false) String merchantbankcard,
                                 @RequestParam(value="merchantrealname",required = false) String merchantrealname,
                                 @RequestParam(value="merchantsex",required = false) String merchantsex,
                                 @RequestParam(value="merchantphone",required = false) String merchantphone,
                                 @RequestParam(value="licensechange",required = false) String licensechange,
                                 @RequestParam(value="cardchange",required = false) String cardchange,
                                 @RequestParam(value="businesslicense",required = false) MultipartFile businesslicense,
                                 @RequestParam(value="identitycard",required = false) MultipartFile[] identitycard) throws Exception {
        if(licensechange.equals("true")){
            if(businesslicense.isEmpty())return "2";
            UUID randomUUID = UUID.randomUUID();
            businesslicense.transferTo(new File("D:\\secondhandplatformpics/"+ randomUUID));
            this.businesslicensepath="D:\\secondhandplatformpics/"+ randomUUID;
        }
        if(cardchange.equals("true")){
            if(identitycard.length!=2)return "3";
            String fidentitycardpath=null;
            String zidentitycardpath=null;
            if (identitycard != null && identitycard.length == 2) {
                UUID randomUUID1 = UUID.randomUUID();
                UUID randomUUID2 = UUID.randomUUID();
                identitycard[0].transferTo(new File("D:\\secondhandplatformpics/"+ randomUUID1));
                this.zidentitycardpath="D:\\secondhandplatformpics/"+ randomUUID1;
                identitycard[1].transferTo(new File("D:\\secondhandplatformpics/"+ randomUUID2));
                this.fidentitycardpath="D:\\secondhandplatformpics/"+ randomUUID2;
            }
            else{
                zidentitycardpath=null;
                fidentitycardpath=null;
            }
        }
            int merchantresultc = merchantService.updateMerchant(merchantid,merchantname,merchantbankcard,merchantrealname,merchantsex,merchantphone);
        if(licensechange.equals("true")){
            int merchantresultc1 = merchantService.saveMerchantbusinesspath(merchantname,businesslicensepath);}
        if(cardchange.equals("true")){
            int merchantresultc1 = merchantService.saveMerchantidcardpath(merchantname,zidentitycardpath,fidentitycardpath);
        }
            return "1";
            //返回2：没有营业执照 3：身份证传两张
    }


    @CrossOrigin
    @RequestMapping("/merchantmanagement/editmerchantrank")
    public String editmerchantrank(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantid = request.getParameter("merchantid");
        String merchantrank = request.getParameter("merchantrank");
        int resultc = merchantService.editMerchantrank(merchantid,merchantrank);
        if(resultc!=0){
            return "ok";}
        else{
            return "error";
        }
    }

    @CrossOrigin
    @RequestMapping("/goodsverify")
    public List<GoodsBean> findallgoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<GoodsBean> goodss = goodsService.findAll();
        for(int i=0;i<goodss.size();i++){
            GoodsBean goodsBean =goodss.get(i);
            String goodsid=String.valueOf(goodsBean.getGoodsid());
            String storeid=goodsBean.getStoreid();
            String merchantid=goodsBean.getMerchantid();
            String storename= merchantService.findstorebymerchantid(merchantid).getStorename();
            String merchantname=merchantService.findbymerchantid(merchantid).getMerchantname();
            String goodspic1path=goodsBean.getGoodspic1path();
            goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
            goodsBean.setGoodspic1path("http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path);
            String goodspic2path=goodsBean.getGoodspic2path();
            if(goodspic2path!=null) {
                goodspic2path = goodspic2path.replaceAll("\\\\", "/");
                goodsBean.setGoodspic2path("http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic2path);
            }
            String goodspic3path=goodsBean.getGoodspic3path();
            if(goodspic3path!=null) {
                goodspic3path = goodspic3path.replaceAll("\\\\", "/");
                goodsBean.setGoodspic3path("http://127.0.0.1:8888/merchantshop/getgoodpics?storename=" + goodspic3path);
            }
            goodsBean.setStorename(storename);
            goodsBean.setMerchantname(merchantname);
            goodss.set(i,goodsBean);
        }
        return goodss;
    }

    @CrossOrigin
    @RequestMapping("/goodsverify/agree")
    public String goodsagree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String goodsid = request.getParameter("goodsid");
        int resultc = goodsService.agreeGoods(goodsid);
        if(resultc!=0){
            return "ok";}
        else{
            return "no";
        }
    }

    @CrossOrigin
    @RequestMapping("/goodsverify/disagree")
    public String goodsdisagree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String goodsid = request.getParameter("goodsid");
        int resultc = goodsService.disagreeGoods(goodsid);
        if(resultc!=0){
            return "ok";}
        else{
            return null;
        }
    }

    @CrossOrigin
    @RequestMapping("/goodsverify/checkgoods")
    public GoodsBean goodschaeck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String goodsid = request.getParameter("goodsid");
        GoodsBean goodsBean= goodsService.checkGoods(goodsid);
        String goodspic1path=goodsBean.getGoodspic1path();
        goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
        goodsBean.setGoodspic1path("http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path);
        String goodspic2path=goodsBean.getGoodspic2path();
        if(goodspic2path!=null) {
            goodspic2path = goodspic2path.replaceAll("\\\\", "/");
            goodsBean.setGoodspic2path("http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic2path);
        }
        String goodspic3path=goodsBean.getGoodspic3path();
        if(goodspic3path!=null) {
            goodspic3path = goodspic3path.replaceAll("\\\\", "/");
            goodsBean.setGoodspic3path("http://127.0.0.1:8888/merchantshop/getgoodpics?storename=" + goodspic3path);
        }
        return goodsBean;
    }

    //查看钱包 使用merchantname进行查询
    @CrossOrigin
    @RequestMapping("/checkadminwallet")
    public AdminBean checkmerchantwallet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminBean adminBean=adminService.loginIn("admin","admin");
        return adminBean;
    }




}
