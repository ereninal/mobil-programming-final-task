package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UserUpdatePageActivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY =999;
    HelperDatabaseOperations db = new HelperDatabaseOperations(this);
    ModelUsers users=null;
    EditText fullname,username,email,password;
    Button update;
    ImageView userImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_page);
        fullname = (EditText)findViewById(R.id.u_userfullname);
        username = (EditText)findViewById(R.id.u_username);
        email = (EditText)findViewById(R.id.u_useremail);
        password = (EditText)findViewById(R.id.u_userpassword);
        update = (Button)findViewById(R.id.update);
        userImage =(ImageView) findViewById(R.id.userimageView);
        Bundle bundle = getIntent().getExtras();
        users = bundle.getParcelable("user");
        fullname.setText(users.getFullName());
        username.setText(users.getUsername());
        email.setText(users.getEmail());
        password.setText(users.getPassword());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!db.GetUserCheck(username.getText().toString())){
                    db.UserUpdate(users.getId(),fullname.getText().toString(),username.getText().toString(),email.getText().toString(),password.getText().toString(),db.ImageViewToByte(userImage));
                    Toast.makeText(getApplicationContext(), "Güncelleme Başarılı", Toast.LENGTH_SHORT).show();
                    //TODO: güncelleme ekranına tekrar bakılacak.
                }
                else{
                    Toast.makeText(getApplicationContext(), "Kullanıcı adı zaten var", Toast.LENGTH_SHORT).show();
                }


            }
        });
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(UserUpdatePageActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode==RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                userImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(getApplicationContext(),"Hata",Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
