package com.example.test.controller;

import com.example.test.bean.*;
import com.example.test.service.AdminService;
import com.example.test.service.GoodsService;
import com.example.test.service.MerchantService;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    public String goodspic1path;
    public String goodspic2path;
    public String goodspic3path;
    public int rename;
    public List<Shoppingcart> shoppingcarts=new ArrayList<>();
    public List<BuyRelation> buyRelations=new ArrayList<>();
    public TradeBean tradeBean=new TradeBean();

    //将Service注入Web层
    @Autowired
    UserService userService;
    @Autowired
    MerchantService merchantService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    private CustomTaskScheduler scheduler;
    @Autowired
    AdminService adminService;



    //返回电子类商品
    @CrossOrigin
    @RequestMapping("/getelectronicgoods")//待更改的url
    public List<GoodsBean> getelectronicgoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<GoodsBean> goodss = goodsService.getelectronicgoods();
        for(int i=0;i<goodss.size();i++){
            GoodsBean goodsBean=goodss.get(i);
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
        }
        return goodss;
    }

    //返回文具类商品
    @CrossOrigin
    @RequestMapping("/getstationerygoods")//待更改的url
    public List<GoodsBean> getstationerygoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<GoodsBean> goodss = goodsService.getstationerygoods();
        for(int i=0;i<goodss.size();i++){
            GoodsBean goodsBean=goodss.get(i);
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
        }
        return goodss;
    }

    //返回书籍类商品
    @CrossOrigin
    @RequestMapping("/getbookgoods")//待更改的url
    public List<GoodsBean> getbookgoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<GoodsBean> goodss = goodsService.getbookgoods();
        for(int i=0;i<goodss.size();i++){
            GoodsBean goodsBean=goodss.get(i);
            String goodspic1path=goodsBean.getGoodspic1path();
            goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
            goodsBean.setGoodspic1path("http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path);
            String goodspic2path=goodsBean.getGoodspic2path();
            if(goodspic2path !=null) {
                goodspic2path = goodspic2path.replaceAll("\\\\", "/");
                goodsBean.setGoodspic2path("http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic2path);
            }
            String goodspic3path=goodsBean.getGoodspic3path();
            if(goodspic3path!=null) {
                goodspic3path = goodspic3path.replaceAll("\\\\", "/");
                goodsBean.setGoodspic3path("http://127.0.0.1:8888/merchantshop/getgoodpics?storename=" + goodspic3path);
            }
        }
        return goodss;
    }

    //返回生活用品商品
    @CrossOrigin
    @RequestMapping("/getlivelihoodgoods")//待更改的url
    public List<GoodsBean> getlivelihoodgoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<GoodsBean> goodss = goodsService.getlivelihoodgoods();
        for(int i=0;i<goodss.size();i++){
            GoodsBean goodsBean=goodss.get(i);
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
        }
        return goodss;
    }

    //返回零食商品
    @CrossOrigin
    @RequestMapping("/getsnackgoods")//待更改的url
    public List<GoodsBean> getsnackgoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<GoodsBean> goodss = goodsService.getsnackgoods();
        for(int i=0;i<goodss.size();i++){
            GoodsBean goodsBean=goodss.get(i);
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
        }
        return goodss;
    }

    //模糊查找，返回商品列表
    @CrossOrigin
    @RequestMapping("/findgoodsbywords")//待更改的url
    public List<GoodsBean> findgoodsbywords(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String words = request.getParameter("words");
        List<GoodsBean> goodss = goodsService.findgoodsbywords(words);
        for(int i=0;i<goodss.size();i++){
            GoodsBean goodsBean=goodss.get(i);
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
        }
        String sorttype=request.getParameter("sorttype");
        if(sorttype.equals("goodsgoodrate")){
            goodss.sort((((o1, o2) -> Integer.parseInt(o2.getGoodsgoodrate())-Integer.parseInt(o1.getGoodsgoodrate()))));
            return goodss;
        }
//        else if(sorttype.equals("goodsoriginalprice")){
////            goodss.sort((((o1, o2) -> (int)(Double.parseDouble(o2.getGoodsoriginalprice())*(Double.parseDouble(o2.getGoodsdiscount())/10)*100)
////                    -(int)(Double.parseDouble(o1.getGoodsoriginalprice())*(Double.parseDouble(o1.getGoodsdiscount())/10)*100))));
////            return goodss;
////        }
        else if(sorttype.equals("goodsoriginalprice")){
            goodss.sort((((o1, o2) -> (int)(Double.parseDouble(o2.getGoodsoriginalprice())
                    -(int)(Double.parseDouble(o1.getGoodsoriginalprice()))))));
            return goodss;
        }
        else if(sorttype.equals("goodssales")){
            goodss.sort((((o1, o2) -> Integer.parseInt(o2.getGoodssales())-Integer.parseInt(o1.getGoodssales()))));
            return goodss;
        }
        else{
            return goodss;
        }
    }

    //获得商品详细信息
    @CrossOrigin
    @RequestMapping("/goodsinformation")
    public GoodsBean getgoodsinformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

    @CrossOrigin
    @RequestMapping("/getgoodspics")
    public String[] getgoodspics(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String goodsid = request.getParameter("goodsid");
        GoodsBean goodsBean= goodsService.checkGoods(goodsid);
        String goodspic1path=goodsBean.getGoodspic1path();
        goodspic1path=goodspic1path.replace( "/",    "\\");
        this.goodspic1path=goodspic1path.replaceAll( "\\\\",    "\\\\\\\\");
        String goodspic2path=goodsBean.getGoodspic2path();
        if(goodspic2path!=null) {
            goodspic2path = goodspic2path.replace("/", "\\");
            this.goodspic2path = goodspic2path.replaceAll("\\\\", "\\\\\\\\");
        }
        String goodspic3path=goodsBean.getGoodspic3path();
        if(goodspic3path!=null) {
            goodspic3path = goodspic3path.replace("/", "\\");
            this.goodspic3path = goodspic3path.replaceAll("\\\\", "\\\\\\\\");
        }
        this.rename+=1;
        String addurl=String.valueOf(rename);
        String[] picsurl={"http://127.0.0.1:8888/goodspic1path/"+addurl,"http://127.0.0.1:8888/goodspic2path/"+addurl,
                "http://127.0.0.1:8888/goodspic3path/"+addurl};
        return picsurl;
    }

    @GetMapping(value = "/goodspic1path/*",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] pushbusinesslicense() throws Exception {
        File file = new File(this.goodspic1path);
        FileInputStream inputstream = new FileInputStream(file);
        byte[] bytes = new byte[inputstream.available()];
        inputstream.read(bytes, 0, inputstream.available());
        return bytes;
    }

    @GetMapping (value = "/goodspic2path/*",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] pushzidentitycardpath() throws Exception {
        File file = new File(this.goodspic2path);
        FileInputStream inputstream = new FileInputStream(file);
        byte[] bytes = new byte[inputstream.available()];
        inputstream.read(bytes, 0, inputstream.available());
        return bytes;
    }

    @GetMapping (value = "/goodspic3path/*",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] pushfidentitycardpath() throws Exception {
        File file = new File(this.goodspic3path);
        FileInputStream inputstream = new FileInputStream(file);
        byte[] bytes = new byte[inputstream.available()];
        inputstream.read(bytes, 0, inputstream.available());
        return bytes;
    }

    //进入商铺 通过goodsid
    @CrossOrigin
    @RequestMapping("/getstoreinformation")
    public Storeandmerchant getstoreinformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String goodsid = request.getParameter("goodsid");
        GoodsBean goodsBean= goodsService.checkGoods(goodsid);
        String storeid =goodsBean.getStoreid();
        StoreBean storeBean=merchantService.findpathbystoreid(storeid);
        String merchantid=String.valueOf(storeBean.getMerchantid());
        MerchantBean merchantBean=merchantService.findbymerchantid(merchantid);
        String merchantrank=merchantBean.getMerchantrank();
        String merchantgoodrate=merchantBean.getMerchantgoodrate();
        String storename=storeBean.getStorename();
        Storeandmerchant storeandmerchant=new Storeandmerchant();
        storeandmerchant.setMerchantrank(merchantrank);
        storeandmerchant.setStorename(storename);
        storeandmerchant.setMerchantgoodrate(merchantgoodrate);
        return storeandmerchant;
    }//下一步获得该商家的商品，在merchantcontroller中

    //获取商品详细页面的评价
    @CrossOrigin
    @RequestMapping("/getevaluate")
    public List<EvaluateBean> getevaluate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String goodsid = request.getParameter("goodsid");
        List<BuyRelation> buyRelations=userService.getallbygoodsid(goodsid);
        List<EvaluateBean> evaluateBeans = new ArrayList<EvaluateBean>();;
        for(int i=0;i<buyRelations.size();i++){
            String buyrelationid=buyRelations.get(i).getBuyrelationid();
            String userid=buyRelations.get(i).getUserid();
            String username=userService.finduserbyuserid(userid).getUsername();
            if(userService.findevaluatebybuyrelationid(buyrelationid).size()!=0){
                for(int j=0;j<userService.findevaluatebybuyrelationid(buyrelationid).size();j++){
                    EvaluateBean evaluateBean=userService.findevaluatebybuyrelationid(buyrelationid).get(j);
                    evaluateBean.setUsername(username);
                    evaluateBeans.add(evaluateBean);
                }
            }
        }
        return evaluateBeans;
    }

    //获取已完成页面的评价
    @CrossOrigin
    @RequestMapping("/getevaluated")
    public List<EvaluateBean> getevaluated(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String buyrelationid = request.getParameter("buyrelationid");
        System.out.print(buyrelationid);
        List<EvaluateBean> evaluateBeans=userService.findevaluatebybuyrelationid(buyrelationid);
        for(int i=0;i<evaluateBeans.size();i++){
            EvaluateBean evaluateBean=evaluateBeans.get(i);
            evaluateBean.setGoodrate(String.valueOf(Integer.parseInt(evaluateBean.getGoodrate())/20));
            if(evaluateBean.getMerchantgoodrate()!=null) {
                evaluateBean.setMerchantgoodrate(String.valueOf(Integer.parseInt(evaluateBean.getMerchantgoodrate()) / 20));
            }
            evaluateBeans.set(i,evaluateBean);
        }
        return evaluateBeans;
    }

    //添加评价
    @CrossOrigin
    @RequestMapping("/addevaluate")
    public String addevaluate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        if(userBean==null){
            userBean=new UserBean();
        }
        String userid=String.valueOf(userBean.getUserid());
        String goodsid = request.getParameter("goodsid");
        String buyrelationid1 = request.getParameter("buyrelationid");
        String content = request.getParameter("content");
        if(content.equals("")) content="该用户未评价，默认好评";
        String goodrate="0";
        if(request.getParameter("goodrate").equals("")){
            goodrate="0";
        }else {
            goodrate = String.valueOf(Integer.parseInt(request.getParameter("goodrate")) * 20);
        }
        String merchantgoodrate=null;
        if(request.getParameter("merchantgoodrate")!=null) {
            merchantgoodrate = String.valueOf(Integer.parseInt(request.getParameter("merchantgoodrate")) * 20);
        }
        int merchantnum=0;//对商家评论次数
        int goodsnum=0;//对商品评论次数
        int merchantsum=0;
        int goodssum=0;
        if(merchantgoodrate==null) {//商品详细页面发的评论
            List<BuyRelation> buyRelations = userService.findrelation(userid, goodsid);
            if (buyRelations.size() != 0) {
                String buyrelationid = buyRelations.get(0).getBuyrelationid();
                userService.addevaluate(buyrelationid, content, goodrate, merchantgoodrate);
                List<BuyRelation> buyRelations1=goodsService.findbygoodsid(goodsid);
                for(int i=0;i<buyRelations1.size();i++){
                    BuyRelation buyRelation=buyRelations1.get(i);
                    String buyrelationid2=buyRelation.getBuyrelationid();
                    List<EvaluateBean> evaluateBeans=userService.findevaluatebybuyrelationid(buyrelationid2);
                    goodsnum+=evaluateBeans.size();
                }
                GoodsBean goodsBean=goodsService.checkGoods(goodsid);
                if(goodsBean.getGoodsgoodrate().equals("暂无评价")){
                    goodsBean.setGoodsgoodrate("0");
                }
                goodssum=Integer.parseInt(goodsBean.getGoodsgoodrate())*(goodsnum-1)+Integer.parseInt(goodrate);
                goodsService.updategoodrate(goodsid,String.valueOf(goodssum/goodsnum));
                return "1";//添加成功
            } else if(userService.findreturn(userid,goodsid).size()!=0){
                return "0";//只有退货订单，不能评价
            }else if(userService.finddoing(userid,goodsid).size()!=0){
                return "2";//只有正在进行订单，不能评价
            }else{
                return "3";//啥也不是
            }
        }else{//已完成页面发的评论
            userService.addevaluate(buyrelationid1, content, goodrate, merchantgoodrate);//增加商品和商家评价
            //修改商品好评度
            List<BuyRelation> buyRelations1=goodsService.findbygoodsid(goodsid);//在buyrelation表里找到goodsid对应的数据
            for(int i=0;i<buyRelations1.size();i++){//遍历上一步的数据
                BuyRelation buyRelation=buyRelations1.get(i);
                String buyrelationid2=buyRelation.getBuyrelationid();//得到buyrelationid
                List<EvaluateBean> evaluateBeans=userService.findevaluatebybuyrelationid(buyrelationid2);
                goodsnum+=evaluateBeans.size();
            }
            GoodsBean goodsBean=goodsService.checkGoods(goodsid);
            if(goodsBean.getGoodsgoodrate().equals("暂无评价")){
                goodsBean.setGoodsgoodrate("0");
            }
            goodssum=Integer.parseInt(goodsBean.getGoodsgoodrate())*(goodsnum-1)+Integer.parseInt(goodrate);
            goodsService.updategoodrate(goodsid,String.valueOf(goodssum/goodsnum));
            //修改商家好评度
//            List<BuyRelation> buyRelations1=goodsService.findbygoodsid(goodsid);//在buyrelation表里找到goodsid对应的数据
            for(int i=0;i<buyRelations1.size();i++){//遍历上一步的数据
                BuyRelation buyRelation=buyRelations1.get(i);
                String buyrelationid2=buyRelation.getBuyrelationid();//得到buyrelationid
                List<EvaluateBean> evaluateBeans=userService.findevaluatebybuyrelationid(buyrelationid2);
                for(int j=0;j<evaluateBeans.size();j++){
                    if(evaluateBeans.get(j).getMerchantgoodrate()!=null){
                        merchantnum++;
                    }
                }
            }
            MerchantBean merchantBean=merchantService.findbymerchantid(goodsService.checkGoods(goodsid).getMerchantid());
            if(merchantBean.getMerchantgoodrate().equals("暂无评价")){
                merchantBean.setMerchantgoodrate("0");
            }
            merchantsum=Integer.parseInt(merchantBean.getMerchantgoodrate())*(merchantnum-1)+Integer.parseInt(merchantgoodrate);
            merchantService.updatemerchantgoodrate(String.valueOf(merchantBean.getMerchantid()),String.valueOf(merchantsum/merchantnum));
            userService.evaluatefinish(buyrelationid1);
            return "1";//添加成功

        }
    }


    //查看钱包 使用username进行查询
    @CrossOrigin
    @RequestMapping("/checkwallet")
    public UserBean checkwallet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        return userBean;
    }

    //做延时
    @Scheduled(fixedRate =1000*1)//获取buyralation，如果过了24小时且状态还是1，更改状态为2，停止延时任务，如果没过并且发起退货，更改状态且再也不进入
    public void initialDelay() throws Exception{
        if(this.buyRelations!=null) {

            for(int i=0;i<this.buyRelations.size();i++){
//                .out.print(this.buyRelations.size()+"\n");
                BuyRelation buyRelation=this.buyRelations.get(i);
                java.util.Date date = new java.util.Date(new java.util.Date().getTime());
//                System.out.print("-------------");
//                System.out.print(date + "\n");
                java.util.Date ordertime = new java.util.Date(buyRelation.getOrdertime().getTime() - 8 * 60 * 60 * 1000);
//                System.out.print(ordertime + "\n");
//                System.out.print(new java.util.Date(ordertime.getTime() + 10 * 1000) + "\n");
//                System.out.print(date.compareTo(new java.util.Date(ordertime.getTime() + 60 * 1000)) + "\n");
                if (buyRelation.getState().equals("1") && date.compareTo(new java.util.Date(ordertime.getTime() + 20 * 1000)) == 1) {//24*60*60 * 1000
                    String buyrelationid = buyRelation.getBuyrelationid();
                    userService.buyfinish(buyrelationid);//更改状态为2
                    //商家余额增加，管理员余额增加，还没写
                    String goodsid= buyRelation.getGoodsid();
                    String buynum=buyRelation.getBuynum();
                    GoodsBean goodsBean=goodsService.checkGoods(goodsid);
                    Double totalprice = Double.parseDouble(goodsBean.getGoodsoriginalprice()) * (Double.parseDouble(goodsBean.getGoodsdiscount()) / 10)*Double.parseDouble(buynum);
                    String merchantid=goodsBean.getMerchantid();
                    MerchantBean merchantBean=merchantService.findbymerchantid(merchantid);
                    String merchantbalance=merchantBean.getMerchantbalance();
                    String merchantrank=merchantBean.getMerchantrank();
                    String adminbalance=adminService.loginIn("admin","admin").getAdminbalance();
                    if(merchantrank.equals("1")){
                        merchantbalance=String.valueOf(Double.parseDouble(merchantbalance)+totalprice*0.999);
                        adminbalance=String.valueOf(Double.parseDouble(adminbalance)+totalprice*0.001);
                        BigDecimal bd = new BigDecimal(merchantbalance);
                        merchantbalance = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                        BigDecimal bd1 = new BigDecimal(adminbalance);
                        adminbalance = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
                        merchantService.addbalance(merchantid,merchantbalance);
                        adminService.addbalance(adminbalance);
                    }else if(merchantrank.equals("2")){
                        merchantbalance=String.valueOf(Double.parseDouble(merchantbalance)+totalprice*0.998);
                        adminbalance=String.valueOf(Double.parseDouble(adminbalance)+totalprice*0.002);
                        BigDecimal bd = new BigDecimal(merchantbalance);
                        merchantbalance = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                        BigDecimal bd1 = new BigDecimal(adminbalance);
                        adminbalance = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
                        merchantService.addbalance(merchantid,merchantbalance);
                        adminService.addbalance(adminbalance);
                    }else if(merchantrank.equals("3")){
                        merchantbalance=String.valueOf(Double.parseDouble(merchantbalance)+totalprice*0.995);
                        adminbalance=String.valueOf(Double.parseDouble(adminbalance)+totalprice*0.005);
                        BigDecimal bd = new BigDecimal(merchantbalance);
                        merchantbalance = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                        BigDecimal bd1 = new BigDecimal(adminbalance);
                        adminbalance = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
                        merchantService.addbalance(merchantid,merchantbalance);
                        adminService.addbalance(adminbalance);
                    }else if(merchantrank.equals("4")){
                        merchantbalance=String.valueOf(Double.parseDouble(merchantbalance)+totalprice*0.9925);
                        adminbalance=String.valueOf(Double.parseDouble(adminbalance)+totalprice*0.0075);
                        BigDecimal bd = new BigDecimal(merchantbalance);
                        merchantbalance = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                        BigDecimal bd1 = new BigDecimal(adminbalance);
                        adminbalance = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
                        merchantService.addbalance(merchantid,merchantbalance);
                        adminService.addbalance(adminbalance);
                    }else if(merchantrank.equals("5")){
                        merchantbalance=String.valueOf(Double.parseDouble(merchantbalance)+totalprice*0.99);
                        adminbalance=String.valueOf(Double.parseDouble(adminbalance)+totalprice*0.01);
                        BigDecimal bd = new BigDecimal(merchantbalance);
                        merchantbalance = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                        BigDecimal bd1 = new BigDecimal(adminbalance);
                        adminbalance = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
                        merchantService.addbalance(merchantid,merchantbalance);
                        adminService.addbalance(adminbalance);
                    }
//                scheduler.cancelTask(this);
                    this.buyRelations.remove(buyRelation);
                }
            }
        }
    }

    //购买某一商品
    @CrossOrigin
    @RequestMapping("/buygoods")
    public String buygoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        int resultc1=0;
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        String goodsid = request.getParameter("goodsid");
        String buynum = request.getParameter("buynum");
        GoodsBean goodsBean=goodsService.checkGoods(goodsid);
        Double goodsprice=(Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buynum);
        String str=String.valueOf(Double.valueOf(userBean.getUserbalance())-goodsprice);
        BigDecimal bd = new BigDecimal(str);
        str = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));//新的余额
        if(Integer.parseInt(goodsBean.getGoodsstock())>=Integer.parseInt(buynum) &&
                Double.valueOf(userBean.getUserbalance())>=goodsprice){//你有钱我有货就购买
            java.util.Date date = new java.util.Date(new java.util.Date().getTime()+8*60*60*1000);          // 获取一个Date对象
            Timestamp ordertime = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
            resultc1 = userService.buygoods(userid,goodsid,buynum,ordertime);
            for(int i=0;i<userService.finddoing(userid,goodsid).size();i++) {
                this.buyRelations.add(userService.finddoing(userid, goodsid).get(i));
            }
            int sales=Integer.parseInt(goodsBean.getGoodssales());
            String sales1=String.valueOf(sales+Integer.parseInt(buynum));
            goodsService.salegoods(goodsid,String.valueOf(Integer.parseInt(goodsBean.getGoodsstock())-Integer.parseInt(buynum)),sales1);
            userService.subbalanceaddpoints(userid, str,
                    Integer.parseInt(new java.text.DecimalFormat("0").format(goodsprice)));//double转int
        }else if(Integer.parseInt(goodsBean.getGoodsstock())<Integer.parseInt(buynum)){
            return "0";
            //库存不够
        }else if(Double.valueOf(userBean.getUserbalance())<goodsprice){
            return "1";
            //余额不足
        }
        if(resultc1!=0){
            return "ok";}
        else
            return "error";
    }

    //获得正在进行的订单
    @CrossOrigin
    @RequestMapping("/getorderforming")
    public List<Shoppingcart> getorderforming(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        List<BuyRelation> buyRelations=userService.getorderforming(userid);
        List<Shoppingcart> shoppingcarts=new ArrayList<Shoppingcart>();
        for(int i=0;i<buyRelations.size();i++){
            BuyRelation buyRelation=buyRelations.get(i);
            String goodsid=buyRelation.getGoodsid();
            GoodsBean goodsBean=goodsService.checkGoods(goodsid);
            String goodspic1path=goodsBean.getGoodspic1path();
            String goodsname=goodsBean.getGoodsname();
            String buynum=buyRelation.getBuynum();
            String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buynum));
            BigDecimal bd = new BigDecimal(goodstotalprice);
            goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
            goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
            goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
            String price=String.valueOf(Double.parseDouble(goodsBean.getGoodsoriginalprice())*Double.parseDouble(goodsBean.getGoodsdiscount())/10);
            BigDecimal bd1 = new BigDecimal(price);
            price = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
            String purchasedate= (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(buyRelation.getOrdertime().getTime()-8*60*60*1000));
            Shoppingcart shoppingcart=new Shoppingcart();
            shoppingcart.setBuynum(buynum);
            shoppingcart.setGoodsid(goodsid);
            shoppingcart.setGoodspic1path(goodspic1path);
            shoppingcart.setGoodsname(goodsname);
            shoppingcart.setGoodstotalprice(goodstotalprice);
            shoppingcart.setGoodsoriginalprice(price);
            shoppingcart.setGoodsdiscount(goodsBean.getGoodsdiscount()+"折");
            shoppingcart.setPurchasedate(purchasedate);
            shoppingcart.setBuyrelationid(buyRelation.getBuyrelationid());
            shoppingcarts.add(shoppingcart);
        }
        return shoppingcarts;
    }

    //发起退货
    @CrossOrigin
    @RequestMapping("/returngoods")
    public String returngoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        String goodsid = request.getParameter("goodsid");
        String buyrelationid=request.getParameter("buyrelationid");
        userService.returngoods(buyrelationid);//将state改为3
        for(int i=0;i<this.buyRelations.size();i++){
            if(buyrelationid.equals(this.buyRelations.get(i).getBuyrelationid())){
                this.buyRelations.remove(i);
            }
        }
//        goodsService.salegoods(goodsid,this.tradeBean.getStock(),this.tradeBean.getSale());
//        userService.subbalanceaddpoints(userid, this.tradeBean.getBalance(), this.tradeBean.getPoints());
        return "ok";
    }


    //获得未评价的已完成订单
    @CrossOrigin
    @RequestMapping("/getorderformedtoevaluate")
    public List<Shoppingcart> getorderformedtoevaluate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        List<BuyRelation> buyRelations=userService.getorderformedtoevaluate(userid);
        List<Shoppingcart> shoppingcarts=new ArrayList<Shoppingcart>();
        for(int i=0;i<buyRelations.size();i++){
            BuyRelation buyRelation=buyRelations.get(i);
            String goodsid=buyRelation.getGoodsid();
            GoodsBean goodsBean=goodsService.checkGoods(goodsid);
            String goodspic1path=goodsBean.getGoodspic1path();
            String goodsname=goodsBean.getGoodsname();
            String buynum=buyRelation.getBuynum();
            String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buynum));
            BigDecimal bd = new BigDecimal(goodstotalprice);
            goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
            goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
            goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
            String price=String.valueOf(Double.parseDouble(goodsBean.getGoodsoriginalprice())*Double.parseDouble(goodsBean.getGoodsdiscount())/10);
            BigDecimal bd1 = new BigDecimal(price);
            price = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
            String purchasedate= (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(buyRelation.getOrdertime().getTime()-8*60*60*1000));
            String buyrelationid=buyRelation.getBuyrelationid();
            Shoppingcart shoppingcart=new Shoppingcart();
            shoppingcart.setBuynum(buynum);
            shoppingcart.setGoodsid(goodsid);
            shoppingcart.setGoodspic1path(goodspic1path);
            shoppingcart.setGoodsname(goodsname);
            shoppingcart.setGoodstotalprice(goodstotalprice);
            shoppingcart.setBuyrelationid(buyrelationid);
            shoppingcart.setGoodsoriginalprice(price);
            shoppingcart.setGoodsdiscount(goodsBean.getGoodsdiscount()+"折");
            shoppingcart.setPurchasedate(purchasedate);
            shoppingcarts.add(shoppingcart);
        }
        return shoppingcarts;
    }

    //获得已评价的已完成订单
    @CrossOrigin
    @RequestMapping("/getorderformedevaluated")
    public List<Shoppingcart> getorderformedevaluated(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        List<BuyRelation> buyRelations=userService.getorderformedevaluated(userid);
        List<Shoppingcart> shoppingcarts=new ArrayList<Shoppingcart>();
        for(int i=0;i<buyRelations.size();i++){
            BuyRelation buyRelation=buyRelations.get(i);
            String goodsid=buyRelation.getGoodsid();
            GoodsBean goodsBean=goodsService.checkGoods(goodsid);
            String goodspic1path=goodsBean.getGoodspic1path();
            String goodsname=goodsBean.getGoodsname();
            String buynum=buyRelation.getBuynum();
            String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buynum));
            BigDecimal bd = new BigDecimal(goodstotalprice);
            goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
            goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
            goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
            String price=String.valueOf(Double.parseDouble(goodsBean.getGoodsoriginalprice())*Double.parseDouble(goodsBean.getGoodsdiscount())/10);
            BigDecimal bd1 = new BigDecimal(price);
            price = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
            String purchasedate= (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(buyRelation.getOrdertime().getTime()-8*60*60*1000));
            String buyrelationid=buyRelation.getBuyrelationid();
            Shoppingcart shoppingcart=new Shoppingcart();
            shoppingcart.setBuynum(buynum);
            shoppingcart.setGoodsid(goodsid);
            shoppingcart.setGoodspic1path(goodspic1path);
            shoppingcart.setGoodsname(goodsname);
            shoppingcart.setGoodstotalprice(goodstotalprice);
            shoppingcart.setBuyrelationid(buyrelationid);
            shoppingcart.setGoodsoriginalprice(price);
            shoppingcart.setGoodsdiscount(goodsBean.getGoodsdiscount()+"折");
            shoppingcart.setPurchasedate(purchasedate);
            shoppingcarts.add(shoppingcart);
        }
        return shoppingcarts;
    }

    //获得退货订单
    @CrossOrigin
    @RequestMapping("/getreturnedgoods")
    public List<Shoppingcart> getreturnedgoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        List<BuyRelation> buyRelationsing=userService.getreturninggoods(userid);//正在审核
        List<BuyRelation> buyRelations=userService.getreturnedgoods(userid);//审核完成
        List<Shoppingcart> shoppingcarts=new ArrayList<Shoppingcart>();
        for(int i=0;i<buyRelations.size();i++){
            BuyRelation buyRelation=buyRelations.get(i);
            String goodsid=buyRelation.getGoodsid();
            GoodsBean goodsBean=goodsService.checkGoods(goodsid);
            String goodspic1path=goodsBean.getGoodspic1path();
            String goodsname=goodsBean.getGoodsname();
            String buynum=buyRelation.getBuynum();
            String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buynum));
            BigDecimal bd = new BigDecimal(goodstotalprice);
            goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
            goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
            goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
            String price=String.valueOf(Double.parseDouble(goodsBean.getGoodsoriginalprice())*Double.parseDouble(goodsBean.getGoodsdiscount())/10);
            BigDecimal bd1 = new BigDecimal(price);
            price = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
            String purchasedate= (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(buyRelation.getOrdertime().getTime()-8*60*60*1000));
            Shoppingcart shoppingcart=new Shoppingcart();
            shoppingcart.setBuynum(buynum);
            shoppingcart.setGoodsid(goodsid);
            shoppingcart.setGoodspic1path(goodspic1path);
            shoppingcart.setGoodsname(goodsname);
            shoppingcart.setGoodstotalprice(goodstotalprice);
            shoppingcart.setState("审核完成");
            shoppingcart.setGoodsoriginalprice(price);
            shoppingcart.setGoodsdiscount(goodsBean.getGoodsdiscount()+"折");
            shoppingcart.setPurchasedate(purchasedate);
            shoppingcart.setBuyrelationid(buyRelation.getBuyrelationid());
            shoppingcarts.add(shoppingcart);
        }
        for(int i=0;i<buyRelationsing.size();i++){
            BuyRelation buyRelation=buyRelationsing.get(i);
            String goodsid=buyRelation.getGoodsid();
            GoodsBean goodsBean=goodsService.checkGoods(goodsid);
            String goodspic1path=goodsBean.getGoodspic1path();
            String goodsname=goodsBean.getGoodsname();
            String buynum=buyRelation.getBuynum();
            String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buynum));
            BigDecimal bd = new BigDecimal(goodstotalprice);
            goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
            goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
            goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
            String price=String.valueOf(Double.parseDouble(goodsBean.getGoodsoriginalprice())*Double.parseDouble(goodsBean.getGoodsdiscount())/10);
            BigDecimal bd1 = new BigDecimal(price);
            price = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
            String purchasedate= (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(buyRelation.getOrdertime().getTime()-8*60*60*1000));
            Shoppingcart shoppingcart=new Shoppingcart();
            shoppingcart.setBuynum(buynum);
            shoppingcart.setGoodsid(goodsid);
            shoppingcart.setGoodspic1path(goodspic1path);
            shoppingcart.setGoodsname(goodsname);
            shoppingcart.setGoodstotalprice(goodstotalprice);
            shoppingcart.setState("正在审核");
            shoppingcart.setGoodsoriginalprice(price);
            shoppingcart.setGoodsdiscount(goodsBean.getGoodsdiscount()+"折");
            shoppingcart.setPurchasedate(purchasedate);
            shoppingcart.setBuyrelationid(buyRelation.getBuyrelationid());
            shoppingcarts.add(shoppingcart);
        }
        return shoppingcarts;
    }


    //购买购物车中的某一商品 0是购物车，1是购买
    @CrossOrigin
    @RequestMapping("/buygoodsfromshoppingcart")
    public String buygoodsfromshoppingcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        String goodsid = request.getParameter("goodsid");
        String buynum = request.getParameter("buynum");
        String buyrelationid = request.getParameter("buyrelationid");
        int resultc1=0;
        int resultc2=0;
        int resultc3=0;
        GoodsBean goodsBean=goodsService.checkGoods(goodsid);
        Double goodsprice=(Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buynum);
        String str=String.valueOf(Double.valueOf(userBean.getUserbalance())-goodsprice);
        BigDecimal bd = new BigDecimal(str);
        str = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
        if(Integer.parseInt(goodsBean.getGoodsstock())>=Integer.parseInt(buynum) &&
                Double.valueOf(userBean.getUserbalance())>=goodsprice){//你有钱我有货就购买
            java.util.Date date = new java.util.Date();          // 获取一个Date对象
            Timestamp ordertime = new Timestamp(date.getTime()+8*60*60*1000);     //   讲日期时间转换为数据库中的timestamp类型
            resultc1 = userService.buyfromshoppingcart(buyrelationid,ordertime);
            int sales=Integer.parseInt(goodsBean.getGoodssales());
            String sales1=String.valueOf(sales+Integer.parseInt(buynum));
            resultc2=goodsService.salegoods(goodsid,String.valueOf(Integer.parseInt(goodsBean.getGoodsstock())-Integer.parseInt(buynum)),sales1);
            userService.subbalanceaddpoints(userid, str,
                    Integer.parseInt(new java.text.DecimalFormat("0").format(goodsprice)));
        }else if(Integer.parseInt(goodsBean.getGoodsstock())<Integer.parseInt(buynum)){
            return "0";
            //库存不够
        }else if(Double.valueOf(userBean.getUserbalance())<goodsprice){
            return "1";
            //余额不足
        }
        if(resultc1!=0){
            return "ok";}
        else
            return "error";
    }

    //将某一商品加购
    @CrossOrigin
    @RequestMapping("/addtoshoppingcart")
    public String addtoshoppingcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        String goodsid = request.getParameter("goodsid");
        String buynum = request.getParameter("buynum");
        GoodsBean goodsBean=goodsService.checkGoods(goodsid);
        if(Integer.parseInt(goodsBean.getGoodsstock())<Integer.parseInt(buynum)){
            return "1";//库存不足
        }
        int resultc = userService.savebuyrelation(userid,goodsid,buynum);
        if(resultc!=0)
            return "ok";
        else
            return "error";
    }

    //查看购物车 使用username进行查询
    @CrossOrigin
    @RequestMapping("/checkshoppingcart")
    public List<Shoppingcart> checkshoppingcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        List<BuyRelation> buyRelations=userService.getshoppingcart(userid);
        List<Shoppingcart> shoppingcarts=new ArrayList<Shoppingcart>();
        for(int i=0;i<buyRelations.size();i++){
            BuyRelation buyRelation=buyRelations.get(i);
            String goodsid=buyRelation.getGoodsid();
            GoodsBean goodsBean=goodsService.checkGoods(goodsid);
            String goodspic1path=goodsBean.getGoodspic1path();
            String goodsname=goodsBean.getGoodsname();
            String buynum=buyRelation.getBuynum();
            String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buynum));
            BigDecimal bd = new BigDecimal(goodstotalprice);
            goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
            goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
            goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
            Shoppingcart shoppingcart=new Shoppingcart();
            shoppingcart.setBuynum(buynum);
            shoppingcart.setGoodsid(goodsid);
            shoppingcart.setGoodspic1path(goodspic1path);
            shoppingcart.setGoodsname(goodsname);
            shoppingcart.setGoodstotalprice(goodstotalprice);
            shoppingcart.setBuyrelationid(buyRelation.getBuyrelationid());
            shoppingcarts.add(shoppingcart);
        }
        return shoppingcarts;
    }


    //获得购物车所有商品总价
    @CrossOrigin
    @RequestMapping("/gettotalprice")
    public String gettotalprice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        int resultc1=0;
        int resultc2=0;
        int resultc3=0;
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        List<BuyRelation> buyRelations=userService.getshoppingcart(userid);
        Double totalprice=0.0;
        for(int i=0;i<buyRelations.size();i++){
            String goodsid = buyRelations.get(i).getGoodsid();
            String buynum = buyRelations.get(i).getBuynum();
            GoodsBean goodsBean=goodsService.checkGoods(goodsid);
            Double goodsprice=(Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice());
            totalprice=totalprice+goodsprice*Double.valueOf(buynum);
        }
        String result=String.valueOf(totalprice);
        BigDecimal bd = new BigDecimal(result);
        result=String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
        return  result;

    }

    //一键清空购物车，库存应该在24小时之后减掉
    @CrossOrigin
    @RequestMapping("/clearshoppingcart")
    public String clearshoppingcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        int resultc1=0;
        String username = request.getParameter("username");
        String totalprice=request.getParameter("totalprice");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        List<BuyRelation> buyRelations=userService.getshoppingcart(userid);//获得加购状态的所有buyrelation
        shoppingcarts.clear();
        //判断每件商品的库存量
        for(int i=0;i<buyRelations.size();i++){
            String goodsid = buyRelations.get(i).getGoodsid();
            String buyrelationid=buyRelations.get(i).getBuyrelationid();
            String buynum = buyRelations.get(i).getBuynum();
            GoodsBean goodsBean=goodsService.checkGoods(goodsid);//根据goodsid找goodsbean
            if(Integer.parseInt(goodsBean.getGoodsstock())<Integer.parseInt(buynum)){
                Shoppingcart shoppingcart=new Shoppingcart();
                shoppingcart.setGoodsname(goodsBean.getGoodsname());
                shoppingcart.setBuynum(buynum);
                shoppingcart.setGoodsstock(goodsBean.getGoodsstock());
                this.shoppingcarts.add(shoppingcart);
                return "0";
            }
        }
        //用户余额不足的情况
        if(Double.valueOf(userBean.getUserbalance())<Double.valueOf(totalprice)){
            return "1";
        }
        //正常情况下的一键下单
        for(int i=0;i<buyRelations.size();i++) {
            java.util.Date date = new java.util.Date();          // 获取一个Date对象
            Timestamp ordertime = new Timestamp(date.getTime()+8*60*60*1000);     //   讲日期时间转换为数据库中的timestamp类型
            UserBean userbean=userService.finduserbyusername(username);
            String goodsid = buyRelations.get(i).getGoodsid();
            String buyrelationid = buyRelations.get(i).getBuyrelationid();
            String buynum = buyRelations.get(i).getBuynum();
            GoodsBean goodsBean = goodsService.checkGoods(goodsid);//根据goodsid找goodsbean
            Double goodsprice=(Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buynum);
            String str=String.valueOf(Double.valueOf(userbean.getUserbalance())-goodsprice);
            BigDecimal bd = new BigDecimal(str);
            str = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
            userService.buyfromshoppingcart(buyrelationid,ordertime);//把加购状态变为购买状态
            this.buyRelations.add(userService.findbybuyrelationid(buyrelationid));
            int sales=Integer.parseInt(goodsBean.getGoodssales());
            String sales1=String.valueOf(sales+Integer.parseInt(buynum));
            goodsService.salegoods(goodsid,String.valueOf(Integer.parseInt(goodsBean.getGoodsstock())-Integer.parseInt(buynum)),sales1);
            resultc1 = userService.subbalanceaddpoints(userid, str,
                    Integer.parseInt(new java.text.DecimalFormat("0").format(goodsprice)));
        }
        if(resultc1!=0)
            return "ok";
        else
            return "error";
    }

    //返回库存不足的商品的信息
    @CrossOrigin
    @RequestMapping("/getoutofstock")
    public List<Shoppingcart> getoutofstock(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.shoppingcarts;
    }

    //删除购物车中的所有商品
    @CrossOrigin
    @RequestMapping("/deleteshoppingcart")
    public String deleteshoppingcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        int resultc=userService.deleteallfromshoppingcart(userid);
        if(resultc!=0)
            return "ok";
        else
            return "error";
    }

    //删掉购物车中的某一件商品
    @CrossOrigin
    @RequestMapping("/deleteonegoods")
    public String deleteonegoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
        String goodsid = request.getParameter("goodsid");
        String buyrelationid = request.getParameter("buyrelationid");
        int resultc=userService.deleteonefromshoppingcart(buyrelationid);
        if(resultc!=0)
            return "ok";
        else
            return "error";
    }

    //轮播图
    @CrossOrigin
    @RequestMapping("/getCarouselgoods")//待更改的url
    public List<GoodsBean> getCarouselgoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<GoodsBean> goodss1 = goodsService.findAllagreed();
        goodss1.sort((((o1, o2) -> Integer.parseInt(o2.getGoodsgoodrate())-Integer.parseInt(o1.getGoodsgoodrate()))));
        List<GoodsBean> goodss =new ArrayList<>();
        for(int i=0;i<3;i++){
            goodss.add(goodss1.get(i));
        }
        for(int i=0;i<goodss.size();i++){
            GoodsBean goodsBean=goodss.get(i);
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
        }
        return goodss;
    }

    //充值
    @CrossOrigin
    @RequestMapping("/addbalancetomyself")
    public String addbalance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        UserBean userBean=userService.finduserbyusername(username);
        String userid=String.valueOf(userBean.getUserid());
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

}
