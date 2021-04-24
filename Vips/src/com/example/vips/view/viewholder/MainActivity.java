package com.example.vips.view.viewholder;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.app.PhonebookManager;
import android.content.Context;
import com.example.vips.R;

import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View.OnClickListener;
import com.google.android.material.textfield.TextInputEditText;
import android.view.LayoutInflater;


public class MainActivity extends AppCompatActivity
{


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.buttonMenu);
        button.setOnClickListener((OnClickListener)(new OnClickListener() {
            public void onClick(View v) {
                String login = ((TextInputEditText)findViewById(R.id.textInputEditTextLogin2)).getText().toString();
                String password = ((TextInputEditText)findViewById(R.id.textInputEditTextSenha2)).getText().toString();
                if (login.equals("admin") && password.equals("admin")) {
                    
                    Intent intent = new Intent((Context)MainActivity.this, MenuActivity.class);
                    MainActivity.this.startActivity(intent);
                    return;
                } else {
                    showtoast("User or password incorrect.");
                }
            }
        }));
    }


    private void showtoast(String message){
        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text_toast);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}