package com.kata.spring.swagger.batch.listeners;

import org.springframework.batch.core.SkipListener;

public class SkipLoggingListener implements SkipListener<Object, Object> {

    @Override
    public void onSkipInRead(Throwable t) {
        System.err.println("Erreur rencontrée à la lecture : " + t.getMessage());
    }

    @Override
    public void onSkipInWrite(Object item, Throwable t) {
        System.err.println("Erreur rencontrée pendant l’écriture de : " + item);
    }

    @Override
    public void onSkipInProcess(Object item, Throwable t) {
        System.err.println("Élément ignoré par ce que : " + t.getMessage());
    }
}