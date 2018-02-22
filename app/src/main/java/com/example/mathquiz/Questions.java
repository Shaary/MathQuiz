package com.example.mathquiz;

import java.util.HashSet;
import java.util.Random;

public class Questions {
    private int numOne;
    private int numTwo;
    private Random randomGenerator = new Random();
    private HashSet<Integer> answers;

    public String getQuestion() {
        do {
            numOne = randomGenerator.nextInt(100);
            numTwo = randomGenerator.nextInt(100);
        } while((numOne + numTwo) >= 151);
        return numOne + " + " + numTwo;
    }

    public int getCorrectAnswer() {
        return numOne + numTwo;
    }

    public HashSet<Integer> getAnswers() {
        answers = new HashSet<>();
        answers.add(numOne + numTwo);

        while (answers.size() != 3) {
            int randomNum = randomGenerator.nextInt(randomRange(numOne, numTwo));
            if (Math.abs(randomNum - (numOne + numTwo)) > 10) {
                answers.add(randomNum + 50);
            }
            else {
                answers.add(randomNum);
            }
        }
        return answers;

    }
    public static int randomRange(int numOne, int numTwo) {
        int min;
        int max;
        if (numOne > numTwo) {
            max = numOne;
            min = numTwo;
        }
        else  {
            max = numTwo;
            min = numOne;
        }
        Random rnd = new Random();
        int randomNumber = min + rnd.nextInt(max + 1 - min);
        return randomNumber;
    }
}
