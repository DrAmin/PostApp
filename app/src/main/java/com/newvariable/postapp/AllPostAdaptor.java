package com.newvariable.postapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by deepa on 23/02/2016.
 */
public class AllPostAdaptor extends RecyclerView.Adapter<AllPostAdaptor.AllPostViewHolder> {
    List<Post> data;
    Context context;
    public AllPostAdaptor(Context context,List<Post> data){
       this.context=context;
        this.data=data;
    }
    @Override
    public AllPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main_fragment,parent,false);
        AllPostViewHolder holder=new AllPostViewHolder(v);
       /* v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=v.getChildAdapterPosition(v);
                if(pos>=0 && pos<getItemCount()){
                    Toast.makeText(context,""+data.get(pos).getPost_title(),Toast.LENGTH_LONG).show();
                }
            }
        });
 */       return holder;
    }

    @Override
    public void onBindViewHolder(AllPostViewHolder holder, int position) {
        Post p=data.get(position);
        String start = "<html><head><meta http-equiv='Content-Type' content='text/html' charset='UTF-8'  /></head><body>";
        String end = "</body></html>";
        String dataStr=p.getDesc();
        holder.title.setText(p.getPost_title());
        holder.desc.loadData(URLEncoder.encode(start + dataStr + end).replaceAll("\\+","%20"),"text/html;charset=UTF-8",null);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AllPostViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        WebView desc;
        public AllPostViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cv_main_post);
            title=(TextView)itemView.findViewById(R.id.tv_post_title);
            desc=(WebView)itemView.findViewById(R.id.wv_post_desc);
            desc.setScrollbarFadingEnabled(false);

            WebSettings descWeb=desc.getSettings();
            descWeb.setJavaScriptEnabled(true);
            descWeb.setLoadWithOverviewMode(true);
            descWeb.setTextSize(WebSettings.TextSize.NORMAL);
            descWeb.setRenderPriority(WebSettings.RenderPriority.HIGH);
            descWeb.setCacheMode(WebSettings.LOAD_NO_CACHE);
            descWeb.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            //Recycler Click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    Intent intent=new Intent(context,SinglePostActivity.class);
                    intent.putExtra("Self_link",data.get(pos).getSelf_url());
                    context.startActivity(intent);

                }
            });

        }
    }
}
