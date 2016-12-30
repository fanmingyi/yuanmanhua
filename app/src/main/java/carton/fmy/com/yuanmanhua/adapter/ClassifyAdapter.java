package carton.fmy.com.yuanmanhua.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.bean.HomeBean;

/**
 * 分类的适配器
 */
public class ClassifyAdapter extends BaseItemDraggableAdapter<HomeBean, BaseViewHolder> {

   

    public ClassifyAdapter(List<HomeBean> data) {
        super(R.layout.collect_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder,HomeBean bean) {
        //设置分类
        baseViewHolder.setText(R.id.tv_classify2,bean.getTheme());
        //设置书名
        baseViewHolder.setText(R.id.tv_show,bean.getName());
        //设置作者
        baseViewHolder.setText(R.id.tv_author,"作者:" + bean.getAuthor());
        //设置排名
        baseViewHolder.setText(R.id.tv_ranking2, bean.getRanking());
        //设置是连载还是完结
        baseViewHolder.setText(R.id.tv_status2, bean.getState());
        //下载展示图
        Glide.with(mContext).load(bean.getIcon()).crossFade().placeholder(R.mipmap.placeholder_item).into((ImageView) baseViewHolder.getView(R.id.iv_show));
        //添加点击事件
        baseViewHolder.addOnClickListener(R.id.btn_read);
    }



}