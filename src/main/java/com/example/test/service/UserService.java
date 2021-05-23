package com.example.test.service;

import com.example.test.bean.BuyRelation;
import com.example.test.bean.EvaluateBean;
import com.example.test.bean.UserBean;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface UserService {

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password} AND userstate='1'")
    UserBean loginIn(@Param("username")String username, @Param("password")String password);

    @Select("select * from user WHERE userstate= '0'")
    List<UserBean> findAll();

    @Select("select * from user WHERE userstate= '1'")
    List<UserBean> findallusered();

    @Update("update user set username=#{username},name=#{name},usersex=#{usersex},userphone=#{userphone}," +
            "email=#{email},userbankcard=#{userbankcard},usercity=#{usercity} where userid=#{userid}")
    int updateUser(@Param("userid")String userid,@Param("username")String username,
                   @Param("name")String name,@Param("usersex")String usersex,@Param("userphone")String userphone,
                   @Param("email")String email,@Param("userbankcard")String userbankcard,@Param("usercity")String usercity);


    @Insert("INSERT into user(username, password,name,usersex,userphone,email,userbankcard,usercity) " +
            "VALUES(#{username},#{password},#{name},#{usersex},#{userphone},#{email},#{userbankcard},#{usercity})")
    int saveUser(@Param("username")String username, @Param("password")String password,@Param("name")String name,
                 @Param("usersex")String usersex,@Param("userphone")String userphone,@Param("email")String email,
                 @Param("userbankcard")String userbankcard,@Param("usercity")String usercity);



    @Update("update user set userstate='1' where userid=#{userid}")
    int agreeUser(@Param("userid")String userid);

    @Select("select userbalance from user where userid=#{userid}")
    String getuserbalance(@Param("userid")String userid);

    @Select("select * from user where username=#{username}")
    UserBean finduserbyusername(@Param("username")String username);

    @Select("select * from user where userid=#{userid}")
    UserBean finduserbyuserid(@Param("userid")String userid);

    @Select("select * from buyralation where goodsid=#{goodsid} and state='2'")
    List<BuyRelation> getallbygoodsid(@Param("goodsid")String goodsid);

    @Select("select * from buyralation where goodsid=#{goodsid} and userid=#{userid} and state='2'")
    List<BuyRelation> findrelation(@Param("userid")String userid,@Param("goodsid")String goodsid);

    @Select("select * from buyralation where goodsid=#{goodsid} and userid=#{userid} and (state='3' or state='4')")
    List<BuyRelation> findreturn(@Param("userid")String userid,@Param("goodsid")String goodsid);

    @Select("select * from buyralation where goodsid=#{goodsid} and userid=#{userid} and state='1'")
    List<BuyRelation> finddoing(@Param("userid")String userid,@Param("goodsid")String goodsid);


    @Select("select * from buyralation where goodsid=#{goodsid} and userid=#{userid} and state='0' and buynum=#{buynum}")
    List<BuyRelation> findrelationinshoppingcart(@Param("userid")String userid,@Param("goodsid")String goodsid,@Param("buynum")String buynum);

    @Update("update user set userbalance=#{userbalance} where userid=#{userid}")
    int addbalance(@Param("userid")String userid,@Param("userbalance")String userbalance);

    @Update("update user set usergoodrate=#{usergoodrate} where userid=#{userid}")
    int updateusergoodrate(@Param("userid")String userid,@Param("usergoodrate")String usergoodrate);

    @Update("update user set userbalance=#{userbalance},userpoints=userpoints+#{goodsprice} where userid=#{userid}")
    int subbalanceaddpoints(@Param("userid")String userid,@Param("userbalance")String userbalance,@Param("goodsprice")int goodsprice);

    @Delete("delete from user  where userid=#{userid}")
    int disagreeUser(@Param("userid")String userid);

    @Select("select * from buyralation WHERE state= '0' and userid=#{userid}")
    List<BuyRelation> getshoppingcart(@Param("userid")String userid);

    @Select("select * from buyralation WHERE state= '1' and userid=#{userid}")
    List<BuyRelation> getorderforming(@Param("userid")String userid);

    @Select("select * from buyralation WHERE state= '2' and userid=#{userid} and evaluatestate='0'")//2是已完成  0是未评价
    List<BuyRelation> getorderformedtoevaluate(@Param("userid")String userid);

    @Select("select * from buyralation WHERE state= '2' and userid=#{userid} and evaluatestate='1'")//2是已完成  1是已评价
    List<BuyRelation> getorderformedevaluated(@Param("userid")String userid);

    @Select("select * from buyralation WHERE state= '4' and userid=#{userid}")//4是退货审核已通过
    List<BuyRelation> getreturnedgoods(@Param("userid")String userid);

    @Select("select * from buyralation WHERE state= '3' and userid=#{userid}")//3是正在审核
    List<BuyRelation> getreturninggoods(@Param("userid")String userid);

    @Select("select * from buyralation WHERE  userid=#{userid}")
    List<BuyRelation> getallbyuserid(@Param("userid")String userid);

    @Select("select * from buyralation where buyrelationid=#{buyrelationid}")
    BuyRelation findbybuyrelationid(@Param("buyrelationid")String buyrelationid);

    @Delete("delete  from buyralation WHERE state= '0' and userid=#{userid}")
    int deleteallfromshoppingcart(@Param("userid")String userid);

    @Delete("delete  from buyralation WHERE state= '0' and buyrelationid=#{buyrelationid}")
    int deleteonefromshoppingcart(@Param("buyrelationid")String buyrelationid);

    @Delete("delete  from buyralation WHERE  buyrelationid=#{buyrelationid}")
    int deletefromshoppingcart(@Param("buyrelationid")String buyrelationid);

    @Insert("INSERT into buyralation(userid, goodsid,buynum,state) " +
            "VALUES(#{userid},#{goodsid},#{buynum},'1')")
    int savebuyrelation(@Param("userid")String userid, @Param("goodsid")String goodsid,@Param("buynum")String buynum);

    @Insert("INSERT into buyralation(userid,goodsid ,buynum,state,ordertime) " +
            "VALUES(#{userid},#{goodsid},#{buynum},'1',#{ordertime})")
    int buygoods(@Param("userid")String userid, @Param("goodsid")String goodsid,@Param("buynum")String buynum,@Param("ordertime") Date ordertime);


    @Select("select * from evaluate WHERE buyrelationid=#{buyrelationid}")
    List<EvaluateBean> findevaluatebybuyrelationid(@Param("buyrelationid")String buyrelationid);

    @Update("update buyralation set state='1',ordertime=#{ordertime} where buyrelationid=#{buyrelationid}")
    int buyfromshoppingcart(@Param("buyrelationid")String buyrelationid,@Param("ordertime")Date ordertime);

    @Update("update buyralation set state='3' where buyrelationid=#{buyrelationid}")
    int returngoods(@Param("buyrelationid")String buyrelationid);

    @Update("update buyralation set state='4' where buyrelationid=#{buyrelationid}")
    int agreereturn(@Param("buyrelationid")String buyrelationid);

    @Update("update buyralation set evaluatestate='1' where buyrelationid=#{buyrelationid}")//改为1，已评论
    int evaluatefinish(@Param("buyrelationid")String buyrelationid);

    @Update("update buyralation set state='2' where buyrelationid=#{buyrelationid}")
    int buyfinish(@Param("buyrelationid")String buyrelationid);

    @Insert("INSERT into evaluate(buyrelationid,content ,goodrate,merchantgoodrate) " +
            "VALUES(#{buyrelationid},#{content},#{goodrate},#{merchantgoodrate})")
    int addevaluate(@Param("buyrelationid")String buyrelationid, @Param("content")String content,@Param("goodrate")String goodrate,@Param("merchantgoodrate")String merchantgoodrate);



}
