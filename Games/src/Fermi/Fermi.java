package Fermi;
import javax.swing.*;
//import java.text.*;
import java.util.*;

public class Fermi {

	Guess guess;
	RandomNum random;
	String nano, pico, fermi;
	int askAgain;
	int count;

	boolean playAgain;
	boolean win;

	public Fermi() {
		nano = "Nano";
		pico = "Pico";
		fermi = "Fermi";
		askAgain = 0;
		count = 0;
		playAgain = true;
		win = false;
	}

	public  void runFermi() {

		guess = new Guess();
		random = new RandomNum();

		while(playAgain == true) {

			random.setNumber();
			guess.setRandom(random);

			while(win == false) {
				guess.setGuess(JOptionPane.showInputDialog("Guess a number between 000 and 999"));
				while(guess.getGuess() > 999) {
					guess.setGuess(JOptionPane.showInputDialog("Learn to read. Guess a number between 000 and 999"));
				}
				System.out.println("Guess: " + guess.getGuessStr());
				guess.setHint();
				guess.printHint();
				count++;

				if(guess.getHint1() == fermi && guess.getHint2() == fermi  && guess.getHint3() == fermi) {
					win = true;
				}
			}

			if(win == true) {
				System.out.println("You guessed correctly. It took you " + count + " tries.");
				askAgain = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
			}

			if(askAgain == JOptionPane.YES_OPTION) {
				playAgain = true;
				win = false;
				count = 0;
			} else {
				playAgain = false;
			}
		}
	}
}

class Guess {

	RandomNum random = new RandomNum();

	String hint1, hint2, hint3;
	String nano = "Nano", pico = "Pico", fermi = "Fermi";

	public String guessStr;

	public int guess, guess1, guess2, guess3, pos1, pos2, pos3;

	public Guess() {

		guessStr = "";
		guess1 = 0;
		guess2 = 0;
		guess3 = 0;

		pos1 = 0;
		pos2 = 0;
		pos3 = 0;

		hint1 = "";
		hint2 = "";
		hint3 = "";
	}

	public void setGuess(String guessStr) {
		this.guessStr = guessStr;

		guess = Integer.parseInt(guessStr);
		guess1 = Integer.parseInt(guessStr.substring(0,1));
		guess2 = Integer.parseInt(guessStr.substring(1,2));
		guess3 = Integer.parseInt(guessStr.substring(2,3));
	}

	public String getGuessStr() {
		return guessStr;
	}

	public int getGuess() {
		return guess;
	}

	public void setHint() {

		if(guess1 == random.getRand1()) {
			hint1 = fermi;
		} else if(guess1 != random.getRand1() && (guess1 == random.getRand2() || guess1 == random.getRand3())) {
			hint1 = pico;
		} else if(guess1 != random.getRand1() && guess1 != random.getRand2() && guess1 != random.getRand3()) {
			hint1 = nano;
		}

		if(guess2 == random.getRand2()) {
			hint2 = fermi;
		} else if(guess2 != random.getRand2() && (guess2 == random.getRand1() || guess2 == random.getRand3())) {
			hint2 = pico;
		} else if(guess2 != random.getRand1() && guess2 != random.getRand2() && guess2 != random.getRand3()) {
			hint2 = nano;
		}

		if(guess3 == random.getRand3()) {
			hint3 = fermi;
		} else if(guess3 != random.getRand3() && (guess3 == random.getRand1() || guess3 == random.getRand2())) {
			hint3 = pico;
		} else if(guess3 != random.getRand1() && guess3 != random.getRand2() && guess3 != random.getRand3()) {
			hint3 = nano;
		}
	}

	public void printHint() {
		int min = 1, max = 3;

		pos1 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
		pos2 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
		while(pos2 == pos1)
			pos2 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
		pos3 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
		while(pos3 == pos1 || pos3 == pos2)
			pos3 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);

		if(pos1 < pos2 && pos2 < pos3) { 		//1<2<3
			System.out.println(hint1 + " " + hint2 + " " + hint3);
		} else if(pos1 < pos3 && pos3 < pos2) { //1<3<2
			System.out.println(hint1 + " " + hint3 + " " + hint2);
		} else if(pos2 < pos1 && pos1 < pos3) { //2<1<3
			System.out.println(hint2 + " " + hint1 + " " + hint3);
		} else if(pos2 < pos3 && pos3 < pos1) { //2<3<1
			System.out.println(hint2 + " " + hint3 + " " + hint1);
		} else if(pos3 < pos2 && pos2 < pos1) { //3<2<1
			System.out.println(hint3 + " " + hint2 + " " + hint1);
		} else if(pos3 < pos1 && pos1 < pos2) { //3<1<2
			System.out.println(hint3 + " " + hint1 + " " + hint2);
		}
	}

	public void setRandom(RandomNum random) {
		this.random = random;
	}

	public String getHint1() {
		return hint1;
	}
	public String getHint2() {
		return hint2;
	}
	public String getHint3() {
		return hint3;
	}
}

class RandomNum {

	Random random = new Random();

	public int rand1, rand2, rand3;

	public RandomNum() {
		rand1 = 0;
		rand2 = 0;
		rand3 = 0;
	}

	public void setNumber() {
		int min = 0, max = 9;

		rand1 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
		rand2 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
		while(rand2 == rand1)
			rand2 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
		rand3 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
		while(rand3 == rand1 || rand3 == rand2)
			rand3 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}

	public int getRand1() {
		return rand1;
	}

	public int getRand2() {
		return rand2;
	}

	public int getRand3() {
		return rand3;
	}
}