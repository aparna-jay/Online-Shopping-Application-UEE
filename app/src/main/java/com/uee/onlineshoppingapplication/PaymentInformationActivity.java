package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uee.onlineshoppingapplication.OnlineDB.paymentinfo;

public class PaymentInformationActivity extends AppCompatActivity {

    TextView nametext, expdatetext, cTypetext,CardNumber,CVVtext;
    Button btnPay;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        databaseUsers = FirebaseDatabase.getInstance().getReference("paymentinfo");

        nametext = (TextView) findViewById(R.id.tbName);
        expdatetext = (TextView) findViewById(R.id.tbEXPDate);
        cTypetext = (TextView) findViewById(R.id.tbCardType);
        CardNumber = (TextView) findViewById(R.id.tbccNumber);
        CVVtext = (TextView) findViewById(R.id.tbCVV);
        btnPay = (Button) findViewById(R.id.btnpay);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPaymentInfo();
            }
        });

    }


    /*
     * This method is saving a new artist to the
     * Firebase Realtime Database
     * */
    private void addPaymentInfo() {
        //getting the values to save
        String name = nametext.getText().toString().trim();
        String expdate = expdatetext.getText().toString().trim();
        String ctype = cTypetext.getText().toString().trim();
        String cnumber = CardNumber.getText().toString().trim();
        String cvv = CVVtext.getText().toString().trim();



        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseUsers.push().getKey();

            //creating an Artist Objectd
            paymentinfo info = new paymentinfo(id,name,expdate,ctype,cnumber,cvv);

            databaseUsers.child(id).setValue(info);


            //setting edittext to blank again
            nametext.setText("");
            expdatetext.setText("");
            cTypetext.setText("");


            //displaying a success toast
            Toast.makeText(this, "payment information added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }




}