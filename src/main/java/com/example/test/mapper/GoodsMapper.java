package com.example.test.mapper;

import com.example.test.bean.BuyRelation;
import com.example.test.bean.GoodsBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsMapper {
    @Select("select * from goods WHERE storeid=#{storeid}  and goodsstate='1'")
    List<GoodsBean> findbystoreid(@Param("storeid")String storeid);

    @Select("select * from goods WHERE storeid=#{storeid} and goodsstate='2'")
    List<GoodsBean> finddeletedbystoreid(@Param("storeid")String storeid);

    @Select("select * from goods WHERE goodsstate= '0'")
    List<GoodsBean> findAll();

    @Select("select * from goods WHERE goodsstate= '1'")//0是未审核，1是审核过的
    List<GoodsBean> findAllagreed();

    @Select("select * from buyralation  where goodsid=#{goodsid}")
    List<BuyRelation> findbygoodsid(@Param("goodsid")String goodsid);

    @Select("select * from buyralation  where goodsid=#{goodsid} and state='2'")
    List<BuyRelation> findfinishbygoodsid(@Param("goodsid")String goodsid);

    @Select("select * from buyralation  where goodsid=#{goodsid} and state='2' and merchantevastate='0'")
    List<BuyRelation> findfinishbygoodsidtoeva(@Param("goodsid")String goodsid);

    @Select("select * from buyralation  where goodsid=#{goodsid} and state='2' and merchantevastate='1'")
    List<BuyRelation> findfinishbygoodsidevaed(@Param("goodsid")String goodsid);

    @Select("select * from goods WHERE goodsstate= '1' and goodstype='电子产品'")
    List<GoodsBean> getelectronicgoods();

    @Update("update buyralation set merchantevastate='1' where buyrelationid=#{buyrelationid}")
    int saveevaluate(@Param("buyrelationid")String buyrelationid);


    @Select("select * from goods WHERE goodsstate= '1' and goodstype='文具'")
    List<GoodsBean> getstationerygoods();

    @Select("select * from goods WHERE goodsstate= '1' and goodstype='书籍'")
    List<GoodsBean> getbookgoods();

    @Select("select * from goods WHERE goodsstate= '1' and goodstype='生活用品'")
    List<GoodsBean> getlivelihoodgoods();

    @Select("select * from goods WHERE goodsstate= '1' and goodstype='零食'")
    List<GoodsBean> getsnackgoods();

    @Select("select * from goods where goodsname like '%${words}%' and goodsstate= '1'")
    List<GoodsBean> findgoodsbywords(@Param("words")String words);

    @Select("select * from goods  where goodsid=#{goodsid}")
    GoodsBean checkGoods(@Param("goodsid")String goodsid);


    @Select("select * from buyralation  where goodsid=#{goodsid} and state='3'")
    List<BuyRelation> findreturnbygoodsid(@Param("goodsid")String goodsid);

    @Select("select * from buyralation  where goodsid=#{goodsid} and state='4'")
    List<BuyRelation> findreturnagreed(@Param("goodsid")String goodsid);

    @Select("select * from buyralation  where goodsid=#{goodsid} and state='1'")
    List<BuyRelation> getdoingorder(@Param("goodsid")String goodsid);

    @Update("update goods set goodsgoodrate=#{goodsgoodrate} where goodsid=#{goodsid}")
    int updategoodrate(@Param("goodsid")String goodsid,@Param("goodsgoodrate")String goodsgoodrate);

    @Update("update goods set goodsstate='1' where goodsid=#{goodsid}")
    int agreeGoods(@Param("goodsid")String goodsid);

    @Select("select * from goods  where merchantid=#{merchantid}")
    List<GoodsBean> checkGoodsbuymerchantid(@Param("merchantid")String merchantid);

    @Delete("delete from goods  where goodsid=#{goodsid}")
    int disagreeGoods(@Param("goodsid")String goodsid);

    @Insert("INSERT into goods(goodsname, goodstype,goodsallowbargin,goodsstock,goodssize,goodsexplain," +
            "goodsoldandnew, goodsoriginalprice,goodsdiscount,goodspic1path,goodspic2path,goodspic3path,merchantid,storeid) " +
            "VALUES(#{goodsname},#{goodstype1},#{goodsallowbargin1},#{goodsstock},#{goodssize},#{goodsexplain}," +
            "#{goodsoldandnew1},#{goodsoriginalprice},#{goodsdiscount},#{goodspic1path},#{goodspic2path},#{goodspic3path},#{merchantid},#{storeid})")
    int saveGoods(@Param("goodsname")String goodsname, @Param("goodstype1")String goodstype1, @Param("goodsallowbargin1")String goodsallowbargin1,@Param("goodsstock")String goodsstock, @Param("goodssize")String goodssize,@Param("goodsexplain")String goodsexplain,
                  @Param("goodsoldandnew1")String goodsoldandnew1, @Param("goodsoriginalprice")String  goodsoriginalprice, @Param("goodsdiscount")String goodsdiscount,
                  @Param("goodspic1path")String goodspic1path,@Param("goodspic2path")String goodspic2path,@Param("goodspic3path")String goodspic3path,@Param("merchantid")String merchantid,@Param("storeid")String storeid);

    @Update("update  goods set goodsstate='2' where goodsid=#{goodsid}")
    int deleteGoods(@Param("goodsid")String goodsid);

    @Update("update goods set goodsstock=#{goodsstock},goodssales=#{goodssales} where goodsid=#{goodsid}")
    int salegoods(@Param("goodsid")String goodsid,@Param("goodsstock")String goodsstock,@Param("goodssales")String goodssales);

}
