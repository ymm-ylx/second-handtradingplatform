package com.example.test.controller;

import com.example.test.bean.*;
import com.example.test.service.GoodsService;
import com.example.test.service.MerchantService;
import com.example.test.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
public class MerchantController {
    @Autowired
    MerchantService merchantService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    UserService userService;

    //发布商品页面返给前端商铺名字
    @CrossOrigin
    @RequestMapping("/merchantshop/storeinformation")//待更改的url
    public Storeandmerchant findgoodsbymerchantname(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname = request.getParameter("merchantname");
        MerchantBean merchantBean=merchantService.findpathbymerchantname(merchantname);
        String merchantid=String.valueOf(merchantBean.getMerchantid());
        String merchantrank=merchantBean.getMerchantrank();
        String merchantgoodrate=merchantBean.getMerchantgoodrate();
        StoreBean storeBean=merchantService.findstorebymerchantid(merchantid);
        String storename=storeBean.getStorename();
        Storeandmerchant storeandmerchant=new Storeandmerchant();
        storeandmerchant.setMerchantrank(merchantrank);
        storeandmerchant.setStorename(storename);
        storeandmerchant.setMerchantgoodrate(merchantgoodrate);
        return storeandmerchant;
    }

    //根据商铺名字返回商铺中的商品
    @CrossOrigin
    @RequestMapping("/merchantshop/getgoods")//待更改的url
    public List<GoodsBean> findgoodsbystorename(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String storename = request.getParameter("storename");
        StoreBean storeBean=merchantService.findbystorename(storename);
        String storeid=String.valueOf(storeBean.getStoreid());
        List<GoodsBean> goodss = goodsService.findbystoreid(storeid);
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

    //根据商铺名字返回商铺中的商品
    @CrossOrigin
    @RequestMapping("/merchantshop/getgoodsdeleted")//待更改的url
    public List<GoodsBean> findgoodsdeletedbystorename(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String storename = request.getParameter("storename");
        StoreBean storeBean=merchantService.findbystorename(storename);
        String storeid=String.valueOf(storeBean.getStoreid());
        List<GoodsBean> goodss = goodsService.finddeletedbystoreid(storeid);
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

    @GetMapping (value = "/merchantshop/getgoodpics",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] pushbusinesslicense(@RequestParam String storename) throws IOException {
        File file = new File(storename);
        FileInputStream inputstream = new FileInputStream(file);
        byte[] bytes = new byte[inputstream.available()];
        inputstream.read(bytes, 0, inputstream.available());
        return bytes;
    }



    //开店
    @CrossOrigin
    @RequestMapping("/merchantshop/addstore")
    public String addstore(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String storename = request.getParameter("storename");
        String merchantname = request.getParameter("merchantname");
        MerchantBean merchantbeean=new MerchantBean();
        merchantbeean=merchantService.findpathbymerchantname(merchantname);
        String merchantid=String.valueOf(merchantbeean.getMerchantid());
        int resultc =0;
        resultc=merchantService.saveStore(storename,merchantid);
        if(resultc!=0) {
            return "ok";
        }else{
            return "error";
        }

    }

    //添加新的商品
    @CrossOrigin
    @RequestMapping("/merchantshop/addgoods")
    public String addgoods(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value="storename",required = false) String storename,
                           @RequestParam(value="goodsname",required = false) String goodsname,
                           @RequestParam(value="goodstype",required = false) String goodstype,
                           @RequestParam(value="goodsallowbargin",required = false) String goodsallowbargin,
                           @RequestParam(value="goodsstock",required = false) String goodsstock,
                           @RequestParam(value="goodssize",required = false) String goodssize,
                           @RequestParam(value="goodsexplain",required = false) String goodsexplain,
                           @RequestParam(value="goodsoldandnew",required = false) String goodsoldandnew,
                           @RequestParam(value="goodsoriginalprice",required = false) String goodsoriginalprice,
                           @RequestParam(value="goodsdiscount",required = false) String goodsdiscount,
                           @RequestParam(value="goodspics",required = false) MultipartFile[] goodspics) throws Exception {
        request.setCharacterEncoding("utf-8");
        String goodsallowbargin1="否";
        if(goodsallowbargin.equals("true")){
            goodsallowbargin1="是";
        }
        String type = request.getParameter("goodstype");
        String[] types={"电子产品","书籍","文具","生活用品","零食"};
        String goodstype1=types[Integer.parseInt(type)-1];
        String oldandnew = goodsoldandnew;
        String[] oldandnews={"一成","二成","三成","四成","五成","六成","七成","八成","九成","全"};
        String goodsoldandnew1=oldandnews[Integer.parseInt(oldandnew)-1]+"新";
        UUID randomUUID1 = UUID.randomUUID();
        UUID randomUUID2 = UUID.randomUUID();
        UUID randomUUID3 = UUID.randomUUID();
        String goodspic1path=null;
        String goodspic2path=null;
        String goodspic3path=null;
        if(goodspics.length!=3){
            return "nophoto";
        }
        else{
            goodspics[0].transferTo(new File("D:\\secondhandplatformpics/goods/"+ randomUUID1));
            goodspic1path="D:\\secondhandplatformpics/goods/"+ randomUUID1;
            goodspics[1].transferTo(new File("D:\\secondhandplatformpics/goods/"+ randomUUID2));
            goodspic2path="D:\\secondhandplatformpics/goods/"+ randomUUID2;
            goodspics[2].transferTo(new File("D:\\secondhandplatformpics/goods/"+ randomUUID3));
            goodspic3path="D:\\secondhandplatformpics/goods/"+ randomUUID3;
        }
        StoreBean storeBean=merchantService.findbystorename(storename);
        String storeid=String.valueOf(storeBean.getStoreid());
        String merchantid=storeBean.getMerchantid();
        int resultc =0;
        resultc=goodsService.saveGoods(goodsname,goodstype1,goodsallowbargin1,goodsstock, goodssize,
                goodsexplain,goodsoldandnew1,goodsoriginalprice,goodsdiscount,goodspic1path,goodspic2path,goodspic3path,
                merchantid,storeid);
        if(resultc!=0) {
            return "ok";
        }else{
            return "error";
        }

    }

    //下架商品 将商品状态改为2
    @CrossOrigin
    @RequestMapping("/merchantshop/deletegoods")
    public String deletegoods(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String goodsid = request.getParameter("goodsid");
        int resultc =0;
        resultc=goodsService.deleteGoods(goodsid);
        if(resultc!=0) {
            return "ok";
        }else{
            return "error";
        }

    }

    //获得退货订单
    @CrossOrigin
    @RequestMapping("/merchantshop/getreturntomerchant")
    public List<Shoppingcart> getreturntomerchant(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname = request.getParameter("merchantname");
        MerchantBean merchantBean=merchantService.findpathbymerchantname(merchantname);
        String merchantid=String.valueOf(merchantBean.getMerchantid());
        List<GoodsBean> goodsBeans = goodsService.checkGoodsbuymerchantid(merchantid);//通过merchantid找到所有goods
        List<Shoppingcart> shoppingcarts=new ArrayList<Shoppingcart>();
        for (int j = 0; j < goodsBeans.size(); j++) {
            GoodsBean goodsBean = goodsBeans.get(j);
            String goodsid = String.valueOf(goodsBean.getGoodsid());
            List<BuyRelation> buyRelations = goodsService.findreturnbygoodsid(goodsid);//返回状态是3的所有订单
            for(int i=0;i<buyRelations.size();i++){
                BuyRelation buyRelation=buyRelations.get(i);
                String username=userService.finduserbyuserid(buyRelation.getUserid()).getUsername();
                Shoppingcart shoppingcart=new Shoppingcart();
                shoppingcart.setBuynum(buyRelation.getBuynum());
                shoppingcart.setGoodsid(goodsid);
                shoppingcart.setGoodsname(goodsBean.getGoodsname());
                String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buyRelation.getBuynum()));
                BigDecimal bd = new BigDecimal(goodstotalprice);
                goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                String goodspic1path=goodsBean.getGoodspic1path();
                goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
                goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
                String price=String.valueOf(Double.parseDouble(goodsBean.getGoodsoriginalprice())*Double.parseDouble(goodsBean.getGoodsdiscount())/10);
                BigDecimal bd1 = new BigDecimal(price);
                price = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
                shoppingcart.setGoodstotalprice(goodstotalprice);
                shoppingcart.setGoodsoriginalprice(price);
                shoppingcart.setGoodsdiscount(goodsBean.getGoodsdiscount()+"折");
                String purchasedate= (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(buyRelation.getOrdertime().getTime()-8*60*60*1000));
                shoppingcart.setPurchasedate(purchasedate);
                shoppingcart.setUsername(username);
                shoppingcart.setGoodspic1path(goodspic1path);
                shoppingcart.setBuyrelationid(buyRelation.getBuyrelationid());
                shoppingcarts.add(shoppingcart);
            }
        }
        return shoppingcarts;

    }

    //同意退货同时评价用户,还要返回用户余额和库存原来的状态
    @CrossOrigin
    @RequestMapping("/merchantshop/agreereturn")
    public String agreereturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname=request.getParameter("merchantname");
        String username=request.getParameter("username");
        String usergoodrate=request.getParameter("usergoodrate");
        String buyrelationid=request.getParameter("buyrelationid");
        System.out.print(buyrelationid+"\n");
        //评价用户
        String userid=String.valueOf(userService.finduserbyusername(username).getUserid());
        UserBean userBean=userService.finduserbyusername(username);
        String merchantid=String.valueOf(merchantService.findpathbymerchantname(merchantname).getMerchantid());
        merchantService.addevaluate(buyrelationid,String.valueOf(Integer.parseInt(usergoodrate)*20));//添加评价
        List<BuyRelation> buyRelations=userService.getallbyuserid(userid);
        int num=0;
        int sum=0;
        for(int i=0;i<buyRelations.size();i++){
            BuyRelation buyRelation=buyRelations.get(i);
            String buyrelationid1=buyRelation.getBuyrelationid();
            List<EvaluateBean> evaluateBeans=userService.findevaluatebybuyrelationid(buyrelationid1);
            for(int j=0;j<evaluateBeans.size();j++){
                EvaluateBean evaluateBean=evaluateBeans.get(j);
                if(evaluateBean.getUsergoodrate()!=null){
                    num++;
                    sum+=Integer.parseInt(evaluateBean.getUsergoodrate());
                }

            }
        }
        userService.updateusergoodrate(userid,String.valueOf(sum/num));
        //同意退货
        userService.agreereturn(buyrelationid);
        //把钱和余额加回去
        BuyRelation buyRelation=userService.findbybuyrelationid(buyrelationid);
        String buynum=buyRelation.getBuynum();
        String goodsid=buyRelation.getGoodsid();
        GoodsBean goodsBean=goodsService.checkGoods(goodsid);
        String goodsstock=String.valueOf(Integer.parseInt(goodsBean.getGoodsstock())+Integer.parseInt(buynum));
        String goodssales=String.valueOf(Integer.parseInt(goodsBean.getGoodssales())-Integer.parseInt(buynum));
        goodsService.salegoods(goodsid,goodsstock,goodssales);
        String userbalance=String.valueOf(Double.parseDouble(userBean.getUserbalance())+Double.parseDouble(goodsBean.getGoodsoriginalprice())*(Double.parseDouble(goodsBean.getGoodsdiscount())/10)*Double.parseDouble(buynum));
        BigDecimal bd = new BigDecimal(userbalance);
        userbalance = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
        int userpoints=0-Integer.parseInt(new java.text.DecimalFormat("0").format(Double.parseDouble(goodsBean.getGoodsoriginalprice())*(Double.parseDouble(goodsBean.getGoodsdiscount())/10)*Double.parseDouble(buynum)));
        userService.subbalanceaddpoints(userid, userbalance, userpoints);
        return "ok";
    }

    //返回正在进行的订单
    @CrossOrigin
    @RequestMapping("/merchantshop/getdoingorder")
    public List<Shoppingcart> getdoingorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname = request.getParameter("merchantname");
        MerchantBean merchantBean=merchantService.findpathbymerchantname(merchantname);
        String merchantid=String.valueOf(merchantBean.getMerchantid());
        List<GoodsBean> goodsBeans = goodsService.checkGoodsbuymerchantid(merchantid);//通过merchantid找到所有goods
        List<Shoppingcart> shoppingcarts=new ArrayList<Shoppingcart>();
        for (int j = 0; j < goodsBeans.size(); j++) {
            GoodsBean goodsBean = goodsBeans.get(j);
            String goodsid = String.valueOf(goodsBean.getGoodsid());
            List<BuyRelation> buyRelations = goodsService.getdoingorder(goodsid);//返回状态是1的所有订单
            for(int i=0;i<buyRelations.size();i++){
                BuyRelation buyRelation=buyRelations.get(i);
                String username=userService.finduserbyuserid(buyRelation.getUserid()).getUsername();
                Shoppingcart shoppingcart=new Shoppingcart();
                shoppingcart.setBuynum(buyRelation.getBuynum());
                shoppingcart.setGoodsid(goodsid);
                shoppingcart.setGoodsname(goodsBean.getGoodsname());
                String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buyRelation.getBuynum()));
                BigDecimal bd = new BigDecimal(goodstotalprice);
                goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                String goodspic1path=goodsBean.getGoodspic1path();
                goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
                goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
                String price=String.valueOf(Double.parseDouble(goodsBean.getGoodsoriginalprice())*Double.parseDouble(goodsBean.getGoodsdiscount())/10);
                BigDecimal bd1 = new BigDecimal(price);
                price = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
                shoppingcart.setGoodstotalprice(goodstotalprice);
                shoppingcart.setGoodsoriginalprice(price);
                shoppingcart.setGoodsdiscount(goodsBean.getGoodsdiscount()+"折");
                String purchasedate= (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(buyRelation.getOrdertime().getTime()-8*60*60*1000));
                shoppingcart.setPurchasedate(purchasedate);
                shoppingcart.setGoodspic1path(goodspic1path);
                shoppingcart.setUsername(username);
                shoppingcart.setBuyrelationid(buyRelation.getBuyrelationid());
                shoppingcarts.add(shoppingcart);
            }
        }
        return shoppingcarts;

    }

    //返回已完成未评论的订单
    @CrossOrigin
    @RequestMapping("/merchantshop/getfinishorder")
    public List<Shoppingcart> getfinishorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname = request.getParameter("merchantname");
        MerchantBean merchantBean=merchantService.findpathbymerchantname(merchantname);
        String merchantid=String.valueOf(merchantBean.getMerchantid());
        List<GoodsBean> goodsBeans = goodsService.checkGoodsbuymerchantid(merchantid);//通过merchantid找到所有goods
        List<Shoppingcart> shoppingcarts=new ArrayList<Shoppingcart>();
        for (int j = 0; j < goodsBeans.size(); j++) {
            GoodsBean goodsBean = goodsBeans.get(j);
            String goodsid = String.valueOf(goodsBean.getGoodsid());
            List<BuyRelation> buyRelations = goodsService.findfinishbygoodsidtoeva(goodsid);//返回状态是2未评论的所有订单
            for(int i=0;i<buyRelations.size();i++){
                BuyRelation buyRelation=buyRelations.get(i);
                String username=userService.finduserbyuserid(buyRelation.getUserid()).getUsername();
                Shoppingcart shoppingcart=new Shoppingcart();
                shoppingcart.setBuynum(buyRelation.getBuynum());
                shoppingcart.setGoodsid(goodsid);
                shoppingcart.setGoodsname(goodsBean.getGoodsname());
                String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buyRelation.getBuynum()));
                BigDecimal bd = new BigDecimal(goodstotalprice);
                goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                String goodspic1path=goodsBean.getGoodspic1path();
                goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
                goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
                String price=String.valueOf(Double.parseDouble(goodsBean.getGoodsoriginalprice())*Double.parseDouble(goodsBean.getGoodsdiscount())/10);
                BigDecimal bd1 = new BigDecimal(price);
                price = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
                shoppingcart.setGoodstotalprice(goodstotalprice);
                shoppingcart.setGoodsoriginalprice(price);
                shoppingcart.setGoodsdiscount(goodsBean.getGoodsdiscount()+"折");
                String purchasedate= (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(buyRelation.getOrdertime().getTime()-8*60*60*1000));
                shoppingcart.setPurchasedate(purchasedate);
                shoppingcart.setUsername(username);
                shoppingcart.setGoodspic1path(goodspic1path);
                shoppingcart.setBuyrelationid(buyRelation.getBuyrelationid());
                shoppingcarts.add(shoppingcart);
            }
        }
        return shoppingcarts;

    }


    //在已同意列表评论用户
    @CrossOrigin
    @RequestMapping("/merchantshop/saveevaluate")
    public String saveevaluate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname=request.getParameter("merchantname");
        String username=request.getParameter("username");
        String usergoodrate=request.getParameter("usergoodrate");
        String buyrelationid=request.getParameter("buyrelationid");
        //评价用户
        String userid=String.valueOf(userService.finduserbyusername(username).getUserid());
        String merchantid=String.valueOf(merchantService.findpathbymerchantname(merchantname).getMerchantid());
        merchantService.addevaluate(buyrelationid,String.valueOf(Integer.parseInt(usergoodrate)*20));//添加评价
        goodsService.saveevaluate(buyrelationid);
        List<BuyRelation> buyRelations=userService.getallbyuserid(userid);
        int num=0;
        int sum=0;
        for(int i=0;i<buyRelations.size();i++){
            BuyRelation buyRelation=buyRelations.get(i);
            String buyrelationid1=buyRelation.getBuyrelationid();
            List<EvaluateBean> evaluateBeans=userService.findevaluatebybuyrelationid(buyrelationid1);
            for(int j=0;j<evaluateBeans.size();j++){
                EvaluateBean evaluateBean=evaluateBeans.get(j);
                if(evaluateBean.getUsergoodrate()!=null){
                    num++;
                    sum+=Integer.parseInt(evaluateBean.getUsergoodrate());
                }

            }

        }
        userService.updateusergoodrate(userid,String.valueOf(sum/num));
        return "ok";
    }

    //返回已完成已评论订单
    @CrossOrigin
    @RequestMapping("/merchantshop/getfinishcommentorder")
    public List<Shoppingcart> getfinishcommentorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname = request.getParameter("merchantname");
        MerchantBean merchantBean=merchantService.findpathbymerchantname(merchantname);
        String merchantid=String.valueOf(merchantBean.getMerchantid());
        List<GoodsBean> goodsBeans = goodsService.checkGoodsbuymerchantid(merchantid);//通过merchantid找到所有goods
        List<Shoppingcart> shoppingcarts=new ArrayList<Shoppingcart>();
        for (int j = 0; j < goodsBeans.size(); j++) {
            GoodsBean goodsBean = goodsBeans.get(j);
            String goodsid = String.valueOf(goodsBean.getGoodsid());
            List<BuyRelation> buyRelations = goodsService.findfinishbygoodsidevaed(goodsid);//返回状态是2已评论的所有订单
            for(int i=0;i<buyRelations.size();i++){
                BuyRelation buyRelation=buyRelations.get(i);
                String username=userService.finduserbyuserid(buyRelation.getUserid()).getUsername();
                Shoppingcart shoppingcart=new Shoppingcart();
                shoppingcart.setBuynum(buyRelation.getBuynum());
                shoppingcart.setGoodsid(goodsid);
                shoppingcart.setGoodsname(goodsBean.getGoodsname());
                String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buyRelation.getBuynum()));
                BigDecimal bd = new BigDecimal(goodstotalprice);
                goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                String goodspic1path=goodsBean.getGoodspic1path();
                goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
                goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
                String price=String.valueOf(Double.parseDouble(goodsBean.getGoodsoriginalprice())*Double.parseDouble(goodsBean.getGoodsdiscount())/10);
                BigDecimal bd1 = new BigDecimal(price);
                price = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
                shoppingcart.setGoodstotalprice(goodstotalprice);
                shoppingcart.setGoodsoriginalprice(price);
                shoppingcart.setGoodsdiscount(goodsBean.getGoodsdiscount()+"折");
                String purchasedate= (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(buyRelation.getOrdertime().getTime()-8*60*60*1000));
                shoppingcart.setPurchasedate(purchasedate);
                shoppingcart.setUsername(username);
                shoppingcart.setGoodspic1path(goodspic1path);
                shoppingcart.setBuyrelationid(buyRelation.getBuyrelationid());
                shoppingcarts.add(shoppingcart);
            }
        }
        return shoppingcarts;
    }


    //返回已同意退货订单
    @CrossOrigin
    @RequestMapping("/merchantshop/getreturnagreed")
    public List<Shoppingcart> getreturnagreed(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname = request.getParameter("merchantname");
        MerchantBean merchantBean=merchantService.findpathbymerchantname(merchantname);
        String merchantid=String.valueOf(merchantBean.getMerchantid());
        List<GoodsBean> goodsBeans = goodsService.checkGoodsbuymerchantid(merchantid);//通过merchantid找到所有goods
        List<Shoppingcart> shoppingcarts=new ArrayList<Shoppingcart>();
        for (int j = 0; j < goodsBeans.size(); j++) {
            GoodsBean goodsBean = goodsBeans.get(j);
            String goodsid = String.valueOf(goodsBean.getGoodsid());
            List<BuyRelation> buyRelations = goodsService.findreturnagreed(goodsid);//返回状态是3的所有订单
            for(int i=0;i<buyRelations.size();i++){
                BuyRelation buyRelation=buyRelations.get(i);
                String username=userService.finduserbyuserid(buyRelation.getUserid()).getUsername();
                Shoppingcart shoppingcart=new Shoppingcart();
                shoppingcart.setBuynum(buyRelation.getBuynum());
                shoppingcart.setGoodsid(goodsid);
                shoppingcart.setGoodsname(goodsBean.getGoodsname());
                String goodstotalprice=String.valueOf((Double.valueOf(goodsBean.getGoodsdiscount())/10)*Double.valueOf(goodsBean.getGoodsoriginalprice())*Double.valueOf(buyRelation.getBuynum()));
                BigDecimal bd = new BigDecimal(goodstotalprice);
                goodstotalprice = String.valueOf(bd.setScale(2, BigDecimal.ROUND_HALF_UP));
                String goodspic1path=goodsBean.getGoodspic1path();
                goodspic1path=goodspic1path.replaceAll( "\\\\",    "/");
                goodspic1path="http://127.0.0.1:8888/merchantshop/getgoodpics?storename="+goodspic1path;
                String price=String.valueOf(Double.parseDouble(goodsBean.getGoodsoriginalprice())*Double.parseDouble(goodsBean.getGoodsdiscount())/10);
                BigDecimal bd1 = new BigDecimal(price);
                price = String.valueOf(bd1.setScale(2, BigDecimal.ROUND_HALF_UP));
                shoppingcart.setGoodstotalprice(goodstotalprice);
                shoppingcart.setGoodsoriginalprice(price);
                shoppingcart.setGoodsdiscount(goodsBean.getGoodsdiscount()+"折");
                String purchasedate= (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(buyRelation.getOrdertime().getTime()-8*60*60*1000));
                shoppingcart.setPurchasedate(purchasedate);
                shoppingcart.setUsername(username);
                shoppingcart.setGoodspic1path(goodspic1path);
                shoppingcart.setBuyrelationid(buyRelation.getBuyrelationid());
                shoppingcarts.add(shoppingcart);
            }
        }
        return shoppingcarts;

    }

    //查看钱包 使用merchantname进行查询
    @CrossOrigin
    @RequestMapping("/checkmerchantwallet")
    public MerchantBean checkmerchantwallet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String merchantname = request.getParameter("merchantname");
        MerchantBean merchantBean=merchantService.findpathbymerchantname(merchantname);
        return merchantBean;
    }



}
