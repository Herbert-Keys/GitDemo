package com.herbert.demoapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ContentProviderActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt_add, bt_query,bt_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        bt_add = findViewById(R.id.bt_add);
        bt_query = findViewById(R.id.bt_query);
        bt_delete=findViewById(R.id.bt_delete);
        bt_add.setOnClickListener(this);
        bt_query.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                insert();
                break;
            case R.id.bt_query:
                search();
                break;
            case R.id.bt_delete:
                delete();
                break;
            default:
                break;
        }
    }

    private void delete() {
        ContentResolver contentResolver = getContentResolver();
               Uri uri = Uri
                       .parse("content://com.herbert.server.PersonContentProvider/person");
               contentResolver.delete(uri, null, null);
    }

    private void search() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri
                .parse("content://com.herbert.server.PersonContentProvider/person");
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.i("MyTest",
                    "--->>"+cursor.getInt(cursor.getColumnIndex("id"))
                            + cursor.getString(cursor.getColumnIndex("name"))
            +cursor.getString(cursor.getColumnIndex("address")));
        }
    }

    private void insert() {
        // 使用内容解析者ContentResolver访问内容提供者ContentProvider
        ContentResolver contentResolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "小哥");
        values.put("address", "北京");
        // content://authorities/person
        // http://
        Uri uri = Uri
                .parse("content://com.herbert.server.PersonContentProvider/person");
        contentResolver.insert(uri, values);
    }
}
