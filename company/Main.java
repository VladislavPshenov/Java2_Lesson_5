package com.company;

import java.util.Arrays;

public class Main {

    static final int size = 10000;
    static final int h = size / 2;

    public static void main (String[] args) {

//    public float [] arrOne = new float [h];
//    public float [] arrTwo = new float [h];

        //    public float[] getArrOne() {
//        return arrOne;
//    }
//
//    public float[] getArrTwo() {
//        return arrTwo;
//    }

        System.out.println("Выполнение первого метода:");
        methodFirst();
        System.out.println();
        System.out.println("Выполнение второго метода:");
        Main main = new Main();
        main.secondMethod();


    }

    static void methodFirst () {
        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long finish = System.currentTimeMillis();
        System.out.println("Время начала работы: " + start);
        System.out.println("Время окончания работы: " + finish);
        System.out.println("Время работы: " + (finish - start));

    }

    static void calculatingMethod (float[] arr) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long finish = System.currentTimeMillis();
        System.out.println("Время работы потока: " + (finish - start));

    }


    void secondMethod () {
        float[] arr = new float[size];
        float [] arrOne = new float [h];
        float [] arrTwo = new float [h];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long start = System.currentTimeMillis();
        System.out.println("Время начала работы: " + start);
        System.arraycopy(arr, 0, arrOne, 0, h);
        System.arraycopy(arr, h, arrTwo, 0, h);
        long separation = System.currentTimeMillis();
        System.out.println("Время разделения массива на 2: " + (separation - start));

        MyThread1 t1 = new MyThread1();
        t1.start();

        MyThread2 t2 = new MyThread2();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e){
            System.out.println(String.format("Исключение в потоках.", e.getMessage()));
        }

        long unification = System.currentTimeMillis();
        System.arraycopy(arrOne, 0, arr, 0, h);
        System.arraycopy(arrTwo, 0, arr, h, h);
        long finish = System.currentTimeMillis();
        System.out.println(String.format("Время склейки: " + (finish - unification)));
        System.out.println(String.format("Время завершения работы: " + finish));
        System.out.println(String.format("Время работы: " + (finish - start)));

    }


}

class MyThread1 extends Thread {

    float[] arrOne = this.arrOne;

    public void run () {
        long start = System.currentTimeMillis();
        Main main = new Main();
        main.calculatingMethod(arrOne);

    }


}

class MyThread2 extends Thread {

    float[] arrTwo = this.arrTwo;

    public void run () {
        Main main = new Main();
        main.calculatingMethod(arrTwo);

    }

}
    






