package com.universality.checkinsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.SQLClientInfoException;

//**************************数据表创建CLASS******************************
public class DBOpenHelper extends SQLiteOpenHelper {



    //创建数据表
    final  String createSql = "create table CheckInData(_id integer primary key autoincrement,name,phone,QQ,CheckIn)";

    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL(createSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVerson, int newVerson) {
        Log.i("词典更新",oldVerson + "到" + newVerson);
    }



}
