package com.kata.spring.swagger.service;

import org.springframework.stereotype.Service;

@Service
public class KataService {

    public static String formatResult(int intParameter,String res){
        StringBuilder str = new StringBuilder();
        return str.append(String.valueOf(intParameter)).append("  \"").append(res).append("\"").toString();
    }
    public  String getCharactersFromString(int intNumber) {
        if (intNumber < 1 || intNumber > 99) {
            throw new IllegalArgumentException("Le nombre doit etre compris entre 0 et 100 ");
        }
        String parseNumberToString = String.valueOf(intNumber);

        StringBuilder stringResult = new StringBuilder();
        if (intNumber % 3 == 0) {
            stringResult.append("FOO");
        }

        if (intNumber % 5 == 0) {
            stringResult.append("BAR");
        }

        parseNumberToString.chars()
                .mapToObj(chara -> (char) chara)
                .forEach(c -> {
                    switch (c) {
                        case '3':
                            stringResult.append("FOO");
                            break;
                        case '5':
                            stringResult.append("BAR");
                            break;
                        case '7':
                            stringResult.append("QUX");
                            break;
                        default: break;
                    }
                });

        if (stringResult.length() == 0) {
            return formatResult(intNumber,parseNumberToString);
        }

        return formatResult(intNumber,stringResult.toString());
    }

}
