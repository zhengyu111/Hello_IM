package com.zhengyu.Hello_IM.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhengyu.Hello_IM.R;

/**
 * Created by Administrator on 2016/7/18 0018.
 */
public class ChatFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_main_fragment2,container,false);
        return view;
    }
}
