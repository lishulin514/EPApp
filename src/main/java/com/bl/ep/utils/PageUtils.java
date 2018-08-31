package com.bl.ep.utils;

import com.bl.ep.param.PageParam;
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
}
