package MultiShooter;
import Components.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;

public class ShooterRandom extends JPanel {

	private Image image;
	private Graphics graphics;

	private int windowWidth = 1000;
	private int windowHeight = 700;

	//Variables
	public int hit = 0;
	public int miss = 0;
	public int start = 0;
	public int pause = 0;
	public int restart = 0;
	public int choose = 0;
	public double attempt = 0;
	public int scoreLimit = 10;
	public int speed = 1;
	public boolean bpause = false;
	public boolean badd = false;
	public boolean bminus = false;
	public boolean bmiss = false;
	public int h = 0;
	private int waitTime = 20;
	Color counterColor = Color.LIGHT_GRAY;
	Color pauseColor = Color.BLACK;

	//Image Variables
	String background = "";
	Img targetPic = new Img("Pictures/Target/Chipps.png", true);
	String crosshairs = "";

	//Backgrounds
	String backgroundDefault = "Pictures/Backgrounds/backgroundDef.png";
	String northKorea = "Pictures/Backgrounds/NorthKorea.png";
	String bearRunning = "Pictures/Backgrounds/BearRunning.png";
	String syria = "Pictures/Backgrounds/Syria.png";
	String weed = "Pictures/Backgrounds/weed.png";
	String pearlHarbor = "Pictures/Backgrounds/pearl harbor.png";
	String monkeys = "Pictures/Backgrounds/monkeys.png";
	String first = "Pictures/Backgrounds/firstwpi.png";
	String dox = "Pictures/Backgrounds/doxlogo.png";
	String squeaky = "Pictures/Backgrounds/squeaky.png";
	String blackop = "Pictures/Backgrounds/blackop.png";
	String roundop = "Pictures/Backgrounds/roundop.png";
	String rainbowop = "Pictures/Backgrounds/rainbowop.png";

	//Img
	Img targetImg = new Img();
	ArrayList targets;
	Img chipps = new Img("Pictures/Target/Chipps.png", true);
	//Img nuttall = new Img("Pictures/Target/nuttall.png", false);
	Img nuttallCreepy = new Img("Pictures/Target/nuttallCreepy.png", false);
	Img van = new Img("Pictures/Target/van.png", false);
	Img bashar = new Img("Pictures/Target/assad.png", true);
	//Img bush = new Img("Pictures/Target/Bush2.png", true);
	Img monkeyBush = new Img("Pictures/Target/bush3.png", true);
	//Img john = new Img("Pictures/Target/John1.png", false);
	Img johnCreepy = new Img("Pictures/Target/John2.png", true);
	Img jamie = new Img("Pictures/Target/jamie.png", false);
	//Img alex = new Img("Pictures/Target/alex.png", false);
	Img cedric = new Img("Pictures/Target/cedric.png", true);

	//Game Objects
	public Crosshairs ch = new Crosshairs(getSize().width/2, getSize().height/2, Color.RED);
	public Target target;
	private Random generator = new Random();

    public ShooterRandom() {

		this.setVisible(true);

		targets = new ArrayList();

		//targetPic = targetDefault;
		background = backgroundDefault;

        initGame();
    }

    private void initGame() {
        ch = new Crosshairs(getSize().width/2, getSize().height/2, Color.RED);
        target = new Target(30, getSize().height-50, speed, speed);
        startTarget();
        makeArray();
        chooseTargets();
    }

	public void paint(Graphics g){
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();

		if(pause%2 != 0){
    		bpause = true;
    	}
    	if(bpause == true || (((miss + hit) >= scoreLimit) && scoreLimit != 0)) {
    		paintComponent(graphics);
    	}
       	else {
        	moveTarget();
    		//startTarget();
			if(getImages().evil) {
	    		if(target.x < -180 || target.x > getSize().width + 5 || target.y < -155 || target.y > getSize().height + 5) {
	    			startTarget();
	    			setRandImages();
	    			miss++;
	    			bmiss = true;
	    		}
       		} else {
       			if(target.x < -180 || target.x > getSize().width + 5 || target.y < -155 || target.y > getSize().height + 5) {
	    			startTarget();
	    			setRandImages();
	    			bmiss = true;
	    		}
       		}
    		paintComponent(graphics);
    	}
    	if(restart%2 != 0) {
    		restartGame();
    	}
		g.drawImage(image, 0, 0, null);
		repaint();
	}

	public void paintComponent(Graphics g){
		//g.setColor(Color.CYAN);
        //g.fillRect(0, 0, windowWidth, windowHeight);

        g.drawImage(Toolkit.getDefaultToolkit().getImage(backgroundDefault), 0, 0, getSize().width, getSize().height, null);

        drawCrosshairs(g);
        drawTarget(g);
        //drawCounter(g);
        if(scoreLimit == 0) {

        } else if((miss + hit) >= scoreLimit) {
        	gameOver(g);
        }
        if(bpause) {
        	pauseScreen(g);
        }
        if(badd) {
        	if(h <= waitTime) {
        		drawInc(g);
        		h++;
        	}
        	if(h == waitTime) {
        		badd = false;
        		h = 0;
        	}
        } else if(bminus) {
        	if(h <= waitTime) {
        		drawDec(g);
        		h++;
        	}
        	if(h == waitTime) {
        		bminus = false;
        		h = 0;
        	}
        }
	}

    private void drawCrosshairs(Graphics g) {
    	g.drawImage(Toolkit.getDefaultToolkit().getImage("Pictures/Crosshairs/Crosshairs1.png"), ch.x, ch.y, 50, 50, null);
	}

	private void drawTarget(Graphics g) {
    	getImages();
    	g.drawImage(Toolkit.getDefaultToolkit().getImage(targetPic.path), target.x, target.y, 150, 175, null);
	}

	public void chooseTargets() {

		for(int i = 0; i < ChooseEvil.chosen.size(); i++) {
			if(ChooseEvil.chosen.get(i).equals("Mr. Chipps")) {
				chipps.setEvil(true);
			} else if(ChooseEvil.chosen.get(i).equals("Mr. Nuttall")) {
				nuttallCreepy.setEvil(true);
			} else if(ChooseEvil.chosen.get(i).equals("Mr. Van")) {
				van.setEvil(true);
			} else if(ChooseEvil.chosen.get(i).equals("Bashar Al Assad")) {
				bashar.setEvil(true);
			} else if(ChooseEvil.chosen.get(i).equals("George W. Bush")) {
				monkeyBush.setEvil(true);
			} else if(ChooseEvil.chosen.get(i).equals("John Okahata")) {
				johnCreepy.setEvil(true);
			} else if(ChooseEvil.chosen.get(i).equals("Jamie Moran")) {
				jamie.setEvil(true);
			} else if(ChooseEvil.chosen.get(i).equals("Cedric Kim")) {
				cedric.setEvil(true);
			}
		}
	}

	public void makeArray() {
		targets.add(johnCreepy);
		targets.add(chipps);
		targets.add(nuttallCreepy);
		targets.add(van);
		targets.add(jamie);
		targets.add(cedric);
		targets.add(bashar);
		targets.add(monkeyBush);
	}

	public void setRandImages() {
		int min = 0, max = targets.size()-1;
		int rand = (int) (Math.floor(Math.random() * (max - min + 1)) + min);

		targetPic = (Img) targets.get(rand);
	}

	public Img getImages() {
		return targetPic;
	}

	public void restartGame() {
		initGame();

		pause = 0;
		hit = 0;
		miss = 0;
		attempt = 0;
		bpause = false;

    	moveTarget();
		startTarget();

		if((target.x <= 0 || target.x >= getSize().width || target.y <= 0)) {
			startTarget();
			miss++;
    		bmiss = true;
		}

		paintComponent(graphics);

    	restart = 0;
	}

	public void pauseGame() {
		bpause = true;
		System.out.println("Pause is: " + bpause);
	}

	private void drawCounter(Graphics g) {
		g.setColor(counterColor);
		g.setFont(new Font ("ComicSans", 30, 25));
		g.drawString("Hits: " + hit, 15, 40);
		g.setColor(counterColor);
		g.setFont(new Font ("ComicSans", 30, 25));
		g.drawString("Miss: " + miss, 110, 40);
		g.setColor(counterColor);
		g.setFont(new Font ("ComicSans", 30, 25));
		g.drawString("Attempts: " + Math.round(attempt), 215, 40);
	}

	public void drawPoints(Graphics g, int points) {
		g.setColor(pauseColor);
		g.setFont(new Font ("ComicSans", 30, 75));
		g.drawString("+" + points, 350, 350);
	}

	public void drawInc(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font ("ComicSans", 30, 75));
		g.drawString("+1", ch.x, ch.y);
	}

	public void drawDec(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font ("ComicSans", 30, 75));
		g.drawString("-1", ch.x, ch.y);
	}

	private void startScreen(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font ("ComicSans", 30, 75));
		g.drawString("SHOOTER", 375, 350);
		g.setColor(Color.BLACK);
		g.setFont(new Font ("ComicSans", 30, 35));
		g.drawString("Press 'S' to start game", 325, 400);
	}

	private void gameOver(Graphics g) {
		g.setColor(pauseColor);
		g.setFont(new Font ("ComicSans", 30, 75));
		g.drawString("Hit: " + hit, 375, 350);
		g.setColor(pauseColor);
		g.setFont(new Font ("ComicSans", 30, 35));
		g.drawString("Accuracy: " + Math.round((hit/attempt) * 100) + "%", 350, 400);
		g.setColor(pauseColor);
		g.setFont(new Font ("ComicSans", 30, 35));
		g.drawString("Press 'R' to Restart", 325, 450);
	}

	private void pauseScreen(Graphics g) {
		g.setColor(pauseColor);
		g.setFont(new Font ("ComicSans", 30, 75));
		g.drawString("Hits: " + hit, 350, 350);
		g.setColor(pauseColor);
		g.setFont(new Font ("ComicSans", 30, 35));
		g.drawString("Press 'P' to Resume or 'R' to Restart", 200, 400);
	}

	public void startTarget() {
		//target.x = generator.nextInt((windowWidth)-4)+2;
		//target.y = generator.nextInt((windowHeight)-5)+3;

		int minX = 1;
		int maxX = 2;
		int minY = -5;
		int maxY = getSize().height + 5;

		int randomX = (int) (Math.floor(Math.random() * (maxX - minX + 1)) + minX);
		int randomY = (int) (Math.floor(Math.random() * (maxY - minY + 1)) + minY);

		if(randomX == 1 && randomY <= getSize().height/2) {
			target.x = -5;
    		target.y = randomY;
    		choose = 0;
		}
		else if(randomX == 1 && randomY > getSize().height/2) {
			target.x = -5;
			target.y = randomY;
			choose = 1;
		}
		else if(randomX == 2 && randomY <= getSize().height/2) {
			target.x = getSize().width + 5;
			target.y = randomY;
			choose = 2;
		}
		else if(randomX == 2 && randomY > getSize().height/2) {
			target.x = getSize().width + 5;
			target.y = randomY;
			choose = 3;
		}
	}

	private void moveTarget() {

		if(choose == 0) {
			target.x = target.x + target.dx;
    		target.y = target.y + target.dy;
		}
		else if(choose == 1) {
			target.x = target.x + target.dx;
    		target.y = target.y - target.dy;
		}
		else if(choose == 2) {
			target.x = target.x - target.dx;
    		target.y = target.y + target.dy;
		}
		else if(choose == 3) {
			target.x = target.x - target.dx;
    		target.y = target.y - target.dy;
		}

		/*int min = 1;
		int max = 2;

		int random = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
		System.out.println(random);

		if(random == 1) {
			target.x = target.x - target.dx;
    		target.y = target.y - target.dy;
		}
		else if(random == 2) {
			target.x = target.x + target.dx;
    		target.y = target.y - target.dy;
		}
		else if(random == 3) {
			target.x = target.x - target.dx;
    		target.y = target.y + target.dy;
		}
		else if(random == 4) {
			target.x = target.x - target.dx;
    		target.y = target.y - target.dy;
		}*/
	}
}