package com;

import javax.swing.JOptionPane;

public class BearHunterNinja {

    public static void main(String[] args) {
        game();
    }

    public static void game() {
        String[] outcome = {"BEAR", "NINJA", "HUNTER"};
        int win = 0, lose = 0, round = 1;

        JOptionPane.showMessageDialog(null,
                "Welcome to the Bear, Ninja and Hunter Game!"
                + "\n   The ninja kills the hunter"
                + "\n   The bear eats the ninja"
                + "\n   The hunter shoots the bear",
                "BEAR, NINJA, AND HUNTER", JOptionPane.INFORMATION_MESSAGE);
        while (win <= 3 || lose <= 3) {
            int random = (int) (Math.random() * 3);
            // CHECKER
            System.out.println(random + 1);
            if (win == 3 || lose == 3) {
                if (win == 3) {
                    JOptionPane.showMessageDialog(null, "You Win at round " + (round - 1), "BEAR, NINJA, AND HUNTER", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Imagine losing to a computer", "ROUND " + (round - 1), JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            }
            int user = JOptionPane.showOptionDialog(null, "Choose one of the three choices", "ROUND " + round, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, outcome, outcome);
            if (user == random) {
                JOptionPane.showMessageDialog(null, "It's a TIE!!\nThe Computer picked " + outcome[random], "ROUND " + round, JOptionPane.INFORMATION_MESSAGE);
            } else if (user == 2 && random == 0 || user == 0 && random == 1 || user == 1 && random == 2) {
                JOptionPane.showMessageDialog(null, "You Won!\nThe Computer picked " + outcome[random], "ROUND " + round, JOptionPane.INFORMATION_MESSAGE);
                win++;
            } else {
                JOptionPane.showMessageDialog(null, "You Lose!\nThe Computer picked " + outcome[random], "ROUND " + round, JOptionPane.INFORMATION_MESSAGE);
                lose++;
            }
            round++;
        }
    }
}