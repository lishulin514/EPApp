package com.bl.ep.service;

import com.bl.ep.param.MerchandizeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Merchandize;
import com.bl.ep.pojo.MerchandizeCategory;

import java.util.List;

public interface MerchandizeService {

    public List<Merchandize> merchandizeList(MerchandizeParam param, PageParam pageParam);

    List<MerchandizeCategory> getMerchandizeCategory(Integer merchandizeId);

    Merchandize getMerchandizeInfoById(Integer merchandizeId);
}
