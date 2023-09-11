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
	public Setting minCps = new Setting("Min CPS", this, 13,1,100, true);
	public Setting maxCps = new Setting("Max CPS",this , 15,1,150,true);
	public Setting autoWtap = new Setting("WTap", this, true);
	public Setting aimbot = new Setting("Aimbot", this, true);
	public Setting crits = new Setting("Crits", this, true);

	private Float coolDown = 0f;


	
	public AutoClicker() {
		super("AutoClicker", KeyboardConstants.KEY_NONE, Category.COMBAT);
		addAll(minCps,maxCps, autoWtap, aimbot);
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
	if(aimbot.isEnabled()) {
		this.aim();
	}
	if(autoWtap.isEnabled()){
		coolDown -= 1f;
    	mc.thePlayer.setSprinting(true);
        if (mc.thePlayer.isSwingInProgress)
        	if (coolDown < 0) {
        		mc.thePlayer.setSprinting(false);
        		coolDown = 3f;
	}
}
	if(crits.isEnabled()) {
		if (mc.objectMouseOver != null && mc.objectMouseOver.entityHit instanceof EntityLivingBase)
		doJumpCriticals();
	}
}
}


    public void doJumpCriticals() {
        if (mc.thePlayer.isInWater() && !mc.thePlayer.isInsideOfMaterial(Material.lava) && mc.thePlayer.onGround) {
            mc.thePlayer.motionY = 0.1f;
            mc.thePlayer.fallDistance = 0.1f;
            mc.thePlayer.onGround = false;
        }
    }

	int delay;

	private void aim() {
		List list = Minecraft.getMinecraft().theWorld.playerEntities;
		delay++;

		for (int k = 0; k < list.size(); k++) {
			if (((EntityPlayer) list.get(k)).getName() == Minecraft.getMinecraft().thePlayer.getName()) {
				continue;
			}

			EntityPlayer entityplayer = (EntityPlayer) list.get(1);

			if (Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entityplayer) > Minecraft.getMinecraft().thePlayer.getDistanceToEntity((Entity) list.get(k))) {
				entityplayer = (EntityPlayer) list.get(k);
			}

			float f = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entityplayer);

			if (f < 4F && Minecraft.getMinecraft().thePlayer.canEntityBeSeen(entityplayer) && delay > 7) {
				faceEntity(entityplayer);
				continue;
			}
		}
	}

	public static synchronized void faceEntity(EntityLivingBase entity) {
		final float[] rotations = getRotationsNeeded(entity);

		if (rotations != null) {
			Minecraft.getMinecraft().thePlayer.rotationYaw = rotations[0];
			Minecraft.getMinecraft().thePlayer.rotationPitch = rotations[1] + 1.0F;// 14
		}
	}

	public static float[] getRotationsNeeded(Entity entity) {
		if (entity == null) {
			return null;
		}

		final double diffX = entity.posX - Minecraft.getMinecraft().thePlayer.posX;
		final double diffZ = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
		double diffY;

		if (entity instanceof EntityLivingBase) {
			final EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
			diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		} else {
			diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0D - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
		}

		final double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
		final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
		final float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
		return new float[] { Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - Minecraft.getMinecraft().thePlayer.rotationYaw), Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - Minecraft.getMinecraft().thePlayer.rotationPitch) };
	}

}