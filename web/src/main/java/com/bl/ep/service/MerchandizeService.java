package com.bl.ep.service;

import com.bl.ep.param.MerchandizeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Merchandize;

import java.util.List;

public interface MerchandizeService {

    public List<Merchandize> merchandizeList(MerchandizeParam param, PageParam pageParam);
}
