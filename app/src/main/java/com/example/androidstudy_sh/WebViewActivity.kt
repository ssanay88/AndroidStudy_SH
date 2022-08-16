package com.example.androidstudy_sh

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast

class WebViewActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val myWebView: WebView = findViewById(R.id.webView)
        myWebView.loadUrl("https://AndroidStudy.pplloo748.repl.co")
        myWebView.settings.javaScriptEnabled = true

    }
}

class WebAppInterface(private val mContext: Context) {

    @JavascriptInterface
    fun showToast() {
        Toast.makeText(mContext, "성공적으로 등록되었습니다.", Toast.LENGTH_SHORT).show()
    }

}