package com.example.cj.testwebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 在android中，如果需要进行网页数据的访问，在底层中有一个高速的浏览引擎WebKit可以实现。
 * WebKit定义在核心系统库中，不能直接访问，所以在sdk中封装了一个类WebView，可以调用底层的WebKit。
 * <p>
 * 案例：实现简易版的浏览器
 */
public class MainActivity extends AppCompatActivity {
    private EditText input;
    private Button btn;
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        input = (EditText) findViewById(R.id.wv_input);
        btn = (Button) findViewById(R.id.wv_btn);
        wv = (WebView) findViewById(R.id.wv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = input.getText().toString();
                if (!TextUtils.isEmpty(info)) {
                    showPage(info);
                } else {
                    Toast.makeText(MainActivity.this, "请输入正确的网址", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //显示页面的方法
    protected void showPage(String info) {
        //显示页面内容
        wv.loadUrl(info);
        //设置使用本地的客户端进行呈现
        wv.setWebViewClient(new WebViewClient());
    }

    //点击back按钮实现返回上一级页面，并且如果没有上一级页面的时候，才退出当前的应用
    //重写点击back按钮的方法
    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
