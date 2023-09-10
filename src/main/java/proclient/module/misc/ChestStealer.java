package proclient.module.misc;

import java.util.ArrayList;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import proclient.event.Event;
import proclient.event.EventType;
import proclient.event.events.EventMotion;
import proclient.module.Category;
import proclient.module.Module;
import proclient.settings.Setting;
import proclient.util.Timer;


public class ChestStealer extends Module {

	public ChestStealer() {
		super("ChestStealer", KeyboardConstants.KEY_NONE, Category.MISC, true);
		addAll(chestStealDelay);
	}
	
	public Setting chestStealDelay = new Setting("Delay", this, 150,0,750,true);
	
	ArrayList<TileEntityChest> openedChests = new ArrayList<TileEntityChest>();
	private boolean stole = true;
	private Timer timer = new Timer();

	@Override
	public void onDisable() {
		openedChests.clear();
		stole = true;
		super.onDisable();
	}

	@Override
	public void onEnable() {
		openedChests.clear();
		stole = true;
		super.onEnable();
	}
	
	@Override
	public void onEvent(Event e) {
		if(e instanceof EventMotion && e.getType() == EventType.PRE) {
			if(this.chestStealDelay.getValDouble() != 0) {
				if ((this.mc.thePlayer.openContainer != null) && ((this.mc.thePlayer.openContainer instanceof ContainerChest))) {
					ContainerChest container = (ContainerChest) this.mc.thePlayer.openContainer;
					for (int i = 0; i < container.getLowerChestInventory().getSizeInventory(); i++) {
						if ((container.getLowerChestInventory().getStackInSlot(i) != null) && (timer.hasTimeElapsed((long) chestStealDelay.getValDouble(), true))) {
							this.mc.playerController.windowClick(container.windowId, i, 0, 1, this.mc.thePlayer);
						}
					}
				}
			}
			if (!stole) {
				return;
			}
		}
		if(e instanceof EventMotion && e.getType() == EventType.POST) {
			if (mc.currentScreen instanceof GuiChest) {
				GuiChest chest = (GuiChest) mc.currentScreen;
				chest.steal();
				if (chest.isEmpty()) {
					mc.thePlayer.closeScreen();
					stole = true;
				}
			}
		}
		super.onEvent(e);
	}
	
	public float[] getRotations(double x, double y, double z) {
		EaglercraftRandom rand = new EaglercraftRandom(System.currentTimeMillis());
        double deltaX = (x+0.5) - (mc.thePlayer.posX - (mc.thePlayer.posX - mc.thePlayer.lastTickPosX)),
                deltaY = (y+0.5) - (mc.thePlayer.posY - (mc.thePlayer.posY - mc.thePlayer.lastTickPosY)),
                deltaZ = (z+0.5) - (mc.thePlayer.posZ - (mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ)),
                distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
        
        float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
                pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));

        if (deltaX < 0 && deltaZ < 0) {
            yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        } else if (deltaX > 0 && deltaZ < 0) {
            yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ / deltaX)));
        }

        return new float[]{yaw, pitch};
    }
	
}