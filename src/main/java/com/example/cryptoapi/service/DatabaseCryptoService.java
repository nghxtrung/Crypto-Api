package com.example.cryptoapi.service;

import com.example.cryptoapi.model.CryptoUSD;
import com.example.cryptoapi.model.CryptoVND;
import com.example.cryptoapi.repository.CryptoUSDRepository;
import com.example.cryptoapi.repository.CryptoVNDRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
@Slf4j
public class DatabaseCryptoService {
    private  static  final  String PER_PAGE = "100";
    private  static final  String PAGE = "1";

    @Autowired
    private  CryptoService cryptoService;
    @Autowired
    private CryptoUSDRepository cryptoRepositoryUsd;
    @Autowired
    private CryptoVNDRepository cryptoRepositoryVnd;
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "*/30 * * * * *")
    private  void  addCryptoToDatabaseUsd(){
        Optional<List<CryptoUSD>> optionalCryptos = Optional.of(cryptoGetRestUsd());
        cryptoRepositoryUsd.saveAll(optionalCryptos.orElseThrow());
        // log
        String time  = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        log.info("add to dabtaase ${}",time);
    }
    @Scheduled(cron = "*/30 * * * * *")
    private  void  addCryptoToDatabaseVnd(){
        Optional<List<CryptoVND>> optionalCryptos = Optional.of(cryptoGetRestVnd());
        cryptoRepositoryVnd.saveAll(optionalCryptos.orElseThrow());
        // log
        String time  = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        log.info("add to dabtaase ${}",time);
    }
    public List<CryptoUSD> cryptoGetRestUsd() {
        final  String URL = "https://api.coingecko.com/api/v3/";
        ResponseEntity<List<CryptoUSD>> rateResponse = restTemplate
                .exchange(URL + "coins/markets?"
                                + "vs_currency=usd" + "&"
                                + "per_page=" + PER_PAGE +"&" +
                                "page=" + PAGE, HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<CryptoUSD>>() {
                        });
        List<CryptoUSD> cryptoList = rateResponse.getBody();
        return rateResponse.getBody();
    }
    public List<CryptoVND> cryptoGetRestVnd() {
        final  String URL = "https://api.coingecko.com/api/v3/";
        ResponseEntity<List<CryptoVND>> rateResponse = restTemplate
                .exchange(URL + "coins/markets?"
                                + "vs_currency=vnd" + "&"
                                + "per_page=" + PER_PAGE +"&" +
                                "page=" + PAGE, HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<CryptoVND>>() {
                        });
        List<CryptoVND> cryptoList = rateResponse.getBody();
        return rateResponse.getBody();
    }
}
