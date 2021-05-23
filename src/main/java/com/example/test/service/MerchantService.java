package com.example.test.service;

import com.example.test.bean.AdminBean;
import com.example.test.bean.MerchantBean;
import com.example.test.bean.StoreBean;
import com.example.test.bean.UserBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MerchantService {
    @Delete("delete from merchant  where merchantid=#{merchantid}")
    int disagreeMerchant(@Param("merchantid")String merchantid);

    @Update("update merchant set merchantstate='1' where merchantid=#{merchantid}")
    int agreeMerchant(@Param("merchantid")String merchantid);

    @Update("update merchant set merchantrank=#{merchantrank} where merchantid=#{merchantid}")
    int editMerchantrank(@Param("merchantid")String merchantid,@Param("merchantrank")String merchantrank);

    @Update("update merchant set merchantbalance=#{merchantbalance} where merchantid=#{merchantid}")
    int addbalance(@Param("merchantid")String merchantid,@Param("merchantbalance")String merchantbalance);

    @Select("select * from merchant WHERE merchantstate= '0'")
    List<MerchantBean> findAll();

    @Select("select * from merchant WHERE merchantstate= '1'")
    List<MerchantBean> findallmerchanted();

    @Select("select * from merchant WHERE merchantname = #{merchantname}")
    MerchantBean findpathbymerchantname(@Param("merchantname")String merchantname);

    @Update("update merchant set businesslicensepath=#{businesslicensepath}, zidentitycardpath=#{zidentitycardpath},fidentitycardpath=#{fidentitycardpath}" +
            "where merchantname=#{merchantname}")
    int saveMerchantpicpath(@Param("merchantname")String merchantname,@Param("businesslicensepath")String businesslicensepath, @Param("zidentitycardpath")String zidentitycardpath,
                            @Param("fidentitycardpath")String fidentitycardpath);

    @Update("update merchant set businesslicensepath=#{businesslicensepath}" +
            "where merchantname=#{merchantname}")
    int saveMerchantbusinesspath(@Param("merchantname")String merchantname,@Param("businesslicensepath")String businesslicensepath);

    @Update("update merchant set  zidentitycardpath=#{zidentitycardpath},fidentitycardpath=#{fidentitycardpath}" +
            "where merchantname=#{merchantname}")
    int saveMerchantidcardpath(@Param("merchantname")String merchantname, @Param("zidentitycardpath")String zidentitycardpath,
                            @Param("fidentitycardpath")String fidentitycardpath);

    @Select("SELECT * FROM merchant WHERE merchantname = #{merchantname} AND merchantpassword = #{merchantpassword} AND merchantstate='1'")
    MerchantBean loginIn(@Param("merchantname")String merchantname, @Param("merchantpassword")String merchantpassword);

    @Select("SELECT * FROM store WHERE storename = #{storename}")
    StoreBean findbystorename(@Param("storename")String storename);

    @Select("SELECT * FROM store WHERE storeid = #{storeid}")
    StoreBean findpathbystoreid(@Param("storeid")String storeid);

    @Select("SELECT * FROM store WHERE merchantid = #{merchantid}")
    StoreBean findstorebymerchantid(@Param("merchantid")String merchantid);

    @Select("SELECT * FROM merchant WHERE merchantid = #{merchantid}")
    MerchantBean findbymerchantid(@Param("merchantid")String merchantid);

    @Update("update merchant set merchantgoodrate=#{merchantgoodrate} where merchantid=#{merchantid}")
    int updatemerchantgoodrate(@Param("merchantid")String merchantid,@Param("merchantgoodrate")String merchantgoodrate);

    @Update("update  merchant set merchantname=#{merchantname},merchantbankcard=#{merchantbankcard}," +
            "merchantrealname=#{merchantrealname},merchantsex=#{merchantsex},merchantphone=#{merchantphone} " +
            "where merchantid=#{merchantid}")
    int updateMerchant(@Param("merchantid")String merchantid,@Param("merchantname")String merchantname,@Param("merchantbankcard")String merchantbankcard,
                     @Param("merchantrealname")String merchantrealname,@Param("merchantsex")String merchantsex,@Param("merchantphone")String merchantphone);


    @Insert("INSERT into merchant(merchantname, merchantpassword,merchantbankcard,merchantrealname,merchantsex,merchantphone) " +
            "VALUES(#{merchantname},#{merchantpassword},#{merchantbankcard},#{merchantrealname},#{merchantsex},#{merchantphone})")
    int saveMerchant(@Param("merchantname")String merchantname, @Param("merchantpassword")String merchantpassword,@Param("merchantbankcard")String merchantbankcard,
                 @Param("merchantrealname")String merchantrealname,@Param("merchantsex")String merchantsex,@Param("merchantphone")String merchantphone);

    @Insert("INSERT into store(storename,merchantid) " +
            "VALUES(#{storename},#{merchantid})")
    int saveStore(@Param("storename")String storename,@Param("merchantid")String merchantid);

    @Insert("INSERT into evaluate(buyrelationid,usergoodrate) " +
            "VALUES(#{buyrelationid},#{usergoodrate})")
    int addevaluate(@Param("buyrelationid")String buyrelationid,@Param("usergoodrate")String usergoodrate);



}
