package PPP;

import java.awt.Color;
import java.awt.image.BufferedImage;
import PPP.PPPGameView;
import jgame.Context;
import jgame.GContainer;
import jgame.GMessage;
import jgame.GObject;
import jgame.GSprite;
import jgame.ImageCache;
import jgame.listener.FrameListener;

public class PPPGameOverView extends GContainer{
	private static GMessage gm;
	public PPPGameOverView(){
		setSize(1280, 720);
		this.setBackgroundColor(Color.BLACK);
	}
	
	@Override
	public void viewShown() {
		initMenuView();
	}

	public void initMenuView() {
		removeAllChildren();

		BufferedImage bg = ImageCache.forClass(PlunderingPixelPotato.class).get("Lose.png");
		GSprite g = new GSprite(bg);
		setBackgroundSprite(g);
		
		gm = new GMessage();
		GSprite bankTile = createSprite();
		addAt(bankTile, 1280/2, 720/2 - 125);

		FrameListener fl = new FrameListener() {
			@Override
			public void invoke(GObject target, Context context) {
				gm.setText("Score:" + PPPGameView.Money);
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
