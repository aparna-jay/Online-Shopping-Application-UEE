package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;

public class SelectPaymentActivity extends AppCompatActivity {

    TextView title_selectp;
//    RadioButton radio_pirates,radio_ninjas;
    Button buttonnext;
    String totPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_payment);

        RadioButton radioYes = (RadioButton)findViewById(R.id.radio_pirates);
        RadioButton radioNo = (RadioButton)findViewById(R.id.radio_ninjas);

        title_selectp = (TextView) findViewById(R.id.title_selectp);

        Button btn = (Button) findViewById(R.id.buttonnext);

        title_selectp.setText(LanguageSetter.getresources().getString(R.string.title_selectp));
        btn.setText(LanguageSetter.getresources().getString(R.string.next_selectp));

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            totPrice = extras.getString("EXTRA_TOTAL");
            // and get whatever type user account id is
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent myIntent = new Intent(SelectPaymentActivity.this, PaymentInformationActivity.class);
//                startActivity(myIntent);

                if(radioYes.isChecked()){
                    Intent intent = new Intent(SelectPaymentActivity.this, PaymentInformationActivity.class);
                    intent.putExtra("EXTRA_TOTAL", totPrice);
                    startActivity(intent);
                }else if(radioNo.isChecked()){
                    Intent intent = new Intent(SelectPaymentActivity.this, DeleveryInformationActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Select the radio button",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}