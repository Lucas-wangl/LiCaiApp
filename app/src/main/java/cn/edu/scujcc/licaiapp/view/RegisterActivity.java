package cn.edu.scujcc.licaiapp.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import cn.edu.scujcc.licaiapp.R;
import cn.edu.scujcc.licaiapp.db.MyDBHelper;

public class RegisterActivity extends AppCompatActivity {
//定义对象
TextInputLayout et_name,et_pwd,et_email,et_phone;
    Button btn_register,btn_cancel;
    MyDBHelper mhelper;//创建一个数据库类文件
    SQLiteDatabase db;//创建一个可以操作的数据库对象


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏，节省屏幕空间
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_register);

        //绑定控件
        initView();


        //注册按钮功能
        btnRegister();

        //取消按钮功能
        btnCancel();
    }

    //绑定控件代码
    private void initView() {
        et_name=findViewById(R.id.r_username);
        et_pwd=findViewById(R.id.r_password1);
        et_email=findViewById(R.id.r_email);
        et_phone=findViewById(R.id.r_phone);
        btn_register=findViewById(R.id.register_button);
        btn_cancel=findViewById(R.id.cancel_button);
        mhelper=new MyDBHelper(RegisterActivity.this);//实例化数据库帮助类对象
        db=mhelper.getWritableDatabase();//创建数据库并获取数据库的读写权限
    }

    //注册按钮功能的实现代码
    private void btnRegister() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个对象，用来封装一行数据
                ContentValues values=new ContentValues();
                values.put("name",et_name.getEditText().getText().toString());//将输入的用户名放到name列
                values.put("pwd",et_pwd.getEditText().getText().toString());//将输入的密码放到pwd列
                values.put("email",et_email.getEditText().getText().toString());//将输入的邮箱放到email列
                values.put("phone",et_phone.getEditText().getText().toString());//将输入的电话放到phone列

                //将封装好的一行数据保存到数据库的tb_userinfo表中
                db.insert("tb_userinfo",null,values);
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }
        });

    }

    //取消按钮功能的实现代码
    private void btnCancel() {
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
