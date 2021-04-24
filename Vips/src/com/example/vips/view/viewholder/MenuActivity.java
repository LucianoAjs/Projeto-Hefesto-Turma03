package com.example.vips.view.viewholder;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.app.PhonebookManager;
import android.content.Context;
import com.example.vips.R;
import android.app.PhonebookManager;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import com.google.android.material.textfield.TextInputEditText;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays; 

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity
{
    private PhonebookManager phonebook;
    List<String> contactList;
    RecyclerView recyclerView;
    ContactAdapter adapter;
    EditText e_number;
    Button bt_save;
    Button bt_search;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.phonebook = (PhonebookManager) this.getSystemService("phonebook");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        showNumbersFromDatabase();

        e_number = (EditText) findViewById(R.id.e_Number);
        bt_save = (Button) findViewById(R.id.btn_save);
        bt_search = (Button) findViewById(R.id.btn_update);



        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = e_number.getText().toString().trim();

                if (number.isEmpty() ) {
                    Toast.makeText(MenuActivity.this, "Digite algum número para salvar", Toast.LENGTH_SHORT).show();
                } else {
                    MenuActivity.this.phonebook.setNumber(number);
                    ((EditText) findViewById(R.id.e_Number)).setText("");
                    Toast.makeText(MenuActivity.this, "Número Salvo", Toast.LENGTH_SHORT).show();
                    adapter.reloadContactsFromDatabase();
                }
            }
        });


        bt_search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String number = e_number.getText().toString().trim();

                if (number.isEmpty() ) {
                    adapter.reloadContactsFromDatabase();
                } else {
                    adapter.realoadGetOneFromDatabase(number);
                }
            }
        });
    
    }

    private void showNumbersFromDatabase() {
        List<String> contactList = new ArrayList<String>();
        for (String text:MenuActivity.this.phonebook.getAllNumbers()) {
            contactList.add(text);
        }

        if (contactList.isEmpty()) {
            Toast.makeText(this, "Nenhum número encontrado no banco de dados", Toast.LENGTH_SHORT).show();
        }
        adapter = new ContactAdapter(this, contactList, MenuActivity.this.phonebook);

        recyclerView.setAdapter(adapter);

        adapter.reloadContactsFromDatabase();

    }
}