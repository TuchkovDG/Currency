package com.example.qwert.merger;

import java.io.Serializable;
import java.util.HashMap;

public class Currency implements Serializable {

    public String base;
    public String date;
    public HashMap<String, Double> rates;
    public String sname;
    public String longSname;
}
