package com.example.helloworld.huaruanshopping.acitiviy;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.fragment.fragmentHome;
import com.example.helloworld.huaruanshopping.fragment.fragmentCar;
import com.example.helloworld.huaruanshopping.fragment.fragmentInfo;
import com.example.helloworld.huaruanshopping.fragment.fragmentSort;
import com.example.helloworld.huaruanshopping.util.MD5Util;

import c.b.BP;
import c.b.PListener;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FrameLayout mainContent;
    FragmentManager fm;
    BottomNavigationBar mBottomNavigationView;
    ImageView searchIcon;
    TextView searchTv;
    RelativeLayout searchLayout;
    String jsonString = "{\"name\": \"sysho\",\"address\":\"清风阁\",\"remark\":\"加两双筷子\",\"phone\": \"123124325235\",\"cart\":[{\"id\":1,\"number\":43,\"protype\":{\"id\": 4,\"name\":\"老坛酸菜\",\"pic\":\"4.jpg\",\"inventory\":100,\"product\":{\"id\":2,\"name\":\"方便面\",\"price\": 23}}},{\"id\": 6,\"number\": 3,\"protype\":{\"id\": 3,\"name\":\"烧烤味\",\"pic\":\"4.jpg\",\"inventory\":50,\"product\":{\"id\": 2,\"name\":\"薯片\",\"price\": 23}}}]}";
    String TAG = "111";
    int pagePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏
        initToobar();
        mainContent = (FrameLayout) findViewById(R.id.main);
        fm = getSupportFragmentManager();

        initBottomNav();
        fm.beginTransaction().replace(R.id.main, new fragmentHome()).commit();
        Log.d(TAG, "onCreate: " + MD5Util.getEncryption("123"));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        pagePosition = intent.getIntExtra("pagePosition", 0);
        Log.d(TAG, "onNewIntent: position" + pagePosition);

        if (pagePosition == 0) {
            fm.beginTransaction().replace(R.id.main, new fragmentHome()).commit();
        } else {
            mBottomNavigationView.selectTab(2);
            fm.beginTransaction().replace(R.id.main, new fragmentCar()).commit();
            toolbar.setVisibility(View.VISIBLE);
            searchIcon.setVisibility(View.GONE);
            searchTv.setText("购物车");
        }
    }

    /**
     * 初始化底部导航
     */
    private void initBottomNav() {
        mBottomNavigationView = (BottomNavigationBar) findViewById(R.id.bottomNav);
        mBottomNavigationView.addItem(new BottomNavigationItem(R.drawable.blue_home_32, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.blue_sort_32, "分页"))
                .addItem(new BottomNavigationItem(R.drawable.blue_car_32, "购物车"))
                .addItem(new BottomNavigationItem(R.drawable.blue_my_32, "我的"))
                .initialise();
        mBottomNavigationView.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        fm.beginTransaction().replace(R.id.main, new fragmentHome()).commit();
                        toolbar.setVisibility(View.VISIBLE);
                        searchIcon.setVisibility(View.VISIBLE);
                        searchTv.setText("主页");
                        break;
                    case 1:
                        fm.beginTransaction().replace(R.id.main, new fragmentSort()).commit();
                        toolbar.setVisibility(View.VISIBLE);
                        searchIcon.setVisibility(View.VISIBLE);
                        searchTv.setText("分类");
                        break;
                    case 2:
                        fm.beginTransaction().replace(R.id.main, new fragmentCar()).commit();
                        toolbar.setVisibility(View.VISIBLE);
                        searchIcon.setVisibility(View.GONE);
                        searchTv.setText("购物车");
                        break;
                    case 3:
                        fm.beginTransaction().replace(R.id.main, new fragmentInfo()).commit();
                        toolbar.setVisibility(View.VISIBLE);
                        searchTv.setText("个人信息");
                        searchIcon.setVisibility(View.GONE);
                        break;
                }
            }


            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    /**
     * 初始化状态栏
     */
    private void initToobar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchIcon = (ImageView) findViewById(R.id.searchIcon);
        searchTv = (TextView) findViewById(R.id.searchTv);
        searchLayout = (RelativeLayout) findViewById(R.id.searhLayout);
    }
}
