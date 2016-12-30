package carton.fmy.com.yuanmanhua.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.lang.reflect.Method;
import java.util.ArrayList;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.adapter.ClassifyAdapter;
import carton.fmy.com.yuanmanhua.bean.HomeBean;
import carton.fmy.com.yuanmanhua.customview.MyCollectRecyclerView;
import carton.fmy.com.yuanmanhua.url.UrlEndInterface;
import carton.fmy.com.yuanmanhua.utils.NetUtil;
import carton.fmy.com.yuanmanhua.utils.SnackbarUtil;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ClassifyActivity extends BaseSwipeActivity {

    //展示每个收藏的容器list
    private MyCollectRecyclerView my_recycleView;
    //容器布局
    private LinearLayoutManager linearLayoutManager;
    //容器适配器
    private ClassifyAdapter classifyAdapter;
    //判断用户点的是那个分类
    int type;
    //下载地址类
    private Object urlBaseClassifyInterface;
    //下拉刷新
    SwipeRefreshLayout swipeRefreshLayout;
    //当前网络访问的是第几页
    private int page;
    //每页多少
    private String limit = "20";
    //所有数据
    private ArrayList<HomeBean> dataSet;
    //网络访问方法->>反射机制拿取
    private Method load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);


        //控件初始化
        initView();
        //适配器初始化
        iniAdapter();
        //初始化数据
        inidata();
        //初始化监听
        iniEvent();
    }

    private void iniEvent() {

        swipeRefreshLayout.setOnRefreshListener(() -> {
            //保证当前没有上啦
            if (!classifyAdapter.isLoading()) {
                netData();
                //禁止上啦
                classifyAdapter.setEnableLoadMore(false);
            }


        });

        classifyAdapter.setOnLoadMoreListener(() -> {
            //保证没有下啦
            if (!swipeRefreshLayout.isRefreshing()) {
                netData();
                //使其能让下啦
                swipeRefreshLayout.setEnabled(false);
            }
        });

    }

    //初始化数据
    private void inidata() {

        //获取类型
        type = getIntent().getIntExtra("type", 0);
        //根据类型判断是个下载地址
        switch (type) {
            case R.id.menu_classify_end:
                urlBaseClassifyInterface = NetUtil.getRetrofit().create(UrlEndInterface.class);
                //获取到其中的方法
                initMethod();
                break;
        }

        netData();
    }

    private void initMethod() {

        try {
            //获取下载方法
            load = urlBaseClassifyInterface.getClass().getMethod("load", String.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void netData() {

        if (urlBaseClassifyInterface != null && this.load != null) {
            try {
                Flowable<ArrayList<HomeBean>> loadFlowable = (Flowable<ArrayList<HomeBean>>) this.load.invoke(urlBaseClassifyInterface, "" + page++, limit);
                loadFlowable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(data -> {
                    //如果正在下拉
                    if (swipeRefreshLayout.isRefreshing()) {
                        this.dataSet = data;
                        classifyAdapter.setNewData(dataSet);
                        swipeRefreshLayout.setRefreshing(false);
                        //允许上啦
                        classifyAdapter.setEnableLoadMore(true);
                        //如果当前是上啦
                    } else if (classifyAdapter.isLoading() && this.dataSet != null) {
                        //添加数据
                        this.dataSet.addAll(data);
                        //刷新数据
                        classifyAdapter.notifyDataSetChanged();
                        //完成
                        classifyAdapter.loadMoreComplete();
                        //允许下啦
                        swipeRefreshLayout.setEnabled(true);

                    } else {
                        this.dataSet = data;
                        classifyAdapter.setNewData(dataSet);
                    }
                }, e ->
                {
                    SnackbarUtil.getImgSnackbar(my_recycleView, "暂无数据", Snackbar.LENGTH_LONG, ClassifyActivity.this, -1).show();
                    //如果正在下拉
                    if (swipeRefreshLayout.isRefreshing()) {

                        swipeRefreshLayout.setRefreshing(false);
                        //允许上啦
                        classifyAdapter.setEnableLoadMore(true);
                        //如果当前是上啦
                    } else if (classifyAdapter.isLoading() && this.dataSet != null) {
                        //完成
                        classifyAdapter.loadMoreEnd();
                        //允许下啦
                        swipeRefreshLayout.setEnabled(true);
                    }

                });

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    //适配器初始化
    private void iniAdapter() {
        //容器布局
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //容器适配器
        classifyAdapter = new ClassifyAdapter(dataSet);


        //设置布局
        my_recycleView.setLayoutManager(linearLayoutManager);

        //设置适配器
        my_recycleView.setAdapter(classifyAdapter);


        my_recycleView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                //如果用户点击了阅读
                if (view.getId() == R.id.btn_read) {
                    Intent intent = new Intent(ClassifyActivity.this, IntroduceActivity.class);

                    if (dataSet != null && i >= 0 && dataSet.size() > i) {
                        intent.putExtra("bookId", dataSet.get(i).getId());
                        startActivity(intent);
                    }
                }
            }
        });
    }

    //控件初始化
    private void initView() {
        //展示每个收藏的容器list
        my_recycleView = ((MyCollectRecyclerView) findViewById(R.id.my_recycleView));
        //下啦刷新
        swipeRefreshLayout = ((SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout));
        //下啦交替颜色
        swipeRefreshLayout.setColorSchemeColors(Color.CYAN, Color.BLUE, Color.GREEN);

        //收藏
        // SnackbarUtil.getImgSnackbar(my_recycleView, "手指选择漫画,右滑动可以删除收藏", Snackbar.LENGTH_INDEFINITE, this, -1).show();

    }


}
