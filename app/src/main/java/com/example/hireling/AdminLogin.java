package com.example.hireling;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {
    Button login;
    EditText Uemail,Upass;
    public String Uuseremail,Upassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        //actionBar.setTitle("Adding New Employee");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));
        actionBar.setBackgroundDrawable(colorDrawable);

        login = findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }});
        Uemail = findViewById(R.id.EmailAddress);
        Upass = findViewById(R.id.Password);}
    public void check(){
        Uuseremail = Uemail.getText().toString().trim();
        Upassword = Upass.getText().toString().trim();
        if(Uuseremail.isEmpty()) {
            Uemail.setError("Enter an e-mail,it is blank");
            Uemail.requestFocus();
            return;}
        if(!Patterns.EMAIL_ADDRESS.matcher(Uuseremail).matches()){
            Uemail.setError("Enter an email");
            Uemail.requestFocus();
            return;}
        if(Upassword.isEmpty()) {
            Upass.setError("Enter a password ,it is blank");
            Upass.requestFocus();
            return;}
        if(Upassword.length()<6){
            Upass.setError("Minimum length is 6 characters");
            Upass.requestFocus();
            return;}
        if(Uuseremail.equals("admin@gmail.com") && Upassword.equals("admin")){
            Toast.makeText(AdminLogin.this,"Welcome Admin",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AdminLogin.this, ForAdminPage.class);
            startActivity(intent);}
        else{
            Toast.makeText(this,"Wrong email or password",Toast.LENGTH_LONG).show();
        }}}