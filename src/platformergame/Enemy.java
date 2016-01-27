package platformergame;

import java.awt.Rectangle;

public class Enemy {

	private int power;
	protected int speedX;
	private int speedY;
	protected int centerX;
	private int centerY;
	protected Background bg = StartingClass.getBg1();
	
	private int movementSpeed;
	
	public Rectangle r = new Rectangle(0,0,0,0);
	public int health = 5;

	// Enemy Behavior methods
	public void update() {
		follow();
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		
		r.setBounds(centerX - 25, centerY-25, 50, 60);
		if (r.intersects(Robot.yellowRed)){
			checkCollision();
		}
	}
	
	private void checkCollision() {
		if (Robot.timeofLastHit <= System.currentTimeMillis()-1500) {
			if (r.intersects(Robot.rect) || r.intersects(Robot.rect2) || r.intersects(Robot.rect3) || r.intersects(Robot.rect4)){
				if (StartingClass.robot.health > 0) {
					StartingClass.robot.health -= 1;
					Robot.timeofLastHit = System.currentTimeMillis();
					StartingClass.robot.justGotHit = true;
				}
				if (StartingClass.robot.health == 0) {
					StartingClass.robot.setCenterY(700);				
				}
				
				}
			}
	}
	
public void follow() {
		
		if (centerX < -95 || centerX > 810){
			movementSpeed = 0;
		}

		else if (Math.abs(StartingClass.robot.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {

			if (StartingClass.robot.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

	}

	public void die() {

	}

	public void attack() {

	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}	

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
	
	

}
