package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Models.GhostObject;
import database.SQLiteDatabaseHandler;

/**
 * Created by android on 11/3/17.
 */

public class SecondFragment extends Fragment {

    private SQLiteDatabaseHandler db;
    private EditText etName,etDesscription;
    private Button btnSave,btnSearch;
    private ArrayList<GhostObject> ghostObjectArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        etName = (EditText)view.findViewById(R.id.Name);
        etDesscription = (EditText)view.findViewById(R.id.Description);
        btnSave =(Button) view.findViewById(R.id.btn_save);
        btnSearch =(Button) view.findViewById(R.id.btn_search);


        db = new SQLiteDatabaseHandler(getContext());

btnSave.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        saveDream();
    }
});



        Log.v("@SavedData", String.valueOf(db.displayData()));

        Map<String,String> savedDataMap = new HashMap<>();

for(GhostObject obj :db.displayData()){


    Log.v("@Dreams", obj.getName());

    Log.v("@Description", obj.getDescription());

    savedDataMap.put(obj.getName(),obj.getDescription());

}
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
        Toast.makeText(getContext(), "Your Dream Was Saved Ghost" , Toast.LENGTH_LONG).show();
    }
}
