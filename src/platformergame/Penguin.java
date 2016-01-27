package platformergame;

public class Penguin extends Enemy {

	public Penguin(int centerX, int centerY) {
		setCenterX(centerX);
		setCenterY(centerY);
	}
	
	@Override
	public void update() {
		centerX += speedX;
		speedX = bg.getSpeedX()*5;
		// Do nothing, because penguins are friendly
	}

}
