package com.example.a98189.wechat.Activity.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a98189.wechat.Pager.BasePager;

/**
 * Created by  liangjiachneg.Date: 2019/3/13.Time: 15:16
 * <p>
 * 这个类的作用是：
 */
public class ReplaceFragment extends Fragment {
    private BasePager currPager;

    public ReplaceFragment() {
    }

    @SuppressLint("ValidFragment") //忽略警告
    public ReplaceFragment(BasePager pager) {
        this.currPager = pager;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return currPager.rootView;
    }
}
