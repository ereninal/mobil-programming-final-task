package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    HelperDatabaseOperations db = new HelperDatabaseOperations(this);
    SessionMenager session;
    Button login;
    EditText txtMail,txtpassword;
    TextView twNewuser,txNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionMenager(getApplicationContext());

        setContentView(R.layout.activity_main);
        if (session.IsLoggedIn() == true){
            HashMap us = session.GetUserDetails();
            for ( ModelUsers user:db.GetUser(us.get(SessionMenager.KEY_USERNAME).toString()))
            {
                Log.d("s",us.get(SessionMenager.KEY_USERNAME).toString());
                ModelUsers u = new ModelUsers();
                u.setId(user.getId());
                u.setFullName(user.getFullName());
                u.setUsername(user.getUsername());
                u.setEmail(user.getEmail());
                u.setPassword(user.getPassword());
                u.setImage(user.getImage());
                Intent intent =new Intent(getApplicationContext(),UserPageHomeActivity.class);
                intent.putExtra("user",u);
                startActivity(intent);
            }
        }
        login = (Button)findViewById(R.id.login);
        txtMail = (EditText)findViewById(R.id.useremail);
        txtpassword = (EditText)findViewById(R.id.userpassword);
        twNewuser =(TextView)findViewById(R.id.newuser);
        txNewPassword =(TextView)findViewById(R.id.newpassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.GetUser(txtMail.getText().toString())!=null){
                    for ( ModelUsers user:db.GetUser(txtMail.getText().toString()))
                    {
                        if(txtpassword.getText().toString().equals(user.getPassword())){
                            ModelUsers u = new ModelUsers();
                            u.setId(user.getId());
                            u.setFullName(user.getFullName());
                            u.setUsername(user.getUsername());
                            u.setEmail(user.getEmail());
                            u.setPassword(user.getPassword());
                            u.setImage(user.getImage());
                            Toast.makeText(getApplicationContext(),"Kulanıcı Sayfasına Yönlendiriliyorsunuz..",Toast.LENGTH_LONG).show();
                            session.CreateLoginSession(user.getUsername(), user.getPassword());
                            Intent intent =new Intent(getApplicationContext(),UserPageHomeActivity.class);
                            intent.putExtra("user",u);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Şifre yanlış!",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Böyle bir kullanıcı yok",Toast.LENGTH_LONG).show();
                }


            }
        });
        txNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),UserNewPasswordActivity.class);
                startActivity(intent);
            }
        });
        twNewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),UseraddPageActivity.class);
                startActivity(intent);
            }
        });


    }
}
