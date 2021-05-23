package com.example.test.serviceImpl;

import com.example.test.bean.AdminBean;
import com.example.test.bean.MerchantBean;
import com.example.test.bean.StoreBean;
import com.example.test.bean.UserBean;
import com.example.test.mapper.MerchantMapper;
import com.example.test.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired(required=false)
    private MerchantMapper merchantMapper;

    @Override
    public int disagreeMerchant(String merchantid) {
        return merchantMapper.disagreeMerchant(merchantid);
    }

    @Override
    public int agreeMerchant(String merchantid) {
        return merchantMapper.agreeMerchant(merchantid);
    }

    @Override
    public int editMerchantrank(String merchantid, String merchantrank) {
        return merchantMapper.editMerchantrank(merchantid,merchantrank);
    }

    @Override
    public int addevaluate(String buyrelationid, String usergoodrate) {
        return merchantMapper.addevaluate(buyrelationid,usergoodrate);
    }

    @Override
    public int addbalance(String merchantid, String merchantbalance) {
        return merchantMapper.addbalance(merchantid,merchantbalance);
    }


    @Override
    public int updatemerchantgoodrate(String merchantid,String merchantgoodrate) {
        return merchantMapper.updatemerchantgoodrate(merchantid,merchantgoodrate);
    }

    @Override
    public List<MerchantBean> findAll() {
        return merchantMapper.findAll();
    }

    @Override
    public List<MerchantBean> findallmerchanted() {
        return merchantMapper.findallmerchanted();
    }

    @Override
    public MerchantBean findpathbymerchantname(String merchantname) {
        return merchantMapper.findpathbymerchantname(merchantname);
    }

    @Override
    public int saveMerchantpicpath(String merchantname, String businesslicensepath, String zidentitycardpath, String fidentitycardpath) {
        return merchantMapper.saveMerchantpicpath(merchantname,businesslicensepath,zidentitycardpath,fidentitycardpath);
    }

    @Override
    public int saveMerchantbusinesspath(String merchantname, String businesslicensepath) {
        return merchantMapper.saveMerchantbusinesspath(merchantname,businesslicensepath);
    }

    @Override
    public int saveMerchantidcardpath(String merchantname, String zidentitycardpath, String fidentitycardpath) {
        return merchantMapper.saveMerchantidcardpath(merchantname,zidentitycardpath,fidentitycardpath);
    }


    @Override
    public MerchantBean loginIn(String merchantname, String merchantpassword) {
        return merchantMapper.loginIn(merchantname,merchantpassword);
    }

    @Override
    public StoreBean findbystorename(String storename) {
        return merchantMapper.findbystorename(storename);
    }

    @Override
    public StoreBean findpathbystoreid(String storeid) {
        return merchantMapper.findpathbystoreid(storeid);
    }

    @Override
    public StoreBean findstorebymerchantid(String merchantid) {
        return merchantMapper.findstorebymerchantid(merchantid);
    }

    @Override
    public MerchantBean findbymerchantid(String merchantid) {
        return merchantMapper.findbymerchantid(merchantid);
    }

    @Override
    public int updateMerchant(String merchantid, String merchantname, String merchantbankcard, String merchantrealname, String merchantsex, String merchantphone) {
        return merchantMapper.updateMerchant(merchantid,merchantname,merchantbankcard,merchantrealname,merchantsex,merchantphone);
    }

    @Override
    public int saveMerchant(String merchantname,String  merchantpassword,String merchantbankcard,String merchantrealname,String merchantsex,String merchantphone) {
        return merchantMapper.saveMerchant(merchantname, merchantpassword,merchantbankcard,merchantrealname,merchantsex,merchantphone);
    }

    @Override
    public int saveStore(String storename, String merchantid) {
        return merchantMapper.saveStore(storename,merchantid);
    }


}
