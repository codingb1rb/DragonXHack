package proclient.module.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import proclient.module.Category;
import proclient.module.Module;

public class Wtap extends Module {
    public Wtap() {
        super("WTap", KeyboardConstants.KEY_NONE, Category.COMBAT);
    }

    public void onUpdate() {
        if(this.isToggled()) {
            
        }
    }
}
