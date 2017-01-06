package carton.fmy.com.yuanmanhua;

import android.app.Activity;
import android.app.Application;

import com.baidu.appx.BaiduAppX;

import java.util.ArrayList;

import static com.baidu.autoupdatesdk.BDAutoUpdateSDK.silenceUpdateAction;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MyAplication extends Application {

    //保存所有的activity
    static  public   ArrayList<Activity> activities = new ArrayList<>();



    @Override
    public void onCreate() {
        super.onCreate();
         silenceUpdateAction(MyAplication.this);
        BaiduAppX.version();

    }

    //推出程序
    public void exit(){
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
}
