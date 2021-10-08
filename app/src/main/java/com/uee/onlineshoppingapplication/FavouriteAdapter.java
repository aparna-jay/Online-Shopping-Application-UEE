package com.uee.onlineshoppingapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    Button favDelete;
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
        favDelete = (Button)  listViewItem.findViewById(R.id.delButtonstr);

        Favourites favr = favItems.get(position);
        Picasso.get().load(favr.getImage()).into(image);
        price.setText("Rs. " + String.valueOf(favr.getPrice())+ ".00");


        favDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Reference = FirebaseDatabase.getInstance().getReference();
//              Reference.removeValue();
                Reference.child("favourites").child(favr.getId()).removeValue();

                Toast.makeText(context.getApplicationContext(), "Successfully Deleted From Favourites!", Toast.LENGTH_SHORT).show();

            }
        });


        return listViewItem;
    }
}
