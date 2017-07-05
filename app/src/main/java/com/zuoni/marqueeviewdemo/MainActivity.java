package com.zuoni.marqueeviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zuoni.bean.ALiNotice;
import com.zuoni.marqueeview.MarqueeView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MarqueeView marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
        List<Notice> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
           Notice info=new Notice();
            info.setType("臧艺"+i);
            info.setContent("========================="+i);
            list.add(info);
        }
        // Notice  转ALiNotice

        //若返回的的集合为单数则增加一个空的Notice
        if(list.size()%2!=0){
            Notice info=new Notice();
            info.setType("");
            info.setContent("");
            list.add(info);
        }
        //把双列List转换成双列List
        List<ALiNotice> mList=new ArrayList<>();
        for (int i = 0; i <list.size()/2 ; i++) {
            ALiNotice info =new ALiNotice();
            info.setType01(list.get(2*i).getType());
            info.setContent01(list.get(2*i).getContent());
            info.setType02(list.get(2*i+1).getType());
            info.setContent02(list.get(2*i+1).getContent());
            mList.add(info);
        }

        marqueeView.startWithList(mList);
    }
}
