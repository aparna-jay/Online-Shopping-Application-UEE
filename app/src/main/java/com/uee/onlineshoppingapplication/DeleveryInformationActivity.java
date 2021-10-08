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
import com.uee.onlineshoppingapplication.OnlineDB.delivaryinfo;

public class DeleveryInformationActivity extends AppCompatActivity {

    TextView nametext, tptext, addresstext;
    Button nextbtn;
    DatabaseReference databaseUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delevery_information);

        databaseUsers = FirebaseDatabase.getInstance().getReference("delivaryinfo");

        nametext = (TextView) findViewById(R.id.textboxname);
        addresstext = (TextView) findViewById(R.id.textboxaddress);
        tptext = (TextView) findViewById(R.id.textboxtp);
        nextbtn = (Button) findViewById(R.id.btnNext);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDeliveryInfo();
            }
        });

    }




    /*
     * This method is saving a new artist to the
     * Firebase Realtime Database
     * */
    private void addDeliveryInfo() {
        //getting the values to save
        String name = nametext.getText().toString().trim();
        String address = addresstext.getText().toString().trim();
        String tp = tptext.getText().toString().trim();
        //       String genre = spinnerGenre.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseUsers.push().getKey();

            //creating an Artist Objectd
            delivaryinfo info = new delivaryinfo(id,name,address,tp);

            databaseUsers.child(id).setValue(info);


            //setting edittext to blank again
            nametext.setText("");
            addresstext.setText("");
            tptext.setText("");


            //displaying a success toast
            Toast.makeText(this, "information added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}