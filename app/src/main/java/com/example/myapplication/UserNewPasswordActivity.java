package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserNewPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final HelperDatabaseOperations db = new HelperDatabaseOperations(this);
        final EditText username, password, passwordrepeat;
        Button check;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_new_password);
        username = (EditText)findViewById(R.id.p_useremail);
        password = (EditText)findViewById(R.id.p_userpassword);
        passwordrepeat = (EditText)findViewById(R.id.p_userpasswordrepeat);
        check = (Button)findViewById(R.id.p_newpassword);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().equals("") && !password.getText().toString().equals("") && !passwordrepeat.getText().toString().equals("")){
                    if(password.getText().toString().equals(passwordrepeat.getText().toString())){
                        for ( ModelUsers user:db.GetUser(username.getText().toString()))
                        {
                            if(user !=null){
                                db.UserPasswordUptade(username.getText().toString(),password.getText().toString());
                                Toast.makeText(getApplicationContext(),"Güncelleme Başarılı.Kulanıcı Sayfasına Yönlendiriliyorsunuz..",Toast.LENGTH_LONG).show();
                                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Böyle bir kullanıcı kaydı bulunamadı.",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Şifreler uyuşmuyor.",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Boş yer bıraktınız!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
