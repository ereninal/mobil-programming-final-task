package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UseraddPageActivity extends AppCompatActivity {
    HelperDatabaseOperations db = new HelperDatabaseOperations(this);
    ModelUsers users=null;
    EditText fullname,username,email,password;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useradd_page);
        fullname = (EditText)findViewById(R.id.a_userfullname);
        username = (EditText)findViewById(R.id.a_username);
        email = (EditText)findViewById(R.id.a_useremail);
        password = (EditText)findViewById(R.id.a_userpassword);
        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fullname.getText().toString().equals("") &&
                        !username.getText().toString().equals("") &&
                        !email.getText().toString().equals("") &&
                        !password.getText().toString().equals(""))
                {
                    if(!db.GetUserCheck(username.getText().toString())){
                        users = new ModelUsers();
                        users.setUsername(username.getText().toString());
                        users.setFullName(fullname.getText().toString());
                        users.setEmail(email.getText().toString());
                        users.setPassword(password.getText().toString());
                        db.InsertUser(users);
                        Toast.makeText(getApplicationContext(),"Kulanıcı Sayfasına Yönlendiriliyorsunuz..",Toast.LENGTH_LONG).show();
                        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("user",users);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Kullanıcı adı zaten var.",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Boş alan bırakamazsınız.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
