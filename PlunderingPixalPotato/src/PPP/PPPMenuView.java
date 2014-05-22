package PPP;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import PPP.PlunderingPixelPotato.Views;
import jgame.ButtonState;
import jgame.Context;
import jgame.GButton;
import jgame.GContainer;
import jgame.GMessage;
import jgame.GObject;
import jgame.GSprite;
import jgame.ImageCache;
import jgame.controller.Interpolation;
import jgame.controller.MovementTween;
import jgame.listener.ButtonListener;
import jgame.listener.DelayListener;

public class PPPMenuView extends GContainer{
	public PPPMenuView(){
		setSize(1280, 720);
		this.setBackgroundColor(Color.BLACK);
	}
	
	@Override
	public void viewShown() {
		initMenuView();
	}

	public void initMenuView() {
		removeAllChildren();

		BufferedImage bg = ImageCache.forClass(PlunderingPixelPotato.class).get("Menu.png");
		GSprite g = new GSprite(bg);
		setBackgroundSprite(g);
		
		GButton mbPlay = this.createButton(0, "");
		mbPlay.setLocation(-100, 200);

		ButtonListener blPlay = new ButtonListener() {
			@Override
			public void mouseClicked(Context context) {
				super.mouseClicked(context);
				context.setCurrentGameView(Views.GAME);
			}
		};
		mbPlay.addListener(blPlay);
	
	}
	private GButton createButton(final int buttonIndex, String buttonText) {

		MovementTween mt = new MovementTween(20, Interpolation.EASE_IN, 720, 50);
		MovementTween mtb = new MovementTween(10, Interpolation.EASE, -40, 0);
		mt.chain(mtb);
		final GButton btn = new GButton();
		btn.addController(mt);
		btn.setStateSprite(ButtonState.NONE,
				createButtonSprite("Button/None.png"));
		btn.setStateSprite(ButtonState.HOVERED,
				createButtonSprite("Button/Hover.png"));
		btn.setStateSprite(ButtonState.PRESSED,
				createButtonSprite("Button/Pressed.png"));

		DelayListener dl = new DelayListener(buttonIndex * 5) {

			@Override
			public void invoke(GObject target, Context context) {
				addAt(btn, -100, buttonIndex * 100 + 200);
			}
		};
		addListener(dl);
		return btn;
	}

	public static GSprite createButtonSprite(String fn) {
		BufferedImage img = ImageCache.forClass(PlunderingPixelPotato.class).get(fn);
		GSprite gs = new GSprite(img);

		Rectangle nineSliceCenter = new Rectangle(15, 15, 6, 6);
		gs.setNineSliceCenter(nineSliceCenter);
		return gs;
	}
}
