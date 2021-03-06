package com.herbert.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 这是我添加的备注
     * 1.创建分支1
     * 2.创建分支2
     * 3.分支1做了一些修改
     * 4.master 分支修改了一点东西
     * 5.master 修改了，我不能保存，需要去其他分支处理东西，啦啦啦，嘻嘻嘻，嘿嘿嘿
     * 6.bug修改完了
     */
    Button bt_aidl,bt_content,bt_cp,bt_launch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_aidl=findViewById(R.id.bt_aidl);
        bt_content=findViewById(R.id.bt_contentprovider);
        bt_cp=findViewById(R.id.bt_cp);
        bt_launch=findViewById(R.id.bt_launch);
        bt_aidl.setOnClickListener(this);
        bt_content.setOnClickListener(this);
        bt_cp.setOnClickListener(this);
        bt_launch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_aidl:
                startActivity(new Intent(this,AIDLActivity.class));
                break;
            case R.id.bt_contentprovider:
                startActivity(new Intent(this,ContactsActivity.class));
                break;
            case R.id.bt_cp:
                startActivity(new Intent(this,ContentProviderActivity.class));
                break;
            case R.id.bt_launch:
                Intent intent=getPackageManager().getLaunchIntentForPackage("com.herbert.server");
                if (intent!=null){
                    intent.putExtra("name","二哈");
                    intent.putExtra("age","18");
                    startActivity(intent);
                }
                break;
        }
    }
}
