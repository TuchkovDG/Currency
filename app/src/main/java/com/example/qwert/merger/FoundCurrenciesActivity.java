package com.example.qwert.merger;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FoundCurrenciesActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.found_activity);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Retrofit.getCurrenciesByDate("2016-01-03", "USD", new Callback<Currency>() {

            @Override
            public void success(Currency currency, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(FoundCurrenciesActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
