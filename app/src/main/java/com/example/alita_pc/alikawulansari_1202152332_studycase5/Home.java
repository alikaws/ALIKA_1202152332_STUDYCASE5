package com.example.alita_pc.alikawulansari_1202152332_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

import static com.example.alita_pc.alikawulansari_1202152332_studycase5.R.id.home;


public class Home extends AppCompatActivity {
    database db; RecyclerView rc;
    todoadapter adapter; ArrayList<itemtodo> listitemnya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //objek yang akan digunakan
        rc = findViewById(R.id.listtodo);
        listitemnya = new ArrayList<>();

        //mengambil data dari database
        db = new database(this);
        db.getAllItems(listitemnya);

        //mengambil data dari SharedPreferences
        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("pref", 0);
        int warna = pref.getInt("background", R.color.putih);

        //menentukan adapter untuk RecyclerView
        adapter = new todoadapter(this, listitemnya, warna);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);

        //untuk menjalankan method
        initswipe();
    }

    //Method untuk menambahkan ItemTouchHelper pada RecyclerView
    private void initswipe() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Method ketika recyclerview digeser untuk menghapus
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                itemtodo cur = adapter.getItem(pos);

                if(direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                    if (db.deletedata(cur.getName())){
                        adapter.removeditem(pos);
                        Snackbar.make(findViewById(home), "Item Deleted!", 1500).show();
                    }
                }
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rc);
    }

    //Method yang ada ketika menu dibuat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Method yang dijalankan ketika item pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menusetting){
            startActivity(new Intent(Home.this, setting.class));
            finish();
        }
        return true;
    }

    //onClick untuk floatingButton untuk menambahkan task todo
    public void menambahkan(View view) {
        startActivity(new Intent(Home.this, adding.class));

    }
}
