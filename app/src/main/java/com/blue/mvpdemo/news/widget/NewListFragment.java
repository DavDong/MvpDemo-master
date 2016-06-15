package com.blue.mvpdemo.news.widget;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blue.mvpdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewListFragment extends Fragment {


    public NewListFragment() {
        // Required empty public constructor
    }

    public static NewListFragment newInstance(int type){
        Bundle args = new Bundle();
        NewListFragment fragment = new NewListFragment();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_list, container, false);
    }

}
