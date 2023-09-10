package proclient.module;

import proclient.Dragon;
import proclient.event.Event;
import proclient.settings.Setting;
import proclient.util.notifications.NotifType;
import proclient.util.notifications.NotifUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.Packet;

public class Module {

    protected Minecraft mc = Minecraft.getMinecraft();
    public String name;
    private int key;
    public boolean toggled;
    //if jesus no work change above to private again
    Category category;
    public boolean blatant;
    public Module(String nm, int k, Category c) {
        name = nm;
        key = k;
        category = c;
        toggled = false;
    }
    public Module(String nm, int k, Category c, boolean blatant) {
        if(blatant) {
			this.blatant = true;
			name = "⚠ " + nm;
		} else
        name = nm;
        key = k;
        category = c;
        toggled = false;
    }

    public void toggle() {
        toggled = !toggled;
        if(toggled) {
            onEnable();
        }else {
            onDisable();
        }
    }

    public void onEnable() { 
        NotifUtils.addNotification("Enabled", name, 2000, NotifType.INFO);
    }
    public void onDisable() { 
        NotifUtils.addNotification("Disabled", name, 2000, NotifType.INFO);
    }
    public void onUpdate() { }
    public void onRender() { }

    public void addAll(Setting... settings) {
        for(Setting s : settings)
        Dragon.setmgr.rSetting(s);
    }

    public void onEvent(Event e) {
        
    }

    public Minecraft getMc() {
        return mc;
    }

    public void setMc(Minecraft mc) {
        this.mc = mc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    protected EntityPlayerSP player() {
		return mc.thePlayer;
	}
	
	protected PlayerControllerMP playerController() {
		return mc.playerController;
	}
	
	protected WorldClient world() {
		return mc.theWorld;
	}
	
	@SuppressWarnings("rawtypes")
	protected void sendPacket(Packet p) {
		player().sendQueue.addToSendQueue(p);
	}
	
}