package com.example.cryptoapi.repository;

import com.example.cryptoapi.model.CryptoUSD;
import com.example.cryptoapi.model.CryptoVND;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoVNDRepository extends JpaRepository<CryptoVND , String> {
    Page<CryptoVND> findAllByNameContains(String keyword, Pageable pageable);
}
