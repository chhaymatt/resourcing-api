package com.matthewchhay.resourcingapi.jobs;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService {
    // @Autowired
    // private JobRepository repository;

    public String hello() {
        return "Hello World from Job";
    }
}
