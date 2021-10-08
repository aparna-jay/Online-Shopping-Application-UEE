package com.uee.onlineshoppingapplication;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.uee.onlineshoppingapplication.OnlineDB.Favourites;
import com.uee.onlineshoppingapplication.OnlineDB.ScrollHome;

import java.util.List;

public class FavouriteAdapter extends ArrayAdapter<Favourites> {

    private Activity context;
    List<Favourites> favItems;
    DatabaseReference Reference;
    Button favBtn;
    String user;

    public FavouriteAdapter(Activity context, List<Favourites> favItems) {
        super(context, R.layout.favourite_row, favItems);
        this.context = context;
        this.favItems = favItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.favourite_row, null, true);

        ImageView image = (ImageView) listViewItem.findViewById(R.id.imageV);
        TextView price = (TextView) listViewItem.findViewById(R.id.price1);
        favBtn = (Button) listViewItem.findViewById(R.id.imageButton);

        Favourites favr = favItems.get(position);
        Picasso.get().load(favr.getImage()).into(image);
        price.setText(String.valueOf(favr.getPrice()));
//        price.setText("Rs. " + String.valueOf(product.getPrice())+ ".00");


//        favBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //getting a unique id using push().getKey() method
//                String id = Reference.push().getKey();;
//
//                //creating an Object
//                Favourites fav = new Favourites(id, product.getPrice(), product.getImage(), user);
//
//                //Saving
//                Reference.child(id).setValue(fav);
//
//            }
//        });

        return listViewItem;
    }
}
