package com.newvariable.postapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlTextView;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by deepa on 24/02/2016.
 */
public class SinglePostActivity extends AppCompatActivity {
    TextView tv_single_post_title;
    HtmlTextView tv_single_post_desc;
    WebView wv_single_post_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_post_activity);
        Intent in=getIntent();
        String link=in.getStringExtra("Self_link");

        tv_single_post_title=(TextView)findViewById(R.id.tv_single_post_title);
        //tv_single_post_desc=(HtmlTextView)findViewById(R.id.tv_single_post_desc);
        wv_single_post_desc=(WebView)findViewById(R.id.wv_single_post_desc);
        //wv_single_post_desc.setInitialScale(1);
        wv_single_post_desc.setScrollbarFadingEnabled(false);
        WebSettings descWeb=wv_single_post_desc.getSettings();
        float fontSize=getResources().getDimension(R.dimen.txtsize);
        descWeb.setJavaScriptEnabled(true);
        descWeb.setLoadWithOverviewMode(true);
        descWeb.setDefaultFontSize((int) fontSize);
        descWeb.setMinimumFontSize((int)fontSize);
        descWeb.setTextSize(WebSettings.TextSize.NORMAL);
        descWeb.setUseWideViewPort(true);
        descWeb.setRenderPriority(WebSettings.RenderPriority.HIGH);
        descWeb.setCacheMode(WebSettings.LOAD_NO_CACHE);
        descWeb.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        descWeb.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        new SinglePostData().execute(link);

    }
/*

    private int getScale(){
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width)/200d;
        val = val * 100d;
        return val.intValue();
    }
*/

    private class SinglePostData extends AsyncTask<String,String,String>{
        InputStream iStream;
        BufferedReader bReader;
        HttpURLConnection urlConnection=null;

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                iStream = urlConnection.getInputStream();
                bReader = new BufferedReader(new InputStreamReader(iStream));
                StringBuffer sBuf = new StringBuffer();
                String line = "";
                while ((line = bReader.readLine()) != null) {
                    sBuf.append(line);
                }
                return sBuf.toString();
            }catch(MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                try {
                    if (bReader != null) {
                        bReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            try{
                JSONObject s_post_object=new JSONObject(result);

               //     JSONObject s_post_object=s_post_array.getJSONObject(i);
                        Log.i("result++++++>",result);

                    //Content of JSon File
                    JSONObject s_titleJson=s_post_object.getJSONObject("title");
                    String title=s_titleJson.getString("rendered");
                    // JSONArray titleArray=json.getJSONArray("title");
                    Log.i("title-------->", title);
                    JSONObject s_descJson=s_post_object.getJSONObject("content");
                    String desc=s_descJson.getString("rendered");
                    Log.i("desc----",desc);
                    tv_single_post_title.setText(title);
                    String start = "<html><head><meta http-equiv='Content-Type' content='text/html' charset='UTF-8'  /></head><body>";
                    String end = "</body></html>";
                Spanned htmlSpanned=Html.fromHtml(start + desc + end);
                  //  tv_single_post_desc.setText(htmlSpanned);
                   // tv_single_post_desc.setMovementMethod(LinkMovementMethod.getInstance());
                    wv_single_post_desc.loadData(URLEncoder.encode(start+desc+end).replaceAll("\\+","%20"),"text/html;charset=UTF-8",null);


            }catch (JSONException e){
                e.printStackTrace();
            }

        }
    }
}
