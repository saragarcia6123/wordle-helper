package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Solver solver = new Solver();
        String guess = "";
        int[] outcome = new int[5];
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        do {
            System.out.print("\nEnter guess: ");
            guess = scanner.next();
            System.out.print("\nEnter outcomes: ");
            userInput = scanner.next();
            for (int i = 0; i < 5; i++) {
                outcome[i] = Integer.parseInt(userInput.substring(i,i+1));
            }
            for (int i = 0; i < 4; i++) {
                solver.removeImpossibleSolutions(guess, outcome);
            }
            System.out.println("Possible solutions:");
            for (String s : solver.getValidWords()) {
                System.out.println(s);
            }
            System.out.println("\nContinue?");
            userInput = scanner.next();
        } while (!userInput.equals("n"));

    }

}
