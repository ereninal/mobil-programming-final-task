package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UserUpdatePageActivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY =999;
    HelperDatabaseOperations db = new HelperDatabaseOperations(this);
    ModelUsers user=null;
    EditText fullname,username,email,password;
    Button update;
    ImageView userImage;
    String old_username;
    Spinner spinner;
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
        user = bundle.getParcelable("user");
        fullname.setText(user.getFullName());
        username.setText(user.getUsername());
        old_username=user.getUsername();
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        byte[] imageArray = user.getImage();
        if(imageArray != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageArray,0,imageArray.length);
            userImage.setImageBitmap(bitmap);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        spinner = (Spinner)findViewById(R.id.spinner);
        setSupportActionBar(toolbar);
        setTitle("Toolbar Example");
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(UserUpdatePageActivity.this,
                android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.toolbar));
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Intent home =new Intent(getApplicationContext(),UserPageHomeActivity.class);
                        home.putExtra("user",user);
                        startActivity(home);
                        break;
                    case 2:
                        Intent addprop =new Intent(getApplicationContext(),AddPropertActivity.class);
                        addprop.putExtra("user",user);
                        startActivity(addprop);
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),"Güncellenecek",Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Intent userupdate =new Intent(getApplicationContext(),UserUpdatePageActivity.class);
                        userupdate.putExtra("user",user);
                        startActivity(userupdate);
                        break;
                    case 5:
                        SessionMenager session = new SessionMenager(getApplicationContext());
                        session.LogoutUser();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals(old_username)){

                    db.UserUpdate(user.getId(),fullname.getText().toString(),username.getText().toString(),email.getText().toString(),password.getText().toString(),db.ImageViewToByte(userImage));
                    Toast.makeText(getApplicationContext(), "Güncelleme Başarılı", Toast.LENGTH_SHORT).show();
                }
                else if(!db.GetUserCheck(username.getText().toString())){
                    db.UserUpdate(user.getId(),fullname.getText().toString(),username.getText().toString(),email.getText().toString(),password.getText().toString(),db.ImageViewToByte(userImage));
                    Toast.makeText(getApplicationContext(), "Güncelleme Başarılı", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Kullanıcı adı zaten var", Toast.LENGTH_SHORT).show();
                    Log.d("d",old_username);

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
