package carton.fmy.com.yuanmanhua.fragment;


import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.ArrayList;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.activity.HomeActivity;
import carton.fmy.com.yuanmanhua.adapter.HomeItemDragAdapter;
import carton.fmy.com.yuanmanhua.bean.HomeBean;
import carton.fmy.com.yuanmanhua.url.UrlHomeInterface;
import carton.fmy.com.yuanmanhua.utils.LogUtils;
import carton.fmy.com.yuanmanhua.utils.NetUtil;
import carton.fmy.com.yuanmanhua.utils.SnackbarUtil;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    //用户点击的时候回调
    interface CallBackOnClick {
        void click(String id);
    }

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
                Toast.makeText(getActivity(), "你的内容", Toast.LENGTH_SHORT).show();
                HomeActivity homeActivity = (HomeActivity) mActivity;

                //----打开书籍简介---
                //获取activity fragement管理器
                FragmentTransaction fragmentTransaction = homeActivity.supportFragmentManager.beginTransaction();
                //创建 漫画简介
                CatalogueFragment catalogueFragment = new CatalogueFragment();
                //放入数据
                catalogueFragment.setBookId(homeBeen.get(i).getId());
                //找到当前正在显示的fragement
                Fragment homeFragment = homeActivity.supportFragmentManager.findFragmentByTag("homeFragment");
                //隐藏前面的fragement
                fragmentTransaction.hide(homeFragment);
                //打开fragment 并关闭当前页面
                fragmentTransaction.add(R.id.content_fragment, catalogueFragment).addToBackStack("catalogue").commit();
            }
        });
    }

    //初始化滑动
    private void initSwipe() {

        //设置刷新的 圈的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.CYAN, Color.GREEN, Color.RED);
        //设置刷新监听
        swipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下啦的时候禁止 上拉
                quickAdapter.setEnableLoadMore(false);
                //下载数据
                LoadNetData();
            }
        });


    }

    //初始化适配器
    private void initAdapter() {


        gridLayoutManager = new GridLayoutManager(mActivity, 2, GridLayoutManager.VERTICAL, false);

        recycler_view.setLayoutManager(gridLayoutManager);

        quickAdapter = new HomeItemDragAdapter(homeBeen);

        quickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

        quickAdapter.isFirstOnly(false);

        recycler_view.setAdapter(quickAdapter);

        quickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //正在加载的时候禁止下啦
                swipeRefreshLayout.setEnabled(false);

                //加载数据
                LoadNetData();
            }
        });

        //item 交互事件
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(quickAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recycler_view);


        // 开启滑动删除
        quickAdapter.enableSwipeItem();
        quickAdapter.setOnItemSwipeListener(onItemSwipeListener);


    }

    private static final String TAG = "HomeFragment";


    //初始化一些数据
    private void LoadNetData() {

        //下载第一页数据 接口
        UrlHomeInterface urlHomeInterface = NetUtil.getRetrofit().create(UrlHomeInterface.class);

        Call<ArrayList<HomeBean>> arrayListCall = urlHomeInterface.loadPh("" + page++, limit);

        arrayListCall.enqueue(new Callback<ArrayList<HomeBean>>() {


            @Override
            public void onResponse(Call<ArrayList<HomeBean>> call, Response<ArrayList<HomeBean>> response) {
                //下载成功返回 bean数据
                //如果是上啦加载
                if (quickAdapter.isLoading()) {
                    //添加数据
                    homeBeen.addAll(response.body());
                    quickAdapter.addData(response.body());
                    quickAdapter.loadMoreComplete();
                    //上啦结束 下拉可用
                    swipeRefreshLayout.setEnabled(true);
                } else if (swipeRefreshLayout.isRefreshing()) {//如果是下啦刷新 那么清楚数据重新加载
                    //清除数据
                    homeBeen.clear();
                    homeBeen.addAll(response.body());
                    quickAdapter.setNewData(homeBeen);
                    //下啦刷新 完毕上啦那么可用
                    quickAdapter.setEnableLoadMore(true);
                    //关闭下啦刷新
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    homeBeen.clear();
                    homeBeen.addAll(response.body());
                    quickAdapter.setNewData(homeBeen);
                }


            }

            @Override
            public void onFailure(Call<ArrayList<HomeBean>> call, Throwable t) {
                LogUtils.loge("tag", "下载错误:" + t.getMessage());


                   SnackbarUtil.getImgSnackbar(getView(), "下载错误,重新刷新试试", Snackbar.LENGTH_SHORT, mActivity, -1).show();


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

            }
        });


    }


    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float v, float v1, boolean b) {

        }
    };

}
