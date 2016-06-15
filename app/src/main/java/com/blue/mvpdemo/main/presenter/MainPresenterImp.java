package com.blue.mvpdemo.main.presenter;

import com.blue.mvpdemo.R;
import com.blue.mvpdemo.main.view.MainView;

/**
 * Created by Administrator on 2016/5/9.
 */
public class MainPresenterImp implements MainPresenter{

    private MainView mMainView;

    public MainPresenterImp(MainView mMainView) {
        this.mMainView = mMainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id){
            case R.id.navigation_item_news:
                break;
            case R.id.navigation_item_images:
                break;
            case R.id.navigation_item_weather:
                break;
            case R.id.navigation_item_about:
                break;
            default:
                mMainView.switchNews();
                break;
        }
    }
}
