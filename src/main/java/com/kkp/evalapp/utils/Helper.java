package com.kkp.evalapp.utils;

import java.util.List;
import java.util.Optional;

import com.kkp.evalapp.model.Simple;

public class Helper {
    public static Integer getIdFromSimpleList(List<Simple> list, String name){
        Optional<Integer> optionalId = list.stream()
            .filter(simple -> simple.getName().equals(name))
            .map(Simple::getId)
            .findFirst();

        return optionalId.orElse(-1);
    }
}
