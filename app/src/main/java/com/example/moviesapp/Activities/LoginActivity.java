package com.example.moviesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviesapp.R;

public class LoginActivity extends AppCompatActivity {
    private EditText userEdit, passEdit;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PrivateAnhxa();
    }
    private void PrivateAnhxa()
    {
        userEdit=findViewById(R.id.editTextUserName);
        passEdit=findViewById(R.id.editTextPassword);
        loginBtn=findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Pleas fill to user and password", Toast.LENGTH_SHORT).show();
                }else if(userEdit.getText().toString().equals("Test") || passEdit.getText().toString().equals("Test"))
                {
                    startActivity(new Intent(LoginActivity.this, MainActivity2.class));
                }else {
                    Toast.makeText(LoginActivity.this, "Your user and password is not connect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}