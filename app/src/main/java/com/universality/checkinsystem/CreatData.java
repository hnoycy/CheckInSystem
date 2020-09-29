package com.universality.checkinsystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class CreatData extends AppCompatActivity {


    private DBOpenHelper dbOpenHelper ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creat_data);
        Log.i("打开CrearData","成功");



        //创建数据表
        dbOpenHelper = new DBOpenHelper(CreatData.this,"CheckIn",null,1);
        SQLiteDatabase DB = dbOpenHelper.getWritableDatabase();
        Log.i("创建数据表","成功");

        //获得TextView对象
        TextView textView = (TextView)findViewById(R.id.creat_data);

        //删除数据表
        DB.execSQL("DROP TABLE IF EXISTS CheckInData");
        Log.i("删除数据表","成功");
        DB.execSQL("CREATE TABLE CheckInData(_id integer primary key autoincrement,name String,phone String,QQ String,CheckIn String,Change String)");
        Log.i("创建数据表","成功");

        //插入数据
        //第一个人
//        String[] name00001 = new String[]{"欧阳成邑","13574272577","1547559269","是","点击取消"};
//        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values(?,?,?,?,?)", name00001);
//        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('欧阳成邑','13574272577','1547559269','是','点击取消')");
//        String creat_data00001 = "加载完成1%";
//        textView.setText(creat_data00001);
        Log.i("数据库写入","0001%");

        //第二个人
//        String[] name00002 = new String[]{"党志豪","13574272577","1547559269","是","点击取消"};
//        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values(?,?,?,?,?)", name00002);
//        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('党志豪','13574272577','1547559269','是','点击取消')");
//        String creat_data0002 = "加载完成2%";
//        textView.setText(creat_data0002);
        Log.i("数据库写入","0002%");


        Intent intent = new Intent(CreatData.this,CheckInSystem.class);
        startActivity(intent);

finish();

}


}
