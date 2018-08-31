package com.bl.ep.service;

import com.bl.ep.dao.MerchandizeCategoryMapper;
import com.bl.ep.dao.MerchandizeMapper;
import com.bl.ep.param.MerchandizeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Merchandize;
import com.bl.ep.pojo.MerchandizeCategory;
import com.bl.ep.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MerchandizeService{

    @Autowired
    private MerchandizeMapper merchandizeMapper;

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
        PageHelper.startPage(pageParam.getPage(), pageParam.getRows());


        Example example = new Example(Merchandize.class);
        Example.Criteria criteria = example.createCriteria();
        //设置查询条件
        if(!StringUtils.isEmpty(param.getTitle())){
            criteria.andLike("title","%"+param.getTitle()+"%");
        }
        //设置排序条件
        PageUtils.pageHelperOrderBy(example, pageParam);
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
        //设置查询条件
        Merchandize param = new Merchandize();
        param.setId(merchandizeId);
        //根据查询条件查询数据
        return merchandizeMapper.selectOne(param);
    }
}
