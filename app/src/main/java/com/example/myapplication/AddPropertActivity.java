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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddPropertActivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY =999;
    HelperDatabaseOperations db = new HelperDatabaseOperations(this);
    Date currentTime = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    String date = df.format(currentTime);
    ModelPropert propert;
    EditText title,roomCount,age,loc,heating,country,dist,address,fee;
    RadioButton gender;
    RadioGroup genderGroup;
    Button image,save;
    ImageView propertImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_propert);
        Bundle bundle = getIntent().getExtras();
        final ModelUsers user = bundle.getParcelable("user");
        title =(EditText)findViewById(R.id.title);
        roomCount =(EditText)findViewById(R.id.roomCount);
        age =(EditText)findViewById(R.id.buildingAge);
        loc=(EditText)findViewById(R.id.location);
        heating =(EditText)findViewById(R.id.heating);
        country =(EditText)findViewById(R.id.country);
        dist=(EditText)findViewById(R.id.district);
        address =(EditText)findViewById(R.id.address);
        fee =(EditText)findViewById(R.id.fee);
        genderGroup =(RadioGroup)findViewById(R.id.gender);
        int selectedId = genderGroup.getCheckedRadioButtonId();
        gender = (RadioButton)findViewById(selectedId);
        propertImage =(ImageView)findViewById(R.id.imageAppIcon);
        image =(Button)findViewById(R.id.uploadImage);
        save =(Button)findViewById(R.id.save);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(AddPropertActivity.this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
                //Log.d("upload","upload");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!title.getText().toString().equals(" ") &&
                    !title.getText().toString().equals( "") &&
                    !roomCount.getText().toString().equals(" ") &&
                    !age.getText().toString().equals(" ") &&
                    !loc.getText().toString().equals(" ") &&
                    !heating.getText().toString().equals(" ") &&
                    !country.getText().toString().equals(" ") &&
                    !dist.getText().toString().equals(" ") &&
                    !address.getText().toString().equals(" ") &&
                    !fee.getText().toString().equals(" ")){
                    propert = new ModelPropert();
                    propert.setTitle(title.toString());
                    propert.setRoomCount(roomCount.toString());
                    propert.setBuildingAge(age.toString());
                    propert.setFloorLocation(loc.toString());
                    propert.setCountry(country.toString());
                    propert.setDistrict(dist.toString());
                    propert.setType(gender.getText().toString());
                    propert.setAddress(address.toString());
                    propert.setImages(db.ImageViewToByte(propertImage));
                    propert.setFee(fee.toString());
                    propert.setHeating(heating.toString());
                    propert.setDate(currentTime.toString());
                    propert.setUserId(user.getId());
                    try{
                        db.InsertPropert(propert);
                        Toast.makeText(getApplicationContext(),"Mülk Eklendi",Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }



                }
                else{
                    //TODO: giriş yapamazsa yapıalcak olanalar
                }
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
                propertImage.setImageBitmap(bitmap);
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
