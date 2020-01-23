package com.github.jorgenringen.lambda.stream;

import java.io.DataInputStream;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Util {

    public static List<String> mapToUppercase(List<String> input) {
        return input.stream().map(s->s.toUpperCase()).collect(Collectors.toList());
    }

    public static List<String> removeElementsWithMoreThanFourCharacters(List<String> input) {
        return input.stream().filter(s->s.length()<4).collect(Collectors.toList());
    }

    public static Long countStringsWithFiveCharacters(List<String> input) {
        return input.stream().filter(s->s.length()==5).count();
    }

    public static List<String> sortStrings(List<String> input) {
        return input.stream().sorted().collect(Collectors.toList());
    }

    public static List<Integer> sortIntegers(List<String> input) {
        return input.stream().map(Integer::valueOf).sorted().collect(Collectors.toList());
    }

    public static List<Integer> sortIntegersDescending(List<String> input) {
        return input.stream().map(Integer::valueOf).sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }).collect(Collectors.toList());
    }

    public static Integer sum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::valueOf).sum();
    }

    public static Integer multiply(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::valueOf).reduce((s,r)->s*r).getAsInt();
    }

    public static List<String> flattenToSingleCollection(List<List<String>> input) {
        return input.stream().flatMap(l -> l.stream()).collect(Collectors.toList());
    }

    public static String separateNamesByComma(List<Person> input) {
        return input.stream().map(Person::getName).collect(Collectors.joining(", ", "Names: ","."));
    }

    public static String findNameOfOldestPerson(List<Person> input) {

        return input.stream().max(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        }).get().getName();
    }

    public static List<String> filterPeopleLessThan18YearsOld(List<Person> input) {
        return input.stream().filter(p -> p.getAge()<18).map(s->s.getName()).collect(Collectors.toList());
    }

    public static IntSummaryStatistics getSummaryStatisticsForAge(List<Person> input) {
        return input.stream().mapToInt(Person::getAge).summaryStatistics();
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> input) {
        return input.stream().collect(Collectors.partitioningBy(s->s.getAge()>18));
    }

    public static Map<String, List<Person>> partitionByNationality(List<Person> input) {
        return input.stream().collect(Collectors.groupingBy(Person::getCountry));
    }
}
