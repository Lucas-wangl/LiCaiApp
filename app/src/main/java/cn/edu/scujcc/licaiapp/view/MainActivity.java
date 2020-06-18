package cn.edu.scujcc.licaiapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.licaiapp.R;
import cn.edu.scujcc.licaiapp.activity.DataAnalyseActivity;
import cn.edu.scujcc.licaiapp.activity.InComeDetailActivity;
import cn.edu.scujcc.licaiapp.activity.NewInComeActivity;
import cn.edu.scujcc.licaiapp.activity.NewPayActivity;
import cn.edu.scujcc.licaiapp.activity.PayDetailActivity;
import cn.edu.scujcc.licaiapp.activity.SysSettingActivity;


public class MainActivity extends AppCompatActivity {
//定义对象
    ImageView bt_newincome,bt_incomedetail,btn_newpay,btn_paydetail,bt_dataanalyse,btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏，节省屏幕空间
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);

        // 绑定控件
        initView();

        //按钮单击事件
        btnOnClick();

    }


    // 绑定控件代码
    private void initView() {
        bt_newincome=findViewById(R.id.newincome_main);
        bt_incomedetail=findViewById(R.id.incomedetail_main);
        btn_newpay=findViewById(R.id.newpay_main);
        btn_paydetail=findViewById(R.id.paydetail_main);
        bt_dataanalyse=findViewById(R.id.dataanalyse_main);
        btn_setting=findViewById(R.id.syssetting_main);
    }

    //按钮单击事件代码
    private void btnOnClick() {
        bt_newincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NewInComeActivity.class);
                startActivity(intent);
            }
        });
        bt_incomedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, InComeDetailActivity.class);
                startActivity(intent);
            }
        });
        btn_newpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NewPayActivity.class);
                startActivity(intent);
            }
        });
        btn_paydetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, PayDetailActivity.class);
                startActivity(intent);
            }
        });

        bt_dataanalyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, DataAnalyseActivity.class);
                startActivity(intent);
            }
        });
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SysSettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
