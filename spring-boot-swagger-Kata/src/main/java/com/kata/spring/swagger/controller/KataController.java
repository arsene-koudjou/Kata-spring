package com.kata.spring.swagger.controller;

import com.kata.spring.swagger.service.KataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "kata-service", description = "management APIs")
@RestController("/api")
public class KataController {
  @Autowired
  private KataService kataService;

  @GetMapping("/strings")
  public String parseNumbers(@RequestParam(required = false) int number) {
    return this.kataService.getCharactersFromString(number);
  }

}
