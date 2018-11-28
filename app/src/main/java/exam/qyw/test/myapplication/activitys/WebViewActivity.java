package exam.qyw.test.myapplication.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.LogUtil;

/**
 * Created by Author:qyw
 * on 2018/11/21.
 * QQ:448739075
 * 描述：
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    private String url = "http://www.baidu.com";

    @Override
    public int innitLayout() {
        return R.layout.activity_webview_test;
    }

    @Override
    public void innitData() {
        innitWebViewSettings();
        webView.loadUrl("file:///android_asset/test");
    }

    @SuppressLint("JavascriptInterface")
    private void innitWebViewSettings() {
        WebSettings webSettings = webView.getSettings();
//        设置支持javaScript脚本
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCachePath(Environment.getExternalStorageDirectory() + "webCach");
        webSettings.setAppCacheEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
//        webView.addJavascriptInterface(new AndroidtoJs(), "test");//AndroidtoJS类对象映射到js的test对象

        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtil.i("currenturl-onPageStarted:" + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LogUtil.i("currenturl-onPageFinished:" + url);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String error_msg = error.getDescription().toString();
                    LogUtil.i("error msg:" + error_msg);
                    LogUtil.i("webView:" + view.getUrl());
                    LogUtil.i("webView:" + webView.getUrl());
                    LogUtil.i("is the same:" + (view == webView));
                    if (error_msg.contains("ERR_UNKNOWN_URL_SCHEME")) {
                        view.setVisibility(View.VISIBLE);
                    } else
                        super.onReceivedError(view, request, error);

                } else {
                    super.onReceivedError(view, request, error);
                }


            }



        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                LogUtil.i("newProgress:" + newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                LogUtil.i("title:" + title);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(getmActivity());
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });

        webView.addJavascriptInterface(new Object() {

            public void clickOnAndroid() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getmActivity(), "测试调用java", Toast.LENGTH_LONG).show();

                    }
                });

            }

        }, "demo");
    }

    @OnClick(R.id.bt_alert)
    public void onClick(View view){
        if(view.getId()==R.id.bt_alert){

            // 注意调用的JS方法名要对应上
            // 调用javascript的callJS()方法
//            第一种方法
//            webView.loadUrl("javascript:myFunction()");
//            第二种方法
            webView.evaluateJavascript("javascript:myFunction()", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    //此处为 js 返回的结果
                    LogUtil.i("value:"+value);
                }
            });

        }
    }
}
