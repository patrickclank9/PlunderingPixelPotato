package MainCharacter;

import java.util.List;
import java.util.Set;
import jgame.Context;
import jgame.GObject;
import jgame.SoundManager;
import jgame.controller.Controller;
import PPP.PlunderingPixelPotato;
import Platforms.Platform;
import MainCharacter.PlatformControlScheme;

/**a
 * A controller that controls an objects location based on keyboard input
 * defined by the given {@link PlatformControlScheme}. This controller cannot be
 * shared.
 * 
 * @author William Chargin
 * 
 */
public class PlatformController implements Controller {
	/**
	 * The default gravitational acceleration, in px/frame<sup>2</sup>.
	 */
	public static final double DEFAULT_GRAVITY = 1;
	/**
	 * The control scheme for this platform controller.
	 */
	private final PlatformControlScheme controlScheme;

	/**
	 * The object to be controlled by this platform controller.
	 */
	private GObject object;

	/**
	 * These two variables describe the current velocity of the player.
	 */
	private double vx, vy;

	/**
	 * The maximum horizontal movement speed.
	 */
	private double maxSpeed;

	/**
	 * The maximum jump speed.
	 */
	private double maxJump;

	/**
	 * The gravitational acceleration, in px/frame<sup>2</sup>.
	 */
	private double gravity;

	/**
	 * Creates the controller with the given parameters. The default
	 * gravitational acceleration will be used.
	 * 
	 * @param controlScheme
	 *            the control scheme to use
	 * @param maxSpeed
	 *            the maximum horizontal speed (in px/frame)
	 * @param maxJump
	 *            the maximum jump speed (in px/frame)
	 */
	public PlatformController(PlatformControlScheme controlScheme,
			double maxSpeed, double maxJump) {
		this(controlScheme, maxSpeed, maxJump, DEFAULT_GRAVITY); 
																	
	}

	/**
	 * Creates the controller with the given parameters.
	 * 
	 * @param controlScheme
	 *            the control scheme to use
	 * @param maxSpeed
	 *            the maximum horizontal speed (in px/frame)
	 * @param maxJump
	 *            the maximum jump speed (in px/frame)
	 * @param gravity
	 *            the gravitation acceleration (in px/frame<sup>2</sup>)
	 */
	public PlatformController(PlatformControlScheme controlScheme,
			double maxSpeed, double maxJump, double gravity) {
		super();
		this.controlScheme = controlScheme;
		vx = vy = 0;
		this.maxSpeed = maxSpeed;
		this.maxJump = maxJump;
		this.gravity = gravity;
		
	}

	@Override
	public void controlObject(GObject target, Context context) {
		// Ensure that this is locked to one object
		if (object == null) {
			object = target;
		} else if (object != target) {
			throw new IllegalArgumentException(
					"This PlatformController already belongs to " + object + " you Dope!");
		}

		Set<Integer> keys = context.getKeyCodesPressed();

		int horizontal = 0;
		boolean jump = false;
		
		

		for (int key : keys) {
			if (key == controlScheme.lt) {
				horizontal += 1;
			} else if (key == controlScheme.rt) {
				horizontal -= 1;
			} else if (key == controlScheme.jump) {
				jump = true;
			}
		}

		boolean onSolidGround = false;

		List<Platform> solidGrounds = context
				.getInstancesOfClass(Platform.class);

		for (Platform solidGround : solidGrounds) {

			GObject groundObject = (GObject) solidGround;
			if (target.hitTest(groundObject)) {
				onSolidGround = true;
				break;
			}

		}

		if (onSolidGround) {
			if (jump) {

				vy = maxJump;
				SoundManager.forClass(PlunderingPixelPotato.class).play("Bounce.wav");

			} else {

				vy = 0;
			}
		} else {
			vy += gravity;
			if (vy > target.getHeight()) {
				vy = target.getHeight();
			}
		}
		vx = horizontal * maxSpeed;

		target.setLocation(target.getX() + vx, target.getY() + vy);
	}

	/**
	 * Gets the current maximum jump.
	 * 
	 * @return the maximum jump, in px/frame
	 */
	public double getMaxJump() {
		return maxJump;
	}

	/**
	 * Gets the current maximum horizontal speed.
	 * 
	 * @return the maximum horizontal speed, in px/frame
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * Sets the new maximum jump.
	 * 
	 * @param maxJump
	 *            the maximum jump, in px/frame
	 */
	public void setMaxJump(double maxJump) {
		this.maxJump = maxJump;
	}

	/**
	 * Sets the maximum horizontal speed.
	 * 
	 * @param maxSpeed
	 *            the new maximum horizontal speed, in px/frame
	 */
	public void setMaxSpeed(double maxSpeed) {
		if (maxSpeed < 0) {
			throw new IllegalArgumentException("maxSpeed must be nonnegative: "
					+ maxSpeed);
		}
		this.maxSpeed = maxSpeed;
	}

}
