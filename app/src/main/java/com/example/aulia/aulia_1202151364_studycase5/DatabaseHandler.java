package com.example.aulia.aulia_1202151364_studycase5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Aulia on 25/03/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase data;
    public static final String DB_NAME = "praktikum.db";
    public static final String TABLE_NAME = "tb_todo";
    public static final String COLUMN_NAME_TODO = "name";
    public static final String COLUMN_NAME_DESC = "description";
    public static final String COLUMN_NAME_PRIORITY = "priority";

    //class database
    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        data = this.getWritableDatabase();
        data.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (name varchar(30) PRIMARY KEY, description varchar(50), priority varchar(5))");
    }

    //method untuk membuat database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (name varchar(50) PRIMARY KEY, description varchar(50), priority varchar(5))");
    }

    //method untuk memperbaharui data
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //Method  untuk memasukkan data
    public boolean insertdata(ToDoModel model) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_TODO, model.getNameTodo());
        cv.put(COLUMN_NAME_DESC, model.getDescription());
        cv.put(COLUMN_NAME_PRIORITY, model.getPriority());
        long result = data.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Method digunakan untuk menghapus data
    public boolean deletedata(String name) {
        return data.delete(TABLE_NAME, COLUMN_NAME_TODO + "=\"" + name + "\"", null) > 0;
    }

    //Method untuk mendapatkan data dari database
    public void getAllItems(ArrayList<ToDoModel> list) {
        Cursor cr = this.getReadableDatabase().rawQuery("SELECT name, description, priority FROM " + TABLE_NAME, null);
        while (cr.moveToNext()) {
            list.add(new ToDoModel(cr.getString(0), cr.getString(1), cr.getString(2)));
        }
    }
}
