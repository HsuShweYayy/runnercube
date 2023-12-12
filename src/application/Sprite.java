package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Sprite {

	public double x;
	public double y;
	public double spdx;
	public double spdy;
	public double w;
	public double h;
	
	public void update(double t)
	{
		
	}
	public void render(GraphicsContext gc)
    {
		
    }
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(x,y,w,h);
    }
    public boolean intersect(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
}
