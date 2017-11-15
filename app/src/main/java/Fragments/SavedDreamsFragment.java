package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.android.casperthefriendlyghost.R;

import java.util.ArrayList;

import Adapters.SavedDreamsAdapter;
import Models.GhostObject;
import database.SQLiteDatabaseHandler;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by android on 11/10/17.
 */

public class SavedDreamsFragment extends Fragment {

    private TextView txtusd,txteur;
    private GifImageView mGifImageView;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SQLiteDatabaseHandler db;
    private ArrayList<GhostObject> mData;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saved_dreams, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        //fetching data from the database
        db = new SQLiteDatabaseHandler(getContext());
        mData =db.displayData();

        Log.v("@MData" , String.valueOf(mData));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        // specify an adapter (see also next example)
        mAdapter = new SavedDreamsAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();




        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        mData =db.displayData();
        mAdapter.notifyDataSetChanged();
    }


}
