package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class UserPageActivity extends AppCompatActivity {

    HelperDatabaseOperations db = new HelperDatabaseOperations(this);
    GridView view;
    PropertListAdapter adapter = null;
    /*Bundle bundle = getIntent().getExtras();
    ModelUsers users = bundle.getParcelable("user");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        view = (GridView)findViewById(R.id.data);

        //adapter = new PropertListAdapter(this,R.layout.propert_item, (ArrayList<ModelPropert>) db.GetAllPropertUserId(1));
        //view.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        for (  Object p:db.GetAllPropertUserId(1)){
            Log.d("s","s");
        }

    }
}
