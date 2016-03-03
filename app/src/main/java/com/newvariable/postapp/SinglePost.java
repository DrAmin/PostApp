package com.newvariable.postapp;

/**
 * Created by deepa on 24/02/2016.
 */
public class SinglePost {

    String sigle_post_title;
    String sigle_post_desc;

    public SinglePost(String sigle_post_title,String sigle_post_desc){
        this.sigle_post_title=sigle_post_title;
        this.sigle_post_desc=sigle_post_desc;
    }

    public String getSigle_post_title() {
        return sigle_post_title;
    }



    public String getSigle_post_desc() {
        return sigle_post_desc;
    }

}
