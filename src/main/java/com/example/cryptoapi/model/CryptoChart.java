package com.example.cryptoapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.*;

public class CryptoChart {
    @JsonProperty("prices")
    private long[][] prices;

    public List<Long> getTime(){
          List<Long> time = new ArrayList<>();
          for (int i = 0 ; i < prices.length;i=i+5){
               time.add(prices[i][0]);
          }
          return  time;
    }

    public  List<Double> getPrice(){
        List<Double> listPrice = new ArrayList<>();
        for (int i = 0 ; i < prices.length;i=i+5){
            listPrice.add((double) prices[i][1]);
        }
        return  listPrice;
    }

    public List<Long> get3MonthTime(){
        List<Long> time = new ArrayList<>();
        for (int i = 0 ; i < prices.length;i=i+20){
            time.add(prices[i][0]);
        }
        return  time;
    }

    public  List<Double> get3MonthPrice(){
        List<Double> listPrice = new ArrayList<>();
        for (int i = 0 ; i < prices.length;i=i+20){
            listPrice.add((double) prices[i][1]);
        }
        return  listPrice;
    }

    public List<Long> get12MonthTime(){
        List<Long> time = new ArrayList<>();
        for (int i = 0 ; i < prices.length;i=i+7){
            time.add(prices[i][0]);
        }
        return  time;
    }

    public  List<Double> get12MonthPrice(){
        List<Double> listPrice = new ArrayList<>();
        for (int i = 0 ; i < prices.length;i=i+7){
            listPrice.add((double) prices[i][1]);
        }
        return  listPrice;
    }

    public List<String> covertTimeDay(List<Long> unix){
        List<String> results = new ArrayList<>();
        for (int i = 0; i < unix.size(); i++) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("`h::mm a`");
            results.add(sdf.format(unix.get(i)));
        }
        return  results;
    }

    public List<String> covertTimeMonth(List<Long> unix){
        List<String> results = new ArrayList<>();
        for (int i = 0; i < unix.size(); i++) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("`dd-MM-yyyy` ");
            results.add(sdf.format(unix.get(i)));
        }
        return  results;
    }

}
