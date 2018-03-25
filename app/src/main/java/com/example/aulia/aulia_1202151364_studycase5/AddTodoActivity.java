package com.example.aulia.aulia_1202151364_studycase5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Awl on 25/03/2018.
 */

public class AddTodoActivity extends AppCompatActivity {
    EditText name, description, priority;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        //deklarasi objek yang digunakan
        name = findViewById(R.id.edName);
        description = findViewById(R.id.edDesc);
        priority = findViewById(R.id.edPriority);
        db = new DatabaseHandler(this);
    }

    //Method ketika tombol back ditekan
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddTodoActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    //Method ketika tombol tambah ditekan
    public void AddTodo(View view) {
        if (db.insertdata(new ToDoModel(name.getText().toString(), description.getText().toString(), priority.getText().toString()))) {
            Toast.makeText(this, "Todo added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        } else {
            Toast.makeText(this, "Adding todo failed", Toast.LENGTH_SHORT).show();
            name.setText(null);
            description.setText(null);
            priority.setText(null);
        }
    }
}
