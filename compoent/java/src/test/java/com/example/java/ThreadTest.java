package com.example.java;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {
    Object add = new Object();
    Object even = new Object();
    private int max = 100;
    private AtomicInteger status = new AtomicInteger(1);

    @Test
    public void main() {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new ThreadTest.MyPrinter("偶数MyPrinter", 0));
        executorService.submit(new ThreadTest.MyPrinter("奇数MyPrinter", 1));
        executorService.shutdown();
    }

    class MyPrinter implements Runnable {
        private String name;
        private int type;

        public MyPrinter(String name, int type) {
            this.name = name;
            this.type = type;

        }

        @Override
        public void run() {
            if (type == 1) {

                while (status.get() <= max) {
                    if (status.get() % 2 == 0) {
                        synchronized (add) {
                            try {
                                add.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println(name + "-" + status.getAndIncrement());
                        synchronized (even) {
                            even.notify();
                        }
                    }
                }
            } else {
                while (status.get() <= max) {
                    if (status.get() % 2 != 0) {
                        synchronized (even) {
                            try {
                                even.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println(name + "-" + status.getAndIncrement());
                        synchronized (add) {
                            add.notify();
                        }
                    }
                }
            }
        }
    }


}
