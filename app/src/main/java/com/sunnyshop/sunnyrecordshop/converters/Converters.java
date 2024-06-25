package com.sunnyshop.sunnyrecordshop.converters;

import androidx.databinding.InverseMethod;

public class Converters {

    @InverseMethod("stringToInteger")
    public static String integerToString(Integer value) {
        return value == null ? "" : String.valueOf(value);
    }

    public static Integer stringToInteger(String value) {
        try {
            return value == null || value.isEmpty() ? null : Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}