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

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import static com.example.qwert.merger.MainActivity.SELECTED_CURRENCY;


/**
 * Created by samik on 10.11.2016.
 */

public class FoundCurrenciesActivity extends Activity {
    private TextView name;
    private TextView full_name;
    private TextView description;
    private ImageView imageView;
    private Button search;

    private ListView listView;
    private CurrencyItemAdapter currencyItemAdapter;
    private Currency currencyLocal;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.found_activity);

        search = (Button) findViewById(R.id.fa_found_btn);
        imageView = (ImageView) findViewById(R.id.fa_icon);
        full_name = (TextView) findViewById(R.id.fa_fullName_text);
        name = (TextView) findViewById(R.id.fa_ABR_text);
        listView = (ListView) findViewById(R.id.fa_list_view);

//        long date = System.currentTimeMillis();
//
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd");
//        String dateString = sdf.format(date);

        Intent intent = getIntent();
        int currIndex = intent.getIntExtra(SELECTED_CURRENCY, 0);

        setDataToScreen(currIndex);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void setDataToScreen(int index) {
        currencyLocal = CurrenciesList.getInstance().get(index);

        name.setText(currencyLocal.base);
        full_name.setText(currencyLocal.sname);
        imageView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),
                this.getResources().getIdentifier(currencyLocal.base.toLowerCase(), "drawable", "com.example.qwert.merger")));
        currencyItemAdapter = new CurrencyItemAdapter(this, currencyLocal.rates);

        listView.setAdapter(currencyItemAdapter);
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
