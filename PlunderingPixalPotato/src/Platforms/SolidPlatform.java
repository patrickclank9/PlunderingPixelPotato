package Platforms;

import jgame.GSprite;
import jgame.ImageCache;

public class SolidPlatform extends GSprite implements Platform {

	public SolidPlatform(){
		super(ImageCache.getImage("defaultPlatform.png"));
	}

}
