package com.bawei.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv;
    private MyAdapter adapter;
    private List<Data> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        list = new ArrayList<>();
        for(int i=0;i<50;i++){
            Data data = new Data();
            data.index = i;
            data.text = "item " +i;
            list.add(data);
        }
    }

    private boolean isHide = false;
    private void initView() {
        lv = (ListView) findViewById(R.id.lv);

        adapter = new MyAdapter(list,this,false);
        lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_all){
            check(true);
        }else if(v.getId()==R.id.btn_no){
            check(false);
        }else if(v.getId()==R.id.btn_hide){
            isHide = isHide==true?false:true;
            hide(isHide);
        }
    }

    private void hide(boolean isHide) {
        Button btn = (Button)findViewById(R.id.btn_hide);
        if(isHide)
            btn.setText("取消隐藏");
        else
            btn.setText("隐藏已选");

        adapter.setHide(isHide);
        adapter.notifyDataSetChanged();
    }


    private void check(boolean flag) {
        for (Data data: list)
            data.isCheck = flag;
        adapter.notifyDataSetChanged();
    }

}
