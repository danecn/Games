package MultiShooter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class ChooseEvil extends JFrame implements ActionListener {

	private int frameWidth = 300;
	private int frameHeight = 200;
	public JPanel checkPanel, okPanel;
	public JButton okButton;
	private JCheckBox[] checkBox;
	public static ArrayList chosen = new ArrayList();

	public ChooseEvil() {
		Container contentPane;

		String[] btnText = {"Mr. Chipps", "Mr. Nuttall", "Mr. Van", "Bashar Al Assad", "George W. Bush",
							"John Okahata", "Jamie Moran", "Cedric Kim"};

		setSize(frameWidth, frameHeight);
		setTitle("Choose Targets");
		setLocation(450, 300);

		contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout());

		checkPanel = new JPanel(new GridLayout(0, 1));
		checkPanel.setBorder(BorderFactory.createTitledBorder("Targets"));
		checkBox = new JCheckBox[btnText.length];

		for(int i = 0; i < checkBox.length; i++) {
			checkBox[i] = new JCheckBox(btnText[i]);
			checkPanel.add(checkBox[i]);
		}

		okPanel = new JPanel(new FlowLayout());
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okPanel.add(okButton);

		contentPane.add(checkPanel, BorderLayout.CENTER);
		contentPane.add(okPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent event) {

		if(event.getSource() instanceof JButton) {

			JButton clickedButton = (JButton) event.getSource();

			if(clickedButton == okButton) {
				for(int i = 0; i < checkBox.length; i++) {
					if(checkBox[i].isSelected()) {
						chosen.add(checkBox[i].getText());
					}
				}
				this.dispose();
			}
		}
	}
}