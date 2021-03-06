package cn.edu.scujcc.licaiapp.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.edu.scujcc.licaiapp.R;
import cn.edu.scujcc.licaiapp.db.MyDBHelper;
import cn.edu.scujcc.licaiapp.view.MainActivity;


public class NewPayActivity extends AppCompatActivity {
//定义对象
EditText et_money,et_time,et_payer,et_remark;
    Spinner sp_type;
    Button bt_sava,bt_cancel;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏，节省屏幕空间
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_new_pay);

        //绑定控件
        initView();
        //保存按钮功能的实现
        btnSave();

        //取消按钮功能的实现
        btnCancel();
    }

    //绑定控件代码
    private void initView() {
        et_money=findViewById(R.id.et_money_newout);
        et_time=findViewById(R.id.et_time_newout);
        sp_type=findViewById(R.id.sp_type_newout);
        et_payer=findViewById(R.id.et_payer_newout);
        et_remark=findViewById(R.id.et_remark_newout);
        bt_sava=findViewById(R.id.bt_save_newout);
        bt_cancel=findViewById(R.id.bt_cancel_newout);
        mhelper=new MyDBHelper(NewPayActivity.this);
        db=mhelper.getWritableDatabase();
    }

    //保存按钮功能的实现代码
    private void btnSave() {
        bt_sava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的内容保存到数据库的收入表中
                ContentValues values=new ContentValues();
                values.put("outmoney",et_money.getText().toString());
                values.put("outtime",et_time.getText().toString());
                values.put("outtype",sp_type.getSelectedItem().toString());
                values.put("outpayee",et_payer.getText().toString());
                values.put("outremark",et_remark.getText().toString());
                db.insert("pay_out",null,values);
                Toast.makeText(NewPayActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                //刷新本页面
                Intent intent=new Intent(NewPayActivity.this,NewPayActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //取消按钮功能的实现代码
    private void btnCancel() {
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewPayActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
