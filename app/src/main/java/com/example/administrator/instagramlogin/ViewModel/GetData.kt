package com.example.administrator.instagramlogin.ViewModel

import android.os.AsyncTask
import android.util.Log
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.impl.conn.DefaultClientConnection
import org.apache.http.util.EntityUtils
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.math.log

class GetData(listner: AuthenticationListner) {


    private var listner = listner
    fun getUserinfo(accessToken: String) {
        val infowebsite = "https://api.instagram.com/v1/users/self/?access_token=" + accessToken
        var getinfo = RequestInstagramApi(infowebsite, listner).execute()
    }

    private class RequestInstagramApi(infowebsite: String, listner: AuthenticationListner) : AsyncTask<Void, String, String>() {

        private var listner = listner
        private val infowebsite = infowebsite

        override fun doInBackground(vararg params: Void?): String? {

            var httpclient: HttpClient = DefaultHttpClient()
            var httpget: HttpGet = HttpGet(infowebsite)
            try {
                var response: HttpResponse = httpclient.execute(httpget)
                var httpEntity: HttpEntity = response.entity
                var json: String = EntityUtils.toString(httpEntity)
                return json
            } catch (exception: ClientProtocolException) {
                Log.d("ClientProtocolException", exception.toString())
            } catch (exception: IOException) {
                Log.d("IOException", exception.toString())
            }
            return null


        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var data = mutableMapOf<String, String>()
            if (result != null) {
                try {
                    var jsonobject: JSONObject = JSONObject(result)
                    jsonobject = jsonobject.getJSONObject("data")

                    if (jsonobject.has("id"))
                        data!!.put("id", jsonobject.getString("id"))

                    if (jsonobject.has("username"))
                        data!!.put("username", jsonobject.getString("username"))

                    if (jsonobject.has("profile_picture"))
                        data!!.put("profile_picture", jsonobject.getString("profile_picture"))


                    if (jsonobject.has("full_name"))
                        data!!.put("full_name", jsonobject.getString("full_name"))

                    if (jsonobject.has("bio"))
                        data!!.put("bio", jsonobject.getString("bio"))

                    if (jsonobject.has("website"))
                        data!!.put("website", jsonobject.getString("website"))

                    if (jsonobject.has("is_business"))
                        data!!.put("is_business", jsonobject.getString("is_business"))

                    if (jsonobject.has("counts")){
                        jsonobject = jsonobject.getJSONObject("counts")


                        if (jsonobject.has("media"))
                        data!!.put("media", jsonobject.getInt("media").toString())

                        if (jsonobject.has("follows"))
                            data!!.put("follows", jsonobject.getInt("follows").toString())

                        if (jsonobject.has("followed_by"))
                            data!!.put("followed_by", jsonobject.getInt("followed_by").toString())
                    }




                    listner.DataRecieved(data!!)


                } catch (exception: JSONException) {
                    Log.d("JSONException", exception.toString())
                }
            }
        }
    }

}