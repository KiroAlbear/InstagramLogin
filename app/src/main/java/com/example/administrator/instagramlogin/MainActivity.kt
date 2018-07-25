package com.example.administrator.instagramlogin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.administrator.instagramlogin.ViewModel.AuthenticationListner
import com.example.administrator.instagramlogin.ViewModel.InstagramDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,AuthenticationListner{

    private var instagramlogin: InstagramDialog? = null
    override fun OnCodeRecieved(auth_token: String) {
        if(auth_token==null)
        {
            return
        }
        else
            Log.d("accetoken", auth_token)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log_inbutton.setOnClickListener(View.OnClickListener {
            instagramlogin = InstagramDialog(this,this)
            instagramlogin!!.setCancelable(true)
            instagramlogin!!.show()
        })

    }
}
