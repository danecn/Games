package Hangman;

//import java.text.*;
import javax.swing.*;
//import java.util.*;

public class Hangman {

	String guess, printHint, samePrint, winner;
	boolean playAgain = true;
	boolean player1Turn = true;
	boolean win = false;
	int numChar, wrong, askAgain;
	char letter;

	public Hangman() {
		guess = "";
		printHint = "";
		samePrint = "";
		winner = "";
		numChar = 0;
		wrong = 0;
		askAgain = 0;
	}

	public void runHangman() {

		StringBuffer hint;
		Player player1;
		Player player2;
		player1 = new Player(JOptionPane.showInputDialog("Player 1, enter your name"));
		while(player1.getName().length() == 0) {
			player1 = new Player(JOptionPane.showInputDialog("Player 1, enter your name"));
		}
		player2 = new Player(JOptionPane.showInputDialog("Player 2, enter your name"));
		while(player2.getName().length() == 0) {
			player2 = new Player(JOptionPane.showInputDialog("Player 2, enter your name"));
		}

		while(playAgain) {
			//player 1 turn
			while(player1Turn) {
				//set secret word and make sure it is characters only
				player1.setSecretWord(JOptionPane.showInputDialog(player1.getName() + ", please input a secret word."));
				for(int l = 0; l < player1.secretWord().length(); l++) {
					if(player1.secretWord().length() == 0 || player1.secretWord().charAt(l) < 65 || player1.secretWord().charAt(l) > 122 || (player1.secretWord().charAt(l) > 90 && player1.secretWord().charAt(l) < 97)) {
						player1.setSecretWord(JOptionPane.showInputDialog(player1.getName() + ", please input a secret word."));
					}
				}
				//print dashes for word
				player1.printDash();
				hint = new StringBuffer(player1.getPrintDash());
				printHint = "" + hint;

				while(wrong < 10 && win == false) {

					System.out.println(hint);
					samePrint = printHint;
					//take in guess, make sure only 1 charcter and characters only
					guess = JOptionPane.showInputDialog(player2.getName() + ", guess a letter.");
					while(guess.length() != 1 || guess.charAt(0) < 65 || guess.charAt(0) > 122 || (guess.charAt(0) > 90 && guess.charAt(0) < 97)) {
						guess = JOptionPane.showInputDialog(player2.getName() + ", guess a letter.");
					} System.out.println(guess);
					//check if guess is in the word
					numChar = player1.secretWord().length();

					for(int i = 0; i < numChar; i++) {

						letter = player1.secretWord().charAt(i);

						if(guess.equalsIgnoreCase("" + letter)) {
							hint.setCharAt(i, guess.charAt(0));
							printHint = "" + hint;
							//printHint = printHint.substring(0,i) + guess + printHint.substring(i+1, numChar);
						}
					}
					if(printHint.equalsIgnoreCase(samePrint)) {
						wrong++;
						System.out.println((10 - wrong) + " tries left");
					}

					if(printHint.equalsIgnoreCase(player1.secretWord())){
						win = true;
					}
				}
				if(win) {
					System.out.println(player1.secretWord());
					System.out.println("Bingo! You won.\n");
					player2.addWin();
				} else if (wrong >= 10) {
					System.out.println(printHint);
					System.out.print("You have reached 10 tries. You lose. The secret word was " + player1.secretWord() + ".\n\n");
					player2.addLoss();
				}

				player2.addWrong(wrong);
				wrong = 0;
				player1.reset();
				player1Turn = false;
				win = false;
			}
			//player 2 turn
			while(!player1Turn) {
				//set secret word and make sure it is characters only
				player2.setSecretWord(JOptionPane.showInputDialog(player2.getName() + ", please input a secret word."));
				for(int l = 0; l < player2.secretWord().length(); l++) {
					if(player2.secretWord().length() == 0 || player2.secretWord().charAt(l) < 65 || player2.secretWord().charAt(l) > 122 || (player2.secretWord().charAt(l) > 90 && player2.secretWord().charAt(l) < 97)) {
						player2.setSecretWord(JOptionPane.showInputDialog(player2.getName() + ", please input a secret word."));
					}
				}
				//print dashes for word
				player2.printDash();
				hint = new StringBuffer(player2.getPrintDash());
				printHint = "" + hint;

				while(wrong < 10 && win == false) {

					System.out.println(hint);
					samePrint = printHint;
					//take in guess, make sure only 1 charcter and characters only
					guess = JOptionPane.showInputDialog(player1.getName() + ", guess a letter.");
					while(guess.length() != 1 || guess.charAt(0) < 65 || guess.charAt(0) > 122 || (guess.charAt(0) > 90 && guess.charAt(0) < 97)) {
						guess = JOptionPane.showInputDialog(player1.getName() + ", guess a letter.");
					} System.out.println(guess);
					//check if guess is in the word
					numChar = player2.secretWord().length();

					for(int i = 0; i < numChar; i++) {

						letter = player2.secretWord().charAt(i);

						if(guess.equalsIgnoreCase("" + letter)) {
							hint.setCharAt(i, guess.charAt(0));
							printHint = "" + hint;
							//printHint = printHint.substring(0,i) + guess + printHint.substring(i+1, numChar);
						}
					}
					if(printHint.equalsIgnoreCase(samePrint)) {
						wrong++;
						System.out.println((10 - wrong) + " tries left");
					}

					if(printHint.equalsIgnoreCase(player2.secretWord())){
						win = true;
					}
				}
				if(win) {
					System.out.println(player2.secretWord());
					System.out.println("Bingo! You won.\n");
					player1.addWin();
				} else if (wrong >= 10) {
					System.out.println(printHint);
					System.out.print("You have reached 10 tries. You lose. The secret word was " + player2.secretWord() + ".\n\n");
					player1.addLoss();
				}

				player1.addWrong(wrong);
				wrong = 0;
				player2.reset();
				player1Turn = true;
				if(player1Turn) {
					askAgain = JOptionPane.showConfirmDialog(null, "Would you like to keep playing?", "Keep Playing", JOptionPane.YES_NO_OPTION);
					if(askAgain == JOptionPane.YES_OPTION) {
						playAgain = true;
					} else {
						playAgain = false;
					}
				}
				win = false;
			}
		}

		if(player1.getWin() > player2.getWin()) {
			winner = "The winner is " + player1.getName() + ".";
		} else if(player1.getWin() < player2.getWin()) {
			winner = "The winner is " + player2.getName() + ".";
		} else {
			if(player1.getWrong() < player2.getWrong()) {
				winner = "The winner is " + player1.getName() + ".";
			} else if(player1.getWrong() > player2.getWrong()) {
				winner = "The winner is " + player2.getName() + ".";
			} else {
				winner = "No one won. It was a draw.";
			}
		}
		JOptionPane.showMessageDialog(null, "\t\t     Wins   Losses   Guesses\n" +
											player1.getName() + "      " + player1.getWin() + "          " + player1.getLoss() + "          " + player1.getWrong() + "\n" +
											player2.getName() + "      " + player2.getWin() + "          " + player2.getLoss() + "          " + player2.getWrong() + "\n" +
											winner);
	}
}