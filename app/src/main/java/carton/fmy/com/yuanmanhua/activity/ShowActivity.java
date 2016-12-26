package carton.fmy.com.yuanmanhua.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.bean.ShowBean;
import carton.fmy.com.yuanmanhua.url.UrlShowInterface;
import carton.fmy.com.yuanmanhua.utils.NetUtil;
import com.bumptech.glide.Glide;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;

/**
 * 展示漫画
 */
public class ShowActivity extends AppCompatActivity {
    //播放器
    ViewPager viewPager;
    //上下文
    Activity mActivity;
    //适配器
    private ViewPagerAdapter adapter;
    //数据
    ArrayList<ShowBean> showBeen;
    //第几话
    private TextView tv_sequence;
    //当前第几页
    private TextView tv_sequence_now;
    //一共多少页
    private TextView tv_sequence_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show);
        //初始化view
        initView();
        //初始化数据
        initData();
    }

    //初始化数据
    private void initData() {
        //获取意图
        Intent intent = getIntent();
        //获取书籍id
        String bookId = intent.getStringExtra("bookId");

        //获取number
        String number = intent.getStringExtra("number");
        //获取当前第几话
        String title = intent.getStringExtra("title");

        //设置第几话
        tv_sequence.setText(title);

        //下载网络数据
        netDownData(number,bookId);
    }
    //下载数据
    private void netDownData( String number,String bookId){

        UrlShowInterface urlShowInterface = NetUtil.getRetrofit().create(UrlShowInterface.class);

        Call<ArrayList<ShowBean>> load = urlShowInterface.load(number, bookId);

        load.enqueue(new Callback<ArrayList<ShowBean>>() {
            @Override
            public void onResponse(Call<ArrayList<ShowBean>> call, Response<ArrayList<ShowBean>> response) {
               //获取下载后的数据
                ArrayList<ShowBean> body = response.body();

                if (body != null) {
                    //设置新数据
                    showBeen = body;
                    //更新视图
                    adapter.notifyDataSetChanged();
                    //一共多少画
                    tv_sequence_total.setText("/"+showBeen.size());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ShowBean>> call, Throwable t) {

            }
        });
    }


    //初始化view
    private void initView() {
        //上下文
        mActivity = this;
        //播放器
        viewPager= ((ViewPager) findViewById(R.id.viewpager));
        //适配器
        adapter = new ViewPagerAdapter();
        //设置适配器
        viewPager.setAdapter(adapter);
        //第几话
        tv_sequence = ((TextView) findViewById(R.id.tv_sequence));
        //当前第几页
        tv_sequence_now = ((TextView) findViewById(R.id.tv_sequence_now));
        //一共多少页
        tv_sequence_total = ((TextView) findViewById(R.id.tv_sequence_total));

        //设置滑动监听
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }
    //viewpager 滑动监听
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //当前第几页
            tv_sequence_now.setText(++position+"");
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {

            return showBeen==null?0:showBeen.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView iv = new ImageView(mActivity);
            //设置背景色
            iv.setBackgroundColor(Color.CYAN);
            //这是缩放模式
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            //下载图片
            Glide.with(mActivity).load(showBeen.get(position).getIcon()).crossFade().into(iv);
            //布局参数
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            //设置布局参数到图片
            iv.setLayoutParams(layoutParams);
            //添加到布局
            container.addView(iv,layoutParams);

            return iv;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }
}
