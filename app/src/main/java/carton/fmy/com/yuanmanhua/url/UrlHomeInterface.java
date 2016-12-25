package carton.fmy.com.yuanmanhua.url;

import java.util.ArrayList;

import carton.fmy.com.yuanmanhua.bean.HomeBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 用于加载第一页home漫画数据
 */

public  interface UrlHomeInterface {

        @GET("http://a121.baopiqi.com/api/mh/getCartoonRankingMore.php?type=4&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7")
        Call<ArrayList<HomeBean>> loadPh(@Query("page")String page, @Query("limit")String limit);
}
