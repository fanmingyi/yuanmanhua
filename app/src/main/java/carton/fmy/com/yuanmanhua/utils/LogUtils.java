package carton.fmy.com.yuanmanhua.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/12/21.
 */

public class LogUtils {

    static boolean flag = true;
    static   public void loge(String tag,String msg){
        if (flag)
            Log.e(tag,msg);
    }
}
