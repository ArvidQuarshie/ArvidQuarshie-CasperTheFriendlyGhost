package Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.casperthefriendlyghost.R;

import org.json.JSONException;
import org.json.JSONObject;

import database.SQLiteDatabaseHandler;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by android on 11/3/17.
 */

public class FirstFragment extends Fragment {

    private TextView txtusd,txteur;
    private SQLiteDatabaseHandler db;
    private GifImageView mGifImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        mGifImageView = (GifImageView) view.findViewById(R.id.gifSplash);



        return view;


    }




}

