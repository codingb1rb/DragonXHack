package proclient.module.movement;

import proclient.module.Category;
import proclient.module.Module;
import proclient.settings.Setting;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.potion.Potion;

public class Fly extends Module{
	private Setting speed = new Setting("Speed", this, 3.0, 1.0, 3.0, true);
    private Setting mode = new Setting("Mode", this, "Vanilla");
    public Fly() {
		super("Fly", KeyboardConstants.KEY_NONE, Category.MOVEMENT);
		addAll(speed, mode);
	}

    public void onUpdate() {
        if(this.isToggled()) {
            if(this.mode.getMode().equals("Vanilla")) {
                this.mc.thePlayer.capabilities.isFlying = true;
            }
        }
    }
}
