package com.example.cryptoapi.controller;

import com.example.cryptoapi.model.CryptoUSD;
import com.example.cryptoapi.model.CryptoChart;
import com.example.cryptoapi.model.CryptoVND;
import com.example.cryptoapi.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/listUsd")
public class CryptoUSDController {
    @Autowired
    private CryptoService cryptoService;


    @GetMapping("")
    public String getCryptoUsd(Model model) {
        return listByPage(1,model,"name","asc","");
    }

    @GetMapping("/search")
    public String getCryptoUsdSearch(Model model,@RequestParam String keyword) {
        return listByPage(1,model,"name","asc",keyword);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(@PathVariable("pageNumber") int pageNumber, Model model,
                             @RequestParam(defaultValue = "name") String sortField,
                             @RequestParam(defaultValue = "asc") String sortDir,
                             @RequestParam String keyword){
        int currentPage = pageNumber;

        Page<CryptoUSD> page ;
        if(keyword == null){
            page = cryptoService.listAllCryptoUSD(pageNumber,sortField,sortDir);
        }else{
            page = cryptoService.listCryptoUSDSearch(pageNumber,sortField,sortDir,keyword);
        }


        List<CryptoUSD> cryptoVNDListSlide = cryptoService.getCryptoUsd();
        model.addAttribute("cryptoListSlide",cryptoVNDListSlide);

        List<CryptoUSD> cryptoList = page.getContent();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        model.addAttribute("urlTienTe","listUsd");
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("tienTe","$");
        model.addAttribute("cryptoList",cryptoList);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("keyword",keyword);
        String reverseSortDir = sortDir.equals("asc") ? "desc":"asc";
        model.addAttribute("reverseSortDir",reverseSortDir);
        return "trangchu1";
    }


//    @GetMapping("/pagesearch/{pageNumber}")
//    public String listByPageSearch(@PathVariable("pageNumber") int pageNumber, Model model,
//                             @Param("sortField") String sortField,
//                             @Param("sortDir") String sortDir, @RequestParam(required = false) String keyword){
//        int currentPage = pageNumber;
//
//        Page<CryptoUSD> page = cryptoService.listCryptoUSDSearch(pageNumber,sortField,sortDir,keyword);
//        List<CryptoUSD> cryptoList = page.getContent();
//
//        long totalItems = page.getTotalElements();
//        int totalPages = page.getTotalPages();
//        model.addAttribute("urlTienTe","listUsd");
//        model.addAttribute("currentPage",currentPage);
//        model.addAttribute("totalItems",totalItems);
//        model.addAttribute("totalPages",totalPages);
//        model.addAttribute("tienTe","$");
//        model.addAttribute("cryptoList",cryptoList);
//        model.addAttribute("sortField",sortField);
//        model.addAttribute("sortDir",sortDir);
//        model.addAttribute("keyword",keyword);
//        String reverseSortDir = sortDir.equals("asc") ? "desc":"asc";
//        model.addAttribute("reverseSortDir",reverseSortDir);
//        return "trangchu1";
//    }



    @GetMapping("/cryto/grap")
    public String graphCrypto(@RequestParam String id,Model model) {
        CryptoUSD crypto =cryptoService.findByCrypotoUsdId(id);
        String tienTe = "usd";
        model.addAttribute("kyHieu","$");
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
        model.addAttribute("urlTienTe","listUsd");
        return "index";
    }
}
