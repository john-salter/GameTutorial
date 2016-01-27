package platformergame;

import java.awt.Rectangle;

public class Projectile {
	private int x,y,speedX;
	private boolean visible;
	private Rectangle r;
	
	public Projectile(int startX, int startY) {
		// TODO Auto-generated constructor stub
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;
		
		r = new Rectangle(0,0,0,0);
	}
	
	public void update() {
		x+=speedX;
		r.setBounds(x,y,10,5);
		if (x > 800) {
			visible = false;
			r = null;
		}
		
		if (x<801) {
			checkCollision();
		}
	}
	
	private void checkCollision() {
		if(r.intersects(Robot.rect)){
			visible = false;
			if (StartingClass.robot.health > 0) {
				StartingClass.robot.health -= 1;
				StartingClass.robot.justGotHit = true;
			}
			if (StartingClass.robot.health == 0) {
				StartingClass.robot.setCenterY(700);				
			}
		}
		
		if(r.intersects(StartingClass.hb1.r)){
			visible = false;
			if (StartingClass.hb1.health > 0) {
				StartingClass.hb1.health -= 1;
			}
			if (StartingClass.hb1.health == 0) {
				StartingClass.hb1.setCenterX(-100);
				StartingClass.score += 5;
			}
		}
		
		if (r.intersects(StartingClass.hb2.r)){
			visible = false;
			if (StartingClass.hb2.health > 0) {
				StartingClass.hb2.health -= 1;
			}
			if (StartingClass.hb2.health == 0) {
				StartingClass.hb2.setCenterX(-100);
				StartingClass.score += 5;
			}


		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
