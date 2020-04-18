package com.example.retorfitgetpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retorfitgetpractice.response.GetRepoUrlResp
import com.example.retorfitgetpractice.response.GetUserResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiManager.getInstance().getUser("idstudent").enqueue(object : Callback<GetUserResp> {
            override fun onFailure(call: Call<GetUserResp>, t: Throwable) {
                Log.e("tag", "getUserResp 에러")
            }

            override fun onResponse(call: Call<GetUserResp>, response: Response<GetUserResp>) {
                if(response.isSuccessful) {
                    val body = response.body()
                    body.apply {
                        Log.e("tag", "login  "+this?.login + " url "+this?.url)
                    }
                }
            }
        })
        ApiManager.getInstance().getRepo("idstudent","KaKaoMapAPITest").enqueue(object : Callback<GetRepoUrlResp> {

            override fun onFailure(call: Call<GetRepoUrlResp>, t: Throwable) {
                Log.e("tag", "getRepo 에러")
            }

            override fun onResponse(
                call: Call<GetRepoUrlResp>,
                response: Response<GetRepoUrlResp>
            ) {
                if(response.isSuccessful) {
                    val body = response.body()
                    body.apply {
                        Log.e("tag", "name  "+this?.name + " fullName "+this?.full_name +" owner login" + this?.owner?.login + " owner url "+ this?.owner?.url)
                    }
                }
            }
        })
    }
}
