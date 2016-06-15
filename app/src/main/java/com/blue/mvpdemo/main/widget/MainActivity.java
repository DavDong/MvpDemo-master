package com.blue.mvpdemo.main.widget;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.blue.mvpdemo.R;
import com.blue.mvpdemo.main.presenter.MainPresenter;
import com.blue.mvpdemo.main.presenter.MainPresenterImp;
import com.blue.mvpdemo.main.view.MainView;
import com.blue.mvpdemo.news.widget.NewsFragment;

public class MainActivity extends AppCompatActivity implements MainView {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private MainPresenter mainPresenter;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(navigationView);

        mainPresenter = new MainPresenterImp(this);

        switchNews();

    }

    /**
     * 实例化侧拉栏//选中侧拉栏后更新标题栏的文案
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            return true;
        }

        return onOptionsItemSelected(item);

    }

    /**
     * 初始化侧拉菜单
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mainPresenter.switchNavigation(item.getItemId());
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }


    @Override
    public void switchNews() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content,new NewsFragment())
                .commit();
        toolbar.setTitle("新闻");
    }

    @Override
    public void switchImages() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content,new NewsFragment())
                .commit();
        toolbar.setTitle("图片");
    }

    @Override
    public void switchWeather() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content,new NewsFragment())
                .commit();
        toolbar.setTitle("天气");
    }

    @Override
    public void switchAbout() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content,new NewsFragment())
                .commit();
        toolbar.setTitle("关于");
    }
}
