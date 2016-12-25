package carton.fmy.com.yuanmanhua.url;

import carton.fmy.com.yuanmanhua.bean.CatalogueBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/23.
 * 获取书籍目录
 */

public interface UrlCatalogueInterface {
    @GET("http://a121.baopiqi.com/api/mh/getCartoonInfo.php?&appname=%E7%88%B1%E6%83%85%E6%BC%AB%E7%94%BB%E7%B2%BE%E9%80%89&pkgname=com.platform.cartoonl&imei=863191020203140&versionname=1.2.7&")
    Call<CatalogueBean> get(@Query("id") String bookId);
}
