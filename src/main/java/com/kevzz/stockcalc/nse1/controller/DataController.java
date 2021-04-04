package com.kevzz.stockcalc.nse1.controller;

import com.kevzz.stockcalc.nse1.service.indices.derivative.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/nse1")
public class DataController {

    @Autowired
    private DataService dataService;

    public DataController() {
    }

    @GetMapping("/io/nifty")
    public ResponseEntity<HashMap<String, Object>> getNiftyIndexOptions() {
        return ResponseEntity.ok(this.dataService.evaluateIndicesData());
    }
}
