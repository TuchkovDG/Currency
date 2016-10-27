package com.example.qwert.merger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    public static final String SELECTED_CURRENCY = "selected_currency";

    private List<String> curr;
    private Currency cur;
    private HashMap<String, String> tmpVals;
    private ListView currenciesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        currenciesList = (ListView) findViewById(R.id.am_currencies_list);
        currenciesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
                intent.putExtra(SELECTED_CURRENCY, position); //CurrenciesList.getInstance().get(position));
                startActivity(intent);
            }
        });
    }

    private void init() {
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

        Adapter adapter = new Adapter(this, CurrenciesList.getInstance().getList());
        currenciesList.setAdapter(adapter);
    }


    private void getData(Currency cur) {
        Retrofit.getCurrencies(cur, new Callback<Currency>() {

            @Override
            public void success(Currency currency, Response response) {
                currency.sname = tmpVals.get(currency.base);
                CurrenciesList.getInstance().addValue(currency);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
