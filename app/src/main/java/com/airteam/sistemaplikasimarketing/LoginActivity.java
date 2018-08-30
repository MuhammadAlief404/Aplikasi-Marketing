package com.airteam.sistemaplikasimarketing;

/**
 * Created by Creepz404x on 4/6/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Windows10 on 04/04/2016.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Definisi views
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    //Tipe Variabel boolean untuk cek User Udah Login atau Tidak
    //Default set dengan False
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        //Inisialisasi View
        editTextEmail = (EditText) findViewById(R.id.edtUsername);
        editTextPassword = (EditText) findViewById(R.id.edtPassword);

        buttonLogin = (Button) findViewById(R.id.btnLogin);

        //Tambahan Click Listener
        buttonLogin.setOnClickListener(this);

    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences=getSharedPreferences(Koneksi.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        loggedIn=sharedPreferences.getBoolean(Koneksi.LOGGEDIN_SHARED_PREF, false);

        if (loggedIn){
            // Class Yang akan muncul jika Login Sukses
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

    // Method untuk proses Login
    private void login(){
        // Ubah ketipe String
        final  String username=editTextEmail.getText().toString().trim();
        final String password=editTextPassword.getText().toString().trim();

        //Buatkan Request Dalam bentuk String
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Koneksi.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Jika Respon server sukses
                        if(response.equalsIgnoreCase(Koneksi.LOGIN_SUCCESS)){
                            //Buatkan sebuah shared preference
                            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Koneksi.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Buatkan Sebuah variabel Editor Untuk penyimpanan Nilai shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Tambahkan Nilai ke Editor
                            editor.putBoolean(Koneksi.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Koneksi.EMAIL_SHARED_PREF, username);

                            //Simpan Nilai ke Variabel editor
                            editor.commit();

                            //Starting Class yang dipanggil
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            //Jika Respon Dari Server tidak Sukses
                            //Tampilkan Pesan Errorrr dengan Toast,,
                            Toast.makeText(LoginActivity.this, "Salah Username dan Password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Tambahkan apa yang terjadi setelah Pesan Error muncul, alternatif
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Tambahkan Parameter username dan password untuk Request
                params.put(Koneksi.KEY_EMAIL, username);
                params.put(Koneksi.KEY_PASSWORD, password);

                //Kembalikan Nilai parameter
                return params;
            }
        };

        //Tambahkan Request String ke dalam Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    // Methode Click OnListener
    @Override
    public void onClick(View v) {
        // Panggil Method Login.
        login();
    }
}