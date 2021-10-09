package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;
import com.uee.onlineshoppingapplication.OnlineDB.ShoppingCart;
import com.uee.onlineshoppingapplication.OnlineDB.User;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    ListView cartListView;
    String user;
    String userID;
    DatabaseReference dbref;
    List<ShoppingCart> carts;
    ProgressDialog loading;
    int totalPrice = 0;
    TextView totPrice,name_payment,exp_payment,cardtype_payment,totPriceTitle;
    Button checkout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        dbref = FirebaseDatabase.getInstance().getReference("cart");
        cartListView = (ListView) findViewById(R.id.cartListView);
        totPrice = (TextView) findViewById(R.id.totPrice);
        name_payment = (TextView) findViewById(R.id.name_payment);
        exp_payment = (TextView) findViewById(R.id.exp_payment);
        cardtype_payment = (TextView) findViewById(R.id.cardtype_payment);
        totPriceTitle = (TextView) findViewById(R.id.totPriceTitle);
        checkout = (Button) findViewById(R.id.checkout);

        cardtype_payment.setText(LanguageSetter.getresources().getString(R.string.quatity_shoppingcart));
        exp_payment.setText(LanguageSetter.getresources().getString(R.string.price_shoppigcart));
        name_payment.setText(LanguageSetter.getresources().getString(R.string.product_shoppingcart));
        totPriceTitle.setText(LanguageSetter.getresources().getString(R.string.subtotal_shoppingcart));
        checkout.setText(LanguageSetter.getresources().getString(R.string.checkoutbutton_shoppingcart));

        carts = new ArrayList<>();
        if (LoginActivity.loggedUser == null){
            userID = "temp";
        }
        else {
            userID = LoginActivity.loggedUser;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        loading = ProgressDialog.show(ShoppingCartActivity.this, "Fetching Data...", "Please wait...", true, true);
        //attaching value event listener
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                totalPrice = 0;
                //clearing the previous cart item list
                carts.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting cart item
                    ShoppingCart shoppingCart = postSnapshot.getValue(ShoppingCart.class);
                    //adding cart item to the list
                    if(shoppingCart.getUserId().equals(userID)) {
                        carts.add(shoppingCart);
                        totalPrice = totalPrice + Integer.parseInt(shoppingCart.getPricePerItem());
                    }
                }
                CartListAdapter cartListAdapter = new CartListAdapter(ShoppingCartActivity.this, carts);
                cartListView.setAdapter(cartListAdapter);
                loading.dismiss();
                totPrice.setText("Rs "+ String.valueOf(totalPrice)+ ".00");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

