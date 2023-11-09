package com.example.hireling;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class employee_profile extends AppCompatActivity {
    private DBHelperFinal4 dbhelper;
    private LinearLayout linear2;
    private TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);
        Intent i=getIntent();
        linear2=findViewById(R.id.linear2);
        text1=findViewById(R.id.getText1);
        int userId=i.getIntExtra("userId",0);
        String fname=i.getStringExtra("fname");
        Log.d("{TAG}", "employeeprofile: "+fname);
        dbhelper=new DBHelperFinal4(employee_profile.this);
       Cursor c= dbhelper.getEmployee(fname);
       if(c!=null){
//           TextView text = new TextView(employee_profile.this);
////           linear2.set
//    linear2.setPadding(5+0,0,0,0);
//
//           text.setText("First Name:"+c.getString(1)+"\n"+"Last Name:"+c.getString(2)+"\n"+"Email:"+c.getString(3)+"\n"+"Department:"+c.getString(4)+"\n"+"Qualification:"+c.getString(5)+"\n"+"Date Of Joining"+c.getString(6));
//           text.setTextColor(Color.parseColor("#000000"));
//           linear2.addView(text);
//           Log.d("{}", "onCreate: "+c.getString(1));

           text1.setText("First Name:"+c.getString(1)+"\n"+"Last Name:"+c.getString(2)+"\n"+"Email:"+c.getString(3)+"\n"+"Department:"+c.getString(4)+"\n"+"Qualification:"+c.getString(5)+"\n"+"Date Of Joining"+c.getString(6));

       }
       else{
           Log.d("{}", "null aaya: ");
       }



        ActionBar actionBar;
        actionBar = getSupportActionBar();

        actionBar.setTitle("Profile");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));
        actionBar.setBackgroundDrawable(colorDrawable);
        Toast.makeText(this, "im here on new page", Toast.LENGTH_SHORT).show();
    }
}