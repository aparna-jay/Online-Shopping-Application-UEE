package com.uee.onlineshoppingapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.uee.onlineshoppingapplication.OnlineDB.Favourites;
import com.uee.onlineshoppingapplication.OnlineDB.ScrollHome;
import com.uee.onlineshoppingapplication.OnlineDB.currencySetter;

import java.util.List;

public class ScrollAdapter extends ArrayAdapter<ScrollHome>{

    private Activity context;
    List<ScrollHome> cartItems;
    DatabaseReference Reference;
    Button favBtn;
    TextView name, price, id;
    ImageView image;
    String user;
    float number;
    String currency_Type;

    DatabaseReference updateReference;

    public ScrollAdapter(Activity context, List<ScrollHome> cartItems,float number, String currency_Type) {
        super(context, R.layout.scroll_row, cartItems);
        this.context = context;
        this.cartItems = cartItems;
        this.number = number;
        this.currency_Type=currency_Type;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.scroll_row, null, true);

        Reference = FirebaseDatabase.getInstance().getReference().child("favourites");
        ImageView image = (ImageView) listViewItem.findViewById(R.id.image);
        TextView name = (TextView) listViewItem.findViewById(R.id.name);
        TextView price = (TextView) listViewItem.findViewById(R.id.price);
        favBtn = (Button) listViewItem.findViewById(R.id.imageButton);

        ScrollHome product = cartItems.get(position);
        Picasso.get().load(product.getImage()).into(image);
        name.setText(String.valueOf(product.getName()));
//        price.setText("Rs. " + String.valueOf(product.getPrice())+ ".00");

        double value123 = convertvalue(number,Integer.parseInt(product.getPrice()));
        Log.e("","aaa"+value123);
        price.setText(currency_Type+ "  " + String.valueOf(Math.round(value123)));
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //getting a unique id using push().getKey() method
                    String id = Reference.push().getKey();;

                    //creating an Object
                    Favourites fav = new Favourites(id, product.getPrice(), product.getImage(), user);

                    //Saving
                    Reference.child(id).setValue(fav);

            }
        });

        return listViewItem;
    }

    public float convertvalue(float val1 , float val2){
        return (val1 * val2);
    }



    private void addToFavourites() {


    }

}

