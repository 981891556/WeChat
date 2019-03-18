package com.example.a98189.wechat.Pager;

import android.content.Context;
import android.view.View;

/**
 * Created by  liangjiachneg.Date: 2019/3/13.Time: 14:55
 * <p>
 * 这个类的作用是：基类，给子类继承
 */
public abstract class BasePager {

    public  final Context context;  //上下文

    public View rootView;

    public boolean isInitDate  =  false; //判断数据是否初始化

    public BasePager(Context context) {
        this.context = context;
        rootView = initView();
    }

    public abstract View initView();//初始化视图


    public void initData(){//初始化数据

    }
}
