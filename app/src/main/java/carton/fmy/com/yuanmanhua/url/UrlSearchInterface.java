package carton.fmy.com.yuanmanhua.url;

import java.util.ArrayList;

import carton.fmy.com.yuanmanhua.bean.HomeBean;
import carton.fmy.com.yuanmanhua.bean.ShowBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * asdasdasd
 */

public  interface UrlSearchInterface {

        @GET("http://a121.baopiqi.com/api/mh/getSearchCartoon.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&limit=200&page=0")
        Call<String> load(@Query("key") String key);

}
