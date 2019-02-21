package com.herbert.demoapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.herbert.server.Book;
import com.herbert.server.MyAidl;

import java.util.List;

/**
 * AIDL测试，服务端和客户端的数据传递
 */
public class AIDLActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt_bindservice,bt_getdata;
    TextView tv_data;
    MyAidl myAidl;
    String data;
    List<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        bt_bindservice=findViewById(R.id.bt_bindservice);
        bt_getdata=findViewById(R.id.bt_getInfo);
        tv_data=findViewById(R.id.tv_info);
        bt_bindservice.setOnClickListener(this);
        bt_getdata.setOnClickListener(this);
    }

    private  ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidl=MyAidl.Stub.asInterface(service);
            try {
                data=myAidl.getString();
                books=myAidl.getBooks();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidl=null;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_bindservice:
                Intent intent=new Intent();
                intent.setAction("com.herbert.server.MyService");
                intent.setPackage("com.herbert.server");
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.bt_getInfo:

                Toast.makeText(this,data+"\n"+books.get(0).getName()+","+books.get(0).getPrice(),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
