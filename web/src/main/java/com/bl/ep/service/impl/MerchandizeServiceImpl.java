package com.bl.ep.service.impl;

import com.bl.ep.dao.MerchandizeCategoryMapper;
import com.bl.ep.dao.MerchandizeMapper;
import com.bl.ep.param.MerchandizeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Merchandize;
import com.bl.ep.pojo.MerchandizeCategory;
import com.bl.ep.service.MerchandizeService;
import com.bl.ep.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MerchandizeServiceImpl implements MerchandizeService {

    @Autowired
    private MerchandizeMapper merchandizeMapper;

    @Autowired
    private MerchandizeCategoryMapper merchandizeDetailMapper;

    @Override
    public List<Merchandize> merchandizeList(MerchandizeParam param, PageParam pageParam) {

        PageHelper.startPage(pageParam.getPage(), pageParam.getRows());
        Example example = new Example(Merchandize.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(param.getTitle())){
            criteria.andLike("title","%"+param.getTitle()+"%");
        }
        PageUtils.pageHelperOrderBy(example, pageParam);
        return merchandizeMapper.selectByExample(example);
    }

    @Override
    public List<MerchandizeCategory> getMerchandizeCategory(Integer merchandizeId) {
        if(merchandizeId==null)
            return null;

        MerchandizeCategory param = new MerchandizeCategory();
        param.setMerchandizeId(merchandizeId);
        return merchandizeDetailMapper.select(param);
    }

    @Override
    public Merchandize getMerchandizeInfoById(Integer merchandizeId) {
        if(merchandizeId==null)
            return null;

        Merchandize param = new Merchandize();
        param.setId(merchandizeId);
        return merchandizeMapper.selectOne(param);
    }
}
