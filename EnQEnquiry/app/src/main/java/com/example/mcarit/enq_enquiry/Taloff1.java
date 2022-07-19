package com.example.mcarit.enq_enquiry;

import com.example.mcarit.enq_enquiry.R;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;




public class Taloff1 extends AppCompatActivity {
    TextView rec_fileno, rop, msg;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Button sendSMS;
    EditText phoneNumber;


    TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taloff1);




        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

        rop = (TextView) findViewById(R.id.rop);

        msg = (TextView) findViewById(R.id.msg1);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "AnjaliOldLipi.ttf");

        rop.setTypeface(typeFace);
        String str2=rop.getText().toString();


        text = (TextView) findViewById(R.id.txtt);
        registerForContextMenu(text);


        // create the get Intent object
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        final Intent intent = getIntent();
        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        final String str = intent.getStringExtra("message_key");
        // display the string into textView
        // rec_fileno.setText(str);

        Intent iint = getIntent();
        final String strr = iint.getStringExtra("message_key1");





        // display the string into textView
        //  rop.setText(strr);

      /*  String str1="പ്രിയപ്പെട്ട ഉപഭോക്താവേ താങ്കളുടെ ഫയൽ നമ്പർ : ";
        String str2=" ലെ അപേക്ഷയുടെ നിലവിലെ സ്ഥിതി പരിശോധിച്ചു. ";
        String str3=" - പാലാ താലൂക്ക് ഓഫീസ്";*/
        Spanned ss = Html.fromHtml(getString(R.string.str3));

      /*  SpannableString ss =new SpannableString("റവന്യൂ വകുപ്പ് - സമ്പൂർണ്ണ ഡിജിറ്റലൈസേഷനിലേക്ക് ");
        StyleSpan style=new StyleSpan(Typeface.BOLD_ITALIC);
        ss.setSpan(style,0,32,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
*/
        msg.setText("പ്രിയപ്പെട്ട ഉപഭോക്താവേ താങ്കളുടെ ഫയൽ നമ്പർ : "+ str + "ലെ അപേക്ഷയുടെ നിലവിലെ സ്ഥിതി പരിശോധിച്ചു. " + strr);





        final String msg = "\""+ss +"\"\n\n\n പ്രിയപ്പെട്ട ഉപഭോക്താവേ താങ്കളുടെ ഫയൽ നമ്പർ : " + str + "ലെ അപേക്ഷയുടെ നിലവിലെ സ്ഥിതി പരിശോധിച്ചു." + strr + "\n\n - റവന്യൂ ഡിവിഷണൽ ഓഫീസ് പാലാ";
        // final String msg="Dear Applicant , your enquiry request on file no. :"+str+". "+strr;

        sendSMS = (Button) findViewById(R.id.sendSMS);
        sendSMS.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {



                String phoneNum = phoneNumber.getText().toString();

                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                System.out.println(formattedDate);

               Intent intent2=new Intent(Taloff1.this,UsersListActivity.class);
                intent2.putExtra("fileno",str);
                intent2.putExtra("reply",strr);
                intent2.putExtra("phone",phoneNum);
                intent2.putExtra("date",formattedDate);
                startActivity(intent2);

                System.out.println("\n"+str+"\n"+strr+"\n"+phoneNum+"\n"+formattedDate);


                boolean valid = false;

                if (phoneNum.equals("")) {
                    Toast.makeText(Taloff1.this, "Add  Phone Number", Toast.LENGTH_LONG).show();
                } else {
                    if (phoneNum.length() > 9) {
                        valid = true;
                        phoneNumber.setError(null);
                    } else {
                        valid = false;
                        phoneNumber.setError("Add   Valid Phone Number!");
                    }
                }


                if (!TextUtils.isEmpty(msg) && !TextUtils.isEmpty(phoneNum)) {
                    if (checkPermission()) {
                        SmsManager smsManager = SmsManager.getDefault();
                        sendSMS(phoneNum, msg);


                    } else {
                        Toast.makeText(Taloff1.this, "Permission denied", Toast.LENGTH_LONG).show();
                    }
                }
            }

        });
    }

    protected void sendSMS(String phoneNum, String msg) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";


        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:

                        Intent intent = new Intent(Taloff1.this, taloff3.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "SMS Failed... ",
                                Toast.LENGTH_LONG).show();
                        // Intent intet = new Intent(Taloff1.this, taloff3.class);
                        //  startActivity(intet);
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));
        SmsManager sms = SmsManager.getDefault();

        ArrayList<String> messagelist = sms.divideMessage(msg);


        if (messagelist.size() == 1)
        {
            String msgg = messagelist.get(0);
            sms.sendTextMessage(phoneNum, null, msgg, sentPI, deliveredPI);
        }
        else
        {
            ArrayList<PendingIntent> sentPis = new ArrayList<PendingIntent>();
            ArrayList<PendingIntent> delPis = new ArrayList<PendingIntent>();

            int ct = messagelist.size();
            for (int i = 0; i < ct; i++)
            {
                sentPis.add(i, sentPI);
                delPis.add(i, deliveredPI);
            }

            sms.sendMultipartTextMessage(phoneNum, null, messagelist, sentPis, delPis);
        }





     /*   sms.sendMultipartTextMessage(phoneNum, null,
                messagelist, null, null);

        sms.sendTextMessage(phoneNum, null, msg, sentPI, deliveredPI);
*/

    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(Taloff1.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);
    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(Taloff1.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(Taloff1.this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                    Button sendSMS = (Button) findViewById(R.id.sendSMS);
                    sendSMS.setEnabled(false);

                }
                break;
        }
    }

}
