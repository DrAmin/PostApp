package com.newvariable.postapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepa on 23/02/2016.
 */
public class FragmentMainPost extends Fragment {
    public RecyclerView all_post;
    AllPostAdaptor allPostAdaptor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_main,container,false);
        all_post=(RecyclerView)v.findViewById(R.id.rv_home_all_post);
        all_post.setLayoutManager(new LinearLayoutManager(getActivity()));
        new JsonParseTask().execute("http://test.newvariable.com/wp-json/wp/v2/posts");
//        Log.i("hello", "Oncreate");

        //all_post.setAdapter(allPostAdaptor);
        return v;
    }

public class JsonParseTask extends AsyncTask<String,String,String>{

        InputStream is;
        List<Post> listData=new ArrayList<>();
        HttpURLConnection connection=null;
        BufferedReader reader=null;
        ProgressDialog pd;
        protected void onPreExecute(){
            super.onPreExecute();

        }

            @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                is = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is));
                StringBuffer buf = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buf.append(line);
                }
                return buf.toString();
            }catch(MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            try {

                Log.i("hello","OnPOstExecute"+result);
                JSONArray array = new JSONArray(result);
                for(int i=0;i<array.length();i++){
                    JSONObject json=array.getJSONObject(i);
                    //Store JsonData In Cache Memory
                    ObjectOutput out=new ObjectOutputStream(new FileOutputStream(new File(getActivity().getCacheDir(),"")+File.separator+"allpostjson.srl"));
                    out.writeObject(json.toString());
                    out.close();


                    JSONObject titleJson=json.getJSONObject("title");
                    String title=titleJson.getString("rendered");
                   // JSONArray titleArray=json.getJSONArray("title");
      //              Log.i("title-------->",title);
                    JSONObject descJson=json.getJSONObject("content");
                    String desc=descJson.getString("rendered");
                    JSONObject link=json.getJSONObject("_links");
                    JSONArray self=link.getJSONArray("self");
                    JSONObject self_obj=self.getJSONObject(0);
                    String self_link=self_obj.getString("href");
  //                  Toast.makeText(getActivity(),"SelfLink"+self_link,Toast.LENGTH_LONG).show();
                    Post p=new Post(title,desc,self_link);
                    listData.add(p);
                    //Log.i("title========>", title);
    //                Log.i("desc=======>",desc);
                }
                allPostAdaptor=new AllPostAdaptor(getActivity(),listData);
                all_post.setAdapter(allPostAdaptor);

            }catch (JSONException e){
                e.printStackTrace();
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
//RecyclerView Click Event


class RecyclerItemClick implements RecyclerView.OnItemTouchListener{

    private OnItemClickListener mlistener;
    public interface OnItemClickListener{
        public void onItemClick(View view,int pos);
    }
    GestureDetector gestureDetector;

    public RecyclerItemClick(Context context,OnItemClickListener listener){
        mlistener=listener;
        gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child=rv.findChildViewUnder(e.getX(),e.getY());
        if(child!=null && mlistener!=null && gestureDetector.onTouchEvent(e)){
            mlistener.onItemClick(child,rv.getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
