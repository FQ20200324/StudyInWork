package com.fq.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;


@Data
@Table(name = "dwd_user_oaid_goods_dt")
public class UserOaidGoods {
    @Id
    String oaid;
    String       ds;
    String       manufacturer;
    List<String> goods;

}
