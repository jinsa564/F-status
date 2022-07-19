package com.example.mcarit.enq_enquiry;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Intent.ACTION_CALL;

public class taloff4 extends AppCompatActivity {


    private static final int PERMISSION_REQUEST_CODE = 1;

    Button callButton,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taloff4);
        callButton = (Button) findViewById(R.id.call);

        exit = (Button) findViewById(R.id.ext);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(taloff4.this);
                alertDialogBuilder.setTitle("Exit Application?");
                alertDialogBuilder
                        .setMessage("Are you sure??")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
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

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                call();
            }
        });
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

    }

    private void call() {

        final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        String phoneNum = phoneNumber.getText().toString();
        if (!TextUtils.isEmpty(phoneNum)) {
            String dial = "tel:" + phoneNum;
            startActivity(new Intent(ACTION_CALL, Uri.parse(dial)));
        } else {
            Toast.makeText(taloff4.this, "Please enter a valid telephone number", Toast.LENGTH_SHORT).show();
        }

    }

    private void requestPermission () {
        ActivityCompat.requestPermissions(taloff4.this, new String[]
                {
                        //  Manifest.permission.READ_CONTACTS,
                        Manifest.permission.CALL_PHONE
                }, PERMISSION_REQUEST_CODE);
    }


    public boolean checkPermission () {

        int CallPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE);
        // int ContactPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS);

        return CallPermissionResult == PackageManager.PERMISSION_GRANTED;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(taloff4.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(taloff4.this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                    callButton = (Button) findViewById(R.id.call);
                    callButton.setEnabled(false);

                }
                break;
        }
    }




    /*    public void call (View view)
        {
            final EditText phoneNumber = (EditText) findViewById(R.id.phoneNumber);
            String phoneNum = phoneNumber.getText().toString();
            if (!TextUtils.isEmpty(phoneNum)) {
                String dial = "tel:" + phoneNum;
                startActivity(new Intent(ACTION_CALL, Uri.parse(dial)));
            } else {
                Toast.makeText(taloff4.this, "Please enter a valid telephone number", Toast.LENGTH_SHORT).show();
            }

        }
*/



}
