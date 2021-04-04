package com.kevzz.stockcalc.nse.controller;

import com.kevzz.stockcalc.nse.service.indices.derivative.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/nse")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/io/nifty")
    public ResponseEntity<HashMap<String, Object>> getNiftyIndexOptions() {

        return ResponseEntity.ok(
                this.dataService.evaluateIndicesData()
        );
    }
}
