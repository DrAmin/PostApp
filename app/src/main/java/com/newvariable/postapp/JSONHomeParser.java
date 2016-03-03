package com.newvariable.postapp;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by deepa on 22/02/2016.
 */
public class JSONHomeParser {
    static InputStream is;
    static JSONObject jobj=null;
    static JSONArray jarray;
    static String dataJson="";
   public JSONObject getDataFromUrl(String url){
     try{
        HttpClient httpClient=new DefaultHttpClient();
         HttpPost httpPost=new HttpPost(url);
         HttpResponse response=httpClient.execute(httpPost);
         HttpEntity entity=response.getEntity();
         is=entity.getContent();
     }catch (UnsupportedEncodingException e){
         e.printStackTrace();
     }catch (ClientProtocolException e){
         e.printStackTrace();
     }catch (IOException e){
         e.printStackTrace();
     }
       try{
           BufferedReader reader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
           StringBuilder sb=new StringBuilder();
           String line=null;
           while((line=reader.readLine())!=null){
               sb.append(line+"\n");
           }
           is.close();
            dataJson=sb.toString();
       }catch (Exception e){
           Log.i("Error", e.toString());
       }
        try{
            jobj=new JSONObject(dataJson);
        }catch (JSONException e){
            Log.i("JSONError",e.toString());
        }
        return jobj;
    }

}
