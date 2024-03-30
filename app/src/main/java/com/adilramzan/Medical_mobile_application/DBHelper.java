package com.adilramzan.Medical_mobile_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class DBHelper extends SQLiteOpenHelper {

    Context context;

    private static  String DB_NAME="user.db";
    private  static  int DB_VERSION=1;

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    private static String createTableQuery="Create table LoginUser(userName TEXT"+
        ",email TEXT"+
        ",phone TEXT"+
        ",password TEXT"+
        ",image BLOB)";
    public DBHelper(Context context) {

        super(context, DB_NAME,null,DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createTableQuery);
        Toast.makeText(context.getApplicationContext(), "table created successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void storeData(ModelClass modelClass){
        SQLiteDatabase db=this.getWritableDatabase();
        Bitmap imageToStoreBitmap=modelClass.getProfileImage();

        byteArrayOutputStream=new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        imageInBytes=byteArrayOutputStream.toByteArray();

        ContentValues cv=new ContentValues();
        cv.put("userName",modelClass.getUserName());
        cv.put("email",modelClass.getEmail());
        cv.put("phone",modelClass.getPhone());
        cv.put("password",modelClass.getPassword());
        cv.put("image",imageInBytes);

        long checkIfQueryRun=db.insert("LoginUser",null,cv);
        if(checkIfQueryRun!=-1){
            Toast.makeText(context.getApplicationContext(), "table added successfully", Toast.LENGTH_SHORT).show();
            db.close();
        }else{
            Toast.makeText(context.getApplicationContext(), "fail to add", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getUser(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from LoginUser",null);
        return  cursor;
    }

}
