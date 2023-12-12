package application;
	
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {
	
	public final int WIDTH=1000;
	public final int HEIGHT=500;
	private int score;
	private boolean running=false;
	private Player p;
	private ground g1,g2,g3,g4;
	private Obstacle[] ob=new Obstacle[10];
	Random random=new Random();
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Running Square");
			Group menugrp=new Group();
			Scene menu=new Scene(menugrp);
			Canvas cmenu=new Canvas(WIDTH,HEIGHT);
			menugrp.getChildren().add(cmenu);
			GraphicsContext gcmenu=cmenu.getGraphicsContext2D();
			Group gamegrp=new Group();
			Scene game=new Scene(gamegrp);
			Canvas cgame=new Canvas(WIDTH,HEIGHT);
			gamegrp.getChildren().add(cgame);
			GraphicsContext gcgame=cgame.getGraphicsContext2D();
			gcmenu.setFill(Color.DARKGREEN);
			gcmenu.fillRect(0, 0, WIDTH, HEIGHT);
			gcmenu.setFill(Color.RED);
			gcmenu.setStroke(Color.BLACK);
			gcmenu.setFont(Font.font(50));
			gcmenu.fillText("Running Square", 350, 100);
			gcmenu.strokeText("Running Square", 350, 100);
			gcmenu.fillText("Press SPACE to start", 300,300);
			gcmenu.strokeText("Press SPACE to start", 300, 300);
			gcgame.setFill(Color.DARKGREEN);
			gcgame.fillRect(0, 0, WIDTH, HEIGHT);
			menu.setOnKeyPressed(new EventHandler<KeyEvent>()
					{

						@Override
						public void handle(KeyEvent e) {
							// TODO Auto-generated method stub
							String a=e.getCode().toString();
							if(a=="SPACE")
							{
								primaryStage.setScene(game);
								score=0;running=true;
								p=new Player(30,370,30,30,30.0);
								g1=new ground(0,400,1500,150,175.0);
								g2=new ground(1600,400,1500,150,175.0);
								g3=new ground(3200,400,1500,150,175.0);
								g4=new ground(4800,400,1500,150,175.0);
								p.render(gcgame);
								g1.render(gcgame);
								for(int i=0;i<10;i++)
								{
									if(i==0)ob[i]=new Obstacle(500,370,20,30,175.0);
									else
									{
										double x=ob[i-1].x+300;
										if((x>g1.x+g1.w-150 &&x<g2.x+150))
										{
											x=g2.x+150;
											
										}
										else if((x>g2.x+g2.w-150 &&x<g3.x+150))
										{
											x=g3.x+150;
											
										}
										else if((x>g3.x+g3.w-150 &&x<g4.x+150))
										{
											x=g4.x+150;
											
										}
										else if((x>g4.x+g4.w-150 &&x<g1.x+150)&&g1.x>g4.x)
										{
											x=g1.x+150;
											
										}
										//System.out.println(i+"= "+x);
										ob[i]=new Obstacle(x,370,20,30,175.0);
									}
									
								}
								for(Obstacle ob : ob)
								{
									ob.render(gcgame);
								}
							}
						}
				
					});
			game.setOnKeyPressed(new EventHandler<KeyEvent>()
					{

						@Override
						public void handle(KeyEvent event) {
							// TODO Auto-generated method stub
							if(event.getCode().toString()=="SPACE")
							{
								if(running) {
								if(p.intersect(g1)||p.intersect(g2)||p.intersect(g3)||p.intersect(g4))
								{
									p.jump();
									gcgame.setFill(Color.DARKGREEN);
									gcgame.fillRect(0, 0, WIDTH, HEIGHT);
									p.render(gcgame);
								}
							}
								else
								{
									primaryStage.setScene(menu);
								}
							}
							
						}
				
					});
			new AnimationTimer()
			{

				long start=System.nanoTime();
				@Override
				public void handle(long now) {
					// TODO Auto-generated method stub
					double t=(now-start)/1000000000.0;
					start=now;
					if(running)
					{
						if(p.intersect(g1)||p.intersect(g2)||p.intersect(g3)||p.intersect(g4))
						{
							p.setSpeedY(0.0);
						}
						else {
							p.setSpeedY(150.0);
						}
						p.update(t);
						g1.update(t);
						g2.update(t);
						g3.update(t);
						g4.update(t);
						if(g1.x+g1.w<0) {g1.x=g4.x+g4.w+100;g1.w=random.nextDouble()*300+300;}
						if(g2.x+g2.w<0) {g2.x=g1.x+g1.w+100;g2.w=random.nextDouble()*300+300;}
						if(g3.x+g3.w<0) {g3.x=g2.x+g2.w+100;g3.w=random.nextDouble()*300+300;}
						if(g4.x+g4.w<0) {g4.x=g3.x+g3.w+100;g4.w=random.nextDouble()*300+300;}
						for(int i=0;i<10;i++)
						{
							ob[i].update(t);
							if(ob[i].x<0)
							{
								if(i==0)
								{
									ob[i].x=ob[9].x+300;
									if((ob[i].x>g1.x+g1.w-150 &&ob[i].x<g2.x+150))
									{
										ob[i].x=g2.x+150;
										
									}
									else if((ob[i].x>g2.x+g2.w-150 &&ob[i].x<g3.x+150))
									{
										ob[i].x=g3.x+150;
										
									}
									else if((ob[i].x>g3.x+g3.w-150 &&ob[i].x<g4.x+150))
									{
										ob[i].x=g4.x+150;
										
									}
									else if((ob[i].x>g4.x+g4.w-150 &&ob[i].x<g1.x+150)&&g1.x>g4.x)
									{
										ob[i].x=g1.x+150;
										
									}
								}
								else
								{
									ob[i].x=ob[i-1].x+300;
									if((ob[i].x>g1.x+g1.w-150 &&ob[i].x<g2.x+150))
									{
										ob[i].x=g2.x+150;
										
									}
									else if((ob[i].x>g2.x+g2.w-150 &&ob[i].x<g3.x+150))
									{
										ob[i].x=g3.x+150;
										
									}
									else if((ob[i].x>g3.x+g3.w-150 &&ob[i].x<g4.x+150))
									{
										ob[i].x=g4.x+150;
										
									}
									else if((ob[i].x>g4.x+g4.w-150 &&ob[i].x<g1.x+150)&&g1.x>g4.x)
									{
										ob[i].x=g1.x+150;
										
									}
								
								}
							}
							
						}
						gcgame.setFill(Color.DARKGREEN);
						gcgame.fillRect(0, 0, WIDTH, HEIGHT);
						p.render(gcgame);

						if(g1.x<=1000) g1.render(gcgame);
						if(g2.x<=1000) g2.render(gcgame);
						if(g3.x<=1000) g3.render(gcgame);
						if(g4.x<=1000) g4.render(gcgame);
						for(int i=0;i<10;i++)
						{
							ob[i].render(gcgame);
						}
						score++;
						gcgame.setFont(Font.font(20));
						gcgame.setFill(Color.BLUE);
						gcgame.setStroke(Color.BLACK);
						gcgame.fillText("Score = "+score, 10, 50);
						gcgame.strokeText("Score = "+score, 10, 50);
						if(p.y>500)
						{
							running=false;
						}
						for(int i=0;i<10;i++)
						{
							if(p.intersect(ob[i]))
							{
								running=false;
							}
						}
					}
					else
					{
						gcgame.setFill(Color.DARKGREEN);
						gcgame.fillRect(0, 0, WIDTH,HEIGHT);
						gcgame.setFill(Color.RED);
						gcgame.setFont(Font.font(30));
						gcgame.setStroke(Color.BLACK);
						gcgame.fillText("Game Over!! Press SPACE to go back to menu", 250, 200);
						gcgame.strokeText("Game Over!! Press SPACE to go back to menu", 250, 200);
						gcgame.fillText("Score = "+score, 450, 300);
						gcgame.strokeText("Score = "+score, 450, 300);
					}
				}
				
			}.start();
			
			primaryStage.setScene(menu);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
