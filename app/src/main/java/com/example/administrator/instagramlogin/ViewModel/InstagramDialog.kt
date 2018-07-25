@file:Suppress("DEPRECATION")

package com.example.administrator.instagramlogin.ViewModel

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.text.Layout
import android.util.Log
import android.view.Display
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.Toast
import com.example.administrator.instagramlogin.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.view.*
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.log
import android.webkit.WebSettings



class InstagramDialog(context: Context?, listner: AuthenticationListner) : Dialog(context) {

    private var listner = listner
    private var URL = null
    private var cont: Context? = context

    private var CLIENT_ID = "3fb57bf3f1f54bb2be3b5d696d9e2e5c"
    private var Client_Secret = "9bd94e1966d94625a84bf8c539d8a30f"

    private var webview: WebView? = null;
    //    private var TOKEN_URL = "https://api.instagram.com/oauth/access_token"
    private var AUTH_URL = "https://instagram.com/oauth/authorize/"

    private var Base_URL = "https://instagram.com/"

    private var Redirect_URL = "https://www.algorithmz.net/"

    private val FILL:FrameLayout.LayoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT-15,ViewGroup.LayoutParams.FILL_PARENT-35)

    private val MarginVertical:Int = 50
    private val MarginHorizonral:Int = 15



//    private var url = AUTH_URL  + "?client_id" + CLIENT_ID. + "&client_secret=" +
//            clientsecret + "&redirect_uri=" +
//            callbackurl + "&grant_type=authorization_code"


    private val httpAuth = AUTH_URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + Redirect_URL +
            "&response_type=token&scope=public_content"

    private var conn = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.auth_dialog)
        initWebview()

       // var connection = HttpCon(httpAuth).execute()

        webview = findViewById(R.id.insta_webview)


//        var connecthttp = HttpCon(httpAuth, this)
//        connecthttp.execute()



        var settings = webview!!.settings
        settings.javaScriptEnabled = true
        webview!!.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY)

        var display:Display = window.windowManager.defaultDisplay

        webview!!.webViewClient = object : WebViewClient() {
            var accetoken: String = ""
            var authcomplete: Boolean = false

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }


            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)


                if (url!!.contains("#access_token=") && authcomplete == false) {
                    var uri: Uri = Uri.parse(url)
                    accetoken = uri.encodedFragment
                    accetoken = accetoken.substring(accetoken.lastIndexOf("=") + 1)
                    authcomplete = true

                    listner.OnCodeRecieved(accetoken)
                    dismiss()

                } else if (url.contains("?error")) {
                    Log.e("erorr", url)
                    dismiss()
                }

            }

        }
        webview!!.loadUrl(httpAuth)


    }


    fun initWebview() {


    }
}