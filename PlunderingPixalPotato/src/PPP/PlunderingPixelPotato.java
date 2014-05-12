package PPP;

import java.awt.Color;
import jgame.GRootContainer;
import jgame.Game;
import jgame.ImageCache;
import jgame.SoundManager;
import PPP.PlunderingPixelPotato;
import PPP.PPPGameView;
import PPP.PPPMenuView;

public class PlunderingPixelPotato extends Game{
	public static void main(String[] args) {
		ImageCache.create(PlunderingPixelPotato.class, "/PPP/rsc/");
		SoundManager.create(PlunderingPixelPotato.class, "/PPP/rsc/");
		PlunderingPixelPotato a = new PlunderingPixelPotato();
		a.startGame("Plundering Pixel Potato Alpha");
	}
	public PlunderingPixelPotato(){
		GRootContainer root = new GRootContainer(Color.BLACK);
		setRootContainer(root);
		root.addView(Views.GAME, new PPPGameView());
		root.addView(Views.MENU, new PPPMenuView());
		setTargetFPS(60);
		SoundManager.forClass(PlunderingPixelPotato.class).loopForever("Zalgo.ogg");
	}
	public enum Views {
		MENU, GAME;
	}
}