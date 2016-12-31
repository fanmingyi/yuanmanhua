package carton.fmy.com.yuanmanhua.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.adapter.ClassifyAdapter;
import carton.fmy.com.yuanmanhua.bean.HomeBean;
import carton.fmy.com.yuanmanhua.customview.MyCollectRecyclerView;
import carton.fmy.com.yuanmanhua.url.UrlClassifyIdInterface;
import carton.fmy.com.yuanmanhua.utils.DialogUtil;
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
    String type;
    //下载地址类
    private UrlClassifyIdInterface urlBaseClassifyInterface;
    //下拉刷新
    SwipeRefreshLayout swipeRefreshLayout;
    //当前网络访问的是第几页
    private int page;
    //每页多少
    private String limit = "20";
    //所有数据
    private ArrayList<HomeBean> dataSet;
    //正在加载的dialog
    private Dialog dialog;
    //提示暂无数据
    private Snackbar imgSnackbar;
    //正在下载的提示
    private Snackbar loadingSnackBar;


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
        //上啦简体呢
        swipeRefreshLayout.setOnRefreshListener(() -> {
            //保证当前没有上啦
            if (!classifyAdapter.isLoading()) {
                netData();
                //禁止上啦
                classifyAdapter.setEnableLoadMore(false);
            }


        });
        //下啦简体
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
        //网络接口实现
        urlBaseClassifyInterface = NetUtil.getRetrofit().create(UrlClassifyIdInterface.class);
        //获取类型
        int typeID = getIntent().getIntExtra("type", 0);
        //根据类型判断是个下载地址
        switch (typeID) {
            //完结
            case R.id.menu_classify_1:
                type = "1";
                break;
            //彩漫
            case R.id.menu_classify_2:
                type = "2";
                break;
            //冒险
            case R.id.menu_classify_3:
                type = "3";
                break;
            //搞笑
            case R.id.menu_classify_4:
                type = "4";
                break;
            //爱情
            case R.id.menu_classify_5:
                type = "5";
                break;
            //侦探
            case R.id.menu_classify_6:
                type = "6";
                break;
            //竞技
            case R.id.menu_classify_7:
                type = "7";
                break;
            //魔法
            case R.id.menu_classify_8:
                type = "8";
                break;
            //神鬼
            case R.id.menu_classify_9:
                type = "9";
                break;
            //校园
            case R.id.menu_classify_10:
                type = "10";
                break;
            //魔幻
            case R.id.menu_classify_11:
                type = "11";
                break;
            //其他
            case R.id.menu_classify_12:
                type = "12";
                break;
            //四格
            case R.id.menu_classify_13:
                type = "13";
                break;
            //生活
            case R.id.menu_classify_14:
                type = "14";
                break;
            //百合
            case R.id.menu_classify_15:
                type = "15";
                break;
            //欢乐向
            case R.id.menu_classify_16:
                type = "16";
                break;
            //悬疑
            case R.id.menu_classify_17:
                type = "17";
                break;
            //耽美
            case R.id.menu_classify_18:
                type = "18";
                break;
            //格斗
            case R.id.menu_classify_19:
                type = "19";
                break;
            //科幻
            case R.id.menu_classify_20:
                type = "21";
                break;
            //治愈
            case R.id.menu_classify_21:
                type = "25";
                break;
            //东方
            case R.id.menu_classify_22:
                type = "35";
                break;
        }

        netData();
    }


    public void netData() {

        if (urlBaseClassifyInterface != null) {
            try {
                //网络下载接口
                Flowable<ArrayList<HomeBean>> loadFlowable = urlBaseClassifyInterface.load("" + page++, limit, type);
                //下载
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
                        //赋值数据
                        this.dataSet = data;
                        classifyAdapter.setNewData(dataSet);
                    }
                }, e ->
                {
                    imgSnackbar = SnackbarUtil.getImgSnackbar(my_recycleView, "暂无数据,下啦刷新试试", Snackbar.LENGTH_INDEFINITE, ClassifyActivity.this, -1);

                    imgSnackbar.show();
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
                    //关闭正在记载的动画
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                        if (loadingSnackBar!=null){
                            loadingSnackBar=null;
                        }
                    }
                }, () -> {
                    //关闭正在记载的动画
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                        if (loadingSnackBar!=null){
                            loadingSnackBar.dismiss();
                            loadingSnackBar=null;
                        }
                    }

                    if (imgSnackbar != null ) {
                        imgSnackbar.dismiss();
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

        //正在加载的dialog
        dialog = DialogUtil.getDialog(this);
        dialog.show();
        //当dialog 正在显示的时候证明数据没有加载完成 那么用户按下返回键那么直接finish
        dialog.setOnKeyListener((dialogInterface, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_BACK) {
                finish();
                return true;
            }
            return false;
        });

        //展示每个收藏的容器list
        my_recycleView = ((MyCollectRecyclerView) findViewById(R.id.my_recycleView));
        //下啦刷新
        swipeRefreshLayout = ((SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout));
        //下啦交替颜色
        swipeRefreshLayout.setColorSchemeColors(Color.CYAN, Color.BLUE, Color.GREEN);

        //正在下载的提示
        loadingSnackBar = SnackbarUtil.getImgSnackbar(my_recycleView, "正在下载...", Snackbar.LENGTH_INDEFINITE, this, -1);
        loadingSnackBar.show();

    }


}
