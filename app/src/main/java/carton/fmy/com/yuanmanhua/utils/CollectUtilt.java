package carton.fmy.com.yuanmanhua.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/12/28.
 */

public class CollectUtilt {

    private static List<String> collectBeenSet1;

    /**
     * 获取所有的收藏列表
     *
     * @param context
     * @return
     */
    public static List<String> getCollect(Context context) {

        SharedPreferences edit = context.getSharedPreferences("collects", Context.MODE_PRIVATE);

        String collect = edit.getString("collect", "");

        Gson gson = GsonUtil.getGson();


            collectBeenSet1 = gson.fromJson(collect, new TypeToken<List<String>>() {
            }.getType());

        if (collectBeenSet1 == null) {
            collectBeenSet1 = new ArrayList<>();
        }

        return collectBeenSet1;
    }

    /**
     * 存储
     *
     * @param context
     * @return
     */
    public static boolean putCollect(Context context, String id) {
        SharedPreferences.Editor collect1 = context.getSharedPreferences("collects", Activity.MODE_APPEND).edit();
    if (collectBeenSet1==null){
        collectBeenSet1= getCollect(context);
    }

        Gson gson = GsonUtil.getGson();

        if (collectBeenSet1 != null &&!collectBeenSet1.contains(id)) {
            collectBeenSet1.add(id);
            String s = gson.toJson(collectBeenSet1);
            collect1.putString("collect", s);
            collect1.commit();
            return true;
        }

        return false;
    }
    /**
     * 移除
     *
     * @param context
     * @return
     */
    public static boolean removeCollect(Context context, String id) {
        SharedPreferences.Editor collect1 = context.getSharedPreferences("collects", Activity.MODE_APPEND).edit();
        if (collectBeenSet1==null){
            collectBeenSet1= getCollect(context);
        }

        Gson gson = GsonUtil.getGson();

        if (collectBeenSet1 != null &&collectBeenSet1.contains(id)) {
            collectBeenSet1.remove(id);
            String s = gson.toJson(collectBeenSet1);
            collect1.putString("collect", s);
            collect1.commit();
            return true;
        }

        return false;
    }
    /**
     * 是否收藏过
     * @param context
     * @param id
     * @return
     */
   public static  boolean isCollect(Context context, String id){
        if (collectBeenSet1==null){
            collectBeenSet1= getCollect(context);
        }
        return collectBeenSet1.contains(id);
    }
}
