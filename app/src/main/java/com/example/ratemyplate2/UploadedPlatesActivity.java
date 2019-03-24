package com.example.ratemyplate2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class UploadedPlatesActivity extends MenuActivity {

    private static final String TAG = "UploadedPlatesList";

    static private RecyclerView recyclerView;
    static private RecyclerViewAdapter adapter;
    static private ArrayList<Plate> mPlates;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_myplates, contentFrameLayout);

        mPlates = PlateCollection.get(getApplication()).getPlates();

        Log.d(TAG, "onCreate: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mPlates, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

//    @Override
//    public void onPause(){
//        super.onPause();
//        PlateCollection.get(getApplication()).savePlates();
//    }

//    @Override
//    public void onBackPressed(){
//
//    }

}
