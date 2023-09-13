package proclient.module.combat;

import java.util.List;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import proclient.module.Category;
import proclient.module.Module;
import proclient.settings.Setting;
import proclient.util.Timer;
import proclient.util.notifications.NotifType;
import proclient.util.notifications.NotifUtils;

public class AutoClicker extends Module {
	
	public Timer clickTimer = new Timer();
	public Setting minCps = new Setting("Min CPS", this, 10,1,50, true);
	public Setting maxCps = new Setting("Max CPS",this , 15,1,100,true);

	private Float coolDown = 0f;


	
	public AutoClicker() {
		super("AutoClicker", KeyboardConstants.KEY_NONE, Category.COMBAT);
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