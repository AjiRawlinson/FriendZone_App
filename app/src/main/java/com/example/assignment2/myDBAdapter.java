package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;


public class myDBAdapter {
    myDbHelper myHelper;

    public myDBAdapter(Context con) {
        myHelper = new myDbHelper(con);
    }

    public long insertData(String name, String number, String email) {
        String nameUpper = "" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(myDbHelper.NAME, nameUpper);
        cv.put(myDbHelper.NUMBER, number);
        cv.put(myDbHelper.EMAIL, email);
        long id = db.insert(myDbHelper.TABLE_NAME, null, cv);
        return id;
    }

    public ArrayList<Friends> getAllFriends() {
        ArrayList<Friends> friendsArrayList = new ArrayList<Friends>();
        SQLiteDatabase db = myHelper.getReadableDatabase();
        String[] columns = {myDbHelper.NAME, myDbHelper.NUMBER, myDbHelper.EMAIL};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, myDbHelper.NAME);
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String number = cursor.getString(cursor.getColumnIndex(myDbHelper.NUMBER));
            String email = cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
            Friends friend = new Friends(name, number, email);
            friendsArrayList.add(friend);
        }
        return friendsArrayList;
    }

    public void removeFriend(Friends f) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] fName = {f.getName()};
        db.delete(myHelper.TABLE_NAME, myHelper.NAME + " = ?", fName);
    }

    public void updateFriend(Friends f, String newName, String newNumber, String newEmail) {
        String newNameUpper = "" + Character.toUpperCase(newName.charAt(0)) + newName.substring(1);
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] fName = {f.getName()};
        ContentValues cv = new ContentValues();
        cv.put(myHelper.NAME, newNameUpper);
        cv.put(myHelper.NUMBER, newNumber);
        cv.put(myHelper.EMAIL, newEmail);
        db.update(myHelper.TABLE_NAME, cv, myHelper.NAME + " = ?", fName);
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "FriendZone";
        private static final String TABLE_NAME = "Friends";
        private static final int DATABASE_VERSION = 1;
        private static final String NAME = "Name";
        private static final String NUMBER = "Number";
        private static final String EMAIL = "Email";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                                                                    + NAME + " VARCHAR(255) , "
                                                                    + NUMBER + " VARCHAR(15), "
                                                                    + EMAIL + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Toast.makeText(context, "Error: " + e, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
            try {
                Toast.makeText(context, "Upgrade", Toast.LENGTH_SHORT).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Toast.makeText(context, "Error: " + e, Toast.LENGTH_SHORT).show();
            }
        }

    }

}
