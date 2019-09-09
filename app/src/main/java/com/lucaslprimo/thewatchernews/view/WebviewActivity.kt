package com.lucaslprimo.thewatchernews.view

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.lucaslprimo.thewatchernews.R
import kotlinx.android.synthetic.main.activity_webview.*


class WebviewActivity : AppCompatActivity() {

    var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        this.webView = this.webview_news

        this.webView?.webViewClient = WebViewClient()

        intent.let { intent ->
            intent.extras?.getString("url").let {
                this.webView?.loadUrl(it)
            }
        }
    }
}
