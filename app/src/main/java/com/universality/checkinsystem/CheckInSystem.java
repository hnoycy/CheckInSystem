package com.universality.checkinsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckInSystem extends AppCompatActivity {

    //创建数据库对象

    private ListView values;
    private EditText username, password;


    private DBOpenHelper dbOpenHelper;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_in_system);


        //***************设置第一次开启软件时写入数据库的Actiity*************************
        SharedPreferences first = getSharedPreferences("first", Context.MODE_PRIVATE);
        if (first.getBoolean("time",true)){
            SharedPreferences.Editor editor = first.edit();
            editor.putBoolean("time", false);
            editor.commit();

            //打开数据库写入Activity
            Intent intent = new Intent(CheckInSystem.this,CreatData.class);
            startActivity(intent);
            finish();
        }






        //****************************数据库展示**************************
        dbOpenHelper = new DBOpenHelper(CheckInSystem.this,"CheckIn",null,1);
        SQLiteDatabase DB = dbOpenHelper.getWritableDatabase();
        String selectSql = "select _id,name,phone,QQ,CheckIn,Change from CheckInData";
        Cursor cursor = DB.rawQuery(selectSql, null);// 我们需要查处所有项故不需要查询条件
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.something, cursor, new String[]{"_id", "name",
                "phone","QQ","CheckIn","Change"}, new int[]{R.id.id002, R.id.name002,
                R.id.phone002,R.id.QQ002,R.id.signin002,R.id.change002},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        ListView values = (ListView)findViewById(R.id.ListView);
        values.setAdapter(adapter);



        TextView textView =(TextView)findViewById(R.id.signin002);
        textView.setTextColor(Color.parseColor("#23FF00"));
//        String useful = textView.getText().toString();
//        if (useful == "已到"){
//            textView.setTextColor(Color.parseColor("#23FF00"));
//        }else {
//            textView.setTextColor(Color.parseColor("#FF0000"));
//        }

//        //*****************第一个人*************************
//        //输入信息
//        String[] Name001 = new String[]{"欧阳成邑","13574272577","1547559269"};
//
//        //获得名字长度
//        Paint paint = new Paint();
//        float floatName001 = paint.measureText(Name001[0]);
//        String changdu = floatName001 + "" ;
//        Log.i("欧阳成邑的长度",changdu);
//
//        //强制转换
//        int intName001 = (int)floatName001 ;
//
//        //判断名字长度
//
//        TextView name001 = (TextView) findViewById(R.id.name001);
//        String Name = Name001[0]+"";
//        name001.setText(Name);
//
////        if (intName001 == 36 ) {
////            TextView name001 = (TextView) findViewById(R.id.name001);
////            String Name = "      " + Name001[0] + "      ";
////            name001.setText(Name);
////            Log.i("001名字长度","36");
////        }else if (intName001 ==24){
////            TextView name001 = (TextView) findViewById(R.id.name001);
////            String Name = "        " + Name001[0] + "        ";
////            name001.setText(Name);
////            Log.i("001名字长度","24");
////        }else if (intName001 == 48){
////            TextView name001 = (TextView) findViewById(R.id.name001);
////            String Name = "    " + Name001[0] + "    ";
////            name001.setText(Name);
////            Log.i("001名字长度","48");
////        }
//
//        //输入手机号
//        TextView phone001 = (TextView)findViewById(R.id.phone001);
//        phone001.setText(Name001[1]);
//
//        //输入QQ号
//        TextView qq001 = (TextView)findViewById(R.id.QQ001);
//        qq001.setText(Name001[2]);
//
//        //签到情况
//        final TextView signin001 = (TextView)findViewById(R.id.signin001);
//        signin001.setText("未到");
//        signin001.setTextColor(Color.parseColor("#FF0000"));
//
//        //签到摁扭
//        final TextView change001 = (TextView)findViewById(R.id.change001);
//        change001.setText("点击签到");
//        change001.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String signinnow = signin001.getText().toString();
//                if (signinnow == "未到"){
//                    signin001.setText("已到");
//                    signin001.setTextColor(Color.parseColor("#23FF00"));
//                    change001.setText("点击取消");
//                }else {
//                    signin001.setText("未到");
//                    signin001.setTextColor(Color.parseColor("#FF0000"));
//                    change001.setText("点击签到");
//                }
//
//            }
//        });
//
//
//

    }





}
