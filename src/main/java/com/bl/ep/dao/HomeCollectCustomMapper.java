package com.bl.ep.dao;

import com.bl.ep.pojo.HomeCollect;
import com.bl.ep.utils.MyMapper;

import java.util.List;
/**
 * 跟数据库映射类（根据resources/mapper下的.xml文件配置映射到对应表）
 */
public interface HomeCollectCustomMapper{

    List<HomeCollect> getHomeCollect();
}
