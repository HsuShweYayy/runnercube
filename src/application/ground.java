package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ground extends Sprite {

	ground(double x,double y,double w,double h,double spdx)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.spdx=spdx;	
	}
	@Override
	public void update(double t)
	{
		this.x-=spdx*t;
	}
	@Override 
	public void render(GraphicsContext gc)
	{
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.GREEN);
		gc.fillRect(x, y, w, h);
		gc.strokeRect(x, y, w, h);
	}
	@Override
	public Rectangle2D getBoundary()
	{
		return new Rectangle2D(x,y,w,1);
	}
}
