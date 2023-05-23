package com.business.app.school.DoctolibBackCodebase.controller.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> register(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}
