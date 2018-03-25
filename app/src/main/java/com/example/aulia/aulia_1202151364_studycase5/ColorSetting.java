package com.example.aulia.aulia_1202151364_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by Aulia on 25/03/2018.
 */

public class ColorSetting extends AppCompatActivity{
        int color;
        TextView colors;
        AlertDialog.Builder builder;
        SharedPreferences.Editor edit;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_setting);

            //deklarasi objek yang digunakan
            builder = new AlertDialog.Builder(this);

            //Mendapatkan SharedPreference dan menentukan editor untuk SharedPreference
            SharedPreferences pref = getApplicationContext().getSharedPreferences("pref", 0);
            edit = pref.edit();
            color = pref.getInt("background", R.color.white);
            colors = findViewById(R.id.colors);
            colors.setText(getWarna(color));
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == android.R.id.home) {
                this.onBackPressed();
            }
            return true;
        }

        @Override
        public void onBackPressed() {
            startActivity(new Intent(ColorSetting.this, MainActivity.class));
            finish();
        }

        //Method untuk menampilkan pilih warna
        public void choosecolors(View view) {
            builder.setTitle("Choose Color");
            View v = getLayoutInflater().inflate(R.layout.choosecolors, null);
            builder.setView(v);

            //untuk menentukan rb ketika di klik
            final RadioGroup rg = v.findViewById(R.id.rg);
            rg.check(getIntColor(color));

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int check = rg.getCheckedRadioButtonId();
                    switch (check) {
                        case R.id.blue:
                            color = R.color.blue;
                            break;
                        case R.id.green:
                            color = R.color.green;
                            break;
                        case R.id.red:
                            color = R.color.red;
                            break;
                        case R.id.white:
                            color = R.color.white;
                            break;
                    }

                    //Mengatur SharedPreference
                    colors.setText(getWarna(color));
                    edit.putInt("background", color);
                    edit.commit();
                }
            });

            //Method ketika menekan Cancel
            builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }
            );

            //menampilkan pilihan warna background
            builder.create().show();
        }

        //Method string warna
        public String getWarna(int i) {
            if (i == R.color.green) {
                return "Green";
            } else if (i == R.color.blue) {
                return "Blue";
            } else if (i == R.color.red) {
                return "Red";
            } else {
                return "White";
            }
        }

        //Method get warna
        public int getIntColor(int i) {
            if (i == R.color.green) {
                return R.id.green;
            } else if (i == R.color.blue) {
                return R.id.blue;
            } else if (i == R.color.red) {
                return R.id.red;
            } else {
                return R.id.white;
            }
        }
    }

