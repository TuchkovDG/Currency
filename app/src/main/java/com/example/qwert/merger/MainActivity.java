package com.example.qwert.merger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private List<String> curr;
    private Currency cur;
    public List<Currency> currs;
    private HashMap<String, String> tmpVals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currs = new ArrayList<>();

        curr = new ArrayList<>();
        curr.add("EUR");
        curr.add("USD");
        curr.add("CNY");
        curr.add("GBP");
        curr.add("INR");
        curr.add("JPY");
        curr.add("KRW");
        curr.add("PHP");
        curr.add("RUB");
        curr.add("THB");

        tmpVals = new HashMap<>();
        tmpVals.put("CNY", "китайский юань");
        tmpVals.put("EUR", "евро");
        tmpVals.put("GBP", "британский фунт");
        tmpVals.put("INR", "индийская рупия");
        tmpVals.put("JPY", "японская йена");
        tmpVals.put("KRW", "южнокорейский вон");
        tmpVals.put("PHP", "филиппинский песо");
        tmpVals.put("RUB", "рубль");
        tmpVals.put("THB", "таиландский бат");
        tmpVals.put("USD", "доллар США");
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (String c : curr) {
            cur = new Currency();
            cur.base = c;
            getData(cur);
        }
    }


    private void getData(Currency cur) {
        Retrofit.getCurrencies(cur, new Callback<Currency>() {

            @Override
            public void success(Currency currency, Response response) {
                currency.longSname = tmpVals.get(currency.base);
                currs.add(currency);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
