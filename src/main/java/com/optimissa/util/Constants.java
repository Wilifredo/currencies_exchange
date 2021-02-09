package com.optimissa.util;

import java.math.BigDecimal;

public class Constants {

    public static String CSV_SEPARATOR = ", ";
    public static String DATE_FORMAT = "dd-MM-YYYY HH:mm:ss:SS";
    public static BigDecimal MARGIN = new BigDecimal("0.10");
    public static BigDecimal COMMISSION = new BigDecimal("0.10");
    //This path must be changed to the prices.csv path for the program to load the prices
    public static String CSV_PATH="E:\\GitOptimissa\\currencies_exchange\\src\\main\\java\\src\\prices.csv";
}
