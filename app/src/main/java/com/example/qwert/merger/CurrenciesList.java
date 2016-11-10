package com.example.qwert.merger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrenciesList {

    private static CurrenciesList ourInstance = new CurrenciesList();

    public static CurrenciesList getInstance() {
        return ourInstance;
    }

    private CurrenciesList() {
    }

    private List<Currency> currencies = new ArrayList<>();

    public List<Currency> getList() {
        return currencies;
    }

    public Currency get(int i) {
        return currencies.get(i);
    }

    public String getSname(String base) {
        for (Currency cur : getList()) {
            if (cur.base.equals(base)) {
                return cur.sname;
            }
        }
        return null;
    }

    public void addValue(Currency currency) {
        currencies.add(currency);
    }

    public void addList(List<Currency> cur) {
        currencies = cur;
    }
}
