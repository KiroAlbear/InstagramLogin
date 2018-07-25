package com.example.administrator.instagramlogin.ViewModel

import android.os.AsyncTask
import java.net.URL

class HttpCon(connecturl: String,con:Connecturl): AsyncTask<Void, Void, Void>(){
    var connecturl = connecturl
    var conn = con

    override fun doInBackground(vararg params: Void?): Void? {
        val con = URL(connecturl).openConnection()
        con.connect()
        con.getInputStream()


        var  constr= con.getHeaderField("Location")
        conn.connecttourl(constr)
        return null
    }



    public interface Connecturl{
    fun connecttourl(con:String)
}


}