package com.example.check;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    EditText editTextTitle, editTextPlace, editTextTime, editTextContent;
    Button buttonInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        editTextTitle = findViewById(R.id.crud_title);
        editTextPlace = findViewById(R.id.crud_place);
        editTextTime =  findViewById(R.id.crud_time);
        editTextContent = findViewById(R.id.crud_content);

        buttonInsert = findViewById(R.id.crud_submit);

        ViewData();

        Button addCrud = (Button) findViewById(R.id.addCrud);
        addCrud.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), crud.class);
                startActivity(intent);
            }
        });

    }

    private void ViewData() {
        Cursor res = myDB.getAllData();

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append(res.getString(0)+"\n");
            buffer.append("장소 : " + res.getString(1) + "\n");
            buffer.append("시간 : " + res.getString(2) + "\n");
            buffer.append(res.getString(3) + "\n");
        }
    }
}