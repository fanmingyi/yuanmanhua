package carton.fmy.com.yuanmanhua.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.activity.ShowActivity;
import carton.fmy.com.yuanmanhua.adapter.CataloguAdapter;
import carton.fmy.com.yuanmanhua.bean.CatalogueBean;
import carton.fmy.com.yuanmanhua.url.UrlCatalogueInterface;
import carton.fmy.com.yuanmanhua.utils.NetUtil;
import carton.fmy.com.yuanmanhua.utils.SnackbarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 这个Fragment用于显示书籍目录
 */
public class CatalogueFragment extends Fragment {
    //书籍id
    String bookId;
    //展示图
    private ImageView iv_show;
    //作者
    private TextView tv_author;
    //排名
    private TextView tv_ranking;
    //书名
    private TextView tv_show;
    //简介
    private ExpandableTextView expand_text_view;
    //分类
    private TextView tv_classify2;
    //书籍信息
    CatalogueBean catalogueBean;
    //上下文
    Context mContext;
    // 用于显示目录
    private RecyclerView recycler_view;
    //显示目录的布局管理器
    private LinearLayoutManager linearLayoutManager;
    //目录适配器
    private CataloguAdapter cataloguAdapter;
    //书籍目录信息
    List<carton.fmy.com.yuanmanhua.bean.CatalogueBean.ChapterBean> chapterListBean;

    public CatalogueFragment() {

    }
    public void setBookId(String bookId){
        this.bookId = bookId;
        initNet();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //填充对象
        View inflateView = inflater.inflate(R.layout.fragment_catalogue, container, false);
        //展示图
        iv_show = ((ImageView) inflateView.findViewById(R.id.iv_show));
        //作者
        tv_author = (TextView) inflateView.findViewById(R.id.tv_author);
        //排名
        tv_ranking = ((TextView) inflateView.findViewById(R.id.tv_ranking2));
        //书名
        tv_show = ((TextView) inflateView.findViewById(R.id.tv_show));
        //简介
        expand_text_view = ((ExpandableTextView) inflateView.findViewById(R.id.expand_text_view));

        //分类
        tv_classify2 = ((TextView) inflateView.findViewById(R.id.tv_classify2));
        //获取RecycalView 用于显示目录
        recycler_view = ((RecyclerView) inflateView.findViewById(R.id.recycler_view));
        //显示目录的布局管理器
        linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        //设置布局
        recycler_view.setLayoutManager(linearLayoutManager);
        //设置数据
        cataloguAdapter = new CataloguAdapter(chapterListBean);
        //设置适配器
        recycler_view.setAdapter(cataloguAdapter);
        //每个目录的点击事件
        recycler_view.addOnItemTouchListener(onItemChildClickListener);

        return inflateView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    //每个目录的点击事件
    OnItemChildClickListener onItemChildClickListener = new OnItemChildClickListener() {
        @Override
        public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            //获得书籍id
            String bookId = catalogueBean.getId();
            //获取本书籍章节
            String number = catalogueBean.getChapter().get(i).getNumber();
            //跳转意图
            Intent intent = new Intent(mContext, ShowActivity.class);
            //放入书籍
            intent.putExtra("bookId",bookId);
            //章节
            intent.putExtra("number",number);
            //传入第几话
            intent.putExtra("title",catalogueBean.getChapter().get(i).getTitle());

            startActivity(intent);
        }
    };
    /**
     * 初始化网络
     */
    public void initNet(){

        UrlCatalogueInterface urlCatalogueInterface = NetUtil.getRetrofit().create(UrlCatalogueInterface.class);
        Call<CatalogueBean> catalogueBeanClass = urlCatalogueInterface.get(bookId);
        catalogueBeanClass.enqueue(new Callback<CatalogueBean>() {
            @Override
            public void onResponse(Call<CatalogueBean> call, Response<CatalogueBean> response) {
                catalogueBean = response.body();
                if (catalogueBean != null) {
                    //设置分类
                    tv_classify2.setText(catalogueBean.getTheme());
                    //设置书名
                    tv_show.setText(catalogueBean.getName());
                    //设置作者
                    tv_author.setText("作者:"+catalogueBean.getAuthor());
                    //设置排名
                    tv_ranking.setText(catalogueBean.getRanking());
                    //设置简介
                    expand_text_view.setText(catalogueBean.getIntroduction());
                    //下载展示图
                    Glide.with(mContext).load(catalogueBean.getIcon()).crossFade().placeholder(R.mipmap.placeholder_item).into(iv_show);

                    //获取目录
                    chapterListBean = catalogueBean.getChapter();

                    //设置数据
                    cataloguAdapter.setNewData(chapterListBean);

                    //刷新
                    cataloguAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<CatalogueBean> call, Throwable t) {
                SnackbarUtil.getImgSnackbar(getView(), "下载错误,返回重试看看", Snackbar.LENGTH_SHORT, mContext, -1).show();
            }
        });
    }

}
