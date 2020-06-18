package cn.edu.scujcc.licaiapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.licaiapp.R;


public class WelcomeActivity extends AppCompatActivity {
//定义对象
    Button bt_know_wel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏，节省屏幕空间
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_welcome);
        //绑定控件
       bt_know_wel=findViewById(R.id.bt_know_wel);

       //按钮单击事件
        bt_know_wel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
