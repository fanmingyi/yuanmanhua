package carton.fmy.com.yuanmanhua.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import carton.fmy.com.yuanmanhua.MyAplication;
import carton.fmy.com.yuanmanhua.R;

/**
 * Created by Administrator on 2016/12/25.
 */

public class SnackbarUtil {


    /**
     * @param view
     * @param text
     * @param duration
     * @param context
     * @param  drawable
     * @param bg       snack的背景  -1为不设置 否则默认天蓝
     * @return
     */
    //返回一个带有图片的img
    public static Snackbar getImgSnackbar(@NonNull View view, @NonNull CharSequence text,
                                    int duration, Context context, int bg) {

        ImageView iv = new ImageView(context);
        //设置背景图
        iv.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_launcher));

        Snackbar snackbar = Snackbar.make(view, text, duration);

        View view1 = snackbar.getView();

        if (bg != -1){
            view1.setBackgroundColor(bg);
        }else {
//            view1.setBackgroundColor(Color.BLUE);
        }

        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) view1;

        snackbarLayout.addView(iv, 0);

        return snackbar;
    }
}
