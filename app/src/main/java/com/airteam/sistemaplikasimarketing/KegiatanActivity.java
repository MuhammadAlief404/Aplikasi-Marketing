package com.airteam.sistemaplikasimarketing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Creepz404x on 4/6/2016.
 */
public class KegiatanActivity extends AppCompatActivity {
    private ImageButton btnpiket;
    private ImageButton btnfollow;
    private ImageButton btnkanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kegiatan_activity);

        SharedPreferences preferences = getSharedPreferences(Koneksi.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String nama = preferences.getString(Koneksi.EMAIL_SHARED_PREF, null);

        Toast.makeText(KegiatanActivity.this, nama, Toast.LENGTH_SHORT).show();

        cek();
    }

    private void cek() {
        btnpiket = (ImageButton) findViewById(R.id.btnPiket);
        btnfollow = (ImageButton) findViewById(R.id.btnFollowup);
        btnkanvas = (ImageButton)findViewById(R.id.btnKanvas);

        btnpiket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == btnpiket) {
                    Intent intent = new Intent(KegiatanActivity.this, PiketActivity.class);
                    startActivity(intent);
                }

            }

        });
        btnfollow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == btnfollow) {
                    Intent intent = new Intent(KegiatanActivity.this, FollowUp_Kegiatan.class);
                    startActivity(intent);
                }

            }

        });
        btnkanvas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == btnkanvas) {
                    Intent intent = new Intent(KegiatanActivity.this, KanvasingActivity.class);
                    startActivity(intent);
                }

            }

        });

    }
}
