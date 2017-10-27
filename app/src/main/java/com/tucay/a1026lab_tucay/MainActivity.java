package com.tucay.a1026lab_tucay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnSharedPref, btnInternalStorage, btnNext;
    FileOutputStream fos;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnSharedPref = (Button) findViewById(R.id.btn_sharedPref);
        btnInternalStorage = (Button) findViewById(R.id.btn_internalStorage);
        btnNext = (Button) findViewById(R.id.btn_next);
        preferences = getSharedPreferences("Preferences",MODE_WORLD_READABLE);
    }

    public void SharedPref (View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",etUsername.getText().toString());
        editor.putString("password",etPassword.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved Successfully in Shared Preferences", Toast.LENGTH_LONG).show();
    }

    public void InternalStorage (View view) {
        String message = "INTERNAL STORAGE\nUsername: " + etUsername.getText().toString() + "\nPassword: " + etPassword.getText().toString();

        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Message Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public void Next(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
    }