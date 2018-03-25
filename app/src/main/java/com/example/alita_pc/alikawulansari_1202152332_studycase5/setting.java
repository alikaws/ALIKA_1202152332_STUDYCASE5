package com.example.alita_pc.alikawulansari_1202152332_studycase5;

import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Alita-pc on 3/25/2018.
 */

public class setting extends AppCompatActivity {
    //variable yang akan digunakan
    int warna; TextView warnanya;
    AlertDialog.Builder bld; SharedPreferences.Editor edit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Menginisialisasi objek yang digunakan
        bld = new AlertDialog.Builder(this);

        //Mendapatkan SharedPreference dan menentukan editor untuk SharedPreference
        SharedPreferences pref = getApplicationContext().getSharedPreferences("pref", 0);
        edit = pref.edit();
        warna = pref.getInt("background", R.color.putih);
        warnanya = findViewById(R.id.warnanya); warnanya.setText(getwarna(warna));
    }

    //Method yang dijalankan ketika item pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.onBackPressed();
        }
        return true;
    }

    //ketika tombol back diklik(dipencet)
    @Override
    public void onBackPressed() {
        startActivity(new Intent(setting.this, Home.class));
        finish();
    }

    //Method untuk menampilkan dialog memilih warna
    public void dialogwarna(View view) {
        bld.setTitle("Choose Color");
        View v = getLayoutInflater().inflate(R.layout.colordialog, null);
        bld.setView(v);

        //Menentukan radiobutton yang dipilih
        final RadioGroup rg = v.findViewById(R.id.rg);
        rg.check(getIntCOlor(warna));

        //Method ketika menekan OK
        bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int check = rg.getCheckedRadioButtonId();
                switch (check){
                    case R.id.blue:
                        warna = R.color.biru;
                        break;
                    case R.id.green:
                        warna = R.color.hijau;
                        break;
                    case R.id.red:
                        warna = R.color.merah;
                        break;
                    case R.id.white:
                        warna = R.color.putih;
                        break;
                }

                //Mengatur SharedPreference dan mengubah text
                warnanya.setText(getwarna(warna));
                edit.putInt("background", warna);
                edit.commit();
            }
        });

        //Method ketika menekan Cancel
        bld.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //Menampilkan dialog
        bld.create().show();
    }

    //Method untuk mendapatkan String warna
    public String getwarna(int i){
        if(i==R.color.hijau){
            return "Green";
        }else if(i==R.color.biru){
            return "Blue";
        }else if(i==R.color.merah){
            return "Red";
        }else{
            return "White";
        }
    }
    //Method untuk mendapatkan id warna
    public int getIntCOlor(int i){
        if(i==R.color.hijau){
            return R.id.green;
        }else if(i==R.color.biru){
            return R.id.blue;
        }else if(i==R.color.merah){
            return R.id.red;
        }else{
            return R.id.white;
        }
    }
}
