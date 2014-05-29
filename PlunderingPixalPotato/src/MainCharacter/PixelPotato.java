package MainCharacter;

import PPP.PlunderingPixelPotato;
import PPP.PlunderingPixelPotato.Views;
import Platforms.deathSpike;
import jgame.Context;
import jgame.GObject;
import jgame.ImageCache;
import jgame.GSprite;
import jgame.SoundManager;
import jgame.listener.BoundaryRemovalListener;
import jgame.listener.HitTestListener;

public class PixelPotato extends GSprite{
	
	public PixelPotato(){
		super(ImageCache.getImage("Potato.png"));
		HitTestListener htl = new HitTestListener(deathSpike.class) {

			@Override
			public void invoke(GObject target, Context context) {  
				SoundManager.forClass(PlunderingPixelPotato.class).play("Death.wav");
				context.setCurrentGameView(Views.LOSE);
				target.removeSelf();
			}
		};
		addListener(htl);
		addListener(new BoundaryRemovalListener() {
			@Override
			public void invoke(GObject target, Context context) {
				SoundManager.forClass(PlunderingPixelPotato.class).play("Fall.wav");
				context.setCurrentGameView(Views.LOSE);
				target.removeSelf();
			}
		});
	}
	
}
