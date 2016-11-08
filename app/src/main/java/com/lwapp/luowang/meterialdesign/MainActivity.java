package com.lwapp.luowang.meterialdesign;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="System.out";
    private TextView mTextView;
    private Handler mHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView= (TextView) findViewById(R.id.textview);
//        mTextView.setText("cnm");

        new Thread(){
            @Override
            public void run() {
                super.run();
                String url="http://c.m.163.com/nc/article/headline/T1348649145984/0-20.html";

                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(5000);

                    if(conn.getResponseCode()==conn.HTTP_OK){
                        InputStream is=conn.getInputStream();
                        InputStreamReader isr=new InputStreamReader(is);
                        BufferedReader br=new BufferedReader(isr);
                        final StringBuffer sb=new StringBuffer();
                        String line="";

                        while((line=br.readLine())!=null){
                            sb.append(line);
                        }

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mTextView.setText(sb.toString());
                            }
                        });

                        br.close();
                        isr.close();
                        is.close();
                    }
                    conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //md okhttpUtils绝对大姨妈来了
//        OkHttpUtils
//                .post()
//                .url(url)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e) {
//                        Log.d(TAG, "发生错误了:"+e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(String s) {
//                        Log.d(TAG, "获取的数据为："+s);
//                        mTextView.setText("md今天真是遇见鬼了！！！"+s);
//                    }
//                });

    }
}
