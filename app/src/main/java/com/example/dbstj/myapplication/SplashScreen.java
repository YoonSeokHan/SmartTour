package com.example.dbstj.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {  // 스플래쉬 엑티비티


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //엑티비티 생성함수
        setContentView(R.layout.activity_what_su_gha); //왓수과 레이아웃 불러오기 함수

        //핸들러 메소드
        Handler hd = new Handler(); // hd 라는 Handler 메소드 생성
        hd.postDelayed(new Runnable() {
            @Override
            public void run() { // 핸들러 메소드에 3초라는 시간 부여( 3초후에 화면 꺼짐 )
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();       // 3 초후 이미지를 닫아버림
            }
        }, 2000);
    }
}
