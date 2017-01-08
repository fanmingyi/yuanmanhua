package carton.fmy.com.yuanmanhua.utils;


import android.widget.Toast;

import carton.fmy.com.yuanmanhua.MyAplication;

/**
 * Created by Administrator on 2017/1/8.
 */

public class ToastUtil {

    private static Toast toast;

    static {
        if (toast == null) {
            toast = Toast.makeText(MyAplication.getInstant(),"",Toast.LENGTH_SHORT);
        }

    }

    public static void showLongToast(String content) {
        toast.setText(content);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showShotToast(String content) {
        toast.setText(content);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
