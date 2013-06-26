package SingleShooter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

/*
 *TODO
 *Add Random mode
 *Fix movement and start of target
 */

public class ShooterFrame extends JFrame implements ActionListener, MouseMotionListener, MouseListener, KeyListener {

	private int windowWidth = 1000;
	private int windowHeight = 700;
	private Shooter shooter;
	public Container contentPane;
	private JPanel controlPanel;
	private JPanel scorePanel;
	private JPanel targetPanel;
	private JPanel buttonPanel;
	private JPanel centerPanel;
	private JLabel hitLabel;
	private JLabel missLabel;
	private JLabel attemptLabel;
	private JLabel prompt;
	private JMenu fileMenu;
	private JMenu optionsMenu;
	private JMenu helpMenu;
	private JButton shooter1;
	private JButton shooter2;

	//Image Variables
	String background = "";
	String targetPic = "";
	String crosshairs = "";

	//Backgrounds
	String northKorea = "Pictures/Backgrounds/NorthKorea.png";
	String bearRunning = "Pictures/Backgrounds/BearRunning.png";
	String syria = "Pictures/Backgrounds/Syria.png";
	String weed = "Pictures/Backgrounds/weed.png";
	String blackop = "Pictures/Backgrounds/blackop.png";
	String roundop = "Pictures/Backgrounds/roundop.png";
	String rainbowop = "Pictures/Backgrounds/rainbowop.png";

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

	/*public static void main(String[] args){
		ShooterFrame frame = new ShooterFrame();
		frame.setVisible(true);
	}*/

	public ShooterFrame(){

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
		contentPane.setLayout(new BorderLayout());

		shooter = new Shooter();
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

		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		buttonPanel.setLayout(new GridLayout(2, 1));
		shooter1 = new JButton("Single Target Shooter");
		shooter2 = new JButton("Multi Target Shooter");
		buttonPanel.add(shooter1);
		buttonPanel.add(shooter2);


		controlPanel.add(scorePanel, BorderLayout.NORTH);
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
		} else if(menuName.equals("Default Target")) {
			shooter.setImages(targetDefault);
			shooter.restartGame();
		} else if(menuName.equals("Mr. Chipps")) {
			shooter.setImages(chipps);
			shooter.restartGame();
		} else if(menuName.equals("Mr. Nuttall")) {
			shooter.setImages(nuttall);
			shooter.restartGame();
		} else if(menuName.equals("Creepy Mr. Nuttall")) {
			shooter.setImages(nuttallCreepy);
			shooter.restartGame();
		} else if(menuName.equals("Mr. Vanderway")) {
			shooter.setImages(van);
			shooter.restartGame();
		} else if(menuName.equals("Jamie Moran")) {
			shooter.setImages(jamie);
			shooter.restartGame();
		} else if(menuName.equals("Alex Anikstein")) {
			shooter.setImages(alex);
			shooter.restartGame();
		} else if(menuName.equals("George Bush")) {
			shooter.setImages(bush);
			shooter.restartGame();
		} else if(menuName.equals("Monkey Bush")) {
			shooter.setImages(monkeyBush);
			shooter.restartGame();
		} else if(menuName.equals("Cedric Kim")) {
			shooter.setImages(cedric);
			shooter.restartGame();
		} else if(menuName.equals("John Okahata")) {
			shooter.setImages(johnCreepy);
			shooter.restartGame();
		} else if(menuName.equals("Bashar Al-Assad")) {
			shooter.setImages(bashar);
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

		optionsMenu.add(createTargetSubmenu());
		//optionsMenu.add(createBackgroundSubmenu());
		//optionsMenu.add(createCrosshairsSubmenu());

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

			if((shooter.ch.x >= shooter.target.x && shooter.ch.x <= shooter.target.x + 150) &&
			   (shooter.ch.y >= shooter.target.y && shooter.ch.y <= shooter.target.y + 160)) {
	    		shooter.startTarget();
	    		shooter.hit++;
	    		hitLabel.setText("    " + shooter.hit);
	    		shooter.bhit = true;
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