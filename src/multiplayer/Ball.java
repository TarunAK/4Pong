package multiplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

import singleplayer.PingPong;

public class Ball extends Rectangle {
	
	private static final long serialVersionUID = 1L;

	//CreateServer cs;
	double speed = 3;
	public int x;
	List<Integer> number = Arrays.asList(1,-1);
	public int y;

	int width;
	int height;
	double vx = speed; 
	double vy = speed;
	
	int initialx = 0;
	int initialy = 0;
	int initialvx = 3;
	int initialvy = 3;
	

	public Ball(int x,int y) {
		//this.cs = cs;
        this.x = x;
        this.y = y;
		width = 16;
		height = 16;
		initialx = x;
		initialy = y;
		//x = cs.getWidth() / 2 - width;
		//y = cs.getHeight() / 2 - height;

		setBounds(x, y, width, height);
	} // End constructor
	
	public void remove(){
		width=0;
		height=0;
	}
	
	boolean isBound1 = false,isBound2=false;
	
	public void tick(CreateServer cs, double delta) {
		movement(cs,delta);
		setBounds(x, y, width, height);
	} // End tick
	
	public void tick_(ClientPlayer cs, double delta) {
		movement_(cs,delta);
		setBounds(x, y, width, height);
	} // End tick
	
	
	
	private void movement(CreateServer cs,double delta) {
		if (y <= 0){
			vy = speed;	
			cs.client2Score++;
		}
		if (y >= 510 - height){
			vy = -speed;
			cs.client3Score++;
		}
		if (x <= 0) { // If hits left side (Player)
			vx = speed;
			cs.serverScore++;
			isBound1 = true;
		
		}
		if (x >= 510 - width) { // If hits right side (AI)
			vx = -speed;
			cs.client1Score++;
			isBound1 = true;
			
		}

		x += vx;
		y += vy;
		
		if (this.intersects(cs.serverRect)) {
			vx = speed;
		}
		if (this.intersects(cs.client1Rect)) {
			vx = -speed;
		}
		if ((PingPong.players==3 || PingPong.players==4) && this.intersects(cs.client2Rect)) {
			vy = speed;
		}
		if (PingPong.players==4 && CreateServer.ainumber==2 && this.intersects(cs.client3Rect)) {
			vy = -speed;
		}
	} // End movement

	
	private void movement_(ClientPlayer cs,double delta) {
		if (y <= 0){
			vy = speed;	
			cs.client2Score++;
		}
		if (y >= 510 - height){
			vy = -speed;
			cs.client3Score++;
		}
		if (x <= 0) { // If hits left side (Player)
			vx = speed;
			cs.serverScore++;
			isBound2 = true;
		}
		if (x >= 510 - width) { // If hits right side (AI)
			vx = -speed;
			cs.client1Score++;
			isBound2 = true;
		}

		x += vx;
		y += vy;

		
		if (this.intersects(cs.serverRect)) {
			vx = speed;
		}
		if (this.intersects(cs.client1Rect)) {
			vx = -speed;
		}
		
	} // End movement
	
	public boolean isWait  = false;
	
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		if(isBound1 || isBound2) {
			g.fillOval(initialx, initialy, width, height);
			vx = initialvx;
			vy = initialvy;
			x = initialx;
			y = initialy;
		
			isBound1 = false;
			isBound2 = false;
			isWait = true;
			
		} else {
			g.fillOval(x, y, width, height);
			
			/*if(isWait) {
				try{Thread.sleep(3000);}catch(Exception e){}
				isWait = false;
			}*/
			
		}
	} // End render
	

	

}
