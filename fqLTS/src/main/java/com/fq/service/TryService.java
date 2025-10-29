package com.fq.service;


import com.fq.dao.UserOaidGoodsDao;
import com.fq.entity.UserOaidGoods;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class TryService {
    UserOaidGoodsDao userOaidGoodsDao;

    public UserOaidGoods getUser(List<String> ids){
        return userOaidGoodsDao.findInterestGoods(ids).getFirst();
    }

}
