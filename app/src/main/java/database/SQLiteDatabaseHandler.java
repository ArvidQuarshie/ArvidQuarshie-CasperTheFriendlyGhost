package database;

/**
 * Created by android on 11/3/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.opengl.GLDebugHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import Models.GhostObject;


public class SQLiteDatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Ghost";
    private static final String TABLE_NAME = "Dream";
    private static final String KEY_ID = "id";
    private static final String DES = "DES";
    private static final String NAME = "NAME";


    private static final String[] COLUMNS = { DES, NAME};



    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


//    String CREATION_TABLE = "CREATE TABLE Bit ( "
//            + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name TEXT, "
//            + "position TEXT, " + "height INTEGER )";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATION_TABLE = "CREATE TABLE Dream ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,  " +  "DES TEXT, "
                + "NAME TEXT )";

        sqLiteDatabase.execSQL(CREATION_TABLE);



    }

    public void deleteItem( String id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"id = ?",new String[]{id});

    }

    /*
    Saved Data in the Table
     */

    public void insertData(GhostObject ghostObject){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, ghostObject.getName());
        values.put(DES, ghostObject.getDescription());

        db.insert(TABLE_NAME,null, values);

        db.close();

    }

    public boolean updateData( String id ,String des,String name){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DES,des );
        values.put(NAME,name);

        // updating row
        db.update(TABLE_NAME, values,  "id = ?",
                new String[] { id});

        return true;
    }


     public boolean updateStory(long Id, String description){
         SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(KEY_ID, Id);
        args.put(DES, description);

          Log.v("Update", String.valueOf(db.update(TABLE_NAME, args, KEY_ID + "=" + Id, null) > 0)) ;
        return db.update(TABLE_NAME, args, KEY_ID + "=" + Id, null) > 0;


      }




/*
Fetch Data from the db
 */

    public ArrayList<GhostObject> displayData(){

        ArrayList<GhostObject> arrayList = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        GhostObject ghostObject = null;

        Log.v("@cursor contents", DatabaseUtils.dumpCursorToString(cursor));

        if (cursor.moveToFirst()) {
            do {
                ghostObject = new GhostObject();
                ghostObject.setName(cursor.getString(1));
                ghostObject.setDescription(cursor.getString(2));

                arrayList.add(ghostObject);
            } while (cursor.moveToNext());
        }
        return arrayList;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }
}

