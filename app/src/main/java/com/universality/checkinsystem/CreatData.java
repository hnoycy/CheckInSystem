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

        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('董洁茹','13059071433','1901681359','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('刘怡如','15039042966','1213862101','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('马雪晴','18860265189','1606442787','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('端木若冰','18839365837','291823205','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('张朝','15839790060','1511970144','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('易思成','15660956382','892903544','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('谢沁江','19839777687','2387216465','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('徐浚凯','15290365573','3197055744','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('罗文鑫','15637583090','1668372500','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('陈科赢','18625322280','1063351705','是','点击取消')");
        String creat_data00001 = "加载完成33%";
        textView.setText(creat_data00001);
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('刘思瀚','17837320361','3532512652','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('李坤豪','15515488372','827695971','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('宋慧萍','18236994232','1973913873','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('梅心语','17707337110','2909558519','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('刘畅','15915256217','2472874435','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('史骞骞','18639770632','2929276696','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('欧阳成邑','13574272577','1547559269','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('李鹏辉','16696538883','1696806413','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('王铭浩','13137666928','2662047487','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('党志豪','16627578848','524285968','是','点击取消')");
        String creat_data00002 = "加载完成66%";
        textView.setText(creat_data00002);
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('赵航','13781438998','1626866351','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('梁哲浩','18638928117','1085532921','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('刘烁','15660769822','2507374713','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('胡炎林','13137390950','2217689216','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('崔书菡','15890056877','1425080535','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('李嘉璐','15836722066','1549541291','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('崔振华','13838799781','1774040012','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('张珂铭','18639970889','2538283884','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('梅秋阳','13673899473','2100204332','是','点击取消')");
        DB.execSQL("insert into CheckInData(name,phone,QQ,CheckIn,Change) values('冯涯林','18697789352','2427467516','是','点击取消')");
        String creat_data00003 = "加载完成100%";
        textView.setText(creat_data00003);
        Intent intent = new Intent(CreatData.this,CheckInSystem.class);
        startActivity(intent);


}

}
