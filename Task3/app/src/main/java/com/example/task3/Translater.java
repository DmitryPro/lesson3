package com.example.task3;

import android.net.http.*;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.*;


public class Translater {

    protected String APIKey;
    protected String Languege;
    protected String Text;

    void setAPIKey(String APIKey) {
        this.APIKey = APIKey;
    }

    void setLanguege(String Languege) {
        this.Languege = Languege;
    }

    void setText(String Text) {
        this.Text = Text.replace(' ', '+');
    }

    private String setRequestURL() {
        String requestURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?"
                + "key=" + APIKey + "&"
                + "text=" + Text + "&"
                + "lang=" + Languege;
        return requestURL;
    }


    String res;

    String sendRequest(){

            YATranslateTask tt = new YATranslateTask();
            tt.execute();
            return res;

    }

    class YATranslateTask extends AsyncTask<Void, Void, Void> {

        AndroidHttpClient client;
        HttpGet request;
        InputStream is;
        HttpResponse response;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            client = AndroidHttpClient.newInstance("YourBunnyWrote");
            request = new HttpGet(setRequestURL());


        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                response = client.execute(request);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {
                is = response.getEntity().getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder st = new StringBuilder();
                String read = reader.readLine();
                while (read != null){
                    st.append(read);
                    read = reader.readLine();
                }
                read = st.toString();
                JSONObject obj = new JSONObject(read);
                int code = obj.getInt("code");
                if(code == 200){
                    res = obj.getString("text");
                }
                else{
                    res = obj.getString("message");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}