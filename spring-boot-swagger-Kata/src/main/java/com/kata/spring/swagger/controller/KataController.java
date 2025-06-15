package com.kata.spring.swagger.controller;

import com.kata.spring.swagger.service.KataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "kata-service", description = "management Kata APIs")
@RestController("/api")
public class KataController {
  private final KataService kataService;

  public KataController(KataService kataService){
      this.kataService = kataService;
  }

  @GetMapping("/strings")
  public ResponseEntity<String> parseNumbers(@RequestParam(required = false) int number) {
      try {
        return ResponseEntity.status(HttpStatus.OK).body(this.kataService.getCharactersFromString(number));
      } catch (IllegalArgumentException e){
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nombre doit etre compris entre 0 et 100 ");
      }

  }



}
