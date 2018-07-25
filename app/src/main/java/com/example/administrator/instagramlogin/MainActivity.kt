package com.example.administrator.instagramlogin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.example.administrator.instagramlogin.ViewModel.AuthenticationListner
import com.example.administrator.instagramlogin.ViewModel.GetData
import com.example.administrator.instagramlogin.ViewModel.InstagramDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,AuthenticationListner{


    override fun DataRecieved(data: MutableMap<String, String>) {

        var text:String = ""
        for (i in data)
        {
            if(i.key=="profile_picture")
            continue
            else
                text+=i.key+":   "+i.value+"\n"
        }
        testtext.text=text



}


    private var instagramdialog: InstagramDialog? = null

    override fun OnCodeRecieved(auth_token: String) {
        if(auth_token==null)
        {
            return
        }
        else
        {
            Log.d("accetoken", auth_token)
            var getdata = GetData(this)
            getdata.getUserinfo(auth_token)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        log_inbutton.setOnClickListener(View.OnClickListener {
            instagramdialog = InstagramDialog(this,this)
            instagramdialog!!.setCancelable(true)
            instagramdialog!!.show()

         var window = instagramdialog!!.window
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)

        })



    }
}
