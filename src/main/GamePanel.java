package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import graphics.SpriteSheetObject;
import graphics.SpriteSheetPlayer;
import graphics.SpriteSheetTile;
import object.SuperObject;
import world.Tile;
import world.World;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	// SCREEN SETTINGS
	final static int originalTileSize = 16; // 16x16 pixels
	public final static int scale = 3;

	public final static int tileSize = originalTileSize * scale; // 48x48 pixels
	public final static int maxScreenCol = 16;
	public final static int maxScreenRow = 12;
	public final static int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final static int screenHeight = tileSize * maxScreenRow; // 576 pixels

	// WORLD SETTINGS
	public int maxWorldCol;
	public int maxWorldRow;

	// FPS
	int FPS = 60;

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	public static SpriteSheetTile spriteSheetTile;
	public static SpriteSheetPlayer spriteSheetPlayer;
	public static SpriteSheetObject spriteSheetObject;
	public static World world;
	public static Player player;
	public CollisionChecker cChecker;
	public AssetSetter aSetter = new AssetSetter(this);
	public SuperObject obj[] = new SuperObject[10];	

	public GamePanel() {

		spriteSheetTile = new SpriteSheetTile("/tile/tiles.png");	
		spriteSheetObject = new SpriteSheetObject("/objects/objects.png");		
		world = new World("/map/map.png");
		player = new Player(this, keyH);
		cChecker = new CollisionChecker(this);
		
		maxWorldCol = World.WIDTH;
		maxWorldRow = World.HEIGHT;

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);			

	}

	public void setupGame() {

		aSetter.setObject();

	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();

	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}

			if (timer >= 1000000000) {
				//System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

	public void update() {

		player.update();		

	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		// WORLD
		world.draw(g2, this);		

		// OBJECT
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// PLAYER
		player.draw(g2);

		g2.dispose();
	}

}
