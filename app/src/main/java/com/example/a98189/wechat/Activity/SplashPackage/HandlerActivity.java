package com.example.a98189.wechat.Activity.SplashPackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.a98189.wechat.Activity.LoginActivity;
import com.example.a98189.wechat.R;

//欢迎界面
public class HandlerActivity extends Activity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //不显示状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        handler.sendEmptyMessageDelayed(0, 3000);
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            //执行一次后销毁本页面
            finish();
        }

    };






}
