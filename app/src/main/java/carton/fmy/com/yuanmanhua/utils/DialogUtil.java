package carton.fmy.com.yuanmanhua.utils;

import android.app.Dialog;
import android.content.Context;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.customview.LHLoadingView;

/**
 * Created by Administrator on 2016/12/28.
 */

public class DialogUtil {

    static public Dialog getDialog(Context context) {
        Dialog dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        LHLoadingView lhLoadingView = new LHLoadingView(context);
        dialog.setContentView(lhLoadingView);
        return dialog;
    }
}
