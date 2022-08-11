package com.example.springboottesting.controller;

import com.example.springboottesting.model.Demo;
import com.example.springboottesting.request.DemoRequest;
import com.example.springboottesting.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    @PostMapping
    public ResponseEntity<Demo> createDemo(@RequestBody DemoRequest demoRequest) {
        return new ResponseEntity<>(
                demoService.createDemo(demoRequest.getValue()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demo> getDemoById(@PathVariable String id) {
        return new ResponseEntity<>(
                demoService.getDemo(id),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<Demo>> getDemosByValue(@RequestParam String value) {
        return new ResponseEntity<>(
                demoService.getDemos(value),
                HttpStatus.OK
        );
    }

    @GetMapping("/spring-data")
    public ResponseEntity<List<Demo>> getDemosBySpringData(@RequestParam String value) {
        return new ResponseEntity<>(
                demoService.getDemosBySpringData(value),
                HttpStatus.OK
        );
    }

}
