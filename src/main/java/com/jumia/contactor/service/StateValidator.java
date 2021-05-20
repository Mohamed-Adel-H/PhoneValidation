package com.jumia.contactor.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class StateValidator {
    Map<String, String> stateRegex;

    public StateValidator() {
        stateRegex = new HashMap<>();
        stateRegex.put("237", "^[2368]\\d{7,8}$");
        stateRegex.put("251", "^[1-59]\\d{8}$");
        stateRegex.put("212", "^[5-9]\\d{8}$");
        stateRegex.put("258", "^[28]\\d{7,8}$");
        stateRegex.put("256", "^\\d{9}$");
    }

    public Boolean isValid(String countryCode, String state) {
        return Pattern.compile(stateRegex.get(countryCode)).matcher(state).matches();
    }
}
