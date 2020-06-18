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
import cn.edu.scujcc.licaiapp.adapter.OutpayAdapter;
import cn.edu.scujcc.licaiapp.bean.OutpayBean;
import cn.edu.scujcc.licaiapp.db.MyDBHelper;

public class PayDetailActivity extends AppCompatActivity {
//定义对象
RecyclerView recy_view;
    MyDBHelper mhelper;
    SQLiteDatabase db;
    List<OutpayBean> arr1=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏，节省屏幕空间
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_pay_detail);

        //绑定控件
        initView();

        //准备数据
        initData();

        //设计每一行的子布局

        //创建适配器
        OutpayAdapter adapter=new OutpayAdapter(PayDetailActivity.this,arr1);

        //将适配器和布局管理器加载到控件当中
        StaggeredGridLayoutManager st=new StaggeredGridLayoutManager(StaggeredGridLayoutManager.VERTICAL,1);
        recy_view.setLayoutManager(st);
        recy_view.setAdapter(adapter);

    }

    //绑定控件代码
    private void initView() {
        recy_view=findViewById(R.id.recy_view_outdetail);
        mhelper=new MyDBHelper(PayDetailActivity.this);
        db=mhelper.getWritableDatabase();
    }

    //准备数据代码
    private void initData() {
        //从数据库查询所有的新增收入信息,取出数据
        Cursor cursor=db.rawQuery("select * from pay_out",null);
        while(cursor.moveToNext()){
            int myid=cursor.getInt(cursor.getColumnIndex("id"));
            double mymoney=cursor.getDouble(cursor.getColumnIndex("outmoney"));
            String mytime=cursor.getString(cursor.getColumnIndex("outtime"));
            String mytype=cursor.getString(cursor.getColumnIndex("outtype"));
            String mypayer=cursor.getString(cursor.getColumnIndex("outpayee"));
            String myremark=cursor.getString(cursor.getColumnIndex("outremark"));
            OutpayBean outpayBean=new OutpayBean( myid,mymoney,mytime,mytype,mypayer,myremark);
            arr1.add(outpayBean);
        }

    }

}
