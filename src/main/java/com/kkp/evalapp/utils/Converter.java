package com.kkp.evalapp.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.kkp.evalapp.model.Simple;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Converter {

    public static ObservableList<String> convertListToObservableList(List<Simple> list) {

        ObservableList<String> observableList = list.stream()
                .map(Simple::getName)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        return observableList;
    }

}
