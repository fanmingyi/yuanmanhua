package carton.fmy.com.yuanmanhua.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.util.Log;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.bean.HomeBean;
import carton.fmy.com.yuanmanhua.url.UrlSearchInterface;
import carton.fmy.com.yuanmanhua.utils.NetUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        UrlSearchInterface urlSearchInterface = NetUtil.getRetrofit().create(UrlSearchInterface.class);
        Call<String> test = urlSearchInterface.load(URLEncoder.encode("一拳超人"));
        test.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                Log.i( "onResponse: ", "onResponse: ");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i( "onResponse: ", "onFailure: ");
            }
        });
//        test.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ArrayList<HomeBean>>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(ArrayList<HomeBean> homeBeen) {
//                Log.i("", "onNext: ");
//            }
//        });
    }
}
