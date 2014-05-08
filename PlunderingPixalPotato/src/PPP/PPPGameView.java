package PPP;

import java.awt.Color;

import Platforms.SolidPlatform;
import MainCharacter.PlatformControlScheme;
import MainCharacter.PlatformController;
import MainCharacter.PixelPotato;
import Mechanics.Pixel;
import jgame.GContainer;
import jgame.GSprite;
import jgame.ImageCache;

public class PPPGameView extends GContainer{
	
	public PPPGameView(){
		setSize(1280, 720);
		this.setBackgroundColor(Color.BLACK);
	}
	public void viewShown() {
		initScrollGameView();
	}

	public void initScrollGameView() {
		GSprite bk = new GSprite(ImageCache.getImage("background.png"));
		bk.setAnchorTopLeft();
		addAt(bk, 0,0);
		PlatformController hc = new PlatformController(PlatformControlScheme.WASD, -7, -17, 1);
		PixelPotato hero = new PixelPotato();
		Pixel p = new Pixel();
		hero.setAnchorPositionY(getHeight()/(-2));
		addAt(new SolidPlatform(), 1280/2, 715);
		addAt(hero, 20, 330);
		addAt(p, 40, 330);
		hero.addController(hc);
	}
}

