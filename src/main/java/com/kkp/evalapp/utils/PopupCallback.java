package com.kkp.evalapp.utils;

import java.util.Map;

public interface PopupCallback {
    void setCallerParameter(Map<String,Object> parameter);
    void setCallback(PopupCallback callback);
    void onPopupClosed(Map<String,Object> result);
}

