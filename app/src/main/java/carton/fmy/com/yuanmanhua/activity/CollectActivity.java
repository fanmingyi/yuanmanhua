package carton.fmy.com.yuanmanhua.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.List;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.adapter.CollectAdapter;
import carton.fmy.com.yuanmanhua.customview.MyCollectRecyclerView;
import carton.fmy.com.yuanmanhua.utils.CollectUtilt;

/**
 * 展示收藏界面
 */
public class CollectActivity extends BaseSwipeActivity {

    //展示每个收藏的容器list
    private MyCollectRecyclerView my_recycleView;
    //容器布局
    private LinearLayoutManager linearLayoutManager;
    //容器适配器
    private CollectAdapter collectAdapter;
    //收藏数据为空的时候显示
    private View emptyView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        //控件初始化
        initView();
        //适配器初始化
        iniAdapter();

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
        collectAdapter = new CollectAdapter(CollectUtilt.getCollect(this));

        //设置空布局显示的view
        collectAdapter.setEmptyView(emptyView);

        //设置布局
        my_recycleView.setLayoutManager(linearLayoutManager);


        // 开启滑动删除
        collectAdapter.enableSwipeItem();

        //滑动删除监听
        collectAdapter.setOnItemSwipeListener(onItemSwipeListener);

        //设置适配器
        my_recycleView.setAdapter(collectAdapter);


        //item 交互事件
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(collectAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(my_recycleView);

        my_recycleView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
               //如果用户点击了阅读
                if (view.getId()==R.id.btn_read){
                    Intent intent = new Intent(CollectActivity.this, IntroduceActivity.class);
                    List<String> collect = CollectUtilt.getCollect(CollectActivity.this);
                    if (collect!=null&&i>=0&&collect.size()>i){
                        intent.putExtra("bookId",collect.get(i));
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

        //收藏数据为空的时候显示
        emptyView = getLayoutInflater().inflate(R.layout.empty_loading, null);


    }

    private OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        //用于记录用户当前滑动的是那个
        int postion;

        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int i) {
            Log.e("onItemSwipeStart", "onItemSwipeStart: " + i);
            postion = i;
        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int i) {

            Log.e("onItemSwipeStart", "clearView: " + i);
            if (i == -1) {
                List<String> collect = CollectUtilt.getCollect(CollectActivity.this);
                if (collect != null && collect.size() > postion) {
                    CollectUtilt.removeCollect(CollectActivity.this, collect.get(postion));
                }
            }
        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int i) {
            Log.e("onItemSwipeStart", "onItemSwiped: " + i);
        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float v, float v1, boolean b) {
            Log.e("onItemSwipeStart", "onItemSwipeMoving: " + b);
        }
    };
}
