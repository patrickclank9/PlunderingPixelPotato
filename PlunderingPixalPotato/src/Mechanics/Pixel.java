package Mechanics;

import MainCharacter.PixelPotato;
import PPP.PlunderingPixelPotato;
import jgame.Context;
import jgame.GObject;
import jgame.GSprite;
import jgame.ImageCache;
import jgame.SoundManager;
import jgame.listener.HitTestListener;

public class Pixel extends GSprite{
	public Pixel(){
		super(ImageCache.getImage("Pixel.png"));
		HitTestListener htl = new HitTestListener(PixelPotato.class) {

			@Override
			public void invoke(GObject target, Context context) {
				SoundManager.forClass(PlunderingPixelPotato.class).play("Pixelget/" + (int)(Math.random() * 5 + 1) + ".wav");
				Bank.addMoney(100);
				target.removeSelf();
			}
		};
		addListener(htl);
	}
}

