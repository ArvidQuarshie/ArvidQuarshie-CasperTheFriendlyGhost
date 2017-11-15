package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.casperthefriendlyghost.R;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import Models.GhostObject;
import database.SQLiteDatabaseHandler;
import es.dmoral.toasty.Toasty;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by android on 11/3/17.
 */

public class SearchFragment extends Fragment {

    private SQLiteDatabaseHandler db;
    private EditText search ;
    private TextView name,Description;
    private Button btnSearch;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search, container, false);
        search = (EditText) view.findViewById(R.id.search);
        name = (TextView) view.findViewById(R.id.Name);
        Description = (TextView) view.findViewById(R.id.Description);
        btnSearch =(Button) view.findViewById(R.id.btn_search);


        db = new SQLiteDatabaseHandler(getContext());

        final Map<String,String> savedDataMap = new HashMap<>();


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(GhostObject obj :db.displayData()){


                    Log.v("@Dreams", obj.getName());

                    Log.v("@Description", obj.getDescription());

                    savedDataMap.put(obj.getName(),obj.getDescription());

                    if(savedDataMap.containsKey(search.getText().toString())){

                        Log.v("@Dreamsinsideif", obj.getName());
                        Description.setText(savedDataMap.get(search.getText().toString()));
                        Toasty.success(getContext(),"Dream Found").show();

                    }else{
                        Toasty.error(getContext(),"Dream not Found").show();
                    }




                }
            }
        });



        return view;


    }

}
