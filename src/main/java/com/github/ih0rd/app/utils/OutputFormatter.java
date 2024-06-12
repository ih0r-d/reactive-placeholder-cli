package com.github.ih0rd.app.utils;

import com.github.ih0rd.app.exceptions.FieldAccessException;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OutputFormatter {

    public static <T> void printTable(List<T> records) throws FieldAccessException {
        if (records == null || records.isEmpty()) {
            System.out.println("No records to display");
            return;
        }

        Class<?> clazz = records.getFirst().getClass();
        Field[] fields = clazz.getDeclaredFields();

        // Calculate the maximum length for each column
        int[] maxLengths = new int[fields.length];
        System.out.println("\n");

        calculateMaxLengthColumns(records, fields, maxLengths);

        // Print header
        printSeparatorLine(maxLengths);
        printHeader(fields, maxLengths);
        printSeparatorLine(maxLengths);

        // Print rows
        printRows(records, fields, maxLengths);
        printSeparatorLine(maxLengths);
    }


    public static <T> Disposable subscribe(Flux<ResponseEntity<List<T>>> flux) {
        return flux.doOnError(ex -> System.out.println(ex.getMessage()))
                .onErrorResume(ex -> Flux.empty())
                .subscribe(responseEntity -> {
                    List<T> body = responseEntity.getBody();
                    if (body != null) {
                        try {
                            printTable(body);
                        } catch (FieldAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }


    private static <T> void printRows(List<T> records, Field[] fields, int[] maxLengths) throws FieldAccessException {
        for (T record : records) {
            List<String[]> rowLines = new ArrayList<>();
            int maxLines = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(record);
                    String[] lines = value != null ? value.toString().split("\n") : new String[]{""};
                    rowLines.add(lines);
                    maxLines = Math.max(maxLines, lines.length);
                } catch (IllegalAccessException e) {
                    throw new FieldAccessException("Cannot read value from field", e);
                }
            }

            for (int lineIndex = 0; lineIndex < maxLines; lineIndex++) {
                for (int i = 0; i < fields.length; i++) {
                    String[] lines = rowLines.get(i);
                    String line = lineIndex < lines.length ? lines[lineIndex] : "";
                    System.out.printf("| %-"+ maxLengths[i]+"s ", line);
                }
                System.out.println("|");
            }
        }
    }

    private static void printHeader(Field[] fields, int[] maxLengths) {
        for (int i = 0; i < fields.length; i++) {
            System.out.printf("| %-"+ maxLengths[i]+"s ", fields[i].getName());
        }
        System.out.println("|");
    }

    private static <T> void calculateMaxLengthColumns(List<T> records, Field[] fields, int[] maxLengths) throws FieldAccessException {
        for (int i = 0; i < fields.length; i++) {
            maxLengths[i] = fields[i].getName().length();
        }
        for (T record : records) {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                try {
                    Object value = fields[i].get(record);
                    if (value != null) {
                        for (String line : value.toString().split("\n")) {
                            maxLengths[i] = Math.max(maxLengths[i], line.length());
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new FieldAccessException("Cannot read value from field", e);
                }
            }
        }
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


