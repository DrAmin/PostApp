package com.newvariable.postapp;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBar actionBar;
    RecyclerView recyclerView;
    ExpandableListView elv_drawer;
    ExapandableListAdapter elv_adapter;
    DrawerMenuAdaptor adaptor;
    TextView tv_post_date,tv_post_category,tv_post_tags;
    List<String> group;
    List<Integer> icon=new ArrayList<>();
    HashMap<String,List<String>> child;

    //List<String> data=new ArrayList<>();
    //List<Integer> icon=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar_main);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container,new FragmentMainPost(),"Post").commit();
      /*  data.add("Home");
        data.add("WordPress");
        data.add("PHP");
        data.add("About");
        icon.add(R.drawable.home);
        icon.add(R.drawable.home);
        icon.add(R.drawable.home);
        icon.add(R.drawable.home);

        adaptor=new DrawerMenuAdaptor(icon,data);
      */
        predata();
        //ExpandableListView
        elv_drawer=(ExpandableListView)findViewById(R.id.elv_drawer_menu_list);
        elv_adapter=new ExapandableListAdapter(this,group,icon,child);
        elv_drawer.setAdapter(elv_adapter);
        elv_drawer.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(MainActivity.this,elv_adapter.getGroup(groupPosition)+"->clicked",Toast.LENGTH_LONG).show();
                return false;
            }
        });
        elv_drawer.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this,elv_adapter.getChild(groupPosition,childPosition)+"clicked",Toast.LENGTH_LONG).show();
                return true;
            }
        });

        //Recycle View Code
        /*recyclerView=(RecyclerView)findViewById(R.id.rv_drawer_menu_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptor);
*/
        //ActionBar code
        actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        // drawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    //Post Footer (date,Link,Category,Tag) Strat

     //   layout=(LinearLayout)findViewById(R.id.ll_post_footer);
        tv_post_date=new TextView(this);
        //tv_post_date.setText();



    }

    private void predata() {
        group=new ArrayList<>();
        child=new HashMap<>();
        group.add("Home");
        group.add("WordPress");
        group.add("PHP");
        group.add("About");
        icon.add(R.drawable.home);
        icon.add(R.drawable.home);
        icon.add(R.drawable.home);
        icon.add(R.drawable.home);


        List<String> home=new ArrayList<>();
        List<String> wordpress=new ArrayList<>();
        wordpress.add("BuddyPress");
        wordpress.add("WooCommrce");
        List<String> php=new ArrayList<>();
        List<String> about=new ArrayList<>();
        child.put(group.get(0),home);
        child.put(group.get(1),wordpress);
        child.put(group.get(2),php);
        child.put(group.get(3),about);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
