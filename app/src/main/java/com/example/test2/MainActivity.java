package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.net.Uri;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private WebView wv1;

    final class arkoseJavaScriptInterface {

        @JavascriptInterface
        public void returnToken(String fctoken) {
            Log.w("Arkose", fctoken );
        }

        @JavascriptInterface
        public void setInvisible(){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    wv1.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv1 = (WebView) findViewById(R.id.webview);
        wv1.setWebChromeClient(new WebChromeClient());
        wv1.addJavascriptInterface(new arkoseJavaScriptInterface(), "android");
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String url = Uri.parse("file:///android_asset/index.html").toString();
        wv1.loadUrl(url);
    }
}
