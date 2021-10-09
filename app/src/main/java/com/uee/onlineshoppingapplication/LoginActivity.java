package com.uee.onlineshoppingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;

public class LoginActivity extends AppCompatActivity {

    Button createAccount, login;
    EditText email, password;
    TextView tvSignUp, DontHave;
    DatabaseReference databaseUsers;
    public static String loggedUser;
    public String savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//Initialize.
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        DontHave = findViewById(R.id.phone_delivery);
        createAccount = findViewById(R.id.signUp);
        login = findViewById(R.id.login);

        DontHave.setText(LanguageSetter.getresources().getString(R.string.donthave_login));
        email.setHint(LanguageSetter.getresources().getString(R.string.email_login));
        password.setHint(LanguageSetter.getresources().getString(R.string.password_login));
        login.setText(LanguageSetter.getresources().getString(R.string.loginbutton_login));
        createAccount.setText(LanguageSetter.getresources().getString(R.string.signupbutton_login));

//        Intent intent = new Intent(LoginActivity.this, SplashScreenActivity.class);
//        startActivity(intent);

        //go to the create account acctivity.
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateAcount();
            }
        });

        //go to the create user profile acctivity.
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openlogin();
            }
            });
    }

    public void openCreateAcount() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void openlogin() {

        final String userEnteredUsername = email.getText().toString().trim().split("@")[0];
        final String userEnteredPassword = password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");

        Query checkUser = reference.orderByChild("id").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);


                    if (passwordFromDB.equals(userEnteredPassword)) {
                        Toast.makeText(getApplicationContext(), "valid user", Toast.LENGTH_SHORT).show();
                        loggedUser = email.getText().toString().split("@")[0];
                        Intent intent = new Intent(LoginActivity.this, ScrollActivity.class);
                        intent.putExtra("user",email.getText().toString().split("@")[0]);
                        startActivity(intent);
                        finish();


                    } else {
                        password.setError("Invalid Password");
//                        Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }else{
                    password.setError("Invalid Username");
//                    Toast.makeText(getApplicationContext(), "No such User exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}