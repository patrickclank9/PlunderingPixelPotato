package PPP;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Platforms.Blockform;
import Platforms.SolidPlatform;
import Platforms.deathSpike;
import MainCharacter.PlatformControlScheme;
import MainCharacter.PlatformController;
import MainCharacter.PixelPotato;
import Mechanics.Bank;
import Mechanics.Pixel;
import jgame.Context;
import jgame.GContainer;
import jgame.GMessage;
import jgame.GObject;
import jgame.GSprite;
import jgame.ImageCache;
import jgame.listener.FrameListener;

public class PPPGameView extends GContainer {

	private Bank bank;
	private static GMessage gm;
	
	public int[] spikex = new int[] { 676, 506, 857, 507, };
	public int[] spikey /*Wikey*/ = new int[] { 498, 604, 377, 306, };
	
	public int[] platformx = new int[] { 302, 676, 506, 1008, 857, 507, 187 };
	public int[] platformy = new int[] { 567, 506, 612, 469, 385, 314, 269 };
	
	public int[] pixelx = new int[] { 637, 612, 585, 559, 552, 551, 576, 602, 628, 582, 606, 636 };
	public int[] pixely = new int[] { 527, 530, 530, 529, 504, 479, 476, 476, 476, 503, 499, 497 };

	public PPPGameView() {
		setSize(1280, 720);
		this.setBackgroundColor(Color.BLACK);
	}

	public void viewShown() {
		initScrollGameView();
	}

	public void initScrollGameView() {
		//Create and place Background
		GSprite bk = new GSprite(ImageCache.getImage("Background/" + ((int) Math.random() * 3 + 1) + ".png"));
		bk.setAnchorTopLeft();
		addAt(bk, 0, 0);
		
		//Create, place, and add PlateformControler to Hero
		PixelPotato hero = new PixelPotato();
		hero.setAnchorPositionY(getHeight() / (-2));
		hero.addController(new PlatformController(PlatformControlScheme.WASD, -10, -20, 1.5));
		addAt(hero, 20, 330);
		
		addAt(new SolidPlatform(), 1280 / 2, 715);
		
		//Create and place all deathSpikes
		for (int a = 0; a < spikex.length - 1; a++) addAt(new deathSpike(), spikex[a], spikey[a]);
		
		//Create and place all Block Platforms
		for (int a = 0; a < platformx.length - 1; a++) addAt(new Blockform(), platformx[a], platformy[a]);
		
		//Create and place all Pixels
		for (int a = 0; a < pixelx.length - 1; a++) addAt(new Pixel(), pixelx[a], pixely[a]);
		
		bank = new Bank();
		gm = new GMessage();
		GSprite bankTile = createSprite();
		addAt(bankTile, 1200, 40);

		FrameListener fl = new FrameListener() {
			@Override
			public void invoke(GObject target, Context context) {
				gm.setText(bank.toString());
			}
		};
		addListener(fl);
	}

	public static GSprite createSprite() {
		BufferedImage img = ImageCache.forClass(PlunderingPixelPotato.class).get("Moneypad.png");
		GSprite gs = new GSprite(img);
		gm.setAlignmentX(0.5);
		gm.setAlignmentY(0.5);
		gm.setFontSize(18);
		gm.setColor(Color.white);
		gs.addAtCenter(gm);
		return gs;
	}
}
