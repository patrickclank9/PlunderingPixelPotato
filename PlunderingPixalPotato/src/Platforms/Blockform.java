package Platforms;

import jgame.GSprite;
import jgame.ImageCache;

public class Blockform extends GSprite implements Platform {

	public Blockform(){
		super(ImageCache.getImage("Platformblock.png"));
	}

}
