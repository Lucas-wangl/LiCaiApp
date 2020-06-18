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


public class NewInComeActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_new_in_come);

        //绑定控件
        initView();
        //保存按钮功能的实现
        btnSave();

        //取消按钮功能的实现
        btnCancel();

    }


    //绑定控件代码
    private void initView() {
        et_money=findViewById(R.id.et_money_newin);
        et_time=findViewById(R.id.et_time_newin);
        sp_type=findViewById(R.id.sp_type_newin);
        et_payer=findViewById(R.id.et_payer_newin);
        et_remark=findViewById(R.id.et_remark_newin);
        bt_sava=findViewById(R.id.bt_save_newin);
        bt_cancel=findViewById(R.id.bt_cancel_newin);
        mhelper=new MyDBHelper(NewInComeActivity.this);//实例化数据库帮助类对象
        db=mhelper.getWritableDatabase();//创建数据库并获取数据库的读写权限
    }

    //保存按钮功能的实现代码
    private void btnSave() {
        bt_sava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的内容保存到数据库的收入表中
                ContentValues values=new ContentValues();
                values.put("inmoney",et_money.getText().toString());
                values.put("intime",et_time.getText().toString());
                values.put("intype",sp_type.getSelectedItem().toString());
                values.put("inpayer",et_payer.getText().toString());
                values.put("inremark",et_remark.getText().toString());
                db.insert("in_come",null,values);
                Toast.makeText(NewInComeActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                //刷新本页面
                Intent intent=new Intent(NewInComeActivity.this,NewInComeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // 取消按钮功能的实现代码
    private void btnCancel() {
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewInComeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
