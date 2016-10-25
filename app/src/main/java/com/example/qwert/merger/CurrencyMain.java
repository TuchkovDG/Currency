package com.example.qwert.merger;

import android.content.Intent;
import android.media.Image;

/**
 * Created by samik on 25.10.2016.
 */

public class CurrencyMain {
    public String currencyAbbName; //имя аббривиатура
    public String currencyName; // полное имя валюты
    public Integer numberImage;

    public CurrencyMain(String currencyAbbName, String currencyName, Integer numberImage) {
        this.currencyAbbName = currencyAbbName;
        this.currencyName = currencyName;
        this.numberImage = numberImage;
    }
}
