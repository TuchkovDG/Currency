package com.example.qwert.merger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    public static final String SHAREDPREFS = "com.example.zer.qwert.merger";
    public static final String CURRENCY = "currency";
    public static final String SELECTED_CURRENCY = "selected_currency";

    private List<String> curr;
    private Currency cur;
    private HashMap<String, String> tmpVals;
    private ListView currenciesList;
    private Gson gson;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        gson = new Gson();

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

//        SharedPreferences sp = getSharedPreferences(SHAREDPREFS, Context.MODE_PRIVATE);
//        String json = sp.getString(CURRENCY, "Unknown");
//
//        if (!json.equals("Unknown")) {
//            Type listType = new TypeToken<List<Currency>>() {}.getType();
//            CurrenciesList.getInstance().addList((List<Currency>) gson.fromJson(json, listType));
//        } else {
//            for (String c : curr) {
//                cur = new Currency();
//                cur.base = c;
////                getData(cur);
//            }
            Retrofit.getCurrencies(curr, new Callback<Currency>() {

                @Override
                public void success(Currency currency, Response response) {
                    currency.sname = tmpVals.get(currency.base);
                    CurrenciesList.getInstance().addValue(currency);
                    adapter = new Adapter(MainActivity.this, CurrenciesList.getInstance().getList());
                    currenciesList.setAdapter(adapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

//        SharedPreferences sp1 = getSharedPreferences(SHAREDPREFS, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp1.edit();
//        editor.putString(CURRENCY, gson.toJson(CurrenciesList.getInstance().getList()));
//        editor.apply();


//    }
}
