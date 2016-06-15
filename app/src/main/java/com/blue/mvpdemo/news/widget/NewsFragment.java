package com.blue.mvpdemo.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blue.mvpdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻fragment的tabLayout
 */
public class NewsFragment extends Fragment {
    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;

    private TabLayout mTablayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);//加载的页数
        initViewPager(mViewPager);

        mTablayout.addTab(mTablayout.newTab().setText("头条"));
        mTablayout.addTab(mTablayout.newTab().setText("NBA"));
        mTablayout.addTab(mTablayout.newTab().setText("汽车"));
        mTablayout.addTab(mTablayout.newTab().setText("笑话"));
        mTablayout.setupWithViewPager(mViewPager);
        return view;
    }

    private void initViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewListFragment.newInstance(NEWS_TYPE_TOP),"头条");
        adapter.addFragment(NewListFragment.newInstance(NEWS_TYPE_NBA),"NBA");
        adapter.addFragment(NewListFragment.newInstance(NEWS_TYPE_CARS),"汽车");
        adapter.addFragment(NewListFragment.newInstance(NEWS_TYPE_JOKES),"笑话");
        mViewPager.setAdapter(adapter);

    }

    public static class MyPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title){
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }


        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
