package com.yifuyou.commontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button start,stop;
    TextView textView;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                case 0x0223:
                    System.out.println(msg.obj.toString());
                    textView.setText(msg.obj.toString());
                    textView.post(()->{
                    });
                    break;
                default:break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.start_btn);
        stop=findViewById(R.id.stop_btn);
        textView=findViewById(R.id.count_view);

        start.setOnClickListener((v)->{
            NumCaculater.GetInstance().reset();

            Thread runThread=new Thread(()->{
                while (true){
                    try {
                        Thread.sleep(1000L);
                        if (NumCaculater.GetInstance().countAdd()) {
                            Message msg = new Message();
                            msg.what=0x0223;
                            msg.obj=NumCaculater.GetInstance().getCount();
                            handler.sendMessage(msg);
                        }else{
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            });
            runThread.start();
            start.setClickable(false);
        });
        stop.setOnClickListener((v)->{
            NumCaculater.GetInstance().setCulBool(false);

            NumCaculater.GetInstance().release();
            start.setClickable(true);
        });

    }





}