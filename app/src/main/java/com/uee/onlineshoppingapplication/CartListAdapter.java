package com.uee.onlineshoppingapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.uee.onlineshoppingapplication.OnlineDB.ShoppingCart;

import java.util.List;

public class CartListAdapter extends ArrayAdapter<ShoppingCart>{

    private Activity context;
    List<ShoppingCart> cartItems;

    public CartListAdapter(Activity context, List<ShoppingCart> cartItems) {
        super(context, R.layout.cart_row, cartItems);
        this.context = context;
        this.cartItems = cartItems;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.cart_row, null, true);

        ImageView image = (ImageView) listViewItem.findViewById(R.id.image);
        TextView price = (TextView) listViewItem.findViewById(R.id.price);
        TextView quantity = (TextView) listViewItem.findViewById(R.id.quantity);

        ShoppingCart cart = cartItems.get(position);
        Picasso.get().load(cart.getImage()).into(image);
        price.setText(String.valueOf(cart.getUnitPrice()));
        quantity.setText(String.valueOf(cart.getQuantity()));

        return listViewItem;
    }
}

