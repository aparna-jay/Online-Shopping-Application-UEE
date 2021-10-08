package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;
import com.uee.onlineshoppingapplication.OnlineDB.paymentinfo;

public class PaymentInformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView nametext, expdatetext,CardNumber,CVVtext;
    Spinner cTypetext;
    Button btnPay;
    DatabaseReference databaseUsers;
    String  text;
    TextView name_payment,cardtype_payment,ccnumber_payment,exp_payment,cvv_payment,title_payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        databaseUsers = FirebaseDatabase.getInstance().getReference("paymentinfo");

        nametext = (TextView) findViewById(R.id.tbName);
        expdatetext = (TextView) findViewById(R.id.tbEXPDate);
//        cTypetext = (TextView) findViewById(R.id.tbCardType);
        CardNumber = (TextView) findViewById(R.id.tbccNumber);
        CVVtext = (TextView) findViewById(R.id.tbCVV);
        name_payment = (TextView) findViewById(R.id.name_payment);
        cardtype_payment = (TextView) findViewById(R.id.cardtype_payment);
        ccnumber_payment = (TextView) findViewById(R.id.ccnumber_payment);
        exp_payment = (TextView) findViewById(R.id.exp_payment);
        cvv_payment = (TextView) findViewById(R.id.cvv_payment);
        title_payment = (TextView) findViewById(R.id.title_payment);
        btnPay = (Button) findViewById(R.id.btnpay);

        name_payment.setText(LanguageSetter.getresources().getString(R.string.name_payment));
        cardtype_payment.setText(LanguageSetter.getresources().getString(R.string.cardtype_payment));
        ccnumber_payment.setText(LanguageSetter.getresources().getString(R.string.ccnumber_payment));
        exp_payment.setText(LanguageSetter.getresources().getString(R.string.exp_payment));
        cvv_payment.setText(LanguageSetter.getresources().getString(R.string.cvv_payment));
        title_payment.setText(LanguageSetter.getresources().getString(R.string.title_payment));
        btnPay.setText(LanguageSetter.getresources().getString(R.string.paybutton_payment));

        Spinner spinner = findViewById(R.id.tbCardType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.card_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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
        String ctype = text.trim();
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
            CardNumber.setText("");
            CVVtext.setText("");

            //displaying a success toast
            Toast.makeText(this, "payment information added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}