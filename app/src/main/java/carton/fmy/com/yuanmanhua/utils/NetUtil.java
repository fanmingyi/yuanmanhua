package carton.fmy.com.yuanmanhua.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/12/21.
 */

public class NetUtil {
    private  static  Retrofit retrofit;



    static {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()) // 使用Gson作为数据转换器
                .build();
    }
    public  static Retrofit getRetrofit(){
        return  retrofit;
    }
}
