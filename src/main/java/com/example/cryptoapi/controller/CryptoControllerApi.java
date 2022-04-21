package com.example.cryptoapi.controller;


import com.example.cryptoapi.model.CryptoUSD;
import com.example.cryptoapi.model.CryptoChart;
import com.example.cryptoapi.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CryptoControllerApi {

    @Autowired
    private CryptoService cryptoService;



   @GetMapping("/listPrice")
   public List<String> getCryptoTimePrice(@RequestParam String id) {
       CryptoChart cryptoChart = cryptoService.getCurrency3Month(id,"usd");
       return  cryptoChart.covertTimeMonth(cryptoChart.get3MonthTime()).stream().distinct().collect(Collectors.toList());
   }

}
