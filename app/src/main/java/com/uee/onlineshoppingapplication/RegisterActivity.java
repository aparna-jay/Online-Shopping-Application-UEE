package com.uee.onlineshoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uee.onlineshoppingapplication.OnlineDB.LanguageSetter;
import com.uee.onlineshoppingapplication.OnlineDB.User;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    TextView username, email, address, phone, password;
    List<User> users;
    DatabaseReference databaseUsers;
    Button addUser;
    User user;
    String Text;
    TextView signUpTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        username = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        password = (TextView) findViewById(R.id.password);
        addUser = (Button) findViewById(R.id.addUser);
        signUpTitle = (TextView) findViewById(R.id.title_delivery);

        users = new ArrayList<>();

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUsers();
            }
        });

        signUpTitle.setText(LanguageSetter.getresources().getString(R.string.signup_titile_register));
        username.setHint(LanguageSetter.getresources().getString(R.string.username_register));
        email.setHint(LanguageSetter.getresources().getString(R.string.email_register));
        address.setHint(LanguageSetter.getresources().getString(R.string.address_register));
        phone.setHint(LanguageSetter.getresources().getString(R.string.phone_register));
        password.setHint(LanguageSetter.getresources().getString(R.string.password_register));
        addUser.setText(LanguageSetter.getresources().getString(R.string.register_button));
    }


    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                users.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    User user = postSnapshot.getValue(User.class);
                    //adding artist to the list
                    Log.e("UserList", " " + user.getUsername()+ user.getEmail()+ user.getAddress()
                            + user.getPhone()+ user.getPassword());
                    users.add(user);
                }
//                //creating adapter
//                UserList artistAdapter = new ArtistList(MainActivity.this, artists);
//                //attaching adapter to the listview
//                listViewArtists.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    /*
     * This method is saving a new artist to the
     * Firebase Realtime Database
     * */
    private void addUsers() {
        //getting the values to save
        String name = username.getText().toString().trim();
        String email1 = email.getText().toString().trim();
        String address1 = address.getText().toString().trim();
        String phone1 = phone.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        //       String genre = spinnerGenre.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = email1.split("@")[0];

            //creating an Artist Object
            User user = new User(id, name,email1,address1,phone1,password1);

            //Saving the Artist
            databaseUsers.child(id).setValue(user);

            //setting edittext to blank again
            username.setText("");
            email.setText("");
            address.setText("");
            phone.setText("");
            password.setText("");

            //displaying a success toast
            Toast.makeText(this, "User added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}