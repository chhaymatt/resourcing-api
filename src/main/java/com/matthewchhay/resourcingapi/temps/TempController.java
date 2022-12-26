package com.matthewchhay.resourcingapi.temps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temps")
public class TempController {
    @Autowired
    private TempService service;

    @GetMapping("/test")
    public String test() {
        return this.service.hello();
    }
}
