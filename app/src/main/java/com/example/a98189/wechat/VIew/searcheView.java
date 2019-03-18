package com.example.a98189.wechat.VIew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.a98189.wechat.MainActivity;
import com.example.a98189.wechat.R;

public class searcheView extends AppCompatActivity {
    private SearchView sv;
    private ListView lv;
    private final  String[] mStrings = {"aaaa","bbbb","cccc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searche_view);
            init();

    }

    private void init() {
        sv = findViewById(R.id.sv);
        lv = findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mStrings));

        //设置ListView启用过滤
        lv.setTextFilterEnabled(true);

        sv.setIconifiedByDefault(false);//是否默认缩小图标
        sv.setSubmitButtonEnabled(true);//是否显示搜索按钮
        sv.setQueryHint("查找");//提示文本
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {//监听器
            @Override//用户输入字符时激发该方法
            public boolean onQueryTextSubmit(String s) {
                if (TextUtils.isEmpty(s)){
                    //清除LsitView的过滤
                    lv.clearTextFilter();
                }else {
                    //使用用户输入的内容对ListView的列表项进行过滤
                    lv.setFilterText(s);
                }
                return false;
            }

            @Override//单机搜索按钮时激发该方法
            public boolean onQueryTextChange(String s) {
                //实际应用中应该在该方法内执行实际查询
                //此处仅使用Toast显示用户输入的查询内容
                Toast.makeText(searcheView.this,"您选择的是："+ s,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

}
}