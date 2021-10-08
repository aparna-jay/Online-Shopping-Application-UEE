package com.uee.onlineshoppingapplication;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    DatabaseReference updateReference;

    public ScrollAdapter(Activity context, List<ScrollHome> cartItems) {
        super(context, R.layout.scroll_row, cartItems);
        this.context = context;
        this.cartItems = cartItems;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.scroll_row, null, true);

        ImageView image = (ImageView) listViewItem.findViewById(R.id.image);
        TextView name = (TextView) listViewItem.findViewById(R.id.name);
        TextView price = (TextView) listViewItem.findViewById(R.id.price);
        Button view  =(Button) listViewItem.findViewById(R.id.view);

        ScrollHome product = cartItems.get(position);
        Picasso.get().load(product.getImage()).into(image);
        name.setText(String.valueOf(product.getName()));
        price.setText("Rs. " + String.valueOf(product.getPrice())+ ".00");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(context, ProductProfileActivity.class);
             intent.putExtra("EXTRA_NAME", String.valueOf(product.getName()));
             intent.putExtra("EXTRA_PRICE", String.valueOf(product.getPrice()));
             intent.putExtra("EXTRA_IMAGE", String.valueOf(product.getImage()));
             intent.putExtra("EXTRA_DESCRIPTION", String.valueOf(product.getDescription()));
             context.startActivity(intent);
            }
        });


        return listViewItem;
    }

}

