package com.tucay.a1026lab_tucay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    TextView tvOutput;
    Button btnLoadSharedPref, btnLoadInternalStorage, btnClear, btnBack;
    FileInputStream fis;
    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvOutput = (TextView) findViewById(R.id.tv_output);
        btnLoadSharedPref = (Button) findViewById(R.id.btn_loadSharedPref);
        btnLoadInternalStorage = (Button) findViewById(R.id.btn_loadInternalStorage);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnBack = (Button) findViewById(R.id.btn_back);

        preferences = getSharedPreferences("Preferences", MODE_WORLD_READABLE);
    }


    public void LoadInternalStorage(View view) {
        StringBuffer buffer = new StringBuffer();
        int read = 0;

    try{
        fis = openFileInput("output.txt");
        while((read = fis.read()) != -1){
            buffer.append((char)read);
        }
    } catch (FileNotFoundException e){
        e.printStackTrace();
    } catch (IOException e){
        e.printStackTrace();
    }
        tvOutput.setText(buffer.toString());
    }

    public void LoadSharePref(View view) {
        String username = preferences.getString("username","");
        String password = preferences.getString("password","");
        tvOutput.setText("SHARED PREFERENCES\nUsername: " + username + "\nPassword: " + password);
    }

    public void Back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Clear(View view) {
        tvOutput.setText("");
    }
}
