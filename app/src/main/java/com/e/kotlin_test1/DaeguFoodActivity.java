package com.e.kotlin_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class DaeguFoodActivity extends AppCompatActivity {

    private List<Restaurant> restaurantList = new ArrayList<>();
    private List<Restaurant2> restaurant2List = new ArrayList<>();
    private RestaurantAdapter adapter;
    private ListView lv_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daegu_food);

        lv_1 = findViewById(R.id.lv_1);
        adapter = new RestaurantAdapter(restaurant2List,this);
        lv_1.setAdapter(adapter);

        new BackgroundTask("달서구").execute();
    }

    class BackgroundTask extends AsyncTask<Void,String,Boolean> {
        String target;
        String s_text;

        public BackgroundTask(String text){
            this.s_text = text;
        }

        @Override
        protected void onPreExecute(){
            try {
                target="https://www.daegufood.go.kr/kor/api/tasty.html?mode=json&addr="+ URLEncoder.encode(s_text,"UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while ((temp=bufferedReader.readLine()) != null){
                    stringBuilder.append(temp+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                String result = stringBuilder.toString().trim();
                result = result.replace(", }","}");
                result = result.replace("\"\"","\"");
                result = result.replace(":\",",":\"\",");
                result = result.replaceAll("^[ |ㄱ-ㅎ|ㅏ-ㅣ|가-힣]\"[ |ㄱ-ㅎ|ㅏ-ㅣ|가-힣]","");

                /*for(int i = 0; i < 30; i++){
                    System.out.print(result.charAt(100833+i));
                }
                System.out.println();
                for(int i = 0; i < 30; i++){
                    System.out.print(result.charAt(18230+i));
                }
                System.out.println();*/
                String name, menu,address;
                int count = 0;
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                while(count<jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    name = object.getString("BZ_NM");
                    menu = object.getString("MNU");
                    address = object.getString("GNG_CS");
                    publishProgress(name,menu,address);
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        @Override
        public void onProgressUpdate(String... values){
            String name , address, menu;
            name = values[0];
            menu = values[1];
            address = values[2];
            restaurantList.add(new Restaurant(name,menu,address));
            restaurant2List.add(new Restaurant2(name,menu,address));
        }
        @Override
        public void onPostExecute(Boolean result){
            adapter.notifyDataSetChanged();
        }
    }
}