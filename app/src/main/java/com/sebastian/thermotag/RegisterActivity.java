package com.sebastian.thermotag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, email, password;
    private Button registerButton;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://thermotag-f5e05-default-rtdb.firebaseio.com/");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();

                if (usernameValue.isEmpty() || emailValue.isEmpty() || passwordValue.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Ada data yang masih belum diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    database = FirebaseDatabase.getInstance().getReference("Users");
                    database.child(usernameValue).child("username").setValue(usernameValue);
                    database.child(usernameValue).child("email").setValue(emailValue);
                    database.child(usernameValue).child("password").setValue(passwordValue);
                    Toast.makeText(RegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();

                    Intent register = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(register);

                }
            }
        });
    }
}
