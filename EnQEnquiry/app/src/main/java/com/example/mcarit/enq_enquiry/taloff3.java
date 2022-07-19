package com.example.mcarit.enq_enquiry;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class taloff3 extends AppCompatActivity {




       // private static final int PERMISSION_REQUEST_CODE = 1;
        //ListView listView ;
       // ArrayList<String> contactsArray ;
        //ArrayAdapter<String> arrayAdapter ;
       // Button contactsButton;
        Button exit;
        //Cursor cursor ;
        String name; //contactNumber ;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_taloff3);


            exit = (Button) findViewById(R.id.ext);


            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(taloff3.this);
                    alertDialogBuilder.setTitle("Exit Application?");
                    alertDialogBuilder.setMessage("Are you sure??").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    })

                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            });








         /*   if (Build.VERSION.SDK_INT >= 23) {
                if (checkPermission()) {
                    Log.e("permission", "Permission already granted.");
                } else {
                    requestPermission();
                }
            }
           listView = (ListView)findViewById(R.id.listview);
            contactsArray = new ArrayList<String>();
            contactsButton = (Button)findViewById(R.id.cntct);
            contactsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddContactstoArray();
                    arrayAdapter = new ArrayAdapter<String>(
                            taloff3.this,
                            R.layout.contact_listview,
                            R.id.textView, contactsArray
                    );
                    listView.setAdapter(arrayAdapter);
                }
            });
        }
        public void AddContactstoArray(){
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsArray.add(name + " "  + ":" + " " + contactNumber);
            }
            cursor.close();
        }
        public boolean checkPermission() {
            int CallPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE);
            int ContactPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS);
            return CallPermissionResult == PackageManager.PERMISSION_GRANTED &&
                    ContactPermissionResult == PackageManager.PERMISSION_GRANTED;
        }
        private void requestPermission() {
            ActivityCompat.requestPermissions(taloff3.this, new String[]
                    {
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.CALL_PHONE
                    }, PERMISSION_REQUEST_CODE);
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
            switch (requestCode) {
                case PERMISSION_REQUEST_CODE:
                    callButton = (Button)findViewById(R.id.call);
                    if (grantResults.length > 0) {
                        boolean CallPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                        boolean ReadContactsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                        if (CallPermission && ReadContactsPermission) {
                            Toast.makeText(taloff3.this,
                                    "Permission accepted", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(taloff3.this,
                                    "Permission denied", Toast.LENGTH_LONG).show();
                            callButton.setEnabled(false);
                            contactsButton.setEnabled(false);
                        }
                        break;
                    }
            }
        }
        public void call(View view)
        {
            final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
            String phoneNum = phoneNumber.getText().toString();
            if(!TextUtils.isEmpty(phoneNum)) {
                String dial = "tel:" + phoneNum;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }else {
                Toast.makeText(taloff3.this, "Please enter a valid telephone number", Toast.LENGTH_SHORT).show();
            }*/
        }

    @Override
    public void onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        /* your specific things...*/

        Intent intent2=new Intent(taloff3.this,Taloffice.class);
        startActivity(intent2);
        super.onBackPressed();

        finish();
    }


    }





