package com.example.test.serviceImpl;

import com.example.test.bean.BuyRelation;
import com.example.test.bean.EvaluateBean;
import com.example.test.bean.UserBean;
import com.example.test.mapper.UserMapper;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //将DAO注入Service层
    @Autowired(required=false)
    private UserMapper userMapper;

    @Override
    public UserBean loginIn(String username, String password) {
        return userMapper.loginIn(username,password);
    }

    @Override
    public List<UserBean> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<UserBean> findallusered() {
        return userMapper.findallusered();
    }

    @Override
    public int updateUser(String userid, String username, String name, String usersex, String userphone, String email, String userbankcard, String usercity) {
        return userMapper.updateUser(userid,username,name,usersex,userphone,email,userbankcard,usercity);
    }

    @Override
    public int agreeUser(String userid) {
        return userMapper.agreeUser(userid);
    }

    @Override
    public String getuserbalance(String userid) {
        return userMapper.getuserbalance(userid);
    }

    @Override
    public UserBean finduserbyusername(String username) {
        return userMapper.finduserbyusername(username);
    }

    @Override
    public UserBean finduserbyuserid(String userid) {
        return userMapper.finduserbyuserid(userid);
    }

    @Override
    public List<BuyRelation> getallbygoodsid(String goodsid) {
        return userMapper.getallbygoodsid(goodsid);
    }

    @Override
    public List<BuyRelation> findrelation(String userid, String goodsid) {
        return userMapper.findrelation(userid,goodsid);
    }

    @Override
    public List<BuyRelation> getallbyuserid(String userid) {
        return userMapper.getallbyuserid(userid);
    }

    @Override
    public int agreereturn(String buyrelationid) {
        return userMapper.agreereturn(buyrelationid);
    }



    @Override
    public List<BuyRelation> findreturn(String userid, String goodsid) {
        return userMapper.findreturn(userid,goodsid);
    }

    @Override
    public List<BuyRelation> finddoing(String userid, String goodsid) {
        return userMapper.finddoing(userid,goodsid);
    }


    @Override
    public int updateusergoodrate(String userid, String usergoodrate) {
        return userMapper.updateusergoodrate(userid,usergoodrate);
    }

    @Override
    public List<BuyRelation> findrelationinshoppingcart(String userid, String goodsid,String buynum) {
        return userMapper.findrelationinshoppingcart(userid,goodsid,buynum);
    }

    @Override
    public int addbalance(String userid, String userbalance) {
        return userMapper.addbalance(userid,userbalance);
    }

    @Override
    public int subbalanceaddpoints(String userid, String userbalance, int goodsprice) {
        return userMapper.subbalanceaddpoints(userid,userbalance,goodsprice);
    }

    @Override
    public int disagreeUser(String userid) {
        return userMapper.disagreeUser(userid);
    }


    @Override
    public List<BuyRelation> getshoppingcart(String userid) {
        return userMapper.getshoppingcart(userid);
    }

    @Override
    public List<BuyRelation> getorderforming(String userid) {
        return userMapper.getorderforming(userid);
    }

    @Override
    public List<BuyRelation> getorderformedtoevaluate(String userid) {
        return userMapper.getorderformedtoevaluate(userid);
    }

    @Override
    public List<BuyRelation> getreturnedgoods(String userid) {
        return userMapper.getreturnedgoods(userid);
    }

    @Override
    public BuyRelation findbybuyrelationid(String buyrelationid) {
        return userMapper.findbybuyrelationid(buyrelationid);
    }

    @Override
    public List<BuyRelation> getreturninggoods(String userid) {
        return userMapper.getreturninggoods(userid);
    }

    @Override
    public List<BuyRelation> getorderformedevaluated(String userid) {
        return userMapper.getorderformedevaluated(userid);
    }

    @Override
    public int evaluatefinish(String buyrelationid) {
        return userMapper.evaluatefinish(buyrelationid);
    }

    @Override
    public int deleteallfromshoppingcart(String userid) {
        return userMapper.deleteallfromshoppingcart(userid);
    }

    @Override
    public int deleteonefromshoppingcart(String buyrelationid) {
        return userMapper.deleteonefromshoppingcart(buyrelationid);
    }

    @Override
    public int deletefromshoppingcart(String buyrelationid) {
        return userMapper.deletefromshoppingcart(buyrelationid);
    }

    @Override
    public int savebuyrelation(String userid, String goodsid, String buynum) {
        return userMapper.savebuyrelation(userid,goodsid,buynum);
    }

    @Override
    public int buygoods(String userid, String goodsid, String buynum, Date ordertime) {
        return userMapper.buygoods(userid,goodsid,buynum,ordertime);
    }


    @Override
    public int addevaluate(String buyrelationid, String content, String goodrate,String merchantgoodrate) {
        return userMapper.addevaluate(buyrelationid,content,goodrate,merchantgoodrate);
    }

    @Override
    public List<EvaluateBean> findevaluatebybuyrelationid(String buyrelationid) {
        return userMapper.findevaluatebybuyrelationid(buyrelationid);
    }

    @Override
    public int buyfromshoppingcart(String buyrelationid, Date ordertime) {
        return userMapper.buyfromshoppingcart(buyrelationid,ordertime);
    }

    @Override
    public int returngoods(String buyrelationid) {
        return userMapper.returngoods(buyrelationid);
    }

    @Override
    public int buyfinish(String buyrelationid) {
        return userMapper.buyfinish(buyrelationid);
    }


    @Override
    public int saveUser(String username, String password,String name,String usersex,String userphone,String email,String userbankcard,String usercity) {
        return userMapper.saveUser(username,password,name,usersex,userphone,email,userbankcard,usercity);
    }

}
