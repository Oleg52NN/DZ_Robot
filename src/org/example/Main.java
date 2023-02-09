package org.example;

import java.util.*;

import static java.lang.System.out;


public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new TreeMap<>();
    public static final int QUANTITY_STRING = 1000;

    public static void main(String[] args) throws InterruptedException {
        Thread[] myThreads = new myThread[QUANTITY_STRING];
        synchronized (sizeToFreq) {
            for (int i = 0; i < QUANTITY_STRING; i++) {
                myThreads[i] = new myThread();
                myThreads[i].start();
                sizeToFreq.wait();
            }
        }

        Optional<Object> max = sizeToFreq.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        int keyMaxValue = (int) max.get();

        out.println("Самое частое количество повторений " + keyMaxValue + " (встретилось " + sizeToFreq.get(keyMaxValue) + " раз)");
        //sizeToFreq.remove(keyMaxValue);
        out.println("Другие размеры:");
        sizeToFreq.entrySet().forEach(key -> out.println("- " + key.getKey() + " (" + key.getValue() + " раз)"));

    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}