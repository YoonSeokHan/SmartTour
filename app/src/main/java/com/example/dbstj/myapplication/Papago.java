package com.example.dbstj.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import static android.content.ContentValues.TAG;

/**
 * Created by voidbluelabtop on 17. 11. 27.
 */



public class Papago extends AsyncTask{
    private String clientId;//애플리케이션 클라이언트 아이디값";
    private String clientSecret;//애플리케이션 클라이언트 시크릿값";
    private String translatedText;
    private HttpURLConnection con;
    public Papago(){
        clientId = "Ngg9JMSukHPt8BgTNdh_";
        clientSecret = "MJeTMgAeVx";
        translatedText = null;
        String apiURL = "https://openapi.naver.com/v1/language/translate";
        URL url = null;
        try {
            url = new URL(apiURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        translatedText = translate((String)objects[0]);
        return null;
    }
    @Override
    protected void onCancelled(Object result){
        super.onCancelled();
    }
    public String translate(String text) {
        try {
            text = URLEncoder.encode(text, "UTF-8");
            // post request
            String postParams = "source=ko&target=en&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            Log.d(TAG, "translate: " + "테스트");
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                Log.d(TAG, "translate: " + "정상호출");
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));

                Log.d(TAG, "translate: " + "에러");
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            System.out.println("에러" + e);
        }
        return "";
    }

    public String getTranslatedText(){
        return translatedText;
    }
}