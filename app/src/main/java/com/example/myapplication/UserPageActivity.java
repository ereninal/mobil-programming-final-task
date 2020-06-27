package com.example.myapplication;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class UserPageActivity extends AppCompatActivity {

    HelperDatabaseOperations db = new HelperDatabaseOperations(this);
    GridView view;
    PropertListAdapter adapter = null;
    ModelUsers users=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        view = (GridView)findViewById(R.id.data);
        Bundle bundle = getIntent().getExtras();
        users = bundle.getParcelable("user");
        /*adapter = new PropertListAdapter(this,R.layout.propert_item, (ArrayList<ModelPropert>) db.GetAllPropertUserId(users.getId()));
        view.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/





    }
}
