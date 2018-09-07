package com.bl.ep.service;

import com.bl.ep.dao.MerchandizeCategoryMapper;
import com.bl.ep.dao.MerchandizeCollectMapper;
import com.bl.ep.dao.MerchandizeMapper;
import com.bl.ep.param.MerchandizeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Merchandize;
import com.bl.ep.pojo.MerchandizeCategory;
import com.bl.ep.pojo.MerchandizeCollect;
import com.bl.ep.pojo.Request;
import com.bl.ep.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class MerchandizeService{

    @Autowired
    private MerchandizeMapper merchandizeMapper;

    @Autowired
    private MerchandizeCollectMapper merchandizeCollectMapper;

    @Autowired
    private MerchandizeCategoryMapper merchandizeDetailMapper;

    /**
     * 根据商品属性为条件筛选记录、默认获取第一页数据、999条
     * @param param 商品的属性
     * @param pageParam 分页属性
     * @return 根据条件筛选出的商品集合
     */
    public List<Merchandize> merchandizeList(MerchandizeParam param, PageParam pageParam) {


        //设置分页开始和分页条数
        Example example = PageUtils.getExample(pageParam, Merchandize.class);
        //设置查询条件
        if(!StringUtils.isEmpty(param.getTitle())){
            example.createCriteria().andLike("title","%"+param.getTitle()+"%");
        }
        //根据条件获取数据集合
        return merchandizeMapper.selectByExample(example);
    }

    /**
     * 根据商品Id获取属性Id
     * @param merchandizeId 商品id
     * @return 获取当前属性集合
     */
    public List<MerchandizeCategory> getMerchandizeCategory(Integer merchandizeId) {
        //如何没有传入商品id 返回空
        if(merchandizeId==null)
            return null;
        //设置查询条件
        MerchandizeCategory param = new MerchandizeCategory();
        param.setMerchandizeId(merchandizeId);
        //根据查询条件查询数据
        return merchandizeDetailMapper.select(param);
    }

    /**
     * 根据商品Id获取商品详情
     * @param merchandizeId 商品id
     * @return 商品详情
     */
    public Merchandize getMerchandizeInfoById(Integer merchandizeId) {
        //如何没有传入商品id 返回空
        if(merchandizeId==null)
            return null;
        //根据查询条件查询数据
        return merchandizeMapper.selectByPrimaryKey(merchandizeId);
    }

    /**
     * 收藏商品
     * @param userId 用户id
     * @param categoryId 商品种类Id
     */
    public void addMerchandizeCollect(Integer userId, Integer categoryId) {
        MerchandizeCollect collect = new MerchandizeCollect();
        collect.setUserId(userId);
        collect.setMerchandizeCategoryId(categoryId);
        collect.setIsDelete((byte)0);
        collect.setCreateTime(new Date());
        merchandizeCollectMapper.insertSelective(collect);
    }
}
