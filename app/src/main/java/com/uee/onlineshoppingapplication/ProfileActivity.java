package com.uee.onlineshoppingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;
import com.uee.onlineshoppingapplication.OnlineDB.User;

public class ProfileActivity extends AppCompatActivity {

    String user;
    private FirebaseUser fUser;
    private DatabaseReference reference;
    TextView name1,email1,address1,phone1,password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name1 = (TextView) findViewById(R.id.name1);
        email1 = (TextView) findViewById(R.id.email1);
        address1 = (TextView) findViewById(R.id.address1);
        phone1 = (TextView) findViewById(R.id.cvv_payment);
        password1 = (TextView) findViewById(R.id.password1);

//        ImageButton back = (ImageButton) findViewById(R.id.back);
        Button logout = (Button) findViewById(R.id.logout);
        if (LoginActivity.loggedUser == null){
            user = "null";
        }
        else {
            user = LoginActivity.loggedUser;
        }
        Log.e("Logged User", user);


//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });

        name1.setText(LanguageSetter.getresources().getString(R.string.name_profile));
        email1.setText(LanguageSetter.getresources().getString(R.string.email_profile));
        address1.setText(LanguageSetter.getresources().getString(R.string.address_profile));
        phone1.setText(LanguageSetter.getresources().getString(R.string.phone_profile));
        password1.setText(LanguageSetter.getresources().getString(R.string.password_profile));
        logout.setText(LanguageSetter.getresources().getString(R.string.logout_profile));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
//        user = fUser.getUid();

        final TextView userNameT = (TextView) findViewById(R.id.username);
        final TextView emailT = (TextView) findViewById(R.id.email);
        final TextView addressT = (TextView) findViewById(R.id.address);
        final TextView phoneT = (TextView) findViewById(R.id.phone);
        final TextView userPasswordT = (TextView) findViewById(R.id.password);

        reference.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile !=null){
                    String name = userProfile.username;
                    String email = userProfile.email;
                    String address = userProfile.address;
                    String phone = userProfile.phone;
                    String password = userProfile.password;

                    userNameT.setText(name);
                    emailT.setText(email);
                    addressT.setText(address);
                    phoneT.setText(phone);
                    userPasswordT.setText(password);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}