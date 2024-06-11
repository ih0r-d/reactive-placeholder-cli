package com.github.ih0rd.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.http.ResponseEntity;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.List;

import static com.github.ih0rd.app.utils.TableFormatter.*;

public class JsonFormatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    private static String toJson(Object response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> toListObjects(String json, Class<T> clazz) {
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return objectMapper.readValue(json, listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON", e);
        }
    }

    public static String formatOutput() {
        return "%s :: %s\n";
    }

    public static <T> Disposable subscribe(Flux<ResponseEntity<List<T>>> flux, Class<T> clazz) {
        return flux.doOnError(ex -> System.out.println(ex.getMessage()))
                .onErrorResume(ex -> Flux.empty())
                .subscribe(responseEntity -> {
                    List<T> body = responseEntity.getBody();
                    if (body != null) {
//                        System.out.printf(
//                                formatOutput(),
//                                responseEntity.getStatusCode(),
//                                toJson(responseEntity.getBody())
//                        );
//                        System.out.println(formatTable(body));
                        System.out.println("\n");
                        printTable(body);
                    }
                });
    }


}
