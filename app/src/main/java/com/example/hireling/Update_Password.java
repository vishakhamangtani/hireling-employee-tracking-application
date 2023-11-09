package com.example.hireling;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Update_Password extends AppCompatActivity {
 EditText old_pwd,new_pwd;
Button set_pwd;
DBHelperFinal4 dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        old_pwd = findViewById(R.id.old_pwd);
        new_pwd = findViewById(R.id.new_pwd);
        set_pwd = findViewById(R.id.set_pwd);
        dbhelper = new DBHelperFinal4(Update_Password.this);


        Intent i=getIntent();


        int userId=i.getIntExtra("userId",0);
        String fname=i.getStringExtra("fname");
        Log.d("{TAG}", "employeeprofile: "+fname);
        dbhelper=new DBHelperFinal4(Update_Password.this);
        Cursor c= dbhelper.getEmployee(fname);
        set_pwd.setOnClickListener(new View.OnClickListener() {
//            String updated=new_pwd.getText().toString();

            @Override
            public void onClick(View view) {


                if(c!=null)
                {
                    dbhelper.updatePassword(fname,new_pwd.getText().toString());
                   // dbhelper.updatePassword(fname,"Vishu");
                    Toast.makeText(Update_Password.this, "password updated  "+new_pwd.getText().toString()+" New pwd"+fname, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Update_Password.this,MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Update_Password.this, "password NOT updated", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}