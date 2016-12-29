package carton.fmy.com.yuanmanhua.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import carton.fmy.com.yuanmanhua.R;
import carton.fmy.com.yuanmanhua.fragment.HomeFragment;

/**
 * 主页面的Activity
 */
public class HomeActivity extends BaseActivity  {


    //标题栏
    Toolbar toolbar;

    //存放抽屉布局容器
    private DrawerLayout drawerLayout;

    //导航栏
    private NavigationView navigationView;

    //侧滑菜单监听
    private ActionBarDrawerToggle actionBarDrawerToggle;

    //主页homefragment
    private HomeFragment homeFragment;

    //Fragment管理器
    public FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        //实例化控件
        initView();

        //初始化标题栏 toobar等
        initBar();

        //初始化侧滑菜单的每个条目
        initItem();

        //初始化所有的fragment
        initFragment();

    }
    //初始化所有的fragment
    private void initFragment() {
        //主页的fragment
        homeFragment = new HomeFragment();

       //得到fragement管理器
        supportFragmentManager = getSupportFragmentManager();
        //提交
        supportFragmentManager.beginTransaction().add(R.id.content_fragment,homeFragment,"homeFragment").commit();

        supportFragmentManager.popBackStack();

    }


    //初始化侧滑菜单的每个条目
    private void initItem() {

        //抽屉布局开关
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this,drawerLayout,toolbar,  R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);


            }

        };

        drawerLayout.setScrimColor(Color.TRANSPARENT);
        //给抽屉布局添加监听
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        navigationView.setNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()){
                //如果按钮是收藏
                case R.id.menu_collect:
                    Intent intent = new Intent(HomeActivity.this,CollectActivity.class);
                    startActivity(intent);
                    return  true;
                //跳转市场去评价
                case R.id.menu_market:
                    String mAddress ="market://details?id="+getPackageName();
                    Intent marketIntent = new Intent("android.intent.action.VIEW");
                    marketIntent.setData(Uri.parse(mAddress));
                    startActivity(marketIntent);
                    return  true;
            }

            return false;});
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //同步左上角 不然就一个箭头不会变化
        actionBarDrawerToggle.syncState();
    }
    //监听左上角item 变化
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //这里是让用户点击按钮的时候可以打开抽屉
        return actionBarDrawerToggle.onOptionsItemSelected(item)
                || super.onOptionsItemSelected(item);
    }

    private void initView() {
        //标题栏
        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        //侧滑栏
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //导航栏
        navigationView = ((NavigationView) findViewById(R.id.navigation));
        //设置每个item显示原本自身颜色
        navigationView.setItemIconTintList(null);

    }

    //初始化标题栏 toobar等
    private void initBar() {
        //设置toolbar为标题栏
        setSupportActionBar(toolbar);
        //设置显示旋转菜单
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }




    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}
