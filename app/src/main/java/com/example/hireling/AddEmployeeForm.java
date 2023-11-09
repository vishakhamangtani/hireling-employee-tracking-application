package com.example.hireling;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddEmployeeForm extends AppCompatActivity {
    private EditText fname,lname,email,qualification, dateSelected;
    private Button addB,datePicker;
    private DBHelperFinal4 dbHelper;
    private String selectedDept;
    private int mYear, mMonth, mDay;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee_form);
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        actionBar.setTitle("Adding New Employee");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        email=findViewById(R.id.email);
        qualification=findViewById(R.id.qualification);
        dateSelected=findViewById(R.id.dateSelected);
        datePicker=findViewById(R.id.date);
        addB=findViewById(R.id.register);

        dbHelper=new DBHelperFinal4(AddEmployeeForm.this);

        Spinner spinner = findViewById(R.id.dept);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDept=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle nothing selected
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEmployeeForm.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dateSelected.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Fname = fname.getText().toString();
                String Lname = lname.getText().toString();
                String Qualification = qualification.getText().toString();

                if (Email.isEmpty()) {
                    email.setError("Enter an e-mail,it is blank");
                    email.requestFocus();
                }
                else if (Fname.isEmpty()) {
                    fname.setError("First Name cannot be blank,Enter a First Name");
                    fname.requestFocus();
                }
                else if (Lname.isEmpty()) {
                    lname.setError("Last Name cannot be blank,Enter a Last Name");
                    lname.requestFocus();
                }

                else if (Qualification.isEmpty()) {
                    qualification.setError("Qualification cannot be blank");
                    qualification.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    email.setError("Enter a valid email");
                    email.requestFocus();
                } else{
                    boolean ans = dbHelper.insertEmployee(fname.getText().toString(), lname.getText().toString(), email.getText().toString(), selectedDept, qualification.getText().toString(), dateSelected.getText().toString());
                if (ans) {
                    Toast.makeText(AddEmployeeForm.this, "Employee Registered Successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(AddEmployeeForm.this, ForAdminPage.class);
                    startActivity(i);
                } else {
                    Toast.makeText(AddEmployeeForm.this, "Sorry Not Inserted! Try Again", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(AddEmployeeForm.this, "Details are:" + fname.getText() + "\n" + lname.getText() + "\n" + email.getText() + "\n" + qualification.getText() + "\n" + selectedDept + "\n" + dateSelected.getText().toString(), Toast.LENGTH_SHORT).show();
            } }
        });

    }
}