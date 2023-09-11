package proclient.module.movement;

import proclient.module.Category;
import proclient.module.Module;
import proclient.settings.Setting;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.potion.Potion;

public class Speed extends Module{
	private Setting speed = new Setting("Speed", this, 3.0, 1.0, 3.0, false);
	private Setting speedMode = new Setting("Mode", this, "vanilla");
    public Speed() {
		super("Speed", KeyboardConstants.KEY_NONE, Category.MOVEMENT);
		addAll(speed, speedMode);
	}

    public void onDisable() {
        mc.timer.timerSpeed = 1.0F;
    }
	
	public void onUpdate() {
		if(this.isToggled()) {
			if(speedMode.getMode().equals("vanilla")) {
			if(mc.thePlayer.onGround) {
				mc.thePlayer.motionX *= speed.getValDouble();
				mc.thePlayer.motionZ *= speed.getValDouble();
				}
			} 
		}
		
	}

    private boolean isMoving() {
        return mc.thePlayer.moveForward != 0 || mc.thePlayer.moveStrafing != 0;
    }

	    private double getNCPSpeedOnGround() {
        double moveSpeed;

        if (mc.thePlayer.moveForward != 0) {
            if (mc.thePlayer.moveStrafing != 0) {
                moveSpeed = 0.45;
            } else {
                moveSpeed = 0.48;
            }
        } else {
            moveSpeed = 0.4f;
        }
        if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            moveSpeed *= 1.0 + 0.17 * (mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1);
        }

        if (mc.thePlayer.isPotionActive(Potion.moveSlowdown)) {
            moveSpeed *= 0.9f;
        }

        return moveSpeed;
    }
}
