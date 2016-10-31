package com.example.qwert.merger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    public static final String SHAREDPREFS = "com.example.zer.qwert.merger";
    public static final String CURRENCY = "currency";
    public static final String SELECTED_CURRENCY = "selected_currency";

    private List<String> curr;
    private HashMap<String, String> tmpVals;
    private HashMap<String, String> tmpDescriptionVals;
    private ListView currenciesList;
    private Gson gson;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        tmpDescriptionVals = new HashMap<>();
        tmpDescriptionVals.put("CNY", "Cовременная денежная единица Китайской Народной Республики. Одна из основных резервных валют мира, входит в «корзину» специальных прав заимствования МВФ. Международное обозначение валюты в стандарте ISO 4217.");
        tmpDescriptionVals.put("EUR", "Официальная валюта 19 стран «еврозоны» (Австрии, Бельгии, Германии, Греции, Ирландии, Испании, Италии, Кипра, Латвии, Литвы, Люксембурга, Мальты, Нидерландов, Португалии, Словакии, Словении, Финляндии, Франции, Эстонии).");
        tmpDescriptionVals.put("GBP", "Национальная валюта Соединённого Королевства Великобритании и Северной Ирландии (Великобритании), включающего Англию, Шотландию, Уэльс и Северную Ирландию;");
        tmpDescriptionVals.put("INR", "Денежная единица Индии. Состоит из 100 пайс. " +
                "До 1916 года 1 рупия равнялась 16 английским пенсам. " +
                "Банкноты номиналом — от 1 до 1000.");
        tmpDescriptionVals.put("JPY", "Денежная единица Японии, одна из основных резервных валют мира. Делится на 100 сен (счётная денежная единица, в 1954 году изъята из обращения).");
        tmpDescriptionVals.put("KRW", "Денежная единица Республики Корея. Название вона в русском языке происходит от словосочетания 원화 (произносится как вонхва), дословно: валюта Вон. В обращении находятся банкноты номиналом 1000, 5000, 10000 и 50000 вон.");
        tmpDescriptionVals.put("PHP", "Национальная валюта Филиппин. Состоит из 100 сентаво (сентимо). Появились 1 мая 1852 года, когда Испано-Филиппинский банк выпустил «твёрдые песо»  которые вытеснили реалы, ходившие до этого на Филиппинах.");
        tmpDescriptionVals.put("RUB", "Денежная единица Российской Федерации. Используется также на территории ряда непризнанных и частично признанных государств: Республики Абхазия, Республики Южная Осетия, Луганской Народной Республики и Донецкой Народной Республики (в первых двух случаях по соглашению с РФ).");
        tmpDescriptionVals.put("THB", "Денежная единица Таиланда с 15 апреля 1928 года. Делится на 100 сатангов (สตางค์). Обозначение: ฿.");
        tmpDescriptionVals.put("USD", "Денежная единица США, одна из основных резервных валют мира. 1 доллар = 100 центов. Символьное обозначение в англоязычных текстах: $.");
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = getSharedPreferences(SHAREDPREFS, Context.MODE_PRIVATE);
        String json = sp.getString(CURRENCY, "Unknown");

        if (!json.equals("Unknown")) {
            Type listType = new TypeToken<List<Currency>>() {}.getType();
            CurrenciesList.getInstance().addList((List<Currency>) gson.fromJson(json, listType));
            adapter = new Adapter(MainActivity.this, CurrenciesList.getInstance().getList());
            currenciesList.setAdapter(adapter);
        } else {
            init();
            Retrofit.getCurrencies(curr, new Callback<Currency>() {

                @Override
                public void success(Currency currency, Response response) {
                    currency.sname = tmpVals.get(currency.base);
                    currency.longSname = tmpDescriptionVals.get(currency.base);

                    HashMap<String, Double> tmpMap = new HashMap<>();

                    for (Map.Entry entry : currency.rates.entrySet()) {
                        for (String str : curr) {
                            if (entry.getKey().equals(str)) {
                                tmpMap.put(str, (Double) entry.getValue());
                                break;
                            }
                        }
                    }

                    currency.rates = tmpMap;

                    CurrenciesList.getInstance().addValue(currency);
                    adapter = new Adapter(MainActivity.this, CurrenciesList.getInstance().getList());
                    currenciesList.setAdapter(adapter);

                    SharedPreferences sp1 = getSharedPreferences(SHAREDPREFS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp1.edit();
                    editor.putString(CURRENCY, gson.toJson(CurrenciesList.getInstance().getList()));
                    editor.apply();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
