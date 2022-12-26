package com.matthewchhay.resourcingapi.temps;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TempService {

    public String hello() {
        return "Hello World from Temp!";
    }
}
