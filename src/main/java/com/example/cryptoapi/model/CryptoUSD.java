package com.example.cryptoapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CryptoUSD {
    @Id
    @JsonProperty("id")
    private  String id;
    @JsonProperty("symbol")
    private  String symbol;
    @JsonProperty("name")
    private  String name;
    @JsonProperty("image")
    private  String image;
    @JsonProperty("current_price")
    private  double currentPrice;
    @JsonProperty("high_24h")
    private  double high24h;
    @JsonProperty("low_24h")
    private double  low24h;
    @JsonProperty("price_change_percentage_24h")
    private double percentage_24h;
    @JsonProperty("market_cap")
    private  long marketCap;
    @JsonProperty("market_cap_rank")
    private int marketCapRank;

    private  final String urlTienTe = "listUsd";

    public double percentLamTron2So(){
        return  Math.round(percentage_24h * 100.0) / 100.0;
    }
    public String upperToCase(){
        return symbol.toUpperCase();
    }
}
