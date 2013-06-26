package Slot;

import javax.swing.*;
import java.text.*;
import java.util.*;

class Slot {

	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat("0.00");

		int netCoins = Integer.parseInt(JOptionPane.showInputDialog("Starting number of coins ($0.25 ea):"));
		double startingMoney = netCoins * 0.25;
		double moneyChange = 0;
		int combination = 0, payoffRate = 0;
		int max = 3, min = 1;

		String bell = "BELL";
		String grape = "GRAPE";
		String cherry = "CHERRY";

		while(netCoins > 0) {

			int coinsBet = Integer.parseInt(JOptionPane.showInputDialog("How many coins would you like to bet (1-4 coins)? Press 0 to exit"));

			if(coinsBet < 0 || coinsBet > 4)
				coinsBet = Integer.parseInt(JOptionPane.showInputDialog("You can only bet 1-4 coins buddy. Try Again."));
			if(coinsBet == 0)
				System.exit(0);

			while(coinsBet > netCoins)
				coinsBet = Integer.parseInt(JOptionPane.showInputDialog("You do not have enough coins. How many coins would you like to bet (1-4 coins)?"));

			//determine piece
			int piece1Select = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
			String piece1 = "";
			if(piece1Select == 1) {
				piece1 = bell;
			}
			else if(piece1Select == 2) {
				piece1 = grape;
			}
			else {
				piece1 = cherry;
			}

			int piece2Select = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
			String piece2 = "";
			if(piece2Select == 1) {
				piece2 = bell;
			}
			else if(piece2Select == 2) {
				piece2 = grape;
			}
			else {
				piece2 = cherry;
			}

			int piece3Select = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
			String piece3 = "";
			if(piece3Select == 1) {
				piece3 = bell;
			}
			else if(piece3Select == 2) {
				piece3 = grape;
			}
			else {
				piece3 = cherry;
			}

			//determine combination
			if(piece1 == bell && piece2 == bell && piece3 == bell)
				combination = 1;
			else if(piece1 == grape && piece2 == grape && piece3 == grape)
				combination = 2;
			else if(piece1 == cherry && piece2 == cherry && piece3 == cherry)
				combination = 3;
			else if(piece1 == cherry && piece2 == cherry)
				combination = 4;
			else if(piece1 == cherry && piece3 == cherry)
				combination = 5;
			else if(piece2 == cherry && piece3 == cherry)
				combination = 6;
			else if(piece1 == cherry)
				combination = 7;
			else if(piece2 == cherry)
				combination = 8;
			else if(piece3 == cherry)
				combination = 9;

			//determine payoff rate
			if(combination == 1)
				payoffRate = 10;
			else if(combination == 2)
				payoffRate = 7;
			else if(combination == 3)
				payoffRate = 5;
			else if(combination == 4 || combination == 5 || combination == 6)
				payoffRate = 3;
			else if(combination == 7 || combination == 8 || combination == 9)
				payoffRate = 1;
			else if(combination == 0)
				payoffRate = 0;

			//Calculate printouts
			netCoins = netCoins - coinsBet + coinsBet * payoffRate;
			if(netCoins <= 0)
				netCoins = 0;

			moneyChange = coinsBet * payoffRate * 0.25;
			if(payoffRate == 0)
				moneyChange = coinsBet * 0.25 * -1;
			if(payoffRate == 1)
				moneyChange = 0;

			JOptionPane.showMessageDialog(null, "          COMBINATION                       PAYOFF RATE" +
												"\n" + piece1 + "       " + piece2 + "       " + piece3 + "              " + payoffRate + "x" +
												"\nCoins Remaining: " + netCoins + "          " + "+Won/-Lost: $" + df. format(moneyChange));

			if(netCoins <= 0)
				netCoins = Integer.parseInt(JOptionPane.showInputDialog("You are out of coins. Would you like to add more?"));
		}
	}
}