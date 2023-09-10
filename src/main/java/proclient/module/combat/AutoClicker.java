package proclient.module.combat;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import proclient.module.Category;
import proclient.module.Module;
import proclient.settings.Setting;
import proclient.util.Timer;
import proclient.util.notifications.NotifType;
import proclient.util.notifications.NotifUtils;

public class AutoClicker extends Module {
	
	public Timer clickTimer = new Timer();
	public Setting minCps = new Setting("Min CPS", this, 13,1,20, true);
	public Setting maxCps = new Setting("Maximum CPS",this , 15,1,20,true);
	
	public AutoClicker() {
		super("Auto-Clicker", KeyboardConstants.KEY_NONE, Category.COMBAT);
		addAll(minCps,maxCps);
	}
	
	
	
	@Override
	public void onEnable() {
		clickTimer.reset();
		super.onEnable();
	}
	
	@Override
	public void onUpdate() {
        if(this.isToggled()) {
		EaglercraftRandom rand = new EaglercraftRandom();
		int min = (int) minCps.getValDouble();
		int max = (int) maxCps.getValDouble();
		int cps = (rand.nextInt(max+1 - min) + min);
        if(Mouse.isButtonDown(0)) {
		if(clickTimer.hasTimeElapsed(1000/cps, true)) {
			mc.clickMouse();
			mc.gameSettings.keyBindAttack.setKeyBindState(mc.gameSettings.keyBindAttack.keyCode, true);
			new Thread() {
				public void run() {
					try {
						Thread.sleep(1000/cps);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mc.gameSettings.keyBindAttack.setKeyBindState(mc.gameSettings.keyBindAttack.keyCode, false);
				}
			}.start();
		}
		super.onUpdate();
    }
	}
}

}