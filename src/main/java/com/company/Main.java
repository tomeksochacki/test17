package com.company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.stream.Collectors;

import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
	Logger logger = LoggerFactory.getLogger(Main.class);
	logger.info("Wtaj");

        List<String> names = List.of("Anna", "Bartosz", "Andrzej", "Katarzyna");

        // Implementacja funkcjonalności filtrowania (warunek: imię zaczyna się na 'A')
        Predicate<String> startsWithA = name -> name.startsWith("A");

        // Użycie funkcjonalności w strumieniu (Stream API)
        names.stream()
                .filter(startsWithA)
                .forEach(System.out::println);

        List<String> filteredNames = names.stream()
                .filter(name -> name.equals("Bartosz"))
                .collect(Collectors.toList());

        System.out.println(filteredNames);


    }


}
