package carton.fmy.com.yuanmanhua.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import carton.fmy.com.yuanmanhua.MyAplication;
import carton.fmy.com.yuanmanhua.utils.FixInputMethodManagerLeak;

/**
 * Created by 范明毅 on 2016/12/20.
 */

public abstract class BaseActivity  extends AppCompatActivity{



    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        MyAplication.activities.add(this);

    }

    private static final String TAG = "BaseActivity";


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyAplication.activities.remove(this);
        FixInputMethodManagerLeak.fixInputMethodManagerLeak(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
       //用户按下 判断是否需要关闭软键盘
        if (MotionEvent.ACTION_DOWN == ev.getAction()){
            //获取得到焦点view
            View currentFocus = getCurrentFocus();
            // 是否需要关闭软键盘
            if (isCloseSoftInput(ev,currentFocus)){
                hideSoftInput(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断是否要关闭软键盘,如果触摸区域在View是Edittext范围内
     * @param ev 触摸事件
     * @param v 获得焦点 控件
     * @return 如果等true 那么需要关闭键盘
     */
    public  boolean isCloseSoftInput(MotionEvent ev,View v){

        //如果获取的控件是空的那么不是EditText 关闭软键盘
        if (v==null){
            return false;
        }else {
            Rect viewRect = new Rect();
            //得到view 位置  包含标题栏和状态栏
            v.getGlobalVisibleRect(viewRect);
           //如果获取的焦点不在 Editext 中那么需要关闭
            if (!viewRect.contains((int) ev.getRawX(),(int)ev.getRawY())){
                return true;
            }

        }

        //默认不关闭软键盘
        return  false;
    }

    public void hideSoftInput(IBinder windowToken){
        //输入管理器
        InputMethodManager systemService = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
       // 强制隐藏Android输入法窗口
        systemService.hideSoftInputFromWindow(windowToken,0);
    }

}
