package com.github.ih0rd.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.lang.reflect.Field;
import java.util.List;

@UtilityClass
public class TableFormatter {


    public static <T> void printTable(List<T> records) {
        if (records == null || records.isEmpty()) {
            System.out.println("No records to display");
            return;
        }

        Class<?> clazz = records.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        // Calculate the maximum length for each column
        int[] maxLengths = new int[fields.length];
        for (int i = 0; i < fields.length; i++) {
            maxLengths[i] = fields[i].getName().length();
        }
        for (T record : records) {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                try {
                    Object value = fields[i].get(record);
                    if (value != null) {
                        maxLengths[i] = Math.max(maxLengths[i], value.toString().length());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Print header
        printSeparatorLine(maxLengths);
        for (int i = 0; i < fields.length; i++) {
            System.out.print(String.format("| %-" + maxLengths[i] + "s ", fields[i].getName()));
        }
        System.out.println("|");
        printSeparatorLine(maxLengths);

        // Print rows
        for (T record : records) {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                try {
                    Object value = fields[i].get(record);
                    System.out.print(String.format("| %-" + maxLengths[i] + "s ", value != null ? value.toString() : ""));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("|");
        }
        printSeparatorLine(maxLengths);
    }

    private static void printSeparatorLine(int[] maxLengths) {
        for (int length : maxLengths) {
            System.out.print("+");
            for (int i = 0; i < length + 2; i++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }
}


