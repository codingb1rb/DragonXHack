package proclient.module.movement;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import proclient.module.Category;
import proclient.module.Module;

public class NoSlow extends Module{
    public NoSlow() {
        super("NoSlow", KeyboardConstants.KEY_NONE, Category.MOVEMENT);
    }

    public void onUpdate() {
        if(this.isToggled()) {
            if (mc.thePlayer.isUsingItem()) {
				mc.thePlayer.movementInput.moveStrafe *= 0.2F;
				mc.thePlayer.movementInput.moveForward *= 0.2F;
				mc.thePlayer.sprintToggleTimer = 0;
		}
        }
    }
}
