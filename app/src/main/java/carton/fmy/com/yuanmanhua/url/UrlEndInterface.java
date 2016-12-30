package carton.fmy.com.yuanmanhua.url;

import java.util.ArrayList;

import carton.fmy.com.yuanmanhua.bean.HomeBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 完结类型的漫画
 *
 * http://a121.baopiqi.com/api/mh/getClassificationCartoon.php?&appname=%E6%9E%9C%E6%9E%9C%E6%BC%AB%E7%94%BB&pkgname=com.platform.cartoonf&imei=861110039989046&versionname=1.3.1&id=1&page=0&limit=20
 */

public  interface UrlEndInterface  {

        @GET("http://a121.baopiqi.com/api/mh/getClassificationCartoon.php?&appname=%E6%9E%9C%E6%9E%9C%E6%BC%AB%E7%94%BB&pkgname=com.platform.cartoonf&imei=861110039989046&versionname=1.3.1&id=1")
        Flowable<ArrayList<HomeBean>> load(@Query("page") String page, @Query("limit") String limit);
}
