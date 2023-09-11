package proclient.ui;

import java.util.Comparator;
import java.util.List;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import proclient.Dragon;
import proclient.module.Category;
import proclient.module.Module;
import proclient.module.RenderModule;
import proclient.util.shit.RenderUtil;

public class ArrayList extends RenderModule {
	public ArrayList() {
		super("ArrayList", KeyboardConstants.KEY_NONE, Category.HUD, 0, 0, 0, 0);
	}

    public void draw(){
        Dragon.moduleManager.mods.sort(Comparator.comparingInt(m -> Minecraft.getMinecraft().fontRendererObj.getStringWidth(((Module)m).name)).reversed());
			List<Module> enabledMods = new java.util.ArrayList<Module>();
			
			for(int i = 0; i < Dragon.moduleManager.mods.size(); i++) {
				if(Dragon.moduleManager.mods.get(i).isToggled()) {
					enabledMods.add(Dragon.moduleManager.mods.get(i));
				}
			}
			
			int count = 0;
			for(Module m : enabledMods) {
				if(m.isToggled()) {
					Gui.drawRect(GuiScreen.width-Minecraft.getMinecraft().fontRendererObj.getStringWidth(m.name)-6, count*12, GuiScreen.width, count*12+12, 0x70000000);
					Minecraft.getMinecraft().fontRendererObj.drawString(m.name, GuiScreen.width-Minecraft.getMinecraft().fontRendererObj.getStringWidth(m.name)-2, count*12+12/2-Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2, RenderUtil.getRainbow(4, 0.8f, 0.85f));
					Gui.drawRect(GuiScreen.width-Minecraft.getMinecraft().fontRendererObj.getStringWidth(m.name)-7+1/2, count*12, GuiScreen.width-Minecraft.getMinecraft().fontRendererObj.getStringWidth(m.name)-6, count*12+12, 0x99900000);
					if(count+1 == enabledMods.size()) {
						break;
					}
					++count;
				}
			}
    }
    
}