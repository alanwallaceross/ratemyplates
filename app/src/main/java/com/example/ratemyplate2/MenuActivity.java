package com.example.ratemyplate2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //creates a view for the navigation menu
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //sets burger button on toolbar to open navigation menu
        ImageView clickBurger = (ImageView) findViewById(R.id.burgerButton);
        clickBurger.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    //closes the menu if the back button is tapped
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //sets the clickable options for each menu item
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_uploaded_plates) {
            Intent intent = new Intent(this, UploadedPlatesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_upload) {
            Intent intent = new Intent(this, UploadActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_my_matches) {
            Toast.makeText(this, "Under maintenance", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_favourites) {
            Toast.makeText(this, "Under maintenance", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_log_out) {
            Toast.makeText(this, "Under maintenance", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dashBoardClick(View view){
        Toast.makeText(this, "Under maintenance", Toast.LENGTH_SHORT).show();
    }


}
