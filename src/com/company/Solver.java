package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solver {

    private ArrayList<String> validWords;
    private int validWordsSize;

    public Solver() {
        validWords = new ArrayList<>();
        validWordsSize = 0;
        try {
            Scanner scanner = new Scanner(new File("valid_solutions.csv"));
            scanner.useDelimiter("\n");
            String next = "";
            while (scanner.hasNext()) {
                next = scanner.next();
                validWords.add(next);
                validWordsSize++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeImpossibleSolutions(String guess, int[] outcome) {
        //for each letter
        for (int i = 0; i < 5; i++) {

            //if the solution does not contain a letter
            if (outcome[i] == 0){

                //loop through all the words in the pool
                for (int j = 0; j < validWordsSize; j++) {

                    //if it does contain the letter which the solution does not
                    if (containsLetter(validWords.get(j), guess.charAt(i))) {

                        //remove it from the pool
                        validWords.remove(validWords.get(j));
                        validWordsSize--;

                    }
                }
            }

            //if the solution contains a letter but not at that position
             else if (outcome[i] == 1) {

                //loop through all the words in the pool
                for (int j = 0; j < validWordsSize; j++) {

                    //if it does contain the letter at that position
                    if (containsLetterAtPosition(validWords.get(j), guess.charAt(i), i)) {

                        //remove it from the pool
                        validWords.remove(validWords.get(j));
                        validWordsSize--;
                    }
                }
            }

            //if the solution contains a letter at a certain position
            else if (outcome[i] == 2) {

                //loop through all the words in the pool
                for (int j = 0; j < validWordsSize; j++) {

                    //if it does not contain the letter at that position
                    if (!containsLetterAtPosition(validWords.get(j), guess.charAt(i), i)) {

                        //remove it from the pool
                        validWords.remove(validWords.get(j));
                        validWordsSize--;
                    }
                }

            }

            //if the solution contains a letter (regardless of position)
            if (outcome[i] > 0) {

                //loop through all the words in the pool
                for (int j = 0; j < validWordsSize; j++) {

                    //if it does not contain the letter
                    if (!containsLetter(validWords.get(j), guess.charAt(i))) {

                        //remove it from the pool
                        validWords.remove(validWords.get(j));
                        validWordsSize--;

                    }
                }

            }

        }
    }

    public boolean containsLetter(String s,  char letter) {
        for (char c : s.toCharArray()) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }

    public boolean containsLetterAtPosition(String s, char letter, int position) {
        return containsLetter(Character.toString(s.charAt(position)), letter);
    }

    public ArrayList<String> getValidWords() {
        return validWords;
    }
}
