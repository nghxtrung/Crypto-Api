package com.example.cryptoapi.service;

import com.example.cryptoapi.model.CryptoUSD;
import com.example.cryptoapi.model.CryptoChart;
import com.example.cryptoapi.model.CryptoVND;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CryptoService {
    public List<CryptoUSD> getCryptoUsd();
    public  List<CryptoVND>  getCryptoVnd();
    Page<CryptoVND> listAllCryptoVND(int pageNumber,String sortField, String sortDir);
    Page<CryptoUSD> listAllCryptoUSD(int pageNumber,String sortField, String sortDir);
    Page<CryptoUSD> listCryptoUSDSearch(int pageNumber,String sortField, String sortDir,String keyword);
    Page<CryptoVND> listCryptoVNDSearch(int pageNumber,String sortField, String sortDir,String keyword);
    public CryptoUSD findByCrypotoUsdId(String id);
    public CryptoVND findByCryptoVndId(String id);
    public CryptoChart getCurrencyDay(String id,String tienTe);
    public CryptoChart getCurrencyMonth(String id,String tienTe);
    public CryptoChart getCurrency3Month(String id,String tienTe);
    public CryptoChart getCurrencyYear(String id,String tienTe);
}
