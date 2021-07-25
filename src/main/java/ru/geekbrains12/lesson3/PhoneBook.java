package ru.geekbrains12.lesson3;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    private HashMap<String, String> map = new HashMap<>();

    public void add(String phoneNumber, String secondName) {
        map.put(phoneNumber, secondName);
    }

    public void get(String secondName) {
        for (Map.Entry<String, String> me : map.entrySet()) {
            if (me.getValue().equals(secondName)) System.out.println(me.getValue() + " : " + me.getKey());
        }
    }

}
