package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    HelperDatabaseOperations db = new HelperDatabaseOperations(this);
    Button login;
    EditText txtMail,txtpassword;
    TextView twNewuser,txNewPassword,test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        txtMail = (EditText)findViewById(R.id.useremail);
        txtpassword = (EditText)findViewById(R.id.userpassword);
        test = (TextView)findViewById(R.id.test);
        final String pass =txtpassword.getText().toString();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.GetUser(txtMail.getText().toString())!=null){
                    for ( ModelUsers user:db.GetUser(txtMail.getText().toString()))
                    {
                        if(txtpassword.getText().toString().equals(user.getPassword())){
                            Toast.makeText(getApplicationContext(),"Giriş Başarılı",Toast.LENGTH_LONG).show();
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


    }
}
