package carton.fmy.com.yuanmanhua.url;

import java.util.ArrayList;

import carton.fmy.com.yuanmanhua.bean.HomeBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 用于加载第一页home漫画数据
 */

public  interface UrlTodayRecommendInterface {
        /*排行*/
        @GET("http://a121.baopiqi.com/api/mh/getBoutique.php?&appname=%E6%9E%9C%E6%9E%9C%E6%BC%AB%E7%94%BB&pkgname=com.platform.cartoonf&imei=861110039989046&versionname=1.3.1")
        Call<ArrayList<HomeBean>> loadPh(@Query("page") String page, @Query("limit") String limit);
}
