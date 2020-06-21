package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ModelUsers user = null;
    Button login;
    EditText txtMail,txtpassword;
    TextView twNewuser,txNewPassword,test;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        txtMail = (EditText)findViewById(R.id.useremail);
        txtpassword = (EditText)findViewById(R.id.userpassword);
        test = (TextView)findViewById(R.id.test);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Dataabse classes wil bee add


            }



        });


    }
}
