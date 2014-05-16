package Mechanics;

import java.util.List;

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
				List<PixelPotato> pixelPotatos = context.hitTestClass(PixelPotato.class);
				for (PixelPotato pixelPotato : pixelPotatos) {
					
				}
				SoundManager.forClass(PlunderingPixelPotato.class).play(getSound());
				Bank.addMoney(100);
				target.removeSelf();
			}
		};
		addListener(htl);
	}
	
	public String getSound() {
		int a = (int)(Math.random() * 5 + 1);
		return "Pixelget/" + a + ".wav";
	}
}

