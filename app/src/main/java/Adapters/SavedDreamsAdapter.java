package Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.casperthefriendlyghost.R;

import java.util.ArrayList;

import Models.GhostObject;
import database.SQLiteDatabaseHandler;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

/**
 * Created by android on 11/10/17.
 */
public class SavedDreamsAdapter extends RecyclerView.Adapter<SavedDreamsAdapter.ViewHolder> implements View.OnClickListener {
    private ArrayList<GhostObject> Data;
    Context mContext;
    private SQLiteDatabaseHandler db;

    @Override
    public void onClick(View view) {

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView Name;
        public TextView Description;
        public CircleImageView circleImageView;
        public View mView;
        public Button update,delete;
        private RecyclerView.RecyclerListener mListener;
        public ViewHolder(View view) {
            super(view);

            mView = view;
            view.setOnClickListener(this);
            Name = (TextView) view.findViewById(R.id.Name);
            Description = (TextView) view.findViewById(R.id.Description);
            circleImageView = (CircleImageView) view.findViewById(R.id.profile_image);



        }




        @Override
        public void onClick(View view) {

        }
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public SavedDreamsAdapter(ArrayList<GhostObject> mData) {
        Data = mData;
    }

    // Create new views (invoked by the layout managerArrayList<GhostObject> mData;er)
    @Override
    public SavedDreamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        mContext = parent.getContext();

        return new ViewHolder(itemView);


    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        db = new SQLiteDatabaseHandler(mContext);

        final GhostObject ghostObject = Data.get(position);

//        for(GhostObject s : Data){
//            Log.v("Decription",s.getDescription());
//            Log.v("Decription id", String.valueOf(s.getid()));
//        }

        Drawable myDrawable = ContextCompat.getDrawable(mContext, R.drawable.rocket);
        Drawable myDrawable2 = ContextCompat.getDrawable(mContext, R.drawable.wallpaper2);

        Log.v("@@Name",ghostObject.getName().toString());
//        holder.circleImageView.setImageResource(R.drawable.rocket);

        if(ghostObject.getName().toString().equalsIgnoreCase("scary")){
            holder.circleImageView.setImageResource(R.drawable.rocket);
        }
        if(ghostObject.getName().toString().equalsIgnoreCase("Funny")){
            holder.circleImageView.setImageResource(R.drawable.wallpaper2);
        }

        holder.Name.setText(ghostObject.getName().toString());
        holder.Description.setText(ghostObject.getDescription().toString());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create custom dialog object
                final Dialog dialog = new Dialog(mContext);
                // Include dialog.xml file
                dialog.setContentView(R.layout.dialog);
                // Set dialog title Toasty.error(getContext(),"Dream not Found").show();
                dialog.setTitle("Update Dream");
                EditText edtUpdate = (EditText) dialog.findViewById(R.id.updateName);

                final EditText edtDescription = (EditText) dialog.findViewById(R.id.updateDescription);

                Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
                Button btnDelete = (Button) dialog.findViewById(R.id.btnDelete);


                edtUpdate.setText(ghostObject.getName().toString());
                edtDescription.setText(ghostObject.getDescription().toString());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        long pos = position +1;

                        db.updateStory(pos,edtDescription.getText().toString());
                        Toasty.info(mContext,"Feature under development").show();
                    }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long pos = position +1;
                        db.deleteItem(String.valueOf(pos));
                        Toasty.info(mContext,"Feature under development").show();
                    }
                });




                 dialog.show();


            }
        });







    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Log.v("@Data", String.valueOf(Data));
        return Data.size();
    }
}



