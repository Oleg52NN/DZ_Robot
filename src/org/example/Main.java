package org.example;

import java.util.*;

import static java.lang.System.out;


public class Main {
    public static final int QUANTITY_STRING = 1000;
    public static final Map<Integer, Integer> sizeToFreq = new TreeMap<>();
    public static int counter;

    public static void main(String[] args) throws InterruptedException {

        Thread[] myThreads = new myThread[QUANTITY_STRING];
        Thread leader = new ThreadLeader();

        synchronized (sizeToFreq) {
            for (int i = 0; i < 1000; i++) {
                myThreads[i] = new myThread();
                myThreads[i].start();
                sizeToFreq.wait();
                counter = i;
                if (i < 1) {
                    leader.start();
                }

            }

        }
        leader.interrupt();
        int keyMaxValue = maxMapValue();

        out.println("Самое частое количество повторений " + keyMaxValue + " (встретилось " + sizeToFreq.get(keyMaxValue) + " раз)");
        //sizeToFreq.remove(keyMaxValue); // Если не нужна эта пара в общем спсике
        out.println("Этот и другие случаи:");
        sizeToFreq.forEach((key1, value) -> out.println("- " + key1 + " (" + value + " раз)"));


    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    public static int maxMapValue() {
        Optional<Object> max = sizeToFreq.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
        return (int) max.get();
    }
}
