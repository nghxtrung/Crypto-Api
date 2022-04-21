package com.example.cryptoapi.controller;

import com.example.cryptoapi.model.CryptoChart;
import com.example.cryptoapi.model.CryptoVND;
import com.example.cryptoapi.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/listVND")
public class CryptoVNDController {
    @Autowired
    private CryptoService cryptoService;

    @GetMapping("")
    public String getCryptoVnd(Model model) {
        return listByPage(1,model,"name","asc","");
    }


    @GetMapping("/search")
    public String getCryptoVNDSearch(Model model,@RequestParam String keyword) {
        return listByPage(1,model,"name","asc",keyword);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(@PathVariable("pageNumber") int pageNumber, Model model,
                             @RequestParam(defaultValue = "name") String sortField,
                             @RequestParam(defaultValue = "asc") String sortDir,
                             @RequestParam(required = false) String keyword){
        int currentPage = pageNumber;

        Page<CryptoVND> page ;
        if(keyword == null){
            page = cryptoService.listAllCryptoVND(pageNumber,sortField,sortDir);
        }else{
            page = cryptoService.listCryptoVNDSearch(pageNumber,sortField,sortDir,keyword);
        }

        List<CryptoVND> cryptoVNDListSlide = cryptoService.getCryptoVnd();
        model.addAttribute("cryptoListSlide",cryptoVNDListSlide);

        List<CryptoVND> cryptoList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("urlTienTe","listVND");
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("tienTe","VND");
        model.addAttribute("cryptoList",cryptoList);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("keyword",keyword);
        String reverseSortDir = sortDir.equals("asc") ? "desc":"asc";
        model.addAttribute("reverseSortDir",reverseSortDir);
        return "trangchu1";
    }




    @GetMapping("/cryto/grap")
    public String graphCrypto(@RequestParam String id, Model model) {
        CryptoVND crypto =cryptoService.findByCryptoVndId(id);
        model.addAttribute("kyHieu","VND");
        String tienTe = "vnd";
        model.addAttribute("crypto",crypto);
        CryptoChart cryptoChart = cryptoService.getCurrencyDay(id,tienTe);
        List<String> timeDay = cryptoChart.covertTimeDay(cryptoChart.getTime());
        List<Double> priceDay = cryptoChart.getPrice();
        model.addAttribute("timeArrDay",timeDay);
        model.addAttribute("priceArrDay",priceDay);
        CryptoChart cryptoChartMonth = cryptoService.getCurrencyMonth(id,tienTe);
        List<String> timeMonth = cryptoChartMonth.covertTimeMonth(cryptoChartMonth.getTime());
        List<Double> priceMonth = cryptoChartMonth.getPrice();
        model.addAttribute("timeArrMonth",timeMonth);
        model.addAttribute("priceArrMonth",priceMonth);
        CryptoChart cryptoChart3Month = cryptoService.getCurrency3Month(id,tienTe);
        List<String> time3Month = cryptoChart3Month.covertTimeMonth(cryptoChart3Month.get3MonthTime());
        List<Double> price3Month = cryptoChart3Month.get3MonthPrice();
        model.addAttribute("timeArr3Month",time3Month);
        model.addAttribute("priceArr3Month",price3Month);
        CryptoChart cryptoChart12Month = cryptoService.getCurrencyYear(id,tienTe);
        List<String> time12Month = cryptoChart12Month.covertTimeMonth(cryptoChart12Month.get12MonthTime());
        List<Double> price12Month = cryptoChart12Month.get12MonthPrice();
        model.addAttribute("timeArr12Month",time12Month);
        model.addAttribute("priceArr12Month",price12Month);
        model.addAttribute("urlTienTe","listVND");
        return "index";
    }
}
