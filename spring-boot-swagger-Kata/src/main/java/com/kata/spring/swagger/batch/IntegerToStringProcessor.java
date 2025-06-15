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
        return this.kataService.getCharactersFromString(item.getNumber());
    }

}
