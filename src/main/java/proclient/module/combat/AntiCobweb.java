package proclient.module.combat;

import proclient.module.Category;
import proclient.module.Module;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;

public class AntiCobweb extends Module {

	public AntiCobweb() {
		super("AntiCobweb", KeyboardConstants.KEY_NONE, Category.COMBAT);
	}
	
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
			mc.thePlayer.isInWeb = false;
		}
	}

}