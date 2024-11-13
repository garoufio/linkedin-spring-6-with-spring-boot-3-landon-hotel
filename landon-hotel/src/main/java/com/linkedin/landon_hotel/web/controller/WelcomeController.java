package com.linkedin.landon_hotel.web.controller;

import io.micrometer.common.util.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
  
  @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
  @ResponseBody
  public String getWelcome(@RequestParam(value="name", required = false) String name) {
    String greeting;
    if (StringUtils.isNotBlank(name)) {
      greeting = "Hello " + name;
    }
    else {
      greeting = "Hello World";
    }
    return "<h1>" + greeting + "</h1>";
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
