package com.example.aulia.aulia_1202151364_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<ToDoModel> listitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Deklarasi objek yang akan digunakan
        recyclerView = findViewById(R.id.todolist);
        listitem = new ArrayList<>();

        //Mengambil kembali data dari database
        db = new DatabaseHandler(this);
        db.getAllItems(listitem);

        //Mengambil kembali SharedPreference
        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("pref", 0);
        int warna = pref.getInt("background", R.color.white);

        //Menentukan adapter untuk recyclerview_todolist
        adapter = new RecyclerViewAdapter(this, listitem, warna);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //Menjalankan method
        initswipe();
    }

    //Method untuk menambahkan ItemTouchHelper pada RecyclerView
    public void initswipe(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            //swiped untuk hapus item
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                ToDoModel cur = adapter.getItem(pos);

                if(direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                    if(db.deletedata(cur.getNameTodo())) {
                        adapter.remove(pos);
                        Snackbar.make(findViewById(R.id.root), "Item dihapus", 1500).show();
                    }
                }
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, ColorSetting.class));
            finish();
        }
        return true;
    }

    //untuk menambahkan data
    public void addFab(View view) {
        Intent intent = new Intent(MainActivity.this, AddTodoActivity.class );
        startActivity(intent);
        finish();
    }
}
