package application;

//import com.sun.prism.paint.Color;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Sprite {

	//private Color c =Color.RED;
	Player(double x,double y,double w,double h,double spdy)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.spdy=spdy;
	}
	@Override
	public void render(GraphicsContext gc)
	{
		gc.setFill(Color.RED);gc.setStroke(Color.BLACK);
		gc.strokeRect(x, y, w, h);
		gc.fillRect(x,y,w,h);
	}
	@Override
	public void update(double t)
	{
		y+=this.spdy*t;
	}
	public void jump()
	{
		this.y-=100;
	}
	public void setSpeedY(double spdy)
	{
		this.spdy=spdy;
	}
}
