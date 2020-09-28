package com.universality.checkinsystem;

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
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class CheckInSystem extends AppCompatActivity {

    //创建数据库对象

    private ListView values;
    private EditText username, password;


    private DBOpenHelper dbOpenHelper;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_in_system);

        //***************************增加键***********************************
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //创建弹出窗口
                final AlertDialog.Builder builder = new Builder(CheckInSystem.this);
                builder.setTitle("添加");

                //创建View
                View v = View.inflate(CheckInSystem.this,R.layout.add,null);

                //获取填字框
                final EditText EName = (EditText)v.findViewById(R.id.Name0004);

                final EditText EPhone = (EditText)v.findViewById(R.id.Phone0004);

                final EditText EQQ = (EditText)v.findViewById(R.id.QQ0004);

                //设置弹出窗口内容
                builder.setView(v);

                //添加保存按钮
                builder.setPositiveButton("保存", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //获取输入的内容
                        String Name = EName.getText().toString();
                        String Phone = EPhone.getText().toString();
                        String QQ = EQQ.getText().toString();

                        insert(Name,Phone,QQ,"是","点击取消");

                        replaceList();

                    }
                });

                //添加取消摁扭
                builder.setNeutralButton("取消", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.create().show();
            }
        });



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

        // 设置颜色的变化
        SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i) {

                final String SCursor = cursor.getString(4);
                Log.i("asd",SCursor);

                if(cursor.getColumnIndex("CheckIn")== i) {

                    if (SCursor.equals("是")){
                        TextView textView = (TextView)view;
                        textView.setTextColor(Color.parseColor("#23FF00"));
                        textView.setText(SCursor);
                        Log.i("变色","绿色");
                        return true;
                    }else if (SCursor.equals("未到达")){
                        TextView textView = (TextView)view;
                        textView.setTextColor(Color.parseColor("#FF0000"));
                        textView.setText(SCursor);
                        Log.i("变色","红色");
                        return true;
                    }

                }
                return false;
            }
        };




        //设置展示
        dbOpenHelper = new DBOpenHelper(CheckInSystem.this,"CheckIn",null,1);
        SQLiteDatabase DB = dbOpenHelper.getWritableDatabase();
        String selectSql = "select _id,name,phone,QQ,CheckIn,Change from CheckInData";
        Cursor cursor = DB.rawQuery(selectSql, null);// 我们需要查处所有项故不需要查询条件
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.show, cursor, new String[]{"_id", "name",
                "phone","QQ","CheckIn","Change"}, new int[]{R.id.id002, R.id.name002,
                R.id.phone002,R.id.QQ002,R.id.signin002,R.id.change002},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        adapter.setViewBinder(viewBinder);

        final ListView values = (ListView)findViewById(R.id.ListView);



//        TextView textView =(TextView)values.findViewById(R.id.signin002);
//        textView.setTextColor(Color.parseColor("#23FF00"));

        values.setAdapter(adapter);







        // ***************************长按修改***********************
        values.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                // 获取所点击项的_id
                TextView tv = (TextView) arg1.findViewById(R.id.id002);
                final String _id = tv.getText().toString();
                // 获取所点击项的名字
                TextView tvName = (TextView)arg1.findViewById(R.id.name002) ;
                final String Sname = tvName.getText().toString();
                // 获取所点击项的手机号
                TextView tvPhone = (TextView)arg1.findViewById(R.id.phone002);
                final String SPhone = tvPhone.getText().toString();
                // 获取所点击项的QQ号
                TextView tvQQ = (TextView)arg1.findViewById(R.id.QQ002);
                final String SQQ = tvQQ.getText().toString();

                // 通过Dialog弹出修改界面
                final AlertDialog.Builder builder = new Builder(CheckInSystem.this);
                builder.setTitle("修改");

                // 自定义修改页
                View v = View.inflate(CheckInSystem.this,R.layout.change,null);
                final EditText Name00003 = (EditText)v.findViewById(R.id.Name0003);

                final EditText Phone00003 = (EditText)v.findViewById(R.id.Phone0003);

                final EditText QQ00003 = (EditText)v.findViewById(R.id.QQ0003);

                Button phone = (Button)v.findViewById(R.id.buttonPhone);
                phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String PhoneNumber = Phone00003.getText().toString();
                        Intent Intent =  new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + PhoneNumber));//跳转到拨号界面，同时传递电话号码
                        startActivity(Intent);

                    }
                });

                // Dialog弹出原内容

                Name00003.setText(Sname);
                Phone00003.setText(SPhone);
                QQ00003.setText(SQQ);

                builder.setView(v);


                // 删除按钮点击事件
                builder.setNeutralButton("删除", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder builder1 = new Builder(CheckInSystem.this);
                        builder1.setTitle("确定要删除吗？");
                        builder1.setPositiveButton("确定", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                delete(_id);
                                replaceList();// 删除后刷新列表
                            }
                        });
                        builder1.setNeutralButton("取消", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder1.create().show();

                    }
                });
                // 取消按钮点击事件
                builder.setNegativeButton("取消", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                // 确定按钮点击事件
                builder.setPositiveButton("保存", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = Name00003.getText().toString();
                        String newPhone = Phone00003.getText().toString();
                        String newQQ = QQ00003.getText().toString();
                        updata(newName, newPhone, newQQ ,_id);
                        replaceList();// 更新后刷新列表

                    }
                });
                builder.create().show();

//                // 通过Dialog提示是否删除
//                AlertDialog.Builder builder = new AlertDialog.Builder(
//                        CheckInSystem.this);
//                builder.setMessage("确定要删除吗？");
//
//                // 取消按钮点击事件
//                builder.setNegativeButton("取消", new OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder.create().show();

                return true;
            }
        });

        //*************************短按修改**********************


        values.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                // 获取所点击项的_id
                TextView tv = (TextView) view.findViewById(R.id.id002);
                final String _id = tv.getText().toString();

                // 获取到达状态
                TextView signin00002 = (TextView)view.findViewById(R.id.signin002);
                final String SSigin = signin00002.getText().toString();

                // 获取“点击签到”
                TextView change00002 = (TextView)view.findViewById(R.id.change002);
                final String SChange = change00002.getText().toString();

                if ("是".equals(SSigin)){
                    Log.i("是否为是",SSigin);
                    change("未到达","点击签到",_id);
                }else{
                    change("是","点击取消",_id);
                }

                replaceList();// 更新后刷新列表
                Log.i("修改后数据",SSigin);
            }
        });
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
//*******************************第一个人结束**********************************
//
//

    }

    /**
     * 插入
     */
    public void insert( String name,String phone ,String QQ,String CheckIn ,String Change) {
        dbOpenHelper = new DBOpenHelper(CheckInSystem.this,"CheckIn",null,1);
        SQLiteDatabase DB = dbOpenHelper.getWritableDatabase();
        String insertSql = "insert into CheckInData(name,phone,QQ,CheckIn,Change) values(?,?,?,?,?)";
        DB.execSQL(insertSql, new String[] { name,phone,QQ,CheckIn,Change });
    }

    /**
     * 查询
     */
    public Cursor select() {
        dbOpenHelper = new DBOpenHelper(CheckInSystem.this,"CheckIn",null,1);
        SQLiteDatabase DB = dbOpenHelper.getWritableDatabase();
        String selectSql = "select _id ,name,phone,QQ,CheckIn,Change from CheckInData";
        Cursor cursor = DB.rawQuery(selectSql, null);// 我们需要查处所有项故不需要查询条件
        return cursor;
    }
    /**
     * 删除
     */
    public void delete(String id) {
        dbOpenHelper = new DBOpenHelper(CheckInSystem.this,"CheckIn",null,1);
        SQLiteDatabase DB = dbOpenHelper.getWritableDatabase();
        String deleteSql = "delete from CheckInData where _id="+ id;
        DB.execSQL(deleteSql);
    }
    /**
     * ListView的适配器
     */
    public void replaceList() {
        dbOpenHelper = new DBOpenHelper(CheckInSystem.this,"CheckIn",null,1);
        SQLiteDatabase DB = dbOpenHelper.getWritableDatabase();
        String selectSql = "select _id,name,phone,QQ,CheckIn,Change from CheckInData";
        Cursor cursor = DB.rawQuery(selectSql, null);// 我们需要查处所有项故不需要查询条件



        // 设置颜色的变化
        SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i) {

                final String SCursor = cursor.getString(4);
                Log.i("asd",SCursor);

                if(cursor.getColumnIndex("CheckIn")== i) {


                    if (SCursor.equals("是")){
                        TextView textView = (TextView)view;
                        textView.setTextColor(Color.parseColor("#23FF00"));
                        textView.setText(SCursor);
                        Log.i("变色","绿色");
                        return true;
                    }else if (SCursor.equals("未到达")){
                        TextView textView = (TextView)view;
                        textView.setTextColor(Color.parseColor("#FF0000"));
                        textView.setText(SCursor);
                        Log.i("变色","红色");
                        return true;
                    }

                }



                return false;


            }
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.show, cursor, new String[]{"_id", "name",
                "phone","QQ","CheckIn","Change"}, new int[]{R.id.id002, R.id.name002,
                R.id.phone002,R.id.QQ002,R.id.signin002,R.id.change002},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        adapter.setViewBinder(viewBinder);


        ListView values = (ListView)findViewById(R.id.ListView);
        values.setAdapter(adapter);
        Log.i("刷新数据表","成功");

    }

    /**
     * 更新
     */
    public void updata(String Name, String Phone, String QQ , String id) {
        dbOpenHelper = new DBOpenHelper(CheckInSystem.this,"CheckIn",null,1);
        SQLiteDatabase DB = dbOpenHelper.getWritableDatabase();
        String updataSql = "update CheckInData set name=?,phone=?,QQ=? where _id=?";
        DB.execSQL(updataSql, new String[] { Name, Phone, QQ, id });
    }

    public  void change (String CheckIn ,String Change,String id){
        dbOpenHelper = new DBOpenHelper(CheckInSystem.this,"CheckIn",null,1);
        SQLiteDatabase DB = dbOpenHelper.getWritableDatabase();
        Log.i("传入修改数据CheckIn",CheckIn);
        Log.i("传入修改数据Change",Change);
        String changeSql = "update CheckInData set CheckIn =?,change=? where _id = ?";
        DB.execSQL(changeSql,new String[] {CheckIn,Change,id} );
        Log.i("签到修改数据表","成功");
    }

}
