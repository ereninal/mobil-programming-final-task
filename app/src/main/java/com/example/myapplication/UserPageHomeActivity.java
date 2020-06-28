package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class UserPageHomeActivity extends AppCompatActivity {

    HelperDatabaseOperations db = new HelperDatabaseOperations(this);
    ListView view;
    PropertListAdapter adapter = null;
    ModelUsers users=null;
    ArrayList<ModelPropert> properts = new ArrayList<>();
    ModelPropert modelPropert=null;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page_home);
        Bundle bundle = getIntent().getExtras();
        users = bundle.getParcelable("user");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        spinner = (Spinner)findViewById(R.id.spinner);
        setSupportActionBar(toolbar);
        setTitle("Toolbar Example");
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(UserPageHomeActivity.this,
                android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.toolbar));
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Intent home =new Intent(getApplicationContext(),UserPageHomeActivity.class);
                        home.putExtra("user",users);
                        startActivity(home);
                        break;
                    case 2:
                        Intent addprop =new Intent(getApplicationContext(),AddPropertActivity.class);
                        addprop.putExtra("user",users);
                        startActivity(addprop);
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),"Güncellenecek",Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Intent userupdate =new Intent(getApplicationContext(),UserUpdatePageActivity.class);
                        userupdate.putExtra("user",users);
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









        /*view = (ListView) findViewById(R.id.data);
        Bundle bundle = getIntent().getExtras();
        users = bundle.getParcelable("user");
        for(ModelPropert p:db.GetAllPropertUserId(users.getId())){
            modelPropert = new ModelPropert();
            modelPropert.setId(p.getId());
            modelPropert.setTitle(p.getTitle());
            modelPropert.setDate(p.getDate());
            modelPropert.setType(p.getType());
            modelPropert.setRoomCount(p.getRoomCount());
            modelPropert.setBuildingAge(p.getBuildingAge());
            modelPropert.setFloorLocation(p.getFloorLocation());
            modelPropert.setHeating(p.getHeating());
            modelPropert.setCountry(p.getCountry());
            modelPropert.setDistrict(p.getDistrict());
            modelPropert.setAddress(p.getAddress());
            modelPropert.setFee(p.getFee());
            modelPropert.setImages(p.getImages());
            modelPropert.setUserId(p.getUserId());
            properts.add(modelPropert);
        }
        adapter = new PropertListAdapter(this,properts);
        view.setAdapter(adapter);*///TODO: Ekran donma hatasına vakit kalırsa bakılacak.
    }


}
