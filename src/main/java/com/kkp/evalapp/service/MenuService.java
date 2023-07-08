package com.kkp.evalapp.service;

import java.util.List;

import com.kkp.evalapp.model.ColumnItem;
import com.kkp.evalapp.model.MenuItem;

public interface MenuService {
    List<MenuItem> getAllMenu();

    List<ColumnItem> getColumnByTabName(String tabName);
}
