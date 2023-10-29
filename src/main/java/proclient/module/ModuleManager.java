package proclient.module;

import java.util.ArrayList;

import proclient.module.movement.*;
import proclient.module.hud.*;
import proclient.module.misc.ChestStealer;
import proclient.Dragon;
import proclient.command.CommandManager;
import proclient.event.Event;
import proclient.event.events.EventChat;
import proclient.module.combat.*;
import proclient.module.render.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ModuleManager {

    public static ArrayList<Module> mods;
    public ChestStealer chestStealer;
    public NoSlow noslow;

    public ModuleManager() {
        mods = new ArrayList<Module>();
        newMod(new KillAura());
        newMod(new SafeWalk());
        newMod(new ClickGui());
        newMod(new Nametags());
        newMod(new ArmorStats());
        newMod(new Trails());
        newMod(new Keystrokes());
        newMod(new Fps());
        newMod(new TargetHud());
        newMod(new NoWeather());
        newMod(new VapeOverlay());
        newMod(new Drag());
        newMod(new proclient.ui.ArrayList());
        newMod(new CPS());
        newMod(new PotionHUD());
        newMod(new Info());
        newMod(new AutoClicker());
        newMod(new ChestStealer());
        newMod(new Speed());
        newMod(new Fly());
        newMod(new SafeWalk());
        newMod(new AimAssist());
        newMod(new Criticals());
        newMod(new Wtap());
        newMod(new Velocity());
        newMod(new Jetpack());
    }

    public static void newMod(Module m) {
        mods.add(m);
    }

    public static ArrayList<Module> getModules() {
        return mods;
    }

    public static void onUpdate() {
        for(Module m : mods) {
            m.onUpdate();
        }
    }

    public static void onRender() {
        for(Module m : mods) {
            m.onRender();
        }
    }

    public static void onKey(int k) {
        for(Module m : mods) {
            if(m.getKey() == k) {
                m.toggle();
            }
        }
    }

    public ArrayList<Module> modsInCategory(Category c){
        ArrayList<Module> inCategory = new ArrayList<>();
        for(Module m : this.mods){
            if(m.category == c)
                inCategory.add(m);
        }
        return inCategory;
    }

	public void addChatMessage(String message) {
		message = "\2479" + Dragon.name + "\2477: " + message;
		
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}

    public static void onEvent(Event e) {
        if(e instanceof EventChat) {
            CommandManager.handleChat((EventChat)e);
        }
        for(Module m : mods) {
            if(!m.toggled)
                continue;

            m.onEvent(e);
        }
    }

    
    
}
