package com.example.check;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class crud extends AppCompatActivity{

    DatabaseHelper myDB;

    EditText editTextTitle, editTextPlace, editTextTime, editTextContent;
    Button buttonInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud);
        setContentView(R.layout.crud);

        myDB = new DatabaseHelper(this);

        editTextTitle = findViewById(R.id.crud_title);
        editTextPlace = findViewById(R.id.crud_place);
        editTextTime =  findViewById(R.id.crud_time);
        editTextContent = findViewById(R.id.crud_content);

        buttonInsert = findViewById(R.id.crud_submit);

        AddData();

        //submit 버튼 클릭 시 main으로 화면 전환
        Button submitButton = (Button) findViewById(R.id.crud_submit);
        submitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //일정 추가하기
    public void AddData() {
        buttonInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                boolean isInserted = myDB.insertData(
                        editTextTitle.getText().toString(),
                        editTextPlace.getText().toString(),
                        editTextTime.getText().toString(),
                        editTextContent.getText().toString()
                );

                if(isInserted == true)
                    Toast.makeText(crud.this, "일정이 추가되었습니다.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(crud.this, "일정 추가에 실패했습니다", Toast.LENGTH_LONG).show();
            }
        });
    }
}