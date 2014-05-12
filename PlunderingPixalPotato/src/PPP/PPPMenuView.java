package PPP;

import java.awt.Color;
import java.awt.image.BufferedImage;
import jgame.GContainer;
import jgame.GSprite;
import jgame.ImageCache;

public class PPPMenuView extends GContainer{
	public PPPMenuView(){
		setSize(1280, 720);
		this.setBackgroundColor(Color.BLACK);
	}
	
	@Override
	public void viewShown() {
		initDefendMenuView();
	}

	public void initDefendMenuView() {
		removeAllChildren();

		BufferedImage bg = ImageCache.forClass(PlunderingPixelPotato.class).get(
				"Background/1");
		GSprite g = new GSprite(bg);
		setBackgroundSprite(g);
	
	}
}
