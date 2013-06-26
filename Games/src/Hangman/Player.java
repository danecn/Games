package Hangman;

public class Player {

	int wins, loss, incorrect, numChar = 0;
	String name, secretWord = "", printDash = "";

	public Player(String name) {
		this.name = name;
		wins = 0;
		loss = 0;
		incorrect = 0;
	}

	public void setSecretWord(String secretWord) {
		this.secretWord = secretWord;
	}
	public String secretWord() {
		return secretWord;
	}
	public void printDash() {

		numChar = secretWord().length();

		for(int i = 0; i < numChar; i++) {
			printDash  += "-";
		}
	}
	public String getPrintDash() {
		return printDash;
	}
	public String getName() {
		return name;
	}
	public void addWin() {
		wins++;
	}
	public int getWin() {
		return wins;
	}
	public void addLoss() {
		loss++;
	}
	public int getLoss() {
		return loss;
	}
	public void setWrong() {
		incorrect++;
	}
	public int getWrong() {
		return incorrect;
	}
	public void addWrong(int wrong){
		incorrect += wrong;
	}
	public void reset() {
		secretWord = "";
		printDash = "";
	}
}
