package com.yao.breakskyyo.webview;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yao.breakskyyo.R;
import com.yao.breakskyyo.tools.ClipboardManagerDo;

public class WebViewActivity extends AppCompatActivity {
    WebView webView;
    FloatingActionButton fab;
    Snackbar snackbarMima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
                //    指定下拉列表的显示数据
                String[] toDo;
                if(!TextUtils.isEmpty(getIntent().getStringExtra("mima"))){
                    String[] dos = {"打开浏览器","分享", "复制","帮助","显示密码"};
                    toDo=dos;
                }else{
                    String[] dos = {"打开浏览器","分享", "复制", "帮助"};
                    toDo=dos;
                }

                //    设置一个下拉的列表选择项
                builder.setItems(toDo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       switch (which) {
                            case 0:
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                Uri content_url = Uri.parse("http://www.163.com");
                                intent.setData(content_url);
                                startActivity(intent);
                                break;
                            case 1:
                                share();
                                break;
                            case 2:
                                copy();
                                break;

                        }


                    }
                });
                builder.show();

            }
        });
        init();
    }

    private void init() {
        // 实例化WebView
        webView = (WebView) this.findViewById(R.id.webView);
        // 设置加载进来的页面自适应手机屏幕
        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        String url =getIntent().getStringExtra("url");
        if(TextUtils.isEmpty(url)){
            finish();
        }
        webView.loadUrl(url);

        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        if(!TextUtils.isEmpty(getIntent().getStringExtra("mima"))){
            snackbarMima=Snackbar.make(fab, "密码：" + getIntent().getStringExtra("mima"), Snackbar.LENGTH_INDEFINITE);
            snackbarMima.setAction("隐藏", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbarMima.dismiss();
                }
            });
            snackbarMima.show();
        }
    }

    private void copy() {
        String text = getIntent().getStringExtra("title") + "---(" + getIntent().getStringExtra("url")  + ") ";
        if(!TextUtils.isEmpty(getIntent().getStringExtra("mima"))) {
            text = text + "  (密码--" +getIntent().getStringExtra("mima") + ")";
        }
        ClipboardManagerDo.copy(text, WebViewActivity.this);
        Snackbar.make(webView, "复制成功", Snackbar.LENGTH_LONG).show();
    }
    private void share() {
        // 分享的intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        // 分享的数据类型
        intent.setType("text/plain");
        // 分享的主题
        intent.putExtra(Intent.EXTRA_SUBJECT,getIntent().getStringExtra("title"));
        String text = getIntent().getStringExtra("title") + "---(" + getIntent().getStringExtra("url") + ") ";
        if (!TextUtils.isEmpty(getIntent().getStringExtra("mima"))) {
            text = text + "  (密码--" + getIntent().getStringExtra("mima") + ")";
        }
        // 分享的内容
        intent.putExtra(Intent.EXTRA_TEXT, text);
        // 允许启动新的Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 目标应用寻找对话框的标题
        startActivity(Intent.createChooser(intent,getIntent().getStringExtra("title")));
    }

    @Override
    protected void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        webView.pauseTimers();
        super.onPause();
    }

    @Override
    public void onResume() {
        webView.resumeTimers();
        super.onResume();
    }

}
