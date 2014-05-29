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
	public static int Money;

	public int[] spikex = new int[] { 224, 351, 705, 769, 897, 1057, 1057, 960,
			704, 769, 832, 383, 317, 254 };
	public int[] spikey /* Wikey */= new int[] { 567, 503, 568, 631, 628, 502,
			471, 342, 345, 344, 344, 216, 278, 341 };

	public int[] platformx = new int[] { 192, 320, 575, 64, 833, 960, 1151,
			1215, 896, 639, 448, 127, 64 };
	public int[] platformy = new int[] { 599, 535, 600, 695, 664, 537, 407,
			279, 278, 217, 151, 343, 216 };

	public int[] pixelx = new int[] { 61, 88, 164, 308, 330, 233, 454, 479,
			508, 529, 552, 570, 598, 667, 640, 614, 611, 634, 661, 684, 649,
			753, 769, 795, 823, 848, 857, 850, 805, 938, 1024, 1057, 1087,
			1128, 1165, 1179, 1177, 1158, 1133, 1084, 1055, 1018, 984, 949,
			919, 894, 869, 788, 721, 689, 629, 546, 489, 458, 390, 370, 344,
			323, 302, 288, 260, 215, 187, 159, 135, 109, 114, 99, 57, 24, 61,
			61, 41, 30, 55, 74, 50, 40, 60, 68, 47, 58, 58, 43, 62, 82, 81, 56,
			41, 43, 65, 85, 62, 40 };
	public int[] pixely = new int[] { 632, 592, 520, 441, 429, 477, 443, 459,
			477, 501, 534, 554, 551, 466, 470, 460, 433, 417, 417, 427, 438,
			498, 520, 527, 527, 531, 558, 584, 573, 481, 431, 412, 395, 375,
			352, 316, 287, 267, 251, 232, 223, 223, 233, 220, 213, 205, 200,
			185, 164, 159, 152, 110, 99, 99, 113, 130, 146, 160, 182, 205, 239,
			252, 253, 249, 257, 283, 187, 146, 140, 135, 115, 142, 161, 133,
			153, 172, 156, 131, 113, 140, 154, 131, 159, 137, 120, 143, 172,
			178, 157, 131, 117, 135, 152, 164 };

	public PPPGameView() {
		setSize(1280, 720);
		this.setBackgroundColor(Color.BLACK);
	}

	public void viewShown() {
		initScrollGameView();
	}

	public void initScrollGameView() {
		// Create and place Background
		GSprite bk = new GSprite(ImageCache.getImage("Background/"
				+ ((int) Math.random() * 3 + 1) + ".png"));
		bk.setAnchorTopLeft();
		addAt(bk, 0, 0);

		// Create, place, and add PlateformControler to Hero
		PixelPotato hero = new PixelPotato();
		hero.setAnchorPositionY(getHeight() / (-2));
		hero.addController(new PlatformController(PlatformControlScheme.WASD,
				-10, -20, 1.5));
		addAt(hero, 65, 300);

		// addAt(new SolidPlatform(), 1280 / 2, 715);

		// Create and place all deathSpikes
		for (int a = 0; a < spikex.length - 1; a++)
			addAt(new deathSpike(), spikex[a], spikey[a]);

		// Create and place all Block Platforms
		for (int a = 0; a < platformx.length - 1; a++)
			addAt(new Blockform(), platformx[a], platformy[a]);

		// Create and place all Pixels
		for (int a = 0; a < pixelx.length - 1; a++)
			addAt(new Pixel(), pixelx[a], pixely[a]);

		bank = new Bank();
		gm = new GMessage();
		GSprite bankTile = createSprite();
		addAt(bankTile, 1200, 40);

		FrameListener fl = new FrameListener() {
			@Override
			public void invoke(GObject target, Context context) {
				gm.setText(bank.toString());
				Money = Bank.getMoney();
			}
		};
		addListener(fl);
	}

	public static GSprite createSprite() {
		BufferedImage img = ImageCache.forClass(PlunderingPixelPotato.class)
				.get("Moneypad.png");
		GSprite gs = new GSprite(img);
		gm.setAlignmentX(0.5);
		gm.setAlignmentY(0.5);
		gm.setFontSize(18);
		gm.setColor(Color.white);
		gs.addAtCenter(gm);
		return gs;
	}
}
