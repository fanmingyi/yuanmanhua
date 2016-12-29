package carton.fmy.com.yuanmanhua.utils;

/**
 * Created by Administrator on 2016/12/29.
 */

public class QuickClick {
    static long after;
    public  static boolean quickClick(){
        long now = System.currentTimeMillis();
       //如果用户连续点击在200ms那么返回true
        if (now-after< 200){
            return  true;
        }
        after =now;
        return  false;
    }
}
