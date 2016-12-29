package carton.fmy.com.yuanmanhua.adapter;


import android.support.design.widget.Snackbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.bean.CatalogueBean;
import carton.fmy.com.yuanmanhua.url.UrlCatalogueInterface;
import carton.fmy.com.yuanmanhua.utils.NetUtil;
import carton.fmy.com.yuanmanhua.utils.SnackbarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 收藏界面适配器
 */
public class CollectAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    //数据信息
    private CatalogueBean catalogueBean;

    public CollectAdapter(List<String> data) {
        super(R.layout.collect_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder,String bookId) {
        if (catalogueBean==null){
            initNet(baseViewHolder,bookId);
        }else {
            setData(baseViewHolder);
        }
    }

    private  void setData(BaseViewHolder baseViewHolder){
        //设置分类
        baseViewHolder.setText(R.id.tv_classify2,catalogueBean.getTheme());
        //设置书名
        baseViewHolder.setText(R.id.tv_show,catalogueBean.getName());
        //设置作者
        baseViewHolder.setText(R.id.tv_author,"作者:" + catalogueBean.getAuthor());
        //设置排名
        baseViewHolder.setText(R.id.tv_ranking2, catalogueBean.getRanking());
        //下载展示图
        Glide.with(mContext).load(catalogueBean.getIcon()).crossFade().placeholder(R.mipmap.placeholder_item).into((ImageView) baseViewHolder.getView(R.id.iv_show));
       //添加点击事件
        baseViewHolder.addOnClickListener(R.id.btn_read);
    }
    /**
     * 初始化网络
     */
    public void initNet(BaseViewHolder baseViewHolder,String bookId) {

        UrlCatalogueInterface urlCatalogueInterface = NetUtil.getRetrofit().create(UrlCatalogueInterface.class);
        Call<CatalogueBean> catalogueBeanClass = urlCatalogueInterface.get(bookId);
        catalogueBeanClass.enqueue(new Callback<CatalogueBean>() {
            @Override
            public void onResponse(Call<CatalogueBean> call, Response<CatalogueBean> response) {
                catalogueBean = response.body();
                if (catalogueBean != null) {
                   setData(baseViewHolder);
                }

            }

            @Override
            public void onFailure(Call<CatalogueBean> call, Throwable t) {
                SnackbarUtil.getImgSnackbar(baseViewHolder.getView(R.id.tv_author), "下载错误,返回重试看看", Snackbar.LENGTH_INDEFINITE,mContext, -1);
            }
        });
    }

}