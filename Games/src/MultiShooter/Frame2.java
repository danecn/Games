package MultiShooter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class Frame2 extends JFrame implements ActionListener, MouseMotionListener, MouseListener, KeyListener {

	private int windowWidth = 1000;
	private int windowHeight = 700;
	private ShooterRandom shooter;
	public Container contentPane;
	private JPanel controlPanel, scorePanel, targetPanel, buttonPanel, centerPanel;
	private JLabel hitLabel, missLabel, attemptLabel, prompt;
	private JMenu fileMenu, optionsMenu, helpMenu;
	private JButton shooterB, fermiB, hangmanB;

	//Targets
	String targetDefault = "Pictures/Target/TargetRed.png";
	String chipps = "Pictures/Target/Chipps.png";
	String nuttall = "Pictures/Target/nuttall.png";
	String nuttallCreepy = "Pictures/Target/nuttallCreepy.png";
	String van = "Pictures/Target/van.png";
	String bashar = "Pictures/Target/assad.png";
	String bush = "Pictures/Target/Bush2.png";
	String monkeyBush = "Pictures/Target/bush3.png";
	String john = "Pictures/Target/John1.png";
	String johnCreepy = "Pictures/Target/John2.png";
	String jamie = "Pictures/Target/jamie.png";
	String alex = "Pictures/Target/alex.png";
	String cedric = "Pictures/Target/cedric.png";

	JLabel chippsi = new JLabel(new ImageIcon(chipps));
	JLabel bashari = new JLabel(new ImageIcon(bashar));
	JLabel bushi = new JLabel(new ImageIcon(monkeyBush));
	JLabel johni = new JLabel(new ImageIcon(johnCreepy));
	JLabel cedrici = new JLabel(new ImageIcon(cedric));

	public Frame2(){

		this.setTitle("Shooter");
    	//this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);
        this.setResizable(true);
        this.setLocation(100, 100);

		hideCursor();

        addMouseMotionListener(this);
		addMouseListener(this);
		addKeyListener(this);

		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10, 0));

		shooter = new ShooterRandom();
		shooter.setBorder(BorderFactory.createLoweredBevelBorder());
		controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());

		contentPane.add(shooter, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.EAST);

		scorePanel = new JPanel();
		scorePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		scorePanel.setLayout(new GridLayout(3, 2));
		hitLabel = new JLabel("    " + shooter.hit);
		missLabel = new JLabel("    " + shooter.miss);
		attemptLabel = new JLabel("    " + Math.round(shooter.attempt));
		scorePanel.add(new JLabel("Hits: "));
		scorePanel.add(hitLabel);
		scorePanel.add(new JLabel("Miss: "));
		scorePanel.add(missLabel);
		scorePanel.add(new JLabel("Attempts: "));
		scorePanel.add(attemptLabel);

		targetPanel = new JPanel();
		targetPanel.setBorder(BorderFactory.createTitledBorder("Targets"));
		targetPanel.setLayout(new GridLayout(5, 1));
		/*for(int i = 0; i < ChooseEvil.chosen.size(); i++) {
			targetPanel.add(new JLabel((String) ChooseEvil.chosen.get(i)));
		}*/
		targetPanel.add(new JLabel("Mr. Chipps"));
		targetPanel.add(new JLabel("Bashar Al ASSad"));
		targetPanel.add(new JLabel("George W. Bush"));
		targetPanel.add(new JLabel("John Okahata"));
		targetPanel.add(new JLabel("Cedric Kim"));

		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		centerPanel.setLayout(new GridLayout(3, 1));
		centerPanel.add(targetPanel);

		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		buttonPanel.setLayout(new GridLayout(3, 1));
		shooterB = new JButton("Single Target Shooter");
		fermiB = new JButton("Fermi");
		hangmanB = new JButton("Hangman");
		buttonPanel.add(shooterB);
		buttonPanel.add(fermiB);
		buttonPanel.add(hangmanB);


		controlPanel.add(scorePanel, BorderLayout.NORTH);
		controlPanel.add(centerPanel, BorderLayout.CENTER);
		//controlPanel.add(buttonPanel, BorderLayout.SOUTH);

		createFileMenu();
		createOptionsMenu();
		createHelpMenu();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(optionsMenu);
		menuBar.add(helpMenu);
	}

	public void actionPerformed(ActionEvent event) {

		String menuName;

		menuName = event.getActionCommand();

		if(menuName.equals("Restart")) {
			shooter.restartGame();
		} else if(menuName.equals("Help")) {
			shooter.pause++;
			JOptionPane.showMessageDialog(null, "Move mouse to aim.\n" +
												"Left-click to shoot.\n" +
												"Press P to pause and R to Restart");
			shooter.pause++;
		} else if(menuName.equals("About")) {
			shooter.pause++;
			JOptionPane.showMessageDialog(null, "Created by Omar Issa Attar\n" +
												"Ms. Piper's AP Computer Science Class Period 5 2011-2012\n" +
												"Enjoy");
			shooter.pause++;
		} else if(menuName.equals("Set Targets")) {
			shooter.pauseGame();
			ChooseEvil frame = new ChooseEvil();
			shooter.chooseTargets();
			frame.setVisible(true);
		/*	frame.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent evt) {
			        Frame frame = (Frame)evt.getSource();

			        frame.dispose();
			    }
			});*/
			shooter.restartGame();
		} else if(menuName.equals("Set Score Limit")) {
			shooter.pauseGame();
			String s = JOptionPane.showInputDialog("Set Score Limit (Press 0 for none):");
			for(int i = 0; i < s.length(); i++) {
				if(s.length() == 0 || s.charAt(i) < 48 || s.charAt(i) > 57) {
					s = JOptionPane.showInputDialog("Set Score Limit (Press 0 for none):");
				}
			}
			shooter.scoreLimit = Integer.parseInt(s);
			shooter.restartGame();
		} else if(menuName.equals("Set Target Speed")) {
			shooter.pauseGame();
			String s = JOptionPane.showInputDialog("Set Target Speed (pixels/second):" +
												   "\nBeginner: 1-3, Novice: 4-5,Intermediate: 6, Expert: 7+, Really Damn Beast: 30+");
			for(int i = 0; i < s.length(); i++) {
				if(s.length() == 0 || s.charAt(i) < 48 || s.charAt(i) > 57) {
					s = JOptionPane.showInputDialog("Set Target Speed (pixels/second):" +
												    "\nBeginner: 1-3, Novice: 4-5,Intermediate: 6, Expert: 7+, Really Damn Beast: 30+");
				}
			}
			shooter.speed = Integer.parseInt(s);
			shooter.restartGame();
		}
	}

	public void createFileMenu() {

		JMenuItem item;

		fileMenu = new JMenu("File");

		item = new JMenuItem("Restart");
		item.addActionListener(this);
		fileMenu.add(item);
	}

	public void createOptionsMenu() {

		JMenuItem item;

		optionsMenu = new JMenu("Options");

		//optionsMenu.add(createTargetSubmenu());
		//optionsMenu.add(createBackgroundSubmenu());
		//optionsMenu.add(createCrosshairsSubmenu());

		item = new JMenuItem("Set Targets");
		item.addActionListener(this);
		optionsMenu.add(item);

		item = new JMenuItem("Set Score Limit");
		item.addActionListener(this);
		optionsMenu.add(item);

		item = new JMenuItem("Set Target Speed");
		item.addActionListener(this);
		optionsMenu.add(item);
	}

	public void createHelpMenu() {

		JMenuItem item;

		helpMenu = new JMenu("Help");

		item = new JMenuItem("Help");
		item.addActionListener(this);
		helpMenu.add(item);

		item = new JMenuItem("About");
		item.addActionListener(this);
		helpMenu.add(item);
	}

	private JMenu createTargetSubmenu() {

		JMenu targetSubmenu = new JMenu("Set Target");
		JMenuItem item;

		item = new JMenuItem("Default Target");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("Mr. Chipps");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("Mr. Nuttall");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("Creepy Mr. Nuttall");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("Mr. Vanderway");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("Jamie Moran");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("Alex Anikstein");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("George Bush");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("Monkey Bush");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("Bashar Al-Assad");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("Cedric Kim");
		item.addActionListener(this);
		targetSubmenu.add(item);

		item = new JMenuItem("John Okahata");
		item.addActionListener(this);
		targetSubmenu.add(item);

		return targetSubmenu;
	}

	private JMenu createBackgroundSubmenu() {

		JMenu backgroundSubmenu = new JMenu("Set Background");
		JMenuItem item;

		item = new JMenuItem("Default Background");
		item.addActionListener(this);
		backgroundSubmenu.add(item);

		item = new JMenuItem("Bear Running");
		item.addActionListener(this);
		backgroundSubmenu.add(item);

		item = new JMenuItem("Tumbleweed");
		item.addActionListener(this);
		backgroundSubmenu.add(item);

		item = new JMenuItem("North Korean Border");
		item.addActionListener(this);
		backgroundSubmenu.add(item);

		item = new JMenuItem("Syrian Revolution");
		item.addActionListener(this);
		backgroundSubmenu.add(item);

		return backgroundSubmenu;
	}

	private JMenu createCrosshairsSubmenu() {

		JMenu chSubmenu = new JMenu("Set Crosshairs");
		JMenuItem item;

		item = new JMenuItem("Default Crosshairs");
		item.addActionListener(this);
		chSubmenu.add(item);

		item = new JMenuItem("Crosshairs 2");
		item.addActionListener(this);
		chSubmenu.add(item);

		item = new JMenuItem("Crosshairs 3");
		item.addActionListener(this);
		chSubmenu.add(item);

		return chSubmenu;
	}

	public static void wait(int n){
        long t0, t1;
        t0 = System.currentTimeMillis();
        do {
            t1 = System.currentTimeMillis();
        } while (t1 - t0 < (n * 1000));
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(shooter.bpause == true || (((shooter.miss + shooter.hit) >= shooter.scoreLimit) && shooter.scoreLimit != 0)) {
			shooter.ch.x = shooter.ch.x;
			shooter.ch.y = shooter.ch.y;
		} else {
			shooter.ch.x = e.getX();
			shooter.ch.y = e.getY();
		}
		if(shooter.bmiss) {
			missLabel.setText("    " + shooter.miss);
			shooter.bmiss = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseClicked (MouseEvent e) {
		if(shooter.bpause == true || (((shooter.miss + shooter.hit) >= shooter.scoreLimit) && shooter.scoreLimit != 0)) {

		} else {
			shooter.ch.x = e.getX();
			shooter.ch.y = e.getY();

			shooter.attempt++;
			attemptLabel.setText("    " + Math.round(shooter.attempt));

			if(shooter.getImages().evil) {
			   	if((shooter.ch.x >= shooter.target.x && shooter.ch.x <= shooter.target.x + 150) &&
				   (shooter.ch.y >= shooter.target.y && shooter.ch.y <= shooter.target.y + 160)) {
		    		shooter.startTarget();
	    			shooter.setRandImages();
		    		shooter.hit++;
		    		hitLabel.setText("    " + shooter.hit);
		    		shooter.badd = true;
				}
			} else {
				if((shooter.ch.x >= shooter.target.x && shooter.ch.x <= shooter.target.x + 150) &&
				   (shooter.ch.y >= shooter.target.y && shooter.ch.y <= shooter.target.y + 160)) {
		    		shooter.startTarget();
	    			shooter.setRandImages();
		    		shooter.hit--;
		    		hitLabel.setText("    " + shooter.hit);
		    		shooter.bminus = true;
				}
			}
		}
	}

	@Override
	public void mouseEntered (MouseEvent e) {}

	@Override
	public void mouseExited (MouseEvent e) {}

	@Override
	public void mouseReleased (MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
    public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 's') {
			shooter.start++;
		}
		if(e.getKeyChar() == 'p') {
			shooter.pause++;
			if(shooter.bpause)
				shooter.bpause = false;
			else
				shooter.bpause = true;
		}
		if(e.getKeyChar() == 'r') {
			shooter.restart++;
			hitLabel.setText("    0");
			missLabel.setText("    0");
			attemptLabel.setText("    0");
		}
    }

	@Override
    public void keyReleased(KeyEvent e) {}

	private void hideCursor() {
    	int[] pixels = new int[16 * 16];
    	Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
    	Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisiblecursor");
    	getContentPane().setCursor(transparentCursor);
	}
}
