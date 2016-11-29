package com.shenme.androiddemo.bean.product;



import com.shenme.androiddemo.bean.Pagination;

import java.util.List;

/**
 * Created by CANC on 2016/7/5.
 */
public class ElasticsearchResult {

    //商品数据
    public List<ElasticsearchProduct> listinfo;
    //筛选数据
    public List<FilterList> fiterList;
    //排序数据
    public List<SortList> sortList;
    //分页数据
    public Pagination pageinfo;
}
