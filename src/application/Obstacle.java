package application;

import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Obstacle extends Sprite {
	private int type;
	Obstacle(double x,double y,double w,double h,double spdx)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.spdx=spdx;
		type = new Random().nextInt(3);
	}
	public Obstacle getob()
	{
		return this;
	}
	@Override
	public void update(double t)
	{
		this.x-=spdx*t;
	}
	@Override 
	public void render(GraphicsContext gc)
	{
		if(type==0)
		{
			gc.setFill(Color.BLUE);
			gc.setStroke(Color.BLACK);
			gc.fillRect(x, y, w, h);
			gc.strokeRect(x, y, w, h);
		}
		else if (type == 1)
		{
			gc.setFill(Color.CRIMSON);
			gc.setStroke(Color.BLACK);
			double xpoint[]={x,x+w,x+w/2};
			double ypoint[]= {y+h,y+h,y};
			gc.fillPolygon(xpoint,ypoint, 3);
			gc.strokePolygon(xpoint, ypoint, 3);
		}
		else if (type==2)
		{
			gc.setFill(Color.DARKCYAN);
			gc.setStroke(Color.BLACK);
			gc.fillRect(x, y, w, h);
			gc.strokeRect(x, y, w, h);
		}
		else
		{
			gc.setFill(Color.GRAY);
			gc.setStroke(Color.BLACK);
			gc.fillRect(x, y, w, h);
			gc.strokeRect(x, y, w, h);
		}
	}
	@Override
	public Rectangle2D getBoundary()
	{
		return new Rectangle2D(x,y,w,h);
	}
}
