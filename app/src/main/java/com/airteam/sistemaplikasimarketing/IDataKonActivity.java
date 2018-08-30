package com.airteam.sistemaplikasimarketing;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IDataKonActivity extends AppCompatActivity {

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds

    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private EditText idk;
    private EditText tgl;
    private EditText nama;
    private EditText kelamin;
    private EditText alamat;
    private EditText hp;
    private EditText telp;
    private EditText sumber;
    private EditText call;
    private EditText minat;
    private EditText ktp;
    private RadioGroup radioCalltGroup;
    private RadioButton radioCallButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idkonsumen_dkonsumen);

        // Get Reference to variables
        radioCalltGroup = (RadioGroup) findViewById(R.id.rgCall);
        idk = (EditText) findViewById(R.id.editID);
        tgl = (EditText) findViewById(R.id.editTgl);
        nama = (EditText) findViewById(R.id.editNama);
//        kelamin = (EditText) findViewById(R.id.editKelamin);
        alamat = (EditText) findViewById(R.id.edtAlamat);
        hp = (EditText) findViewById(R.id.editHp);
        telp = (EditText) findViewById(R.id.editTelp);
        sumber = (EditText) findViewById(R.id.editSumber);
        minat = (EditText) findViewById(R.id.editMinat);
        ktp = (EditText) findViewById(R.id.edtKtp);

        Button tambah = (Button) findViewById(R.id.buttonInsert);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from email and passord field
                final String idk1 = idk.getText().toString();
                final String tgl1 = tgl.getText().toString();
                final String nama1 = nama.getText().toString();
                final String kelamin1 = kelamin.getText().toString();
                final String alamat1 = alamat.getText().toString();
                final String hp1 = hp.getText().toString();
                final String telp1 = telp.getText().toString();
                final String sumber1 = sumber.getText().toString();
                final String minat1 = minat.getText().toString();
                final String ktp1 = ktp.getText().toString();
                int selectedId = radioCalltGroup.getCheckedRadioButtonId();
                radioCallButton = (RadioButton) findViewById(selectedId);
                final String call1 = radioCallButton.getText().toString();

                // Initialize  AsyncLogin() class with email and password
                new AsyncLogin().execute(idk1, tgl1, nama1, kelamin1, alamat1, hp1, telp1, sumber1, call1, minat1,ktp1);
            }
        });
    }


    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(IDataKonActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://192.168.1.72:8080/project/android/tambah.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("idk1", params[0])
                        .appendQueryParameter("tgl1", params[1])
                        .appendQueryParameter("nama1", params[2])
                        .appendQueryParameter("kelamin1", params[3])
                        .appendQueryParameter("alamat1", params[4])
                        .appendQueryParameter("hp1", params[5])
                        .appendQueryParameter("telp1", params[6])
                        .appendQueryParameter("sumber1", params[7])
                        .appendQueryParameter("call1", params[8])
                        .appendQueryParameter("minat1", params[9])
                        .appendQueryParameter("ktp1", params[10]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("true"))
            {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */

                Context context = getApplicationContext();
                CharSequence text = "Data Berhasil Disimpan";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Context context = getApplicationContext();
                CharSequence text = "Harap Masukan Data Dengan Lengkap";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(IDataKonActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG);

            }
        }

    }
}
