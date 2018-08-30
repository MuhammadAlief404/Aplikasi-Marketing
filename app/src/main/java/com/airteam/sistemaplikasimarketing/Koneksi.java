package com.airteam.sistemaplikasimarketing;

/**
 * Created by Creepz404x on 5/31/2016.
 */
public class Koneksi {
    //URL letak file new_login.php di server menggunakan alamat IP Komputer
    public static final String LOGIN_URL = "http://192.168.1.72:8080/project/android/login.php";

    // Variabel untuk definisikan Username dan password methode POST sesuai dengan yang di : new_login.php
    public static final String KEY_EMAIL = "user1";
    public static final String KEY_PASSWORD = "pass";

    // Jika respon server adalah sukses login
    public static final String LOGIN_SUCCESS = "berhasil";

    //Kunci untuk Sharedpreferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //Ini digunakan untuk store username jika User telah Login
    public static final String EMAIL_SHARED_PREF = "username";

    // Ini digunakan untuk store sharedpreference untuk cek user melakukan login atau tidak
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}