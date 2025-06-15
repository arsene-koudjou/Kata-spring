package com.kata.spring.swagger.batch;


import com.kata.spring.swagger.model.NumberItem;
import com.kata.spring.swagger.service.KataService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntegerToStringProcessor implements ItemProcessor<NumberItem, String> {
    @Autowired
    private KataService kataService;
    @Override
    public String process(NumberItem item) {
        if (item.getNumber() < 1 || item.getNumber() > 99) {
            throw new IllegalArgumentException("Le nombre doit etre compris entre 0 et 100 ");
        }
        return this.kataService.getCharactersFromString(item.getNumber());
    }

}
