package com.example.hireling;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelperFinal4 extends SQLiteOpenHelper {
    public DBHelperFinal4(@Nullable Context context) {
        super(context, "Hireling1.db",null, 1);
        //name db ka naan eg-Last.db
        //factory null
        //VERSION 1
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Employees(Eid INTEGER PRIMARY KEY autoincrement,Fname text,Lname text,Email text,Dept text,Qualification text,DOJ date,Password text,PaidLeaves INTEGER DEFAULT 10 , UnPaidLeaves INTEGER DEFAULT 15)");
        //PAID =15 UNPAID = 10 DEFAULT VALUE THEN UPDATE QUERY
        DB.execSQL("create Table Leave(Lid INTEGER PRIMARY KEY autoincrement,DateOfApply date,DateFrom date,DateTo date,Reason text, Status text,Eid Integer , Name text,LeaveType text, foreign key(Eid) References Employees(Eid),foreign key(Name) References Employees(Fname) )");
        //SPINNER TO ADD , LEAVE STATUS FETCH
        //MAIL
        DB.execSQL("create Table Attendance(Aid INTEGER PRIMARY KEY autoincrement, Eid Integer,DateofAttendance date,PA text default 'P', foreign key(Eid) References Employees(Eid) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertEmployee(String fname, String lname, String email, String dept, String qualification, String joining){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Fname",fname); //COLUMN NAME AND LOCAL VARIABLE
        cv.put("Lname",lname);
        cv.put("Email",email);
        cv.put("Dept",dept);
        cv.put("Qualification",qualification);
        cv.put("DOJ", String.valueOf(joining));
        cv.put("Password", "Employee");
        long result=DB.insert("Employees",null,cv);

        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean employeeLeave(String dateOfApply,String dateFrom,String dateTo,String reason,Integer eid,String name,String LeaveType){
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //  DB.execSQL("create Table Leave(Lid INTEGER PRIMARY KEY autoincrement,DateOfApply date,DateFrom date,DateTo date,Reason text)");
        cv.put("DateOfApply",String.valueOf(dateOfApply));
        cv.put("DateFrom",String.valueOf(dateFrom));
        cv.put("DateTo",String.valueOf(dateTo));
        cv.put("Reason",reason);
        cv.put("Status","Pending");
        cv.put("Eid",eid);
        cv.put("Name",name);
        cv.put("LeaveType",LeaveType);


        long result=DB.insert("Leave",null,cv);

        if (result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public Cursor appliedLeaves(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor c=DB.rawQuery("select * from Leave WHERE Status=='Pending'  ",null);//WHERE STATUS ID PENDING

        return c;
    }

    public void approvedORNot(String approved, int leaveId){

        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        DB.execSQL("update Leave set Status = ? Where Lid="+leaveId, new String[]{approved} );
    }

    public Cursor employeeLeaveApprovedORNot(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor c=DB.rawQuery("select * from Leave",null);
        return c;
    }

    public  Cursor checkValidation(String email, String password){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor c=DB.rawQuery("select * from Employees where Email= ? and password=?",new String[]{email,password});
        if(c.moveToNext()){
            return c;
//            int x=c.getInt(0);
//            return x;
        }
        return null;
    }

    public Cursor getEmployee(String fname){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor c=DB.rawQuery("select * from Employees where Fname=?",new String[]{fname});
        if(c.moveToNext()){
            Log.d("{}", "getEmployee: "+c.getInt(0));
            return c;
        }
        return null;
    }
    public boolean updatePassword(String fname,String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Password", password);
        Cursor c = DB.rawQuery("select * from Employees where Fname=?", new String[]{fname});
        if (c.getCount() > 0) {
            long res = DB.update("Employees", cv, "Fname=?", new String[]{fname});

            if (res == -1) {
                return false;
            }
        }
        return true;

    }
//    db.execSQL("update "+ isLookUp +" set "+colRank+ "=" +colRank+ " -1 "+" where " + colScreenID +  "='"  +screenName + "' and " + colRank +">" +rank);
    public void deduct_leavesPaid(String fname , int no_of_days)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("PaidLeaves",no_of_days);
        DB.execSQL("update Employees set PaidLeaves=PaidLeaves-"+no_of_days+" where Fname=?", new String[]{fname});

    }
    public void deduct_leavesUnpaid(String fname , int no_of_days)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("UnPaidLeaves",no_of_days);
        DB.execSQL("update Employees set UnPaidLeaves=UnPaidLeaves-"+no_of_days+" where Fname=?", new String[]{fname});
    }

//    public void addColumnDateWise(){//NOT A GOOD APPROACH
//        Calendar calender = Calendar.getInstance();
//        int date= calender.get(Calendar.DAY_OF_MONTH);
//        String dateStr=Integer.toString(date);
//        SQLiteDatabase DB = this.getWritableDatabase();
//      //  DB.execSQL("ALTER TABLE Attendance DROP COLUMN date"+dateStr);
//        DB.execSQL("ALTER TABLE Attendance ADD COLUMN date5"+dateStr+" int default 1");
//    }

    public Cursor getAllEmployees(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor c=DB.rawQuery("select * from Employees",null);
        return c;
    }
   public void addDetails(String datecame)
   {
       SQLiteDatabase DB =this.getWritableDatabase();
      ContentValues cv = new ContentValues();
//       cv.put("DateofAttendance",dateatt);
//       cv.put("DateofAttendance",datecame);
       DB.execSQL("INSERT INTO Attendance(EID) SELECT EID FROM EMPLOYEES;");
//     long result=DB.insert("Attendance",null,cv);
//       DB.execSQL("update Attendance set DateofAttendance=? where DateofAttendance is null" ,  new String[]{datecame});
//       DB.execSQL("INSERT INTO Attendance(DateofAttendance) values (?)",new String[]{datecame} );

   }
   public void updateDate(String datecame)
   {
       SQLiteDatabase DB =this.getWritableDatabase();
       ContentValues cv = new ContentValues();
       DB.execSQL("update Attendance set DateofAttendance=? where DateofAttendance is null  ",  new String[]{datecame});
//       DB.execSQL("update Attendance set DateofAttendance=? where DateofAttendance =' '" ,  new String[]{datecame});
   }
   public void changeDate(String dateprev,String datenow)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        DB.execSQL("update Attendance set DateofAttendance=? where DateofAttendance ="+dateprev,  new String[]{datenow});
    }
   public void absentRecord(int eid,String date)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
       ContentValues cv = new ContentValues();

        //long result=DB.insert("Attendance",null,cv);
        DB.execSQL("update Attendance set PA='A'  where Eid="+ eid+" and DateofAttendance=? " ,new String[]{date} );
    }

    public Cursor displayA()
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select Employees.Eid,Employees.Fname,Employees.Lname,Attendance.DateofAttendance,Attendance.PA from Employees INNER JOIN Attendance ON Employees.Eid = Attendance.Eid order by DateofAttendance desc,Attendance.Eid asc",null);
        return cursor;
    }
    public Cursor displayU(String fname)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select Employees.Eid,Employees.Fname,Employees.Lname,Attendance.DateofAttendance,Attendance.PA from Employees INNER JOIN Attendance ON Employees.Eid = Attendance.Eid where Employees.Fname=?", new String[]{fname},null);
        return cursor;
    }
}
