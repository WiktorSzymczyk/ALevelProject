package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Cursor mCursor;
    private Context mContext;


    public void CartAdapter(Context mContext) {
        this.mContext = mContext;
    }



    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {

        int idIndex = mCursor.getColumnIndex(SkinContract.SkinEntry.CARTID);
        int skinName = mCursor.getColumnIndex(SkinContract.SkinEntry.COLUMN_CART_NAME);
        int image = mCursor.getColumnIndex(SkinContract.SkinEntry.COLUMN_CART_IMAGE);
        int quantity = mCursor.getColumnIndex(SkinContract.SkinEntry.COLUMN_CART_QUANTITY);
        int price = mCursor.getColumnIndex(SkinContract.SkinEntry.COLUMN_CART_TOTAL_PRICE);


        mCursor.moveToPosition(position); 

        final int id = mCursor.getInt(idIndex);
        String name = mCursor.getString(fragranceName);
        String skinImage = mCursor.getString(image);
        int skinQuantity = mCursor.getInt(quantity);
        Double skinPrice = mCursor.getDouble(price);

        DecimalFormat precision = new DecimalFormat("0.00");



        //Set values
        holder.itemView.setTag(id);
        holder.skinName.setText(name);
        holder.skinQuantity.setText("Quantity ordering: " + String.valueOf(skinQuantity));
        holder.skinPrice.setText("£" + precision.format(fragrancePrice));

        String poster = "http://boombox.ng/images/fragrance/" + skinImage;

        Glide.with(mContext)
                .load(poster)
                .placeholder(R.drawable.ic_load_foreground)
                .into(holder.image);

    }


    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public Cursor swapCursor(Cursor c) {
       
        if (mCursor == c) {
            return null; 
        }
        Cursor temp = mCursor;
        this.mCursor = c; 
        
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView skinName, skinQuantity, skinPrice;
        ImageView image;
        public CartViewHolder(View itemView) {
            super(itemView);

            skinName = (TextView) itemView.findViewById(R.id.skinName);
            skinQuantity = (TextView) itemView.findViewById(R.id.quantity);
            skinPrice = (TextView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.cartImage);
        }

    }
}
