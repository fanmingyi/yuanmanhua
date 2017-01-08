package carton.fmy.com.yuanmanhua;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import com.baidu.appx.BaiduAppX;
import com.baidu.autoupdatesdk.UICheckUpdateCallback;

import java.util.ArrayList;

import static com.baidu.autoupdatesdk.BDAutoUpdateSDK.silenceUpdateAction;
import static com.baidu.autoupdatesdk.BDAutoUpdateSDK.uiUpdateAction;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MyAplication extends Application {

    //保存所有的activity
    static public ArrayList<Activity> activities = new ArrayList<>();
    //实例
    private  static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        BaiduAppX.version();
        //开启静默下载 wifi时自动下载
        silenceUpdateAction(MyAplication.this);
        //保存实例
        application = MyAplication.this;

    }
    //获取本类实例
    public static Application getInstant() {
        return  application;
    }

    //推出程序
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                //从集合中移除
                activities.remove(activity);
                activity.finish();
            }
        }
    }

    //推出程序的时候回调
    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    //版本更新检查
    static public void checkUpdata(Context context) {
        uiUpdateAction(context, new UICheckUpdateCallback() {
            @Override
            public void onCheckComplete() {

            }
        });
    }
}
