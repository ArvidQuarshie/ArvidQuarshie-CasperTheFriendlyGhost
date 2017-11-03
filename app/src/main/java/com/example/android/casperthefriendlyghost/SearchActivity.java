package com.example.android.casperthefriendlyghost;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import Adapters.TabsPagerAdapter;
import Models.GhostObject;
import database.SQLiteDatabaseHandler;

/**
 * Created by android on 11/3/17.
 */

public class SearchActivity extends AppCompatActivity {
    private SQLiteDatabaseHandler db;
    private EditText search ;
    private TextView name,Description;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        search = (EditText) findViewById(R.id.search);
        name = (TextView) findViewById(R.id.Name);
        Description = (TextView) findViewById(R.id.Description);



        db = new SQLiteDatabaseHandler(this);

        Map<String,String> savedDataMap = new HashMap<>();


        for(GhostObject obj :db.displayData()){


            Log.v("@Dreams", obj.getName());

            Log.v("@Description", obj.getDescription());


            if(savedDataMap.containsKey(search.getText().toString())){

                name.setText(search.getText().toString());

            }

            savedDataMap.put(obj.getName(),obj.getDescription());


        }


    }
}
