package carton.fmy.com.yuanmanhua.adapter;


import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.bean.CatalogueBean;

/**
 * 每个漫画目录适配器
 */
public class CataloguAdapter extends BaseQuickAdapter<CatalogueBean.ChapterBean, BaseViewHolder> {

    public CataloguAdapter(List<CatalogueBean.ChapterBean> data) {
        super(R.layout.catalogue_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CatalogueBean.ChapterBean chapterBean) {

        baseViewHolder.setText(R.id.tv_catalogu,chapterBean.getTitle()).addOnClickListener(R.id.fl_root);

        //获取在bean中的位置
        int index = getData().indexOf(chapterBean);
        //item 是双数就染色
        if ((index&1)!=1){
            baseViewHolder.getView(R.id.fl_root).setBackgroundColor(Color.argb(0x66,0xaa,0xaa,0xaa));
        }else {
            baseViewHolder.getView(R.id.fl_root).setBackgroundColor(Color.argb(0x66,0xff,0xff,0xff));
        }

    }




}