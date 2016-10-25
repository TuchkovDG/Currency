package com.example.qwert.merger;

import android.media.Image;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<CurrencyMain> currencyMains;
    RecyclerView recyclerView;

    List<ArrayList<String>> stringsList;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.ma_list_currency);

        recyclerView = (RecyclerView) findViewById(R.id.ma_list_currency);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

//    // заполняем список для адаптера
//    public void creatObject(String AbbName, String fullName, Integer iconNumber){
//        currencyMains.add(new CurrencyMain(AbbName,fullName,iconNumber));
//    }
//    public void cretList(){
//        ArrayList<String> listfullNames = new ArrayList<String>();
//        listfullNames.add("китайский юань");
//        listfullNames.add("евро");
//        listfullNames.add("британский фунт");
//        listfullNames.add("индийская рупия");
//        listfullNames.add("японская йена");
//        listfullNames.add("южнокорейский вон");
//        listfullNames.add("филиппинский песо");
//        listfullNames.add("рубль");
//        listfullNames.add("таиландский бат");
//        listfullNames.add("доллар США");
//
//        ArrayList<String> listAbbName = new ArrayList<>();
//        listAbbName.add("CNY");
//        listAbbName.add("EUR");
//        listAbbName.add("GBP");
//        listAbbName.add("INR");
//        listAbbName.add("JPY");
//        listAbbName.add("KRW");
//        listAbbName.add("PHP ");
//        listAbbName.add("RUB");
//        listAbbName.add("THB");
//        listAbbName.add("USD");
//
//        ArrayList<String> listImageNumber = new ArrayList<>();
//        listImageNumber.add("1");
//        listImageNumber.add("2");
//        listImageNumber.add("3");
//        listImageNumber.add("4");
//        listImageNumber.add("5");
//        listImageNumber.add("6");
//        listImageNumber.add("7");
//        listImageNumber.add("8");
//        listImageNumber.add("9");
//        listImageNumber.add("10");
//
//        stringsList.add(listAbbName);
//        stringsList.add(listfullNames);
//        stringsList.add(listImageNumber);
//
//    }

//    CNY - китайский юань
//    EUR - евро
//    GBP - британский фунт
//    INR - индийская рупия
//    JPY - японская йена
//    KRW - южнокорейский вон
//    PHP - филиппинский песо
//    RUB - рубль
//    THB - таиландский бат
//    USD - доллар США
}
