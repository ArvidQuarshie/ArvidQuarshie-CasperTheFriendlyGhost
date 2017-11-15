package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.casperthefriendlyghost.R;
import com.example.android.casperthefriendlyghost.SearchActivity;
import com.github.clans.fab.FloatingActionButton;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Models.GhostObject;
import database.SQLiteDatabaseHandler;
import es.dmoral.toasty.Toasty;


/**
 * Created by android on 11/3/17.
 */

public class SaveDreamsFragment extends Fragment {

    private SQLiteDatabaseHandler db;
    private EditText etName,etDesscription;
    private Button btnSave,btnSearch;
    private ArrayList<GhostObject> ghostObjectArrayList;
    private FloatingActionButton mFab;
    private android.support.design.widget.FloatingActionButton fab;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        etName = (EditText)view.findViewById(R.id.Name);
        etDesscription = (EditText)view.findViewById(R.id.Description);
//        btnSave =(Button) view.findViewById(R.id.btn_save);
        btnSearch =(Button) view.findViewById(R.id.btn_search);
        fab = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.fab);

        final TextInputLayout nameOfDreamInputLayout = (TextInputLayout) view.findViewById(R.id.dreamName);
        final TextInputLayout describeDreamInputLayout = (TextInputLayout) view.findViewById(R.id.dreamDescribe);

        db = new SQLiteDatabaseHandler(getContext());
        ghostObjectArrayList = db.displayData();




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmptyString(etName.getText().toString())){
                    nameOfDreamInputLayout.setError("Add the name of your dream before saving.");
                }
                else if(isEmptyString(etDesscription.getText().toString())){
                    describeDreamInputLayout.setError("Add the description of your dream before saving.");
                }else{
                    saveDream();
                }



            }
        });



        Log.v("@SavedData", String.valueOf(db.displayData()));

//        Map<String,String> savedDataMap = new HashMap<>();

//for(GhostObject obj :db.displayData()){
//
//
//    Log.v("@Dreams", obj.getName());
//
//    Log.v("@Description", obj.getDescription());
//
//    savedDataMap.put(obj.getName(),obj.getDescription());
//
//}





        return view;


    }
/*
 Saving our dream to the database
 */

    public void saveDream(){
        String name = etName.getText().toString();
        String Description = etDesscription.getText().toString();
        GhostObject ghostObject = new GhostObject(name,Description);
        db.insertData(ghostObject);
        Toasty.success(getContext(), " Your Dream is Saved!", Toast.LENGTH_SHORT, true).show();
    }

    public static boolean isEmptyString(String text) {
        boolean isEmpty = false;
        try {
            isEmpty = (text == null || text.trim().equals("null") || text.trim().equals(""));
        } catch (Exception ex) {
            isEmpty = false;
        }
        return isEmpty;
    }
}
