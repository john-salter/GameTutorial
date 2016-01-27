package platformergame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import platformergame.framework.Animation;

public class StartingClass extends Applet implements Runnable, KeyListener {

	enum GameState {
		Running, Dead
	}


    GameState state = GameState.Dead;
	
    static Robot robot;
    public static Heliboy hb1, hb2;
    private Penguin p1, p2, p3;
    
    public static int score = 0;
    private int frameCounter = 0;
    private Font font = new Font(null,Font.BOLD,30);
    
    private Thread thread;
    
    private Image image, currentSprite, character, character2, character3, characterDown,
    characterJumped, background, heliboy, heliboy2, heliboy3, heliboy4, heliboy5, penguin1, penguin2, penguin3,
    penguinb1, penguinb2, penguinb3, characterJumpedhurt1;
    private Image characterJumpedhurt2, characterJumpedhurt3, characterJumpedhurt4, characterJumpedhurt5, characterDownhurt1,
    characterDownhurt2,characterDownhurt3,characterDownhurt4,characterDownhurt5,characterDownhurt6;
    
    private Animation anim, hanim, panim, panim2, panim3, hurtanim1, hurtanim2, hurtanim3, hurtanim4, hurtanim5,
    downhurtanim5;
    
    public static Image tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, tiledirt, tileocean;
    
    private Graphics second;
    private URL base;
    private static Background bg1, bg2;
    
    private ArrayList<Tile> tilearray;

    @Override
    public void init() {

        setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Q-Bot Alpha");
        try {
            base = getDocumentBase();
        } catch (Exception e) {
            // TODO: handle exception
        }

        // Image Setups
        character = getImage(base, "data/character.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");
		
		Image characterhurt1 = getImage(base, "data/characterhurt1.png");
		Image character2hurt1 = getImage(base, "data/character2hurt1.png");
		Image character3hurt1 = getImage(base, "data/character3hurt1.png");
		
		Image characterhurt2 = getImage(base, "data/characterhurt2.png");
		Image character2hurt2 = getImage(base, "data/character2hurt2.png");
		Image character3hurt2 = getImage(base, "data/character3hurt2.png");
				
		Image characterhurt3 = getImage(base, "data/characterhurt3.png");
		Image character2hurt3 = getImage(base, "data/character2hurt3.png");
		Image character3hurt3 = getImage(base, "data/character3hurt3.png");
				
		Image characterhurt4 = getImage(base, "data/characterhurt4.png");
		Image character2hurt4 = getImage(base, "data/character2hurt4.png");
		Image character3hurt4 = getImage(base, "data/character3hurt4.png");
				
		Image characterhurt5 = getImage(base, "data/characterhurt5.png");
		Image character2hurt5 = getImage(base, "data/character2hurt5.png");
		Image character3hurt5 = getImage(base, "data/character3hurt5.png");
		
		Image characterhurt6 = getImage(base, "data/characterhurt6.png");
		Image character2hurt6 = getImage(base, "data/character2hurt6.png");
		
		
		characterDown = getImage(base, "data/down.png");
		characterDownhurt1 = getImage(base, "data/downhurt1.png");
		characterDownhurt2 = getImage(base, "data/downhurt2.png");
		characterDownhurt3 = getImage(base, "data/downhurt3.png");
		characterDownhurt4 = getImage(base, "data/downhurt4.png");
		characterDownhurt5 = getImage(base, "data/downhurt5.png");
		characterDownhurt6 = getImage(base, "data/downhurt6.png");
		
		characterJumped = getImage(base, "data/jumped.png");
		/* TODO I realize that the way I'm implementing the health bar uses up way too much memory for sprites,
		 * but I wouldn't have done it this way normally, I would have had a megaman-style healthbar off to the side
		 * but here I'm just following along with the tutorial (even though he didn't really implement this either)
		*/
		characterJumpedhurt1 = getImage(base, "data/jumpedhurt1.png");
		characterJumpedhurt2 = getImage(base, "data/jumpedhurt2.png");
		characterJumpedhurt3 = getImage(base, "data/jumpedhurt3.png");
		characterJumpedhurt4 = getImage(base, "data/jumpedhurt4.png");
		characterJumpedhurt5 = getImage(base, "data/jumpedhurt5.png");
		
		heliboy = getImage(base, "data/heliboy.png");
		heliboy2 = getImage(base, "data/heliboy2.png");
		heliboy3 = getImage(base, "data/heliboy3.png");
		heliboy4 = getImage(base, "data/heliboy4.png");
		heliboy5 = getImage(base, "data/heliboy5.png");

		penguin1 = getImage(base, "data/penguin.png");
		penguin2 = getImage(base, "data/penguinMed.png");
		penguin3 = getImage(base, "data/penguinBig.png");
		
		penguinb1 = getImage(base, "data/penguinBlink.png");
		penguinb2 = getImage(base, "data/penguinBlinkMed.png");
		penguinb3 = getImage(base, "data/penguinBlinkBig.png");

		background = getImage(base, "data/background.png");
				
		tileocean = getImage(base, "data/tileocean.png");
		tiledirt = getImage(base, "data/tiledirt.png");
        tilegrassTop = getImage(base, "data/tilegrasstop.png");
        tilegrassBot = getImage(base, "data/tilegrassbot.png");
        tilegrassLeft = getImage(base, "data/tilegrassleft.png");
        tilegrassRight = getImage(base, "data/tilegrassright.png");

		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);
		
		hurtanim1 = new Animation();
		hurtanim1.addFrame(characterhurt1, 1250);
		hurtanim1.addFrame(character2hurt1, 50);
		hurtanim1.addFrame(character3hurt1, 50);
		hurtanim1.addFrame(character2hurt1, 50);
		
		hurtanim2 = new Animation();
		hurtanim2.addFrame(characterhurt2, 1250);
		hurtanim2.addFrame(character2hurt2, 50);
		hurtanim2.addFrame(character3hurt2, 50);
		hurtanim2.addFrame(character2hurt2, 50);
		
		hurtanim3 = new Animation();
		hurtanim3.addFrame(characterhurt3, 1250);
		hurtanim3.addFrame(character2hurt3, 50);
		hurtanim3.addFrame(character3hurt3, 50);
		hurtanim3.addFrame(character2hurt3, 50);
				
		hurtanim4 = new Animation();
		hurtanim4.addFrame(characterhurt4, 1250);
		hurtanim4.addFrame(character2hurt4, 50);
		hurtanim4.addFrame(character3hurt4, 50);
		hurtanim4.addFrame(character2hurt4, 50);
		
		hurtanim5 = new Animation();
		for (int i = 0; i<12; i++) {
			hurtanim5.addFrame(characterhurt5, 100);
			hurtanim5.addFrame(characterhurt6, 100);			
		}
		hurtanim5.addFrame(character2hurt5, 50);
		hurtanim5.addFrame(character3hurt5, 50);
		hurtanim5.addFrame(character2hurt6, 50);
		hurtanim5.addFrame(characterhurt6, 50);
		
		panim = new Animation();
		panim.addFrame(penguin2, 1100);
		panim.addFrame(penguinb2, 45);
		
		panim2 = new Animation();
		panim2.addFrame(penguin2, 1222);
		panim2.addFrame(penguinb2, 50);
		
		panim3 = new Animation();
		panim3.addFrame(penguin1, 1190);
		panim3.addFrame(penguinb1, 50);
		
		hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);
		
		currentSprite = anim.getImage();
    }

    @Override
    public void start() {
    	if(state == GameState.Dead) {            
            state = GameState.Running;
            bg1 = new Background(0,0);
            bg2 = new Background(2160, 0);
            robot = new Robot();
            tilearray = new ArrayList<Tile>();
            // Initialize Tiles
            try {
            	loadMap("data/map1.txt");
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	
	        
	        hb1 = new Heliboy(340,360);
	        hb2 = new Heliboy(700,360);
	        
	        p1 = new Penguin(800,382);
	        p2 = new Penguin(900,382);
	        p3 = new Penguin(1000,382);                

	        thread = new Thread(this);
            thread.start();
    	}
    }

    private void loadMap(String filename) throws IOException {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            // no more lines to read
            if (line == null) {
                reader.close();
                break;
            }

            if (!line.startsWith("!")) {
                lines.add(line);
                width = Math.max(width, line.length());

            }
        }
        height = lines.size();

        for (int j = 0; j < height; j++) {
            String line = (String) lines.get(j);
            for (int i = 0; i < width; i++) {
            	System.out.println(line);
                if (i < line.length()) {                	
                    char ch = line.charAt(i);
                    Tile t = new Tile(i, j, Character.getNumericValue(ch));
                    tilearray.add(t);
                }

            }
        }

    }

	@Override
	public void stop() {
        if(state == GameState.Running) {
        	repaint();
            state = GameState.Dead;
            try {
                thread.join();
            } catch(Exception e) {
            }
        }
     }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void run() {    	
    	while (state== GameState.Running) {
    		robot.update();
    		
    		if (robot.isJumped()){
    			switch (robot.health) {
            	case 6:
            		currentSprite = characterJumped;
            		break;
            	case 5:
            		currentSprite = characterJumpedhurt1;
            		break;
            	case 4:
            		currentSprite = characterJumpedhurt2;
            		break;
            	case 3:
            		currentSprite = characterJumpedhurt3;
            		break;
            	case 2:
            		currentSprite = characterJumpedhurt4;
            		break;
            	case 1:
            		currentSprite = characterJumpedhurt5;
            		break;
            	}
    		}
    			else if (robot.isDucked()) {
    				switch (robot.health) {
                	case 6:
                		currentSprite = characterDown;
                		break;
                	case 5:
                		currentSprite = characterDownhurt1;
                		break;
                	case 4:
                		currentSprite = characterDownhurt2;
                		break;
                	case 3:
                		currentSprite = characterDownhurt3;
                		break;
                	case 2:
                		currentSprite = characterDownhurt4;
                		break;
                	case 1:
                		currentSprite = characterDownhurt5;
                		break;
                	}    			
    		}
    			else if (robot.isJumped() == false && robot.isDucked() == false){
    			switch (robot.health) {
            	case 6:
            		currentSprite = anim.getImage();
            		break;
            	case 5:
            		currentSprite = hurtanim1.getImage();
            		break;
            	case 4:
            		currentSprite = hurtanim2.getImage();
            		break;
            	case 3:
            		currentSprite = hurtanim3.getImage();
            		break;
            	case 2:
            		currentSprite = hurtanim4.getImage();
            		break;
            	case 1:
            		currentSprite = hurtanim5.getImage();
            		break;
            	}
    		}
           
    		ArrayList projectiles = robot.getProjectiles();
    		for (int i = 0; i < projectiles.size(); i++) {
    			Projectile p = (Projectile) projectiles.get(i);
    			if (p.isVisible() == true) {
    				p.update();
    			} else {
    				projectiles.remove(i);
    			}
    		}    			
           
    		updateTiles();
    		hb1.update();
    		hb2.update();
           	p1.update();
           	p2.update();
           	p3.update();
           	bg1.update();
           	bg2.update();
           	
           	animate();
           	repaint();
           	try {
           		Thread.sleep(17);
           	} catch (InterruptedException e) {
           		e.printStackTrace();
           	}
           	if (robot.getCenterY() > 500) {
				stop();
           	}
           	}	
    	}


    public void animate() {
    	   anim.update(10);
    	   hurtanim1.update(10);
    	   hurtanim2.update(10);
    	   hurtanim3.update(10);
    	   hurtanim4.update(10);
    	   hurtanim5.update(10);
    	   hanim.update(50);
    	   panim.update(10);
    	   panim2.update(10);
    	   panim3.update(10);
    	}
    
    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }

        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);

    }

    @Override
    public void paint(Graphics g) {
    	
    	if (state == GameState.Running) {
    		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
    		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
	        paintTiles(g);
	        ArrayList projectiles = robot.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				g.setColor(Color.YELLOW);
				g.fillRect(p.getX(), p.getY(), 10, 5);
			}
			
			if (Robot.justGotHit == false) {
				g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
			}
			else if(frameCounter <3) {
				frameCounter++;
			}
			else {
				frameCounter=0;
				Robot.justGotHit = false;
			}
	        g.drawImage(hanim.getImage(), hb1.getCenterX() - 48, hb1.getCenterY() - 48, this);
	        g.drawImage(hanim.getImage(), hb2.getCenterX() - 48, hb2.getCenterY() - 48, this);
	        g.drawImage(panim.getImage(), p1.getCenterX()+23, p1.getCenterY()+17, this);
	        g.drawImage(panim2.getImage(), p2.getCenterX()+23, p2.getCenterY()+17, this);
	        g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("SCORE: "+Integer.toString(score), 600, 30);	        
    	} else if (state == GameState.Dead) {    		
    		g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);
			//g.drawString("Press Enter to try again", 260, 340);
    	}
    	
    }
    
    private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}


	}


	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}
    

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            System.out.println("Move up");
            break;

        case KeyEvent.VK_DOWN:
            currentSprite = characterDown;
            if (robot.isJumped() == false){
                robot.setDucked(true);
                robot.setSpeedX(0);
            }
            break;

        case KeyEvent.VK_LEFT:
            robot.moveLeft();
            robot.setMovingLeft(true);
            break;

        case KeyEvent.VK_RIGHT:
            robot.moveRight();
            robot.setMovingRight(true);
            break;

        case KeyEvent.VK_SPACE:
            robot.jump();
            break;
            
        case KeyEvent.VK_CONTROL:
			if (robot.isDucked() == false && robot.isJumped() == false) {
				robot.shoot();
				robot.setReadyToFire(false);
			}
			break;
        case KeyEvent.VK_ENTER:
        	if (state == GameState.Dead) {        		
        	}
        	break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            System.out.println("Stop moving up");
            break;

        case KeyEvent.VK_DOWN:
        	switch (robot.health) {
        	case 6:
        		currentSprite = anim.getImage();
        		break;
        	case 5:
        		currentSprite = hurtanim1.getImage();
        		break;
        	case 4:
        		currentSprite = hurtanim2.getImage();
        		break;
        	case 3:
        		currentSprite = hurtanim3.getImage();
        		break;
        	case 2:
        		currentSprite = hurtanim4.getImage();
        		break;
        	case 1:
        		currentSprite = hurtanim5.getImage();
        		break;
        	}
            robot.setDucked(false);
            break;

        case KeyEvent.VK_LEFT:
            robot.stopLeft();
            break;

        case KeyEvent.VK_RIGHT:
            robot.stopRight();
            break;

        case KeyEvent.VK_SPACE:
            break;
            
        case KeyEvent.VK_CONTROL:
        	robot.setReadyToFire(true);
            break;

        }

    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public static Background getBg1() {
        return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }

    public static Robot getRobot() {
    	return robot;
    }   

}
