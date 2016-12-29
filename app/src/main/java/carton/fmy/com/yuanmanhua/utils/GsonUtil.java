package carton.fmy.com.yuanmanhua.utils;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/12/28.
 */

public class GsonUtil {

    private static   Gson gson ;

  static   public Gson getGson() {
        if (gson==null)
            gson = new Gson();
        return gson;
    }

}
