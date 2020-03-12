package com.bawei.zhangjiafu20200311;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;

/**
 * @author: 张家辅
 * @date: 2020/03/11
 */
public interface MyService {
    @GET(Api.ORDER_URL)
    Observable<Entity> get(@HeaderMap Map<String,String> headermap, @QueryMap Map<String,String> querymap);
}
