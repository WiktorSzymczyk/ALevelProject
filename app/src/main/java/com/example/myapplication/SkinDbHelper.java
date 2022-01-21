package com.example.myapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SkinDbHelper extends SQLiteOpenHelper {
    private static final String TAG = SkinDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "skins.db";
    private static final int DATABASE_VERSION = 1;

    Context context;
    SQLiteDatabase db;
    ContentResolver mContentResolver;

    private Resources mResources;

    public SkinDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mResources = context.getResources();
        mContentResolver = context.getContentResolver();

        db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_SKIN_TABLE = "CREATE TABLE " + SkinContract.SkinEntry.TABLE_NAME + " (" +
        SkinContract.SkinEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
        SkinContract.SkinEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, "+
        SkinContract.SkinEntry.COLUMN_DESCRIPTION+ " TEXT NOT NULL, "+
        SkinContract.SkinEntry.COLUMN_EXTERIOR+ " TEXT NOT NULL, "+
        SkinContract.SkinEntry.COLUMN_IMAGE+" TEXT NOT NULL, "+
        SkinContract.SkinEntry.COLUMN_PRICE+" REAL NOT NULL "+ ");";

        final String SQL_CREATE_CART_SKIN_TABLE = "CREATE TABLE " + SkinContract.SkinEntry.CART_TABLE + " (" +
                SkinContract.SkinEntry.CARTID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SkinContract.SkinEntry.COLUMN_CART_NAME + " TEXT UNIQUE NOT NULL, "+
                SkinContract.SkinEntry.COLUMN_CART_IMAGE+ " TEXT NOT NULL, "+
                SkinContract.SkinEntry.COLUMN_CART_QUANTITY+ " INTEGER NOT NULL, "+
                SkinContract.SkinEntry.COLUMN_CART_TOTAL_PRICE+" REAL NOT NULL " + ");";

        db.execSQL(SQL_CREATE_SKIN_TABLE);
        db.execSQL(SQL_CREATE_CART_SKIN_TABLE);
        Log.d(TAG, "Database Created Successfully");

        try{
            readSkinsFromResources(db);
        }catch (IOException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+SkinContract.SkinEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS "+SkinContract.SkinEntry.CART_TABLE);
            onCreate(db);
    }

    private void readSkinsFromResources(SQLiteDatabase db) throws IOException, JSONException{
        StringBuilder builder = new StringBuilder();
        InputStream in = mResources.openRawResource(R.raw.skins);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while((line = reader.readLine()) != null){
            builder.append(line + "\n");
        }

        final String rawJson = builder.toString();
        final String BGS_SKINS = "skins";
        final String BGS_SKINNAME = "skinName";
        final String BGS_EXTERIOR = "exterior";
        final String BGS_DESCRIPTION = "description";
        final String BGS_IMAGEURL = "imageUrl";
        final String BGS_PRICE = "price";

        try{
            JSONObject skinJson = new JSONObject(rawJson);
            JSONArray skinArray = skinJson.getJSONArray(BGS_SKINS);

            for(int i = 0; i < skinArray.length(); i++){
                String skinName;
                String description;
                String imageUrl;
                String price;

                JSONObject skinDetails = skinArray.getJSONObject(i);
                skinName = skinDetails.getString(BGS_SKINNAME);
                description = skinDetails.getString(BGS_DESCRIPTION);
                imageUrl = skinDetails.getString(BGS_IMAGEURL);
                price = skinDetails.getString(BGS_PRICE);

                ContentValues skinValues = new ContentValues();

                skinValues.put(SkinContract.SkinEntry.COLUMN_NAME, skinName);
                skinValues.put(SkinContract.SkinEntry.COLUMN_DESCRIPTION, description);
                skinValues.put(SkinContract.SkinEntry.COLUMN_IMAGE, imageUrl);
                skinValues.put(SkinContract.SkinEntry.COLUMN_PRICE, price);

                db.insert(SkinContract.SkinEntry.TABLE_NAME, null, skinValues);

                Log.d(TAG, "Inserted Successfully "+skinValues);
            }

            Log.d(TAG, "Inserted Successfully "+skinArray.length());

        }catch (JSONException e){
            Log.e(TAG, e.getMessage(),e);
            e.printStackTrace();
        }

    }



}
