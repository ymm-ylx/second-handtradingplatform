package com.example.test.serviceImpl;

import com.example.test.bean.BuyRelation;
import com.example.test.bean.GoodsBean;
import com.example.test.mapper.GoodsMapper;
import com.example.test.service.GoodsService;
import com.example.test.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired(required=false)
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsBean> findbystoreid(String storeid) {
        return goodsMapper.findbystoreid(storeid);
    }

    @Override
    public int saveevaluate(String buyrelationid) {
        return goodsMapper.saveevaluate(buyrelationid);
    }

    @Override
    public List<BuyRelation> findreturnagreed(String goodsid) {
        return goodsMapper.findreturnagreed(goodsid);
    }

    @Override
    public List<BuyRelation> findfinishbygoodsidtoeva(String goodsid) {
        return goodsMapper.findfinishbygoodsidtoeva(goodsid);
    }

    @Override
    public List<BuyRelation> findfinishbygoodsidevaed(String goodsid) {
        return goodsMapper.findfinishbygoodsidevaed(goodsid);
    }

    @Override
    public List<BuyRelation> getdoingorder(String goodsid) {
        return goodsMapper.getdoingorder(goodsid);
    }

    @Override
    public List<BuyRelation> findfinishbygoodsid(String goodsid) {
        return goodsMapper.findfinishbygoodsid(goodsid);
    }

    @Override
    public List<BuyRelation> findreturnbygoodsid(String goodsid) {
        return goodsMapper.findreturnbygoodsid(goodsid);
    }


    @Override
    public List<GoodsBean> checkGoodsbuymerchantid(String merchantid) {
        return goodsMapper.checkGoodsbuymerchantid(merchantid);
    }

    @Override
    public int updategoodrate(String goodsid,String goodsgoodrate) {
        return goodsMapper.updategoodrate(goodsid,goodsgoodrate);
    }

    @Override
    public List<BuyRelation> findbygoodsid(String goodsid) {
        return goodsMapper.findbygoodsid(goodsid);
    }

    @Override
    public List<GoodsBean> finddeletedbystoreid(String storeid) {
        return goodsMapper.finddeletedbystoreid(storeid);
    }

    @Override
    public List<GoodsBean> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public List<GoodsBean> findAllagreed() {
        return goodsMapper.findAllagreed();
    }

    @Override
    public List<GoodsBean> getelectronicgoods() {
        return goodsMapper.getelectronicgoods();
    }

    @Override
    public List<GoodsBean> getstationerygoods() {
        return goodsMapper.getstationerygoods();
    }

    @Override
    public List<GoodsBean> getbookgoods() {
        return goodsMapper.getbookgoods();
    }

    @Override
    public List<GoodsBean> getlivelihoodgoods() {
        return goodsMapper.getlivelihoodgoods();
    }

    @Override
    public List<GoodsBean> getsnackgoods() {
        return goodsMapper.getsnackgoods();
    }


    @Override
    public List<GoodsBean> findgoodsbywords(String words) {
        return goodsMapper.findgoodsbywords(words);
    }

    @Override
    public int agreeGoods(String goodsid) {
        return goodsMapper.agreeGoods(goodsid);
    }

    @Override
    public int disagreeGoods(String goodsid) {
        return goodsMapper.disagreeGoods(goodsid);
    }

    @Override
    public GoodsBean checkGoods(String goodsid) {
        return goodsMapper.checkGoods(goodsid);
    }


    @Override
    public int saveGoods(String goodsname,String goodstype1,String goodsallowbargin1,String goodsstock, String goodssize,
                         String goodsexplain,String goodsoldandnew1,String goodsoriginalprice,String goodsdiscount,
                         String goodspic1path,String goodspic2path,String goodspic3path,String merchantid,String storeid) {
        return goodsMapper.saveGoods(goodsname,goodstype1,goodsallowbargin1,goodsstock, goodssize,
                goodsexplain,goodsoldandnew1,goodsoriginalprice,goodsdiscount,goodspic1path,goodspic2path,goodspic3path,merchantid,storeid);
    }

    @Override
    public int deleteGoods(String goodsid) {
        return goodsMapper.deleteGoods(goodsid);
    }

    @Override
    public int salegoods(String goodsid, String goodsstock,String goodssales) {
        return goodsMapper.salegoods(goodsid,goodsstock,goodssales);
    }

}
