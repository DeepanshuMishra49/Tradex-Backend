package com.tradex.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
  public String home(){
     return "welcome to trading app platform";
  }

    @GetMapping("/api")
    public String secure () {
        return "welcome to treading platform Secure";
    }
}
