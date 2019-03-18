package com.example.a98189.wechat.Test;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.example.a98189.wechat.R;
import com.example.a98189.wechat.data.Cheeses;

import java.lang.reflect.Method;


/**
 * 搜索框功能    测试类：   参考https://www.jianshu.com/p/7c1e78e91506
 * <p>
 * 搜索框中：默认的提示文字：搜索本地歌曲，1.在string样式中修改，2.代码修改（如果两个都设置了，以代码为主）
 * data目录下的Cheeses是搜索框的搜索功能信息的存储位置

 * * */



public class SearchViewActivity2 extends AppCompatActivity {

    private ListView mLv;
    private SearchView mSearchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);  // 使用Toolbar代替actionbar
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() { //Toolbar返回按钮的点击事件
            @Override
            public void onClick(View v) {
                if (mSearchAutoComplete.isShown()) { //如果搜索框中有文字，则会先清空文字，但网易云音乐是在点击返回键时直接关闭搜索框
                    try {
                        mSearchAutoComplete.setText("");
                        Method method = mSearchView.getClass().getDeclaredMethod("onCloseClicked");
                        method.setAccessible(true);
                        method.invoke(mSearchView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
            }
        });

        mLv = (ListView) findViewById(R.id.lv);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);

        //通过MenuItem得到SearchView
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);//通过MenuItem得到SearchView
        mSearchAutoComplete = (SearchView.SearchAutoComplete) mSearchView.findViewById(R.id.search_src_text);    //通过id得到搜索框控件
        mSearchView.setQueryHint("搜索本地歌曲by code");

        //设置输入框提示文字样式
        mSearchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.darker_gray));
        mSearchAutoComplete.setTextColor(getResources().getColor(android.R.color.background_light));
        mSearchAutoComplete.setTextSize(14);
        //设置触发查询的最少字符数（默认2个字符才会触发查询）
        mSearchAutoComplete.setThreshold(1);

        //设置搜索框有字时显示叉叉，无字时隐藏叉叉
        mSearchView.onActionViewExpanded();
        mSearchView.setIconified(true);

        //修改搜索框控件间的间隔（这样只是为了更加接近网易云音乐的搜索框）
        LinearLayout search_edit_frame = (LinearLayout) mSearchView.findViewById(R.id.search_edit_frame);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) search_edit_frame.getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        search_edit_frame.setLayoutParams(params);

        //监听SearchView的内容
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                Cursor cursor = TextUtils.isEmpty(s) ? null : queryData(s);

                if (mSearchView.getSuggestionsAdapter() == null) {
                    mSearchView.setSuggestionsAdapter(new SimpleCursorAdapter(SearchViewActivity2.this, R.layout.item_layout, cursor, new String[]{"name"}, new int[]{R.id.text1}));
                } else {
                    mSearchView.getSuggestionsAdapter().changeCursor(cursor);
                }
//                setAdapter(cursor);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    // 让菜单同时显示图标和文字
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private Cursor queryData(String key) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "music.db", null);
        Cursor cursor = null;
        try {
            String querySql = "select * from tb_music where name like '%" + key + "%'";
            cursor = db.rawQuery(querySql, null);
            Log.e("CSDN_LQR", "querySql = " + querySql);
        } catch (Exception e) {
            e.printStackTrace();
            String createSql = "create table tb_music (_id integer primary key autoincrement,name varchar(100))";
            db.execSQL(createSql);

            String insertSql = "insert into tb_music values (null,?)";
            for (int i = 0; i < Cheeses.sCheeseStrings.length; i++) {
                db.execSQL(insertSql, new String[]{Cheeses.sCheeseStrings[i]});
            }

            String querySql = "select * from tb_music where name like '%" + key + "%'";
            cursor = db.rawQuery(querySql, null);

            Log.e("CSDN_LQR", "createSql = " + createSql);
            Log.e("CSDN_LQR", "querySql = " + querySql);
        }
        return cursor;
    }
}



