package com.company.hw3;

import java.util.Scanner;

public class Drinking_Process_SinglyLinkedList {
    private static class Drink {
        private String name;
        private int times;

        public Drink(String name, int times) {
            this.name = name;
            this.times = times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public int getTimes() {
            return times;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SinglyLinkedList<Drink> drinks = new SinglyLinkedList<>();
        while (n != 0) {
            String name = sc.next();
            int times = sc.nextInt();
            Drink drink = new Drink(name, times);
            drinks.addLast(drink);
            n--;
        }
        drinkingProcess(drinks);
    }

    public static void drinkingProcess(SinglyLinkedList<Drink> drinks) {
        while (!drinks.isEmpty()) {
            Drink drink = drinks.removeFirst();
            System.out.println(drink.getName() + " is drinking.");
            drink.setTimes(drink.getTimes() - 1);
            if(drink.getTimes() == 0) {
                System.out.println(drink.getName() + " leaves the queue.");
            }
            else {
                System.out.println(drink.getName() + " moves to the end of the queue.");
                drinks.addLast(drink);
            }
        }
    }
}
