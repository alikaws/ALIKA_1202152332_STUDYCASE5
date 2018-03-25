package com.example.alita_pc.alikawulansari_1202152332_studycase5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    //variable yang akan digunakan
    Context cont;
    SQLiteDatabase data;
    public static final String db_name = "aws.db";
    public static final String table_name = "todo";
    public static final String col_1 = "name";
    public static final String col_2 = "description";
    public static final String col_3 = "priority";

    //Method untuk baris database
    public database(Context context) {
        super(context, db_name, null, 1);
        this.cont = context;
        data = this.getWritableDatabase();
        data.execSQL("create table if not exists "+table_name+" (name varchar(50) primary key, description varchar(50), priority varchar(5))");

    }

    //method untuk baris database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+table_name+" (name varchar(50) primary key, description varchar(50), priority varchar(5))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+table_name);
        onCreate(sqLiteDatabase);
    }

    //untuk memasukkan data pada baris
    public boolean insertdata(itemtodo satuan) {
        ContentValues cv = new ContentValues();
        cv.put(col_1, satuan.getName());
        cv.put(col_2, satuan.getDescription());
        cv.put(col_3, satuan.getPriority());
        long result = data.insert(table_name, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //ketika menghapus data
    public boolean deletedata(String name){
        return data.delete(table_name, col_1+"=\""+name+"\"",null)>0;
    }

    //untuk mendapatkan data dari database
    public void getAllItems(ArrayList<itemtodo> list){
        Cursor cr = this.getReadableDatabase().rawQuery("select name, description, priority from "+table_name, null);
        while(cr.moveToNext()){
            list.add(new itemtodo(cr.getString(0), cr.getString(1), cr.getString(2)));
        }
    }

    //untuk menghapus seluruh data dari table
    public void ClearTabel(){
        data.execSQL("delete from "+table_name);
    }
}
