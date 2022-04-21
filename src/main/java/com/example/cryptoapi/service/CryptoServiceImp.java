package com.example.cryptoapi.service;

import com.example.cryptoapi.model.CryptoUSD;
import com.example.cryptoapi.model.CryptoChart;
import com.example.cryptoapi.model.CryptoVND;
import com.example.cryptoapi.repository.CryptoUSDRepository;
import com.example.cryptoapi.repository.CryptoVNDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CryptoServiceImp implements CryptoService{

    @Autowired
    private CryptoUSDRepository cryptoRepositoryUsd;
    @Autowired
    private CryptoVNDRepository cryptoRepositoryVnd;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CryptoUSD> getCryptoUsd() {
        return cryptoRepositoryUsd.findAll();
    }

    @Override
    public List<CryptoVND> getCryptoVnd() {
        return cryptoRepositoryVnd.findAll();
    }

    @Override
    public Page<CryptoVND> listAllCryptoVND(int pageNumber, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc")?sort.ascending():sort.descending();
        Pageable pageable = PageRequest.of(pageNumber-1,10,sort);
        return cryptoRepositoryVnd.findAll(pageable);
    }

    @Override
    public Page<CryptoUSD> listAllCryptoUSD(int pageNumber, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc")?sort.ascending():sort.descending();
        Pageable pageable = PageRequest.of(pageNumber-1,10,sort);
        return cryptoRepositoryUsd.findAll(pageable);
    }

    @Override
    public Page<CryptoUSD> listCryptoUSDSearch(int pageNumber, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc")?sort.ascending():sort.descending();
        Pageable pageable = PageRequest.of(pageNumber-1,10,sort);
        return  cryptoRepositoryUsd.findAllByNameContains(keyword,pageable);
    }

    @Override
    public Page<CryptoVND> listCryptoVNDSearch(int pageNumber, String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc")?sort.ascending():sort.descending();
        Pageable pageable = PageRequest.of(pageNumber-1,10,sort);
        return  cryptoRepositoryVnd.findAllByNameContains(keyword,pageable);
    }


    @Override
    public CryptoUSD findByCrypotoUsdId(String id) {
        return cryptoRepositoryUsd.findById(id).orElseThrow();
    }
    @Override
    public CryptoVND findByCryptoVndId(String id) {
        return cryptoRepositoryVnd.findById(id).orElseThrow();
    }

    @Override
    public CryptoChart getCurrencyMonth(String id,String tienTe) {
        ResponseEntity<CryptoChart> rateResponse = restTemplate
                .exchange("https://api.coingecko.com/api/v3/coins/"+id+"/market_chart?vs_currency="+tienTe+"&days=30"
                        ,HttpMethod.GET,null,new ParameterizedTypeReference<>(){});
        CryptoChart cryptoChartMonth = rateResponse.getBody();
        return cryptoChartMonth;
    }

    @Override
    public CryptoChart getCurrency3Month(String id,String tienTe) {
        ResponseEntity<CryptoChart> rateResponse = restTemplate
                .exchange("https://api.coingecko.com/api/v3/coins/"+id+"/market_chart?vs_currency="+tienTe+"&days=90"
                        ,HttpMethod.GET,null,new ParameterizedTypeReference<>(){});
        CryptoChart cryptoChart3Month = rateResponse.getBody();
        return cryptoChart3Month;
    }

    @Override
    public CryptoChart getCurrencyYear(String id,String tienTe) {
        ResponseEntity<CryptoChart> rateResponse = restTemplate
                .exchange("https://api.coingecko.com/api/v3/coins/"+id+"/market_chart?vs_currency="+tienTe+"&days=365"
                        ,HttpMethod.GET,null,new ParameterizedTypeReference<>(){});
        CryptoChart cryptoChartYear = rateResponse.getBody();
        return cryptoChartYear;
    }

    @Override
    public CryptoChart getCurrencyDay(String id,String tienTe){
        ResponseEntity<CryptoChart> rateResponse = restTemplate
                .exchange("https://api.coingecko.com/api/v3/coins/"+id+"/market_chart?vs_currency="+tienTe+"&days=1"
                        ,HttpMethod.GET,null,new ParameterizedTypeReference<>(){});
        CryptoChart cryptoChartDay = rateResponse.getBody();
        return cryptoChartDay;
    }
}
