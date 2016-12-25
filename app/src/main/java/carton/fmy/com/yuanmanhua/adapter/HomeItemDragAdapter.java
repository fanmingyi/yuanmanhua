package carton.fmy.com.yuanmanhua.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.bean.HomeBean;

/**
 * Created by 范明毅 on 2016/12/21.用于第一homeFragment 中每个漫画书
 */

public class HomeItemDragAdapter extends BaseItemDraggableAdapter<HomeBean, BaseViewHolder> {
    public HomeItemDragAdapter(List data) {
        super(R.layout.home_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {

        //添加根布局的点击事件 和设置漫画的标题
        helper.setText(R.id.tv_show, item.getName()).addOnClickListener(R.id.root_view);

        //添加漫画的icon
        Glide.with(mContext).load(item.getIcon()).crossFade().into((ImageView) helper.getView(R.id.iv_show));
    }


}
