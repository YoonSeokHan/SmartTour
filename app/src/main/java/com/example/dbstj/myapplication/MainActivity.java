package com.example.dbstj.myapplication;
import android.icu.util.JapaneseCalendar;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dbstj on 2017-11-25.
 */

public class MainActivity extends AppCompatActivity{ // 메인화면 엑티비티
    Papago papago;
    JSONObject jo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        //스피너 동작 메소드
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner s2 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.city, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Button btn = (Button)findViewById(R.id.button);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                papago = new Papago();
                papago.execute("강의실", "ko", "en");
                while(true){
                    if (papago.getTranslatedText() != null){
                        try {
                            JSONArray ja = new JSONArray("[" + papago.getTranslatedText() + "]");
                            JSONObject jo = ja.getJSONObject(0);
                            ja = new JSONArray("[" + jo.get("message") + "]");
                            jo = ja.getJSONObject(0);
                            ja = new JSONArray("[" + jo.get("result") + "]");
                            jo = ja.getJSONObject(0);
                            Toast.makeText(getApplicationContext(),jo.getString("translatedText") ,Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                papago = null;

            }

        });


    }
    public void run() {

        papago = null;
    }
}
