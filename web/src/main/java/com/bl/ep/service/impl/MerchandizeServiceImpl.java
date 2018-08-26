package com.bl.ep.service.impl;

import com.bl.ep.dao.MerchandizeMapper;
import com.bl.ep.param.MerchandizeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Merchandize;
import com.bl.ep.service.MerchandizeService;
import com.bl.ep.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MerchandizeServiceImpl implements MerchandizeService {

    private MerchandizeMapper merchandizeMapper;

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
}
