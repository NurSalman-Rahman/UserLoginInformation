package com.example.login_userinformation;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBtn;
    TextView gotoLoginBtn;

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private EditText regName, regPhone, regGmail, regPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initFunction();
        initComponent();
        initListener();

    }

    private void initListener() {

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = openHelper.getWritableDatabase();
                String fname = regName.getText().toString().trim();
                String fPhone = regPhone.getText().toString().trim();
                String fGmail = regGmail.getText().toString().trim();
                String fPassword = regPassword.getText().toString().trim();

                if (fname.isEmpty() || fPassword.isEmpty() || fGmail.isEmpty() || fPhone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(fname, fPhone, fGmail, fPassword);
                    Toast.makeText(com.example.login_userinformation.RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                }
            }
        });

        gotoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.login_userinformation.RegisterActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void initFunction() {
        openHelper = new DatabaseHelper(this);
    }

    private void initComponent() {
        registerBtn = findViewById(R.id.btnRegLogin);
        gotoLoginBtn = findViewById(R.id.btnGotoLogin);
        regName = findViewById(R.id.etRegName);
        regPhone = findViewById(R.id.etRegPhone);
        regGmail = findViewById(R.id.etRegGmail);
        regPassword = findViewById(R.id.etRegPassword);
    }

    public void insertData(String fname, String fPhone, String fGmail, String fPassword) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, fname);
        contentValues.put(DatabaseHelper.COL_3, fPhone);
        contentValues.put(DatabaseHelper.COL_4, fGmail);
        contentValues.put(DatabaseHelper.COL_5, fPassword);

        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }
}
