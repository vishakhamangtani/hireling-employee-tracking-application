package com.example.hireling;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class AdminLoggedIn extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public View button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_admin);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        //actionBar.setTitle("Adding New Employee");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.admin_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        button=findViewById(R.id.nav_account1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminLoggedIn.this, "I'm going on a new pagee", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(something.this,Attendance.class);
//                startActivity(intent);
            }
        });

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            Toast.makeText(this, "Hello sir", Toast.LENGTH_SHORT).show();

        }
        switch (item.getItemId()) {
            case R.id.nav_account1:
                startActivity(new Intent(this, employee_profile.class));

                return(true);
        }

        return(super.onOptionsItemSelected(item));

    }
}
