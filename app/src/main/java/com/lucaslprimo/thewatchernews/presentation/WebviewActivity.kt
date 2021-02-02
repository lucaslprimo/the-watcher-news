package com.lucaslprimo.thewatchernews.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.lucaslprimo.thewatchernews.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

    var webView: WebView? = null
    var url:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        this.webView = this.webview_news

        this.webView?.webViewClient = WebViewClient()

        intent.let { intent ->
            intent.extras?.getString("url").let {
                url=it!!
                this.webView?.loadUrl(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_share -> {
            share(url)
            true
        }
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun share(url: String) {
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message, url))
        sendIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.share_title))
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, null))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_details, menu)
        return true
    }

    override fun onBackPressed() {
        if(webView!!.canGoBack()){
            webView!!.goBack()
        }else{
            super.onBackPressed()
        }
    }
}
