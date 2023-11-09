package com.example.hireling;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LeaveApprove extends AppCompatActivity {

    private DBHelperFinal4 dbHelperFinal;
    private StringBuffer appliedLeavesString=new StringBuffer();
    private LinearLayout linear;
    private int eid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_approve);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        actionBar.setTitle("Manage Leaves");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#076AE1"));
        actionBar.setBackgroundDrawable(colorDrawable);
        dbHelperFinal=new DBHelperFinal4(LeaveApprove.this);
        linear=findViewById(R.id.linear);

//        RadioGroup radioGroup = findViewById(R.id.radio_group);
//        radioGroup.addView(findViewById(R.id.approve));
//        radioGroup.addView(findViewById(R.id.decline));

        Cursor c=dbHelperFinal.appliedLeaves();
        while(c.moveToNext()){
            TextView text=new TextView(LeaveApprove.this);
            Button approvebutton=new Button(LeaveApprove.this);
            Button declineButton=new Button(LeaveApprove.this);
            int id=c.getInt(0);// this is leave id (LID)
            String name =c.getString(7) ;
            appliedLeavesString.append("Employee id:"+id+"\nEmployeeName:"+c.getString(7)+"\nDate Of Apply"+c.getString(1)+"\n"+"Date From: "+c.getString(2)+"\n"+"DateTo: "+c.getString(3)+"\n"+"Reason: "+c.getString(4)+"\n");
            text.setText(appliedLeavesString);
            text.setPadding(0, 10, 0, 0);
            text.setTextColor(Color.parseColor("#000000"));
            approvebutton.setText("Approve");
            declineButton.setText("Decline");
            approvebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(LeaveApprove.this, "approve clicked"+id, Toast.LENGTH_SHORT).show();
                    dbHelperFinal.approvedORNot((String) approvebutton.getText(),id);
                    Toast.makeText(LeaveApprove.this, "approve clicked"+approvebutton.getText(), Toast.LENGTH_SHORT).show();
                    sendEmail("approvebutton",name);
                }
            });
            declineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbHelperFinal.approvedORNot((String) declineButton.getText(),id);
                    Toast.makeText(LeaveApprove.this, "Decline clicked"+declineButton.getText(), Toast.LENGTH_SHORT).show();
                    sendEmail("declinebutton",name);
                }
            });
//            approvebutton.setBackgroundColor(Color.parseColor("#076AE1"));
//            declineButton.setBackgroundColor(Color.parseColor("#076AE1"));

            approvebutton.setBackgroundColor(Color.GREEN);
            declineButton.setBackgroundColor(Color.RED);

            approvebutton.setPadding(3,3,3,3);
            declineButton.setPadding(3,3,3,3);
//            approvebutton.setLayoutParams (new RelativeLayout.LayoutParams(250, RelativeLayout.LayoutParams.WRAP_CONTENT));
//         declineButton.setLayoutParams (new RelativeLayout.LayoutParams(250, RelativeLayout.LayoutParams.WRAP_CONTENT));
            //approvebutton.offsetTopAndBottom(3);
            linear.addView(text);
            linear.addView(approvebutton);
            linear.addView(declineButton);
            appliedLeavesString.delete(0, appliedLeavesString.length());
           // above line corresponds to appliedLeavesString=" ";
        }
        c.close();
    }
    protected  void sendEmail(String  binfo,String name) {
        String message = new String();
        String emailaddr = new String();
        if (binfo=="declinebutton")
        {
             message=" Hi "+name+"  Your Leave  Request is rejected by higher authorities\n Sorry for the inconvenience\n Regards";
        }
        if (binfo =="approvebutton")
        {
             message=" Hi "+name+"  Your Leave  Request is approved by higher authorities\n Thankyou\n Regards";

        }
        Cursor c = dbHelperFinal.getEmployee(name);
        if (c!=null)
        {
            emailaddr=c.getString(3);
        }

        Log.i("Send email", "");
        String[] TO = {emailaddr};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "LEAVE STATUS");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "Done");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(LeaveApprove.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}