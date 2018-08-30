package com.airteam.sistemaplikasimarketing;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Creepz404x on 4/6/2016.
 */
public class KonsumenActivity extends AppCompatActivity {

    private  ImageButton btndatak;
    private  ImageButton btnberkas;
    private  ImageButton btnfree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konsumen_activity);

        SharedPreferences preferences = getSharedPreferences(Koneksi.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String nama = preferences.getString(Koneksi.EMAIL_SHARED_PREF, null);

        Toast.makeText(KonsumenActivity.this, nama, Toast.LENGTH_SHORT).show();

        cek();
    }
    private void cek()
    {
         btndatak = (ImageButton)findViewById(R.id.btnDataK);
        btnberkas = (ImageButton)findViewById(R.id.btnBerkasK);


        btndatak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == btndatak) {
                    Intent intent = new Intent(KonsumenActivity.this, DataKonActivity.class);
                    startActivity(intent);
                }
            }

        });
        btnberkas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == btnberkas) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(KonsumenActivity.this);
                    builder.setContentTitle("Latihan Notifikasi");
                    builder.setContentText("Ada Beberapa Konsumen Yang Disetujui");
                    builder.setSmallIcon(R.mipmap.ic_launcher);
                    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    builder.setSound(alarmSound);


                    Intent notificationIntent = new Intent(KonsumenActivity.this,BerkasKActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(KonsumenActivity.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);

                    NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    int notificationId = 001;

                    notificationManager. notify (notificationId, builder.build());
                    Intent intent = new Intent(KonsumenActivity.this, BiKonsumen_DKonsumen.class);
                    startActivity(intent);
                }
            }

        });
    }

}
