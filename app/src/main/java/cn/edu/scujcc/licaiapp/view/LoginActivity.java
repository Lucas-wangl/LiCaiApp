package cn.edu.scujcc.licaiapp.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import cn.edu.scujcc.licaiapp.R;
import cn.edu.scujcc.licaiapp.db.MyDBHelper;

public class LoginActivity extends AppCompatActivity {
//定义对象
    TextInputLayout et_name,et_pwd;
    Button btn_newregister,btn_login;
   MyDBHelper mhelper;
   SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏，节省屏幕空间
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_login);
        //绑定控件
        initView();

        //登录按钮功能的实现
        btnLogin();

        //新用户注册按钮功能的实现
        btnNewRegister();
    }

    // 绑定控件代码
    private void initView() {
        et_name=findViewById(R.id.username);
        et_pwd=findViewById(R.id.password);
        btn_newregister=findViewById(R.id.register_button);
        btn_login=findViewById(R.id.login_button);
        mhelper=new MyDBHelper(LoginActivity.this);
        db=mhelper.getWritableDatabase();
    }


    //登录按钮功能的实现代码
    private void btnLogin() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //首先：获取输入的用户名和密码
                String inputname=et_name.getEditText().getText().toString();
                String inputpwd=et_pwd.getEditText().getText().toString();
                //其次：对获取的用户名和密码进行判断
                if(inputname.equals("")||inputpwd.equals("")){//用户名或密码为空
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }else{//用户名或密码不为空时，我们再对输入的正确性进行判断。
                    // 根据输入的用户名和密码从数据库中查询
                    Cursor cursor =db.rawQuery("select * from tb_userinfo where name=? and pwd=?",new String[]{inputname,inputpwd});
                    //根据查询到的结果进行判断
                   if (cursor.moveToNext()){//查询到时
                       String getname=cursor.getString(cursor.getColumnIndex("name"));
                       String getpwd=cursor.getString(cursor.getColumnIndex("pwd"));
                       if(inputname.equalsIgnoreCase(getname)&&inputpwd.equalsIgnoreCase(getpwd)){
                           SharedPreferences.Editor editor=getSharedPreferences("userinfo",0).edit();
                           editor.putString("username",inputname);
                           editor.putString("userpwd",inputpwd);
                           editor.commit();
                           Toast.makeText(LoginActivity.this,"用户名和密码正确，欢迎登陆",Toast.LENGTH_SHORT).show();
                           Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                           startActivity(intent);
                           finish();
                       }
                   }else{//没有查询到结果时
                       Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                       et_name.getEditText().setText("");
                       et_pwd.getEditText().setText("");
                   }
                }
            }
        });

    }

    //新用户注册按钮功能的实现代码
    private void btnNewRegister() {
        btn_newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


}
