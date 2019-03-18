package com.example.a98189.wechat.Activity.SplashPackage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.a98189.wechat.Activity.SplashPackage.GuideActivtiy;
import com.example.a98189.wechat.Activity.SplashPackage.HandlerActivity;
import com.example.a98189.wechat.R;


/**
 * 过度页面：主要是为了选择跳转到引导页面和欢迎界面的
 */
public class SplashActivity extends Activity {

    //是否是第一次使用
    private boolean isFirstUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //不显示状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences preferences = getSharedPreferences("isFirstUse", MODE_WORLD_READABLE);
        isFirstUse = preferences.getBoolean("isFirstUse", true);
        /**
         *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
         */
        if (isFirstUse) {
            startActivity(new Intent(this, GuideActivtiy.class));
        } else {
            startActivity(new Intent(this, HandlerActivity.class));
        }
        finish();
        //实例化Editor对象
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putBoolean("isFirstUse", false);
        //提交修改
        editor.commit();
    }


}


//    public static final String START_MAIN = "start_main";  //静态常量  ,作用是键值对
//    private RelativeLayout rl_start_root; //实例化
//
//    final Handler mHandler = new Handler();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        不显示状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        mHandler.postDelayed(this, 2000);
//    }
//
//    @Override
//        public void run(){
//            Intent intent;
//            boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
//            if (isStartMain) {
//                intent = new Intent(SplashActivity.this, MainActivity.class);/**这里应该跳转到登录界面*/
//
//                如果进入过主页面，直接进入主页面
//            } else {//如果没进入过主页面，则进入引导页面ViewPager的控件来设置
//                intent = new Intent(SplashActivity.this, GuideActivtiy.class);
//            }//此处可以不需要调用finish()了, 因为已经设置了noHistory属性, 从而使得系统接管finish操作
//
//        }


//        Timer timer = new Timer();//计时任务
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
//            }
//        };
//        timer.schedule(timerTask, 3000);
//
//        Intent intent;
//        boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
//        if (isStartMain) {
//            intent = new Intent(SplashActivity.this, MainActivity.class);/**这里应该跳转到登录界面*/
//
//             如果进入过主页面，直接进入主页面
//        } else {//如果没进入过主页面，则进入引导页面ViewPager的控件来设置
    /*        intent = new Intent(SplashActivity.this, GuideActivtiy.class);

        }
        startActivity(intent);
        finish();  //关闭欢迎页面

    }*/

      /*  AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setFillAfter(true); //停留在改状态

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 05f);
        sa.setFillAfter(true);

        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setFillAfter(true);

        //设置三个动态同时播放,添加三个动画没有先后顺序
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(ra);
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.setDuration(2000);
        rl_start_root.startAnimation(set); //开始播放
        set.setAnimationListener(new MyAnimationListener()); //设置动画播放监听

    }*/

  /*  class MyAnimationListener implements  Animation.AnimationListener{

        //动画开始播放回调
        @Override
        public void onAnimationStart(Animation animation) {

        }

        //动画播放结束回调
        @Override
        public void onAnimationEnd(Animation animation) {
            //判断是否进入过主页面
            boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this,START_MAIN); /**  START_MAIN 使用快捷键 ctrl + alt + c 变成成员变量  */
         /*   Intent intent;
            if (isStartMain){
                intent = new Intent(SplashActivity.this,MainActivity.class);/**这里应该跳转到登录界面*/

// 如果进入过主页面，直接进入主页面
         /*   }
            else {//如果没进入过主页面，则进入引导页面ViewPager的控件来设置
                intent  = new Intent(SplashActivity.this,GuideActivtiy.class);

            }
            startActivity(intent);
            finish();  //关闭欢迎页面

        }

     /*   //当动画重复播放的时候回调
        @Override
        public void onAnimationRepeat(Animation animation) {
        }**/







