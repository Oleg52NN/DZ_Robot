package org.example;

import static org.example.Main.generateRoute;
import static org.example.Main.sizeToFreq;

public class myThread extends Thread {
    final int route = 100;

    final String stringRoute = "RLRFR";

    @Override
    public void run() {
        String s = generateRoute(stringRoute, route);
        int quantityR = 0;

        for (int i = 0; i < route; i++) {
            if (s.charAt(i) == 'R') {
                quantityR++;
            }
        }
        synchronized (sizeToFreq) {

            if (sizeToFreq.containsKey(quantityR)) {
                sizeToFreq.put(quantityR, sizeToFreq.get(quantityR) + 1);
            } else {
                sizeToFreq.put(quantityR, 1);
            }
            sizeToFreq.notify();
        }
    }
}
