package com.bl.ep.utils;

import com.bl.ep.molds.Param;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Home;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;

public class PageUtils {

    /**
     * 设置排序方式
     * @param example pageHelper分页插件类
     * @param pageParam 分页参数
     */
    public static void pageHelperOrderBy(Example example, PageParam pageParam){
        if(!StringUtils.isEmpty(pageParam.getSidx())){
            Example.OrderBy orderBy = example.orderBy(pageParam.getSidx());
            if(!StringUtils.isEmpty(pageParam.getSord())){
                if ("asc".equals(pageParam.getSord()))
                    orderBy.asc();
                else
                    orderBy.desc();
            }
        }else{
            Example.OrderBy orderBy = example.orderBy("createTime").desc();
        }
    }

    public static <T> Example getExample(PageParam pageParam, Class<T> tClass){
        PageHelper.startPage(pageParam.getPage(), pageParam.getRows());
        Example example = new Example(tClass);
        pageHelperOrderBy(example, pageParam);
        return example;
    }
}
