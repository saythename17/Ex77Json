package com.icandothisallday2020.ex77json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
    }

    public void click(View view) {
        //assets 폴더의 파일을 가져오기 위해 asset  창고관리자 소환
        AssetManager manager=getAssets();
        //assets/text.json 파일을 얻기위한 InputStream 연결
        try {
            InputStream is=manager.open("test.json");
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader reader=new BufferedReader(isr);
            StringBuffer buffer=new StringBuffer();
            while(true){
                String line=reader.readLine();
                if(line==null) break;
                buffer.append(line+"\n");
            }
            String jsonData=buffer.toString();

            //읽어온 jsonData 문자열을 parsing(분석)
//            JSONObject object=new JSONObject(jsonData);
//            String name=object.getString("name");
//            String message=object.getString("msg");

            //읽어온 jsonData 문자열 배열을 parsing(분석)
            JSONArray jsonArray=new JSONArray(jsonData);
            StringBuffer buffer1=new StringBuffer();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jo=jsonArray.getJSONObject(i);
                String name=jo.getString("name");
                String message=jo.getString("msg");

                JSONObject address=jo.getJSONObject("address");
                String city=address.getString("city");
                int post=address.getInt("post");

                buffer1.append(name+","+message+"===>"+city+","+post+"\n");

            }
            tv.setText(buffer1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
