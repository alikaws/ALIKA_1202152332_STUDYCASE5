package com.example.alita_pc.alikawulansari_1202152332_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class adding extends AppCompatActivity{
    EditText name, description, priority;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        //Menginisialisasi objek yang digunakan
        name = findViewById(R.id.inputname);
        description = findViewById(R.id.inputdescription);
        priority = findViewById(R.id.inputpriority);
        db = new database(this);
    }

    //Method ketika tombol back ditekan
    @Override
    public void onBackPressed() {
        startActivity(new Intent(adding.this, Home.class));
        this.finish();
    }

    //Method ketika tombol tambah ditekan
    public void menambahtodo(View view) {
        if(db.insertdata(new itemtodo(name.getText().toString(), description.getText().toString(), priority.getText().toString()))){
            Toast.makeText(this, "Todo added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(adding.this, Home.class));
            this.finish();
        }else{
            Toast.makeText(this, "Adding todo failed", Toast.LENGTH_SHORT).show();
            name.setText(null); description.setText(null); priority.setText(null);
        }
    }
}
