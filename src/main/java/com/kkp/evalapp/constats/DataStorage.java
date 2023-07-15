package com.kkp.evalapp.constats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kkp.evalapp.model.MenuItem;
import com.kkp.evalapp.model.Simple;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DataStorage {
    
    private static DataStorage instance;
    private List<Simple> positions    ;
    private List<Simple> divisions    ;
    private List<Simple> departements ;
    private List<Simple> levels       ;
    private List<MenuItem> menuItems  ;
    private Map<String,Object> cache;

    private DataStorage (){
        positions    = new ArrayList<Simple>();
        divisions    = new ArrayList<Simple>();
        departements = new ArrayList<Simple>();
        levels       = new ArrayList<Simple>();
        menuItems    = new ArrayList<MenuItem>();
        cache        = new HashMap<>();
    }

    public static DataStorage getInstance() {
        if (instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

}
