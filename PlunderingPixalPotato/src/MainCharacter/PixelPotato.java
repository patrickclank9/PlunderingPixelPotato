package MainCharacter;

import Mechanics.Bank;
import PPP.PlunderingPixelPotato;
import Platforms.deathSpike;
import jgame.Context;
import jgame.GObject;
import jgame.ImageCache;
import jgame.GSprite;
import jgame.SoundManager;
import jgame.listener.HitTestListener;

public class PixelPotato extends GSprite{
	
	public PixelPotato(){
		super(ImageCache.getImage("Potato.png"));
		HitTestListener htl = new HitTestListener(deathSpike.class) {

			@Override
			public void invoke(GObject target, Context context) {
//				List<PixelPotato> pixelPotatos = context.hitTestClass(PixelPotato.class);
//				for (PixelPotato pixelPotato : pixelPotatos)
				SoundManager.forClass(PlunderingPixelPotato.class).play("Death.wav");
				Bank.setMoney(0);
				
				target.removeSelf();
			}
		};
		addListener(htl);
	}
	
}
