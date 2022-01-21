package com.example.myapplication;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class SkinContract {

    private SkinContract(){}

    public static final String CONTENT_AUTHORITY="com.example.myapplication.skincart";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String PATH_SKIN = "skin-path";

    public static final String PATH_CART = "cart-path";

    public static final class SkinEntry implements BaseColumns{
        public static final Uri CONTENT_URI  = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SKIN);
        public static final Uri CONTENT_URI_CART = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CART);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +PATH_SKIN;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SKIN;

        public final static String TABLE_NAME = "skins";

        public final static String CART_TABLE = "cart";

        public final static String ID = BaseColumns._ID;
        public final static String CARTID = BaseColumns._ID;

        public final static String COLUMN_NAME = "skinname";
        public final static String COLUMN_DESCRIPTION = "description";
        public final static String COLUMN_EXTERIOR = "exterior";
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_IMAGE = "imageurl";

        public final static String COLUMN_CART_NAME = "cartskinname";
        public final static String COLUMN_CART_IMAGE = "cartimageurl";
        public final static String COLUMN_CART_QUANTITY = "cartquantity";
        public final static String COLUMN_CART_TOTAL_PRICE = "carttotalprice";

    }

}
