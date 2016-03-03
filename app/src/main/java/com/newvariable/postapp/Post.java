package com.newvariable.postapp;

/**
 * Created by deepa on 23/02/2016.
 */
public class Post {

    String post_title;
    String desc;
    String self_url;

    public Post(String post_title,String desc,String self_url){
        this.post_title=post_title;
        this.desc=desc;
        this.self_url=self_url;
    }

    public String getSelf_url() {
        return self_url;
    }

    public void setSelf_url(String self_url) {
        this.self_url = self_url;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
