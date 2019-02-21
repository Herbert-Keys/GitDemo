package com.herbert.demoapp;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 通过内容提供者获取手机联系人
 */
@RuntimePermissions
public class ContactsActivity extends AppCompatActivity {
    Button bt_getContacts;
    ListView list_contacts;
    List<String> strs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ContactsActivityPermissionsDispatcher.getPermissionWithPermissionCheck(this);
        bt_getContacts = findViewById(R.id.bt_getContacts);
        list_contacts = findViewById(R.id.list_contacts);
        bt_getContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContacts();
            }
        });
    }

    private void getContacts() {
        strs=new ArrayList<>();
        ContentResolver contentResolver = this.getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = contentResolver.query(uri, projection, null, null, null);
//        cursor.moveToFirst();
        while (cursor.moveToNext()){
            //拿到联系人id 跟name
//            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndex("data1"));
            Log.e("contacts_name = " ,name);
            Log.e("contacts_phone = " , phone);
            strs.add(name+":"+phone);
            String[] columnNames = cursor.getColumnNames();
            for (String cName:
                    columnNames) {
                //Log.e("contacts_cName= ",cName);
            }
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,strs);
        list_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ContactsActivity.this,strs.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        list_contacts.setAdapter(arrayAdapter);
    }

    @NeedsPermission({Manifest.permission.READ_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void getPermission() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ContactsActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
