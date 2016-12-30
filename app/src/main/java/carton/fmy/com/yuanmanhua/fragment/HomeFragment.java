package carton.fmy.com.yuanmanhua.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.activity.IntroduceActivity;
import carton.fmy.com.yuanmanhua.adapter.HomeItemDragAdapter;
import carton.fmy.com.yuanmanhua.bean.HomeBean;
import carton.fmy.com.yuanmanhua.url.UrlHomeInterface;
import carton.fmy.com.yuanmanhua.utils.NetUtil;
import carton.fmy.com.yuanmanhua.utils.SnackbarUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //获取根布局view
    private View rootView;
    //使用此布局完成流布局
    private RecyclerView recycler_view;
    //记录数据查询当前是第几页
    private int page = 0;
    //你所需要的数据
    private String limit = "20";
    //上下文
    private Activity mActivity;
    //数据bean
    private ArrayList<HomeBean> homeBeen = new ArrayList<>();
    //网格布局管理器
    private GridLayoutManager gridLayoutManager;
    //下啦控件
    private WaveSwipeRefreshLayout swipeRefreshLayout;
    //适配器
    private HomeItemDragAdapter quickAdapter;
    //一个fragment对应一个snackbar
    private Snackbar snackbar;
    private Dialog dialog;


    public HomeFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //获取根布局view
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //使用此布局完成流布局
        recycler_view = ((RecyclerView) rootView.findViewById(R.id.recycler_view));
        //滑动开关
        swipeRefreshLayout = ((WaveSwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout));

        //初始化滑动
        initSwipe();
        //初始化适配器
        initAdapter();
        //初始化一些网络数据
        LoadNetData();

        //初始化每个漫画的点击事件
        initItemOclick();

        return rootView;
    }

    //初始化每个漫画的点击事件
    private void initItemOclick() {
        recycler_view.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

                //----打开书籍简介---
                Intent intent = new Intent(mActivity, IntroduceActivity.class);
                intent.putExtra("bookId", homeBeen.get(i).getId());
                startActivity(intent);

            }
        });
    }

    //初始化滑动
    private void initSwipe() {

        //设置刷新的 圈的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.CYAN, Color.GREEN, Color.RED);
        //设置刷新监听
        swipeRefreshLayout.setOnRefreshListener(() -> {
            //下啦的时候禁止 上拉
            quickAdapter.setEnableLoadMore(false);
            //下载数据
            LoadNetData();
        });


    }

    //初始化适配器
    private void initAdapter() {

        gridLayoutManager = new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false);

        recycler_view.setLayoutManager(gridLayoutManager);

        quickAdapter = new HomeItemDragAdapter(homeBeen);

        quickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        //设置每个动画是否只执行一次
        quickAdapter.isFirstOnly(false);


        recycler_view.setAdapter(quickAdapter);

        quickAdapter.setOnLoadMoreListener(() -> {
            //正在加载的时候禁止下啦
            swipeRefreshLayout.setEnabled(false);

            //加载数据
            LoadNetData();
        });



    }


    //初始化一些数据
    private void LoadNetData() {
        //下载第一页数据 接口
        UrlHomeInterface urlHomeInterface = NetUtil.getRetrofit().create(UrlHomeInterface.class);

        Observable<ArrayList<HomeBean>> arrayListCall = urlHomeInterface.loadPh("" + page++, limit);

        arrayListCall.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe((t) -> {
//            下载成功返回 bean数据

            //如果是上啦加载
            if (quickAdapter.isLoading()) {
                //添加数据
                homeBeen.addAll(t);
                quickAdapter.addData(t);
                quickAdapter.loadMoreComplete();
                //上啦结束 下拉可用
                swipeRefreshLayout.setEnabled(true);
            } else if (swipeRefreshLayout.isRefreshing()) {//如果是下啦刷新 那么清楚数据重新加载
                //清除数据
                homeBeen.clear();
                homeBeen.addAll(t);
                quickAdapter.setNewData(homeBeen);
                //下啦刷新 完毕上啦那么可用
                quickAdapter.setEnableLoadMore(true);
                //关闭下啦刷新
                swipeRefreshLayout.setRefreshing(false);
            } else {
                homeBeen.clear();
                homeBeen.addAll(t);
                quickAdapter.setNewData(homeBeen);
            }
        }, throwable -> {
            snackbar = SnackbarUtil.getImgSnackbar(getView(), "下载错误,重新刷新试试", Snackbar.LENGTH_INDEFINITE, mActivity, -1);
            snackbar.show();

            //如果是下啦刷新 那么清楚数据重新加载
            if (swipeRefreshLayout.isRefreshing()) {
                //刷新完毕 关闭圈圈
                swipeRefreshLayout.setRefreshing(false);
                quickAdapter.setEnableLoadMore(true);
            }
            //如果是上啦加载
            if (quickAdapter.isLoading()) {
                //关闭上啦
                quickAdapter.loadMoreFail();
                swipeRefreshLayout.setEnabled(true);
            }

        }, () -> {

            if (snackbar!=null&&snackbar.isShown()){
                snackbar.dismiss();
            }
        });


    }




}
