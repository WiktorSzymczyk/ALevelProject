package com.example.myapplication;

import android.database.Cursor;

public class Skin {

    public int id;

    public String name;
    public String description;
    public String exterior;
    public String imageUrl;
    public String price;

    public Skin(Cursor cursor){
        this.name = cursor.getString(cursor.getColumnIndexOrThrow(SkinContract.SkinEntry.COLUMN_NAME));
        this.description = cursor.getString(cursor.getColumnIndexOrThrow(SkinContract.SkinEntry.COLUMN_DESCRIPTION));
        this.exterior = cursor.getString(cursor.getColumnIndexOrThrow(SkinContract.SkinEntry.COLUMN_EXTERIOR));
        this.imageUrl = cursor.getString(cursor.getColumnIndexOrThrow(SkinContract.SkinEntry.COLUMN_IMAGE));
        this.price = cursor.getString(cursor.getColumnIndexOrThrow(SkinContract.SkinEntry.COLUMN_PRICE));
    }

}
