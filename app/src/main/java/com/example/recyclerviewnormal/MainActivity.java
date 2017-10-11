package com.example.recyclerviewnormal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    LinearLayoutManager llm;
    int firstOld = 0;
    ItemAdapter itemAdapter;
    int visibleCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);//获取对象
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);//设置布局管理器，这里选择用竖直的列表
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("" + i);
        }
        itemAdapter = new ItemAdapter(list, this);//添加适配器，这里适配器刚刚装入了数据
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (visibleCount < recyclerView.getChildCount()) {
                    visibleCount = recyclerView.getChildCount();
                }
                Log.d(TAG, "onScrollChange:visibleCount= " + visibleCount);
                int firstNew = llm.findFirstVisibleItemPosition();
//                if (firstNew - firstOld == 1 || firstNew - firstOld == -1) {
//                    itemAdapter.resetItem(firstOld);
//                }
                for(int j=0;j<itemAdapter.bmList.size();j++) {
                    if(itemAdapter.bmList.get(j).isRecycled()){
                        Log.d(TAG, "onScrollChange: "+j+" 释放");
                    }else{
                        Log.d(TAG, "onScrollChange: "+j+" 未释放");
                    }
                }
                itemAdapter.resetItem(firstNew-1);
                itemAdapter.resetItem(firstNew+visibleCount);
                itemAdapter.release(firstNew-2);
                itemAdapter.release(firstNew+visibleCount+1);

            }
        });

    }
}
