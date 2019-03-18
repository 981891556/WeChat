package com.example.a98189.wechat;

import android.app.Activity;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import android.widget.RadioGroup;

import com.example.a98189.wechat.Activity.Fragment.ReplaceFragment;
import com.example.a98189.wechat.Pager.BasePager;
import com.example.a98189.wechat.Pager.ContactPager;
import com.example.a98189.wechat.Pager.FindPager;
import com.example.a98189.wechat.Pager.MePager;
import com.example.a98189.wechat.Pager.WeChatPager;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private RadioGroup rg_bottom_tag;

    private ArrayList<BasePager> basePagers;//页面结合
    private int Position;//选择的位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_bottom_tag = findViewById(R.id.radioGroup1);

        basePagers = new ArrayList<>();
        basePagers.add(new WeChatPager(this));//微信界面 。页面0
        basePagers.add(new ContactPager(this));//通讯录：页面1
        basePagers.add(new FindPager(this));//发现：页面2
        basePagers.add(new MePager(this));//我的：页面3

        //设置RadioGroup的监听
        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rg_bottom_tag.check(R.id.radio0);//默认选中首页
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId) {
                default:
                    Position = 0;//微信
                    break;
                case R.id.radio1://通讯录
                    Position = 1;
                    break;
                case R.id.radio2://发现
                    Position = 2;
                    break;
                case R.id.radio3://我
                    Position = 3;
                    break;
            }
            setFragment();
        }
    }


    private void setFragment() {
        FragmentManager fm = getSupportFragmentManager(); //得到FragmentManager
        FragmentTransaction ft = fm.beginTransaction();//开启事务
        ft.replace(R.id.fl_tag,new ReplaceFragment(getBasePager()));/**解决方法详情：https://ask.csdn.net/questions/354752     */
        ft.commit();//提交事务
    }


    public BasePager getBasePager() {
        BasePager basePager = basePagers.get(Position);
        if (basePager != null && !basePager.isInitDate) {//如果没有初始化数据
            basePager.isInitDate = true;
            basePager.initData();//初始化数据
        }
        return basePager;
    }

}



