package com.uee.onlineshoppingapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.uee.onlineshoppingapplication.OnlineDB.ShoppingCart;

import java.util.List;

public class CartListAdapter extends ArrayAdapter<ShoppingCart>{

    private Activity context;
    List<ShoppingCart> cartItems;
    int totalQuantity;
    ImageButton plus, minus,delete;
    DatabaseReference updateReference, deleteReference;

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
        plus = (ImageButton) listViewItem.findViewById(R.id.plus);
        minus = (ImageButton) listViewItem.findViewById(R.id.minus);
        delete = (ImageButton) listViewItem.findViewById(R.id.remove);

        ShoppingCart cart = cartItems.get(position);
        Picasso.get().load(cart.getImage()).into(image);
        price.setText("Rs. " + String.valueOf(cart.getPricePerItem()) + ".00");
        quantity.setText(String.valueOf(cart.getQuantity()));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQuantity = Integer.parseInt(String.valueOf(cart.getQuantity()));
                totalQuantity = totalQuantity + 1;
                int totPrice = getPricePerItem(totalQuantity, Integer.parseInt(cart.getUnitPrice()));
                updateReference = FirebaseDatabase.getInstance().getReference();
                updateReference.child("cart").child(cart.getId()).child("quantity").setValue(String.valueOf(totalQuantity));
                updateReference.child("cart").child(cart.getId()).child("pricePerItem").setValue(String.valueOf(totPrice));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQuantity = Integer.parseInt(String.valueOf(cart.getQuantity()));
                totalQuantity = totalQuantity - 1;
                int totPrice = getPricePerItem(totalQuantity, Integer.parseInt(cart.getUnitPrice()));
                updateReference = FirebaseDatabase.getInstance().getReference();
                updateReference.child("cart").child(cart.getId()).child("quantity").setValue(String.valueOf(totalQuantity));
                updateReference.child("cart").child(cart.getId()).child("pricePerItem").setValue(String.valueOf(totPrice));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.app_name);
                builder.setMessage("Do you want to delete this item from cart ?");
                builder.setIcon(R.drawable.lehesi);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        deleteReference = FirebaseDatabase.getInstance().getReference();
                        deleteReference.child("cart").child(cart.getId()).removeValue();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
        return listViewItem;
    }

    public int getPricePerItem(int totalQuantity, int unitPrice){
        return totalQuantity * unitPrice;
    }
}

