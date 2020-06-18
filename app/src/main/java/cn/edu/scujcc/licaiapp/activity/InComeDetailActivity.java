package cn.edu.scujcc.licaiapp.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.List;
import cn.edu.scujcc.licaiapp.R;
import cn.edu.scujcc.licaiapp.adapter.IncomeAdapter;
import cn.edu.scujcc.licaiapp.bean.IncomeBean;
import cn.edu.scujcc.licaiapp.db.MyDBHelper;

public class InComeDetailActivity extends AppCompatActivity {
//定义对象
    RecyclerView recy_view;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    List<IncomeBean> arr1=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏，节省屏幕空间
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_in_come_detail);
        //绑定控件
        initView();

        //准备数据
        initData();

        //设计每一行的子布局

        //定义适配器： 数据和子布局关联起来（桥梁的作用）
        IncomeAdapter adapter=new IncomeAdapter(InComeDetailActivity.this,arr1);

        //将适配器和布局管理器加载到控件当中
        StaggeredGridLayoutManager st=new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL,1);
        recy_view.setLayoutManager(st);
        recy_view.setAdapter(adapter);
    }

    //绑定控件代码
    private void initView() {
        recy_view=findViewById(R.id.recy_view_indetail);
        mhelper=new MyDBHelper(InComeDetailActivity.this);
        db=mhelper.getWritableDatabase();
    }

    //准备数据代码
    private void initData() {
        //从数据库查询所有的新增收入信息,取出数据
        Cursor cursor=db.rawQuery("select * from in_come",null);
      while(cursor.moveToNext()){
          int myid=cursor.getInt(cursor.getColumnIndex("id"));
          double mymoney=cursor.getDouble(cursor.getColumnIndex("inmoney"));
          String mytime=cursor.getString(cursor.getColumnIndex("intime"));
          String mytype=cursor.getString(cursor.getColumnIndex("intype"));
          String mypayer=cursor.getString(cursor.getColumnIndex("inpayer"));
          String myremark=cursor.getString(cursor.getColumnIndex("inremark"));
          IncomeBean incomeBean=new IncomeBean( myid,mymoney,mytime,mytype,mypayer,myremark);
          arr1.add(incomeBean);
      }

    }

}
