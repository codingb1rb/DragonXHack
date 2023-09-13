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
import proclient.util.shit.RenderUtils;



public class ArrayList extends RenderModule {

	public ArrayList() {
		super("ArrayList", KeyboardConstants.KEY_NONE, Category.RENDER,0,0,0,0);
	}
	
	@Override
	public void draw() {
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
					Gui.drawRect(GuiScreen.width-mc.fontRendererObj.getStringWidth(m.name)-6, count*12, GuiScreen.width, count*12+12, 0x70000000);
					if(m.blatant)
					mc.fontRendererObj.drawString("§6⚠§r", GuiScreen.width-mc.fontRendererObj.getStringWidth(m.name), count*12+12/2-mc.fontRendererObj.FONT_HEIGHT/2, -1);
					RenderUtils.renderChromaString(m.blatant ? m.name.replace("§6⚠§r","") : m.name, GuiScreen.width-mc.fontRendererObj.getStringWidth(m.name)-2+(m.blatant?mc.fontRendererObj.getStringWidth("§6⚠§r"):0), count*12+12/2-mc.fontRendererObj.FONT_HEIGHT/2);
					RenderUtils.drawChromaRectangle(GuiScreen.width-mc.fontRendererObj.getStringWidth(m.name)-7+1/2, count*12, GuiScreen.width-mc.fontRendererObj.getStringWidth(m.name)-6, count*12+12);
					if(count+1 == enabledMods.size()) {
						RenderUtils.drawChromaRectangle(GuiScreen.width-mc.fontRendererObj.getStringWidth(m.name)-7+1/2, count*12+12, GuiScreen.width, count*12+12+1);
						break;
					}
					RenderUtils.drawChromaRectangle(GuiScreen.width-mc.fontRendererObj.getStringWidth(m.name)-7+1/2, count*12+12, GuiScreen.width-mc.fontRendererObj.getStringWidth(enabledMods.get(count+1).name)-6, count*12+12+1);
					++count;
				}
			}
		}
	}
