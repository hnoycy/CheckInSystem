package com.universality.checkinsystem;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CheckInSystem extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_in_system);


        //*****************第一个人*************************
        //输入信息
        String[] Name001 = new String[]{"欧阳","13574272577","1547559269"};

        //获得名字长度
        Paint paint = new Paint();
        float floatName001 = paint.measureText(Name001[0]);
        String changdu = floatName001 + "" ;
        Log.i("欧阳成邑的长度",changdu);

        //强制转换
        int intName001 = (int)floatName001 ;

        //判断名字长度
        if (intName001 == 36 ) {
            TextView name001 = (TextView) findViewById(R.id.name001);
            String Name = "      " + Name001[0] + "      ";
            name001.setText(Name);
            Log.i("001名字长度","36");
        }else if (intName001 ==24){
            TextView name001 = (TextView) findViewById(R.id.name001);
            String Name = "        " + Name001[0] + "        ";
            name001.setText(Name);
            Log.i("001名字长度","24");
        }else if (intName001 == 48){
            TextView name001 = (TextView) findViewById(R.id.name001);
            String Name = "    " + Name001[0] + "    ";
            name001.setText(Name);
            Log.i("001名字长度","48");
        }

        //输入手机号
        TextView phone001 = (TextView)findViewById(R.id.phone001);
        phone001.setText(Name001[1]);

        //输入QQ号
        TextView qq001 = (TextView)findViewById(R.id.QQ001);
        qq001.setText(Name001[2]);

        //签到情况
        final TextView signin001 = (TextView)findViewById(R.id.signin001);
        signin001.setText("       未到       ");
        signin001.setTextColor(Color.parseColor("#FF0000"));

        //签到摁扭
        final TextView change001 = (TextView)findViewById(R.id.change001);
        change001.setText("点击签到");
        change001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String signinnow = signin001.getText().toString();
                if (signinnow == "       未到       "){
                    signin001.setText("       已到       ");
                    signin001.setTextColor(Color.parseColor("#23FF00"));
                    change001.setText("点击取消");
                }else {
                    signin001.setText("       未到       ");
                    signin001.setTextColor(Color.parseColor("#FF0000"));
                    change001.setText("点击签到");
                }

            }
        });



    }
}
