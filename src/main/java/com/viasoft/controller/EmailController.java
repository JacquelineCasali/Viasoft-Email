package com.viasoft.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {



    @GetMapping
    public String email() {

        return "Hello world";
    }
}
