package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionMenager {
    // SharedPreferences nesnesi
    SharedPreferences pref;

    //Shared preferences için Editor nesnesi
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mod
    int PRIVATE_MODE = 0;

    // Sharedpref dosya adı
    private static final String PREF_NAME = "SharedPreferencesOrnek";

    //SharedPreferences için key-value'lar.
    private static final String IS_LOGIN = "IsLoggedIn";

    //email ve sifre keylerini her yerden ulaşılabilmesi için public yapıyoruz.
    public static final String KEY_USERNAME = "email";

    public static final String KEY_SIFRE = "sifre";

    // Yapıcı fonksiyonumuz
    public SessionMenager(Context context){

        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void CreateLoginSession(String email, String sifre){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USERNAME, email);
        editor.putString(KEY_SIFRE, sifre);
        editor.commit();
    }

    /**
     * Kullanıcının login durumunu kontrol etmek
     * Eger false ise girmesi yasak olan yerden çıkartmak
     * true ise girebilmesini sağlamak.
     * */
    public void CheckLogin(){

        if(!this.IsLoggedIn()){

            // Kullanıcı girmemesi gereken yerde, giriş sayfasına yönlendir.
            Intent i = new Intent(_context, MainActivity.class);
            // flagler ile her şeyi sil
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    /**
     * Kaydedilen verileri çağırma,
     * */
    public HashMap GetUserDetails(){

        HashMap user = new HashMap();

        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));

        user.put(KEY_SIFRE, pref.getString(KEY_SIFRE, null));

        return user;
    }


    public void LogoutUser(){

        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }


    public boolean IsLoggedIn(){

        return pref.getBoolean(IS_LOGIN, false);
    }
}
