package com.upf1sh.shop.utils;

import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class L_Code {
    private L_Code() {
    }

    public static Map<String, String> map = new ConcurrentHashMap<String, String>();

    public static Map<String, String> getMap() {
        return map;
    }

    public static void setMap(Map<String, String> map) {
        L_Code.map = map;
    }
}
