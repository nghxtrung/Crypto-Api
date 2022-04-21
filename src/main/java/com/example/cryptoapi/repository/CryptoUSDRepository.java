package com.example.cryptoapi.repository;


import com.example.cryptoapi.model.CryptoUSD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface CryptoUSDRepository extends JpaRepository<CryptoUSD, String> {
    Page<CryptoUSD> findAllByNameContains(String keyword, Pageable pageable);
}
