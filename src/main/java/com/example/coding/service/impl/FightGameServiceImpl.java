package com.example.coding.service.impl;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import org.springframework.stereotype.Service;

import com.example.coding.model.BufferedImageLoader;
import com.example.coding.model.CharSel1;
import com.example.coding.model.KeyInput;
import com.example.coding.model.Menu;
import com.example.coding.model.MouseInput;
import com.example.coding.model.Player;
import com.example.coding.model.Player2;
import com.example.coding.model.Texture;
import com.example.coding.service.FightGameService;
import com.example.coding.util.IConstants;

@Service
public class FightGameServiceImpl extends Canvas implements FightGameService,
		Runnable {

	private static final long serialVersionUID = -8569179005261735671L;

	private boolean running = false;
	private Thread thread;
	private CharSel1 charSel1;
	private BufferedImage menuIMG = null;
	private BufferedImage charSelIMG = null;
	private Menu menu;
	public BufferedImage player1 = null; 
	public BufferedImage player2 = null; 
	public BufferedImage gameBG = null;
	public static IConstants.STATE State = IConstants.STATE.MENU;
	private Player p;
	private Player2 p2;
	static Texture tex;

	// current state and choice
	public static IConstants.CHOICEP1 ChoiceP1 = IConstants.CHOICEP1.NOTHING;
	public static IConstants.CHOICEP2 ChoiceP2 = IConstants.CHOICEP2.NOTHING2;

	@Override
	public void gameStart() {

		FightGameServiceImpl game = new FightGameServiceImpl();
		game.setPreferredSize(new Dimension(IConstants.gameWindows.WIDTH
				* IConstants.gameWindows.SCALE, IConstants.gameWindows.HEIGHT
				* IConstants.gameWindows.SCALE));
		game.setMaximumSize(new Dimension(IConstants.gameWindows.WIDTH
				* IConstants.gameWindows.SCALE, IConstants.gameWindows.HEIGHT
				* IConstants.gameWindows.SCALE));
		game.setMinimumSize(new Dimension(IConstants.gameWindows.WIDTH
				* IConstants.gameWindows.SCALE, IConstants.gameWindows.HEIGHT
				* IConstants.gameWindows.SCALE));

		JFrame frame = new JFrame("Final Battle.");
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		init();

		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void init() {

		BufferedImageLoader loaderMenu = new BufferedImageLoader();
		BufferedImageLoader loaderChar = new BufferedImageLoader();
		BufferedImageLoader loaderGBG = new BufferedImageLoader();
		tex = new Texture();
		try {
			menuIMG = loaderMenu.loadImage("/res/BG.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			gameBG = loaderGBG.loadImage("/res/GAME_BG.JPG");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			charSelIMG = loaderChar.loadImage("/res/CHAR_BG.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addMouseListener(new MouseInput());
		addKeyListener(new KeyInput(this));
		menu = new Menu();
		charSel1 = new CharSel1();
	}

	private void tick() {
		if (State == IConstants.STATE.GAME) {
			p.tick();
			p2.tick();
		}
		if (State == IConstants.STATE.GAME) {
			p.init();
			p2.init();
		}
		if (State == IConstants.STATE.CHOOSE) {
			p = new Player(140.0, 340.0);
			p2 = new Player2(800.0 - 140.0, 340.0);
			State = IConstants.STATE.GAME;
		}
	}

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		if (State == IConstants.STATE.GAME) {

			g.clearRect(0, 0, 800, 600);

			g.drawImage(gameBG, 0, 0, null);
			p.render(g);
			p2.render(g);

		} else if (State == IConstants.STATE.MENU) {
			g.clearRect(0, 0, 800, 600);
			g.drawImage(menuIMG, 0, 0, null);
			menu.render(g);

		} else if (State == IConstants.STATE.CHARSEL1 || State == IConstants.STATE.CHARSEL2) {
			g.clearRect(0, 0, 800, 600);
			g.drawImage(charSelIMG, 0, 0, null);
			charSel1.render(g);
		}
		g.dispose();
		bs.show();
	}

	public Rectangle playButton = new Rectangle(41, 225, 131, 69);
	public Rectangle optionsButton = new Rectangle(41, 312, 131, 69);
	public Rectangle exitButton = new Rectangle(41, 400, 131, 69);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(playButton);
		g2d.draw(optionsButton);
		g2d.draw(exitButton);
	}

	public static Texture getInstance() {
		return tex;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_D) {
			p.setVelX(5);
		} else if (key == KeyEvent.VK_A) {
			p.setVelX(-5);
		} else if (key == KeyEvent.VK_S) {
			p.setVelY(1);
		} else if (key == KeyEvent.VK_W && !p.isJumping()) {
			p.setVelY(-17);
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(this.getClass().getResource(
								"/music/jump.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			}

			catch (Exception ex) {
			}

		} else if (key == KeyEvent.VK_J) {
			p.punch = true;
			p.inAction = true;

			// punch audio file
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(this.getClass().getResource(
								"/music/punch.au"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			}

			catch (Exception ex) {
			}

			if (p.facing == 1 && p.getX() != 0) {
				p.setVelX(0);
			} else if (p.facing == 0 && p.getX() != 0) {
				p.setVelX(0);
			}
		} else if (key == KeyEvent.VK_K) {
			p.kick = true;
			p.inAction = true;

			// kick audio file

			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(this.getClass().getResource(
								"/music/kick.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception ex) {
			}
			p.setVelX(0);
		} else if (key == KeyEvent.VK_L) {
			p.special = true;
			p.inAction = true;

			// punch audio file
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(this.getClass().getResource(
								"/music/select.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			}

			catch (Exception ex) {
			}

			//
			p.setVelX(0);
		} else if (key == KeyEvent.VK_S && key == KeyEvent.VK_D) {
			p.setX(p.getX() + 140);
			p.strafe = true;
			p.inAction = true;
		} else if (key == KeyEvent.VK_S && key == KeyEvent.VK_A) {
			p.setX(p.getX() - 140);
			p.strafe = true;
			p.inAction = true;
		}

		if (key == KeyEvent.VK_RIGHT) {
			p2.setVelXp2(5);
		} else if (key == KeyEvent.VK_LEFT) {
			p2.setVelXp2(-5);
		} else if (key == KeyEvent.VK_DOWN) {
			p2.setVelYp2(1);
		} else if (key == KeyEvent.VK_UP && !p2.isJumpingp2()) {
			p2.setVelYp2(-17);

			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(this.getClass().getResource(
								"/music/jump.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception ex) {
			}
		} else if (key == KeyEvent.VK_NUMPAD1) {
			p2.punchP2 = true;
			p2.inActionP2 = true;

			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(this.getClass().getResource(
								"/music/punch2.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception ex) {
			}

			p2.setVelXp2(0);
		} else if (key == KeyEvent.VK_NUMPAD2) {
			p2.kickP2 = true;
			p2.inActionP2 = true;
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(this.getClass().getResource(
								"/music/kick2.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			}

			catch (Exception ex) {
			}
			p2.setVelXp2(0);
		} else if (key == KeyEvent.VK_NUMPAD3) {
			p2.specialP2 = true;
			p2.inActionP2 = true;

			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(this.getClass().getResource(
								"/music/special2.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			}

			catch (Exception ex) {
			}

			p2.setVelXp2(0);
		} else if (key == KeyEvent.VK_DOWN && key == KeyEvent.VK_LEFT) {
			p2.strafeP2 = true;
			p2.inActionP2 = true;
		} else if (key == KeyEvent.VK_DOWN && key == KeyEvent.VK_RIGHT) {
			p2.strafeP2 = true;
			p2.inActionP2 = true;

		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_D) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_A) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_S) {
			// p.setVelY(0);
		} else if (key == KeyEvent.VK_W) {
			// p.setVelY(0);
		} else if (key == KeyEvent.VK_J) {
			p.punch = false;
			p.inAction = false;

		} else if (key == KeyEvent.VK_K) {
			p.kick = false;
			p.inAction = false;
			p.setVelX(0);
		} else if (key == KeyEvent.VK_L) {
			p.special = false;
			p.inAction = false;
			p.setVelX(0);
		} else if (key == KeyEvent.VK_S && key == KeyEvent.VK_D) {
			p.setX(p.getX() + 140);
			p.strafe = false;
			p.inAction = false;
		} else if (key == KeyEvent.VK_S && key == KeyEvent.VK_A) {
			p.setX(p.getX() - 140);
			p.strafe = false;
			p.inAction = false;
		}

		if (key == KeyEvent.VK_RIGHT) {
			p2.setVelXp2(0);
		} else if (key == KeyEvent.VK_LEFT) {
			p2.setVelXp2(0);
		} else if (key == KeyEvent.VK_DOWN) {
			// p2.setVelYp2(0);
		} else if (key == KeyEvent.VK_UP) {
			// p2.setVelYp2(0);
		} else if (key == KeyEvent.VK_NUMPAD1) {
			p2.punchP2 = false;
			p2.inActionP2 = false;
			p2.setVelXp2(0);
		} else if (key == KeyEvent.VK_NUMPAD2) {
			p2.kickP2 = false;
			p2.inActionP2 = false;
			p2.setVelXp2(0);
		} else if (key == KeyEvent.VK_NUMPAD3) {
			p2.specialP2 = false;
			p2.inActionP2 = false;
			p2.setVelXp2(0);
		} else if (key == KeyEvent.VK_DOWN && key == KeyEvent.VK_LEFT) {
			p2.strafeP2 = false;
			p2.inActionP2 = false;
		} else if (key == KeyEvent.VK_DOWN && key == KeyEvent.VK_RIGHT) {
			p2.strafeP2 = false;
			p2.inActionP2 = false;

		}

	}

}
