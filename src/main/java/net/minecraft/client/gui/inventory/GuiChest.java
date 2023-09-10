package net.minecraft.client.gui.inventory;

import java.io.IOException;

import proclient.Dragon;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files are (c) 2022-2023 LAX1DUDE. All Rights Reserved.
 * 
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 * 
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 * 
 * (please read the 'LICENSE' file this repo's root directory for more info) 
 * 
 */
public class GuiChest extends GuiContainer {
	/**+
	 * The ResourceLocation containing the chest GUI texture.
	 */
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(
			"textures/gui/container/generic_54.png");
	private IInventory upperChestInventory;
	private IInventory lowerChestInventory;
	private int inventoryRows;

	public GuiChest(IInventory upperInv, IInventory lowerInv) {
		super(new ContainerChest(upperInv, lowerInv, Minecraft.getMinecraft().thePlayer));
		this.upperChestInventory = upperInv;
		this.lowerChestInventory = lowerInv;
		this.allowUserInput = false;
		short short1 = 222;
		int i = short1 - 108;
		this.inventoryRows = lowerInv.getSizeInventory() / 9;
		this.ySize = i + this.inventoryRows * 18;
	}

	/**+
	 * Draw the foreground layer for the GuiContainer (everything in
	 * front of the items). Args : mouseX, mouseY
	 */
	protected void drawGuiContainerForegroundLayer(int var1, int var2) {
		this.fontRendererObj.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
		this.fontRendererObj.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8,
				this.ySize - 96 + 2, 4210752);
	}

	/**+
	 * Args : renderPartialTicks, mouseX, mouseY
	 */
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}

	    
    public void initGui(){
    	super.initGui();
    		int posY = (height - ySize)/2 + 2;
    		buttonList.add(new GuiButton(1, width/2-5, posY, 40, 12, "Steal"));
    		buttonList.add(new GuiButton(2, width/2+40, posY, 40, 12, "Store"));
    	}
    
    @Override
    protected void actionPerformed(GuiButton button) {
    	super.actionPerformed(button);
  
    	if(button.id == 1){
    		new Thread(new Runnable(){
				@Override
				public void run() {
					try{
						for(int i = 0; i < GuiChest.this.inventoryRows * 9; i++){
							Slot slot = (Slot) GuiChest.this.inventorySlots.inventorySlots.get(i);
							if(slot.getStack() != null){
								Thread.sleep(250L);
								GuiChest.this.handleMouseClick(slot, slot.slotNumber, 0, 1);
								GuiChest.this.handleMouseClick(slot, slot.slotNumber, 0, 6);
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
    			
    		})
    		.start();
    	}else if(button.id == 2){
    		new Thread(new Runnable(){
				@Override
				public void run() {
					try{
						for(int i = GuiChest.this.inventoryRows*9; i < GuiChest.this.inventoryRows*9+44; i++){
							Slot slot = (Slot) GuiChest.this.inventorySlots.inventorySlots.get(i);
							if(slot.getStack() != null){
								Thread.sleep(250L);
								GuiChest.this.handleMouseClick(slot, slot.slotNumber, 0, 1);
								GuiChest.this.handleMouseClick(slot, slot.slotNumber, 0, 6);
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
    			
    		})
    		.start();
    	}
    
    }
		public boolean isEmpty() {
		for (int ii = 0; ii < this.inventoryRows * 9; ii++) {
			Slot s = (Slot) inventorySlots.inventorySlots.get(ii);
			if (s.getStack() == null) {
				continue;
			}
			return false;
		}
		return true;
	}
	public void store() {
		for (int ii = 0; ii < 4 * 9; ii++) {
			Slot s = (Slot) inventorySlots.inventorySlots.get(ii + inventoryRows * 9);
			if (s.getStack() == null) {
				continue;
			}
			handleMouseClick(s, s.slotNumber, 0, 1);
			handleMouseClick(s, s.slotNumber, 0, 6);
		}
	}
	
	public void steal() {
		if(Dragon.moduleManager.chestStealer.chestStealDelay.getValDouble() == 0) {
			for (int ii = 0; ii < this.inventoryRows * 9; ii++) {
				Slot s = (Slot) this.inventorySlots.inventorySlots.get(ii);
				if (s.getStack() == null) {
					continue;
				}
				handleMouseClick(s, s.slotNumber, 0, 1);
				handleMouseClick(s, s.slotNumber, 0, 6);
			}
		}
	}
}