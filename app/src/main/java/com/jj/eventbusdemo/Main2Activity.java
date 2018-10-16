package com.jj.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Main2Activity extends AppCompatActivity {
    private TextView tv_back,tv_show1,tv_show2;
    private boolean isFirst = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_back = findViewById(R.id.returns2);
        tv_show1 = findViewById(R.id.sendTo2);
        tv_show2 = findViewById(R.id.sendTo3);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //发送主线程
        tv_show1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MsgBean("主线程发送了一条消息"));
                finish();
            }
        });

        tv_show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFirst){
                    EventBus.getDefault().register(Main2Activity.this);
                    isFirst = false;
                }
            }
        });
    }


    //        接收粘性线程
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void receiveMsgStick(MsgBean msg){
        tv_show2.setText(msg.getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(Main2Activity.this);
    }
}
