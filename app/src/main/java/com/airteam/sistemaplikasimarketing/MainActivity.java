package com.airteam.sistemaplikasimarketing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageButton btnpribadi;
    private ImageButton btnkonsumen;
    private ImageButton btnkegiatan;
    private ImageButton btnpengaturan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences preferences = getSharedPreferences(Koneksi.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String nama = preferences.getString(Koneksi.EMAIL_SHARED_PREF, null);

        Toast.makeText(MainActivity.this, "Selamat Datang " + nama, Toast.LENGTH_SHORT).show();
        cek();
    }
    private void cek()
    {
        btnpribadi = (ImageButton)findViewById(R.id.btnPribadi);
        btnkonsumen = (ImageButton)findViewById(R.id.btnKonsumen);
        btnkegiatan = (ImageButton)findViewById(R.id.btnKegiatan);
        btnpengaturan = (ImageButton)findViewById(R.id.btnPengaturan);

        btnkonsumen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if (v==btnkonsumen)
                {
                    Intent intent = new Intent(MainActivity.this, KonsumenActivity.class);
                    startActivity(intent);

                }
            }

        });
        btnkegiatan.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v)
        {
            if (v==btnkegiatan)
            {
                Intent intent = new Intent(MainActivity.this, KegiatanActivity.class);
                startActivity(intent);
            }
        }

    });
        btnpengaturan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if (v==btnpengaturan)
                {
                    Intent intent = new Intent(MainActivity.this, PengaturanActivity.class);
                    startActivity(intent);
                }
            }

        });
        btnpribadi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                if (v==btnpribadi)
                {
                    Intent intent = new Intent(MainActivity.this, PribadiActivity   .class);
                    startActivity(intent);
                }
            }

        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.pribadi) {
            Intent intent = new Intent(MainActivity.this, PribadiActivity.class);
            startActivity(intent);
        } else if (id == R.id.konsumen) {
            Intent intent = new Intent(MainActivity.this, KonsumenActivity.class);
            startActivity(intent);

        } else if (id == R.id.kegiatan) {
            Intent intent = new Intent(MainActivity.this, KegiatanActivity.class);
            startActivity(intent);
        } else if (id == R.id.pengaturan) {
            Intent intent = new Intent(MainActivity.this, PengaturanActivity.class);
            startActivity(intent);
        } else if (id == R.id.tentang) {

        } else if (id == R.id.logout) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Apakah Kamu Yakin Untuk logout?");
            alertDialogBuilder.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            // Getting out
                            SharedPreferences preferences = getSharedPreferences(Koneksi.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            //Getting editor
                            SharedPreferences.Editor editor = preferences.edit();

                            // put nilai false untuk login
                            editor.putBoolean(Koneksi.LOGGEDIN_SHARED_PREF, false);

                            // put nilai untuk username
                            editor.putString(Koneksi.EMAIL_SHARED_PREF, "");

                            //Simpan ke haredpreferences

                            editor.commit();

                            // Balik dan tampilkan ke halaman Utama aplikasi jika logout berhasil
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            MainActivity.this.finish();
                        }
                    });
// Pilihan jika NO
            alertDialogBuilder.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });

            // Tampilkan alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
