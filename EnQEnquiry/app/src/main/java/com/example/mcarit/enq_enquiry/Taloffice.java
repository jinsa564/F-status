package com.example.mcarit.enq_enquiry;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Intent.ACTION_CALL;

public class Taloffice extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final int PICK_PHONE = 2022;
    private static final int PERMISSION_REQUEST_CODE = 1;

    EditText fileno, reply;
    Button next, addreply;

    Spinner spinner;
    String str1, str, text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taloffice);

        Toast.makeText(getApplicationContext(), "Successully logged in !!!", Toast.LENGTH_SHORT).show();
        spinner = findViewById(R.id.spinner);
        next = (Button) findViewById(R.id.next);
        fileno = (EditText) findViewById(R.id.fileno);
        addreply = (Button) findViewById(R.id.btn1);
        reply = (EditText) findViewById(R.id.txt);


        addreply.setVisibility(View.INVISIBLE);
        reply.setVisibility(View.INVISIBLE);

        next.setVisibility(View.INVISIBLE);

        String[] replies = new String[]{
                " Choose Reply...",
                "വില്ലേജ് ഓഫീസർക്ക് റിപ്പോർട്ടിനായി \nനൽകിയിട്ടുണ്ട്",
                " കൃഷി ഓഫീസർക്ക് റിപ്പോർട്ടിനായി\n നൽകിയിട്ടുണ്ട്",
                " റിപ്പോർട്ടിലെ അപാകത പരിഹരിക്കുന്നതിനായി\n വില്ലേജ് ഓഫീസർക്ക് തിരികെ നൽകിയിട്ടുള്ളതാണ്",
                "റിപ്പോർട്ടിലെ അപാകത പരിഹരിക്കുന്നതിനായി \nകൃഷി  ഓഫീസർക്ക് തിരികെ നൽകിയിട്ടുള്ളതാണ്. ",
                " ഫീസ് അടയ്ക്കുന്നതിന് \nകത്ത് അയച്ചിട്ടുള്ളതാണ്.",
                " ഉത്തരവ് വില്ലേജ് ഓഫീസിലേക്ക് \nഅയച്ചിട്ട് ഉള്ളതാണ് താങ്കൾക്ക് കൈപ്പറ്റാവുന്നതാണ്",
                "റിപ്പോർട്ടിലെ അപാകത പരിഹരിക്കുന്നതിനായി\n തഹസിൽദാർക്ക്  തിരികെ നൽകിയിട്ടുള്ളതാണ്.",
                " റിപ്പോർട്ട് ജില്ലാ കളക്ടർക്ക് \nസമർപ്പിച്ചിട്ടുള്ളത് ആണ്",
                " താങ്കളുടെ ഫയലിൽ \nവിചാരണ നിശ്ചയിച്ചിട്ടുള്ളതാണ്",
                "താങ്കളുടെ ഫയലിൽ  സ്ഥലപരിശോധന \nനിശ്ചയിച്ചിട്ടുള്ളതാണ്",
                "ഉത്തരവ് തപാലിൽ \nഅയച്ചിട്ടുള്ളതാണ്",
                "  പരാതി തുടർനടപടികൾക്കായി ബന്ധപ്പെട്ട \nപഞ്ചായത്ത് സെക്രട്ടറിക്ക് നൽകിയിട്ടുള്ളതാണ്",
                " താങ്കളുടെ അപേക്ഷ മുൻഗണനാക്രമത്തിൽ \nനടപടി സ്വീകരിച്ചു വരുന്നു.",
                "കൃഷി ഓഫീസർക്കും വില്ലേജ് ഓഫീസർക്കും റിപ്പോർട്ടിനായി \nഅയച്ചിട്ടുണ്ട്.",

                "Others",

        };
      /*  String[] replies = new String[]{
                " Choose Reply...",
                "Pay a fee of Rupees 150",
                "Application processed successfully .collect certificate from office",
                "Others",

        };*/

        final List<String> replylist = new ArrayList<>(Arrays.asList(replies));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, replylist) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }


            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String[] Text = {(String) parent.getItemAtPosition(position)};

                addreply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        str1 = reply.getText().toString();
                        if (str1.equals("")) {
                            Toast.makeText(Taloffice.this, "Add Valid Reply", Toast.LENGTH_LONG).show();
                        }


                        // adapter.add(str1);
                        else {
                            str = fileno.getText().toString();
                            if (str.equals("")) {
                                Toast.makeText(Taloffice.this, "Add Valid File Number", Toast.LENGTH_LONG).show();
                            } else {
                                Text[0] = str1;
                                Intent intent = new Intent(Taloffice.this, Taloff1.class);
                                intent.putExtra("message_key", str);
                                intent.putExtra("message_key1", Text[0]);
                                startActivity(intent);
                            }

                        }

                    }
                });

                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        str = fileno.getText().toString();
                        if (str.equals("")) {
                            Toast.makeText(Taloffice.this, "Add Valid File Number", Toast.LENGTH_LONG).show();
                        } else {

                            Intent intent = new Intent(Taloffice.this, Taloff1.class);
                            intent.putExtra("message_key", str);
                            intent.putExtra("message_key1", Text[0]);
                            startActivity(intent);
                        }


                    }
                });


                // If user change the default selection
                // First item is disable and it is used for hint
                if (position > 0) {
                    // Notify the selected item text
                }

                if (Text[0] == "Others") {

                    reply.setVisibility(View.VISIBLE);
                    addreply.setVisibility(View.VISIBLE);
                    next.setVisibility(View.INVISIBLE);
                }

                if (Text[0] != "Others" && Text[0] != " Choose Reply...") {
                    next.setVisibility(View.VISIBLE);
                }
                if (Text[0] != "Others") {
                    reply.setVisibility(View.INVISIBLE);
                    addreply.setVisibility(View.INVISIBLE);
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
    }*/
    public boolean onOptionsItemSelected(MenuItem item) {
        int id1 = item.getItemId();
        switch (id1) {
            case R.id.item1:
                Intent i = new Intent(Taloffice.this, taloff4.class);
                startActivity(i);

                return true;
            case R.id.item2:
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, PICK_PHONE);


                return true;
            case R.id.item3:
                Intent ii = new Intent(Taloffice.this, UsersListActivity.class);
                startActivity(ii);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == PICK_PHONE && resultCode == RESULT_OK) {
            Uri phoneUri = intent.getData();
            Cursor cur = getContentResolver().query(phoneUri, new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            if (cur != null && cur.moveToFirst()) {
                String name = cur.getString(0);
                String number = cur.getString(1);
                Log.d("PHONE-PICKER", "User picker: " + name + " - " + number);
                call(number);
                cur.close();
            }
        }
    }

    private void call(String num) {

        String phoneNum = num;
        if (!TextUtils.isEmpty(phoneNum)) {
            String dial = "tel:" + phoneNum;
            startActivity(new Intent(ACTION_CALL, Uri.parse(dial)));
        } else {
            Toast.makeText(Taloffice.this, "Please enter a valid telephone number", Toast.LENGTH_SHORT).show();
        }

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(Taloffice.this, new String[]
                {
                        //  Manifest.permission.READ_CONTACTS,
                        Manifest.permission.CALL_PHONE
                }, PERMISSION_REQUEST_CODE);
    }


    public boolean checkPermission() {

        int CallPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE);
        // int ContactPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS);

        return CallPermissionResult == PackageManager.PERMISSION_GRANTED;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(Taloffice.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(Taloffice.this,
                            "Permission denied", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


}




   /* public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = fileno.getText().toString();

                Intent intent = new Intent(Taloffice.this, Taloff1.class);
                intent.putExtra("message_key", str);
                intent.putExtra("message_key1", text);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}
*/
