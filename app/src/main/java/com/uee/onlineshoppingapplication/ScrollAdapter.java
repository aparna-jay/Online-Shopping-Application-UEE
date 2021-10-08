package com.uee.onlineshoppingapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.uee.onlineshoppingapplication.OnlineDB.ScrollHome;

import java.util.List;

public class ScrollAdapter extends ArrayAdapter<ScrollHome>{

    private Activity context;
    List<ScrollHome> cartItems;
    int number1;
    DatabaseReference updateReference;

    public ScrollAdapter(Activity context, List<ScrollHome> cartItems , int num) {
        super(context, R.layout.scroll_row, cartItems);
        this.context = context;
        this.cartItems = cartItems;
        this.number1 = num;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.scroll_row, null, true);

        ImageView image = (ImageView) listViewItem.findViewById(R.id.image);
        TextView name = (TextView) listViewItem.findViewById(R.id.name);
        TextView price = (TextView) listViewItem.findViewById(R.id.price);

        ScrollHome product = cartItems.get(position);
        Picasso.get().load(product.getImage()).into(image);
        name.setText(String.valueOf(product.getName()));
//        price.setText("Rs. " + String.valueOf(product.getPrice())+ ".00");
        price.setText("Rs. " + String.valueOf(convertvalue(number1,Integer.parseInt(product.getPrice())))+ ".00");


        return listViewItem;
    }

    public int convertvalue(int val1 , int val2){
        return val1 * val2;
    }

}

