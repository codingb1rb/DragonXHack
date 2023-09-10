package proclient.module.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import proclient.module.Category;
import proclient.module.Module;
import proclient.settings.Setting;

public class Velocity extends Module{
    private Setting horizontal = new Setting("Horizontal", this, 90, 0, 100, true);
    private Setting vertical = new Setting("Vertical", this, 90, 0, 100, true);
    public Velocity() {
        super("Velocity", KeyboardConstants.KEY_NONE, Category.COMBAT, false);
        addAll(horizontal, vertical);
    }


    public void onUpdate() {
        if(this.isToggled()) {
            if (mc.thePlayer.hurtTime == mc.thePlayer.maxHurtTime && mc.thePlayer.maxHurtTime > 0) {
                mc.thePlayer.motionX *= (float) horizontal.getValDouble() / 100;
                mc.thePlayer.motionY *= (float) vertical.getValDouble() / 100;
                mc.thePlayer.motionZ *= (float) horizontal.getValDouble() / 100;
            }
        }
    }
}
