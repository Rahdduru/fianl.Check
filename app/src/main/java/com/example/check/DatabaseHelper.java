package com.example.check;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper{

    //DB 이름
    public static final String DATABASE_NAME= "CHECK.db";

    //일정 저장 TABLE
    public static final String TABLE_NAME= "list";

    //TABLE 항목
    public static final String COL_1= "ID";
    public static final String COL_2= "DATE";
    public static final String COL_3= "TITLE";
    public static final String COL_4= "PLACE";
    public static final String COL_5= "TIME";
    public static final String COL_6= "CONTENT";

    public DatabaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table"+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, TITLE TEXT, PLACE TEXT, TIME TEXT, CONTENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    //DB추가
    public boolean insertData(String s, String date, String title, String place){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, title);
        contentValues.put(COL_4, place);
        Integer time = null;
        contentValues.put(COL_5, time);
        String content = null;
        contentValues.put(COL_6, content);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == 1)
            return false;
        else
            return true;
    }

    //DB 읽기
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select*from"+TABLE_NAME, null);
        return res;
    }

    //DB 삭제하기
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    //DB 수정하기
    public boolean updateData(String id, String date, String title, String place, String time, String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, title);
        contentValues.put(COL_4, place);
        contentValues.put(COL_5, time);
        contentValues.put(COL_6, content);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;

    }
}