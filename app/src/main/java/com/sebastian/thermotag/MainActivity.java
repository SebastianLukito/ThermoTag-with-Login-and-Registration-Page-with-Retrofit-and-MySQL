package com.sebastian.thermotag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button belumRegis;
    private Button enterButton;
    private DatabaseReference database;

    private TextView textViewTitle10;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        textViewTitle10 = findViewById(R.id.textViewTitle10);
        textViewTitle10.setSelected(true);
        textViewTitle10.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textViewTitle10.setSingleLine(true);
        textViewTitle10.setMarqueeRepeatLimit(-1);
        enterButton=findViewById(R.id.enterButton);
        belumRegis = findViewById(R.id.belumRegis);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        enterButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String usernameValue = username.getText().toString();
               String passwordValue = password.getText().toString();

               database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thermotag-f5e05-default-rtdb.firebaseio.com/");

                if (usernameValue.isEmpty() || passwordValue.isEmpty()) {
                     Toast.makeText(MainActivity.this, "Ada data yang masih belum diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    database = FirebaseDatabase.getInstance().getReference("Users");
                     database.addListenerForSingleValueEvent(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.child(usernameValue).exists()) {
                                    if (snapshot.child(usernameValue).child("password").getValue(String.class).equals(passwordValue)) {
                                        Intent login = new Intent(MainActivity.this, menuKalkulator.class);
                                        startActivity(login);
                                    } else {
                                        Toast.makeText(MainActivity.this, "Password Salah", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Username Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                                }
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(MainActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                         }
                     });



                }
           }
       });

        belumRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(regis);

            }
        });
    }
}
