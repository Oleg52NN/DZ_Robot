package org.example;

import static org.example.Main.*;

public class ThreadLeader extends Thread {
    int count = -1;

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (sizeToFreq) {
                int key = maxMapValue();
                if (count < counter) {
                    System.out.println((counter + 1) + " На данном этапе лидер по частоте повторений — " + key + " (" + sizeToFreq.get(key) + " раз)");
                    count = counter;
                }
            }
        }
    }
}
