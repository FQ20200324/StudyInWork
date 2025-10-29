package com.fq.dao;

import com.fq.basic.BaseDao;
import com.fq.entity.UserOaidGoods;
import org.springframework.data.jdbc.repository.query.Query;

import java.util.Collection;
import java.util.List;

public interface UserOaidGoodsDao extends BaseDao<UserOaidGoods, Integer> {

    @Query("SELECT oaid, goods FROM dwd_user_oaid_goods_dt WHERE manufacturer = 'samsung_ai' and oaid IN (:deviceId)")
    List<UserOaidGoods> findInterestGoods(Collection<String> deviceId);

}
