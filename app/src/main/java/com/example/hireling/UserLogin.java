package com.example.hireling;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
public class UserLogin extends AppCompatActivity {
    Button login;
    EditText Uemail,Upass;
    public String Uuseremail,Upassword;
    public DBHelperFinal4 DBHelperFinal4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

       // actionBar.setTitle("Adding New Employee");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));
        actionBar.setBackgroundDrawable(colorDrawable);

        login = findViewById(R.id.button);
        Uemail=findViewById(R.id.EmailAddress);
        Upass=findViewById(R.id.Password);
        DBHelperFinal4=new DBHelperFinal4(UserLogin.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = Uemail.getText().toString();
                if (Email.isEmpty()) {
                    Uemail.setError("Enter an e-mail,it is blank");
                    Uemail.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    Uemail.setError("Enter a valid email");
                    Uemail.requestFocus();
                }
                else{
                Cursor c=DBHelperFinal4.checkValidation(Uemail.getText().toString(),Upass.getText().toString());
//                Log.d("{}", "onClick: control aaya"+c);
                if(c==null){
                    Toast.makeText(UserLogin.this,"Incorrect Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i=new Intent(UserLogin.this,ForUser.class);
                    Log.d("{TAG}", "onClick: "+c.getInt(0));
                    i.putExtra("userId",c.getInt(0));
                    i.putExtra("fname",c.getString(1));
                    startActivity(i);
                }
            }}
        });
    }
}