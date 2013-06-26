import Fermi.*;
import Hangman.*;
import SingleShooter.*;
import MultiShooter.*;

import java.awt.*;
import java.awt.event.*;
//import java.awt.image.*;
//import java.util.*;
import javax.swing.*;

public class StartFrame extends JFrame implements ActionListener {

	public Container contentPane;
	private int windowWidth = 400;
	private int windowHeight = 150;
	private int buttonWidth = 80;
	private int buttonHeight = 30;
	private JPanel buttonPanel;
	private JLabel prompt;
	private JButton single;
	private JButton multi;
	private JButton fermiButton;
	private JButton hangmanButton;

	public static void main(String[] args){
		StartFrame startFrame = new StartFrame();
		startFrame.setVisible(true);
	}

	public StartFrame() {

		this.setTitle("Games");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);
        this.setLocation(450, 300);
        this.setResizable(false);

        contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		prompt = new JLabel();
		prompt.setText("Which game would you like to play?");
		contentPane.add(prompt);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 2));

		single = new JButton("Single Target Shooter");
		single.setSize(buttonWidth, buttonHeight);
		single.addActionListener(this);
		buttonPanel.add(single);

		multi = new JButton("Multi Target Shooter");
		multi.setSize(buttonWidth, buttonHeight);
		multi.addActionListener(this);
		buttonPanel.add(multi);

		fermiButton = new JButton("Fermi");
		fermiButton.setSize(buttonWidth, buttonHeight);
		fermiButton.addActionListener(this);
		buttonPanel.add(fermiButton);

		hangmanButton = new JButton("Hangman");
		hangmanButton.setSize(buttonWidth, buttonHeight);
		hangmanButton.addActionListener(this);
		buttonPanel.add(hangmanButton);

		contentPane.add(buttonPanel);
	}

	public void actionPerformed(ActionEvent event) {

		if(event.getSource() instanceof JButton) {

			JButton clickedButton = (JButton) event.getSource();

			if(clickedButton == single) {
				ShooterFrame frame = new ShooterFrame();
				frame.setVisible(true);
				frame.addWindowListener(new WindowAdapter() {
				    public void windowClosing(WindowEvent evt) {
				        Frame frame = (Frame)evt.getSource();

				        frame.dispose();
				    }
				});
			} else if(clickedButton == multi) {
				Frame2 frame = new Frame2();
				frame.setVisible(true);
				frame.addWindowListener(new WindowAdapter() {
				    public void windowClosing(WindowEvent evt) {
				        Frame frame = (Frame)evt.getSource();

				        frame.dispose();
				    }
				});
			} else if(clickedButton == fermiButton) {
				Fermi fermi = new Fermi();
				fermi.runFermi();
			} else if(clickedButton == hangmanButton) {
				Hangman2 hangman = new Hangman2();
				hangman.runHangman();
			}
		}
	}
}