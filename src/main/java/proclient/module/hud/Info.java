package proclient.module.hud;

import java.awt.Color;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import proclient.module.Category;
import proclient.module.RenderModule;
import proclient.util.shit.RenderUtils;

public class Info extends RenderModule{
    public Info() {
        super("Info", KeyboardConstants.KEY_NONE, Category.HUD, 50, 50, 125, 63);
    }

    public static final String[] directionsF = new String[] { "\u00A79S\u00A7r", "\u00A72W\u00A7r", "\u00A74N\u00A7r", "\u00A76E\u00A7r" };

    public static int[] getPositions() {
        int[] poses = new int[] { (int) Minecraft.getMinecraft().thePlayer.posX, (int) Minecraft.getMinecraft().thePlayer.posY, (int) Minecraft.getMinecraft().thePlayer.posZ };

        return poses;
    }

        public void draw() {
        int rot = MathHelper.floor_double(this.mc.thePlayer.rotationYaw * 4 / 360 + 0.5) & 3;
        if (mc.thePlayer != null) {
            RenderUtils.drawRoundedRect(this.x, this.y, this.x + this.getWidth(), this.y + this.getHeight(), 5, 0x90000000);
            Minecraft.getMinecraft().fontRendererObj.drawString(" X: " + getPositions()[0], this.x + 5, this.y + 14, -1);
            Minecraft.getMinecraft().fontRendererObj.drawString(" Y: " + getPositions()[1], this.x + 5, this.y + 24, -1);
            Minecraft.getMinecraft().fontRendererObj.drawString(" Z: " + getPositions()[2], this.x + 5, this.y + 34, -1);
            Minecraft.getMinecraft().fontRendererObj.drawString(" Dir: ", this.x + 5 + mc.fontRendererObj.getStringWidth(" X:  " + getPositions()[0]), this.y + 14, -1);
            Minecraft.getMinecraft().fontRendererObj.drawString(directionsF[rot], this.x + 5 + mc.fontRendererObj.getStringWidth(" X:   Dir: " + getPositions()[0]), this.y + 14, -1);
            Minecraft.getMinecraft().fontRendererObj.drawString(" Biome: " + mc.theWorld.getBiomeGenForCoords(new BlockPos(getPositions()[0], getPositions()[1], getPositions()[2])).biomeName, this.x + 5, this.y + 44, -1);
        }
    }
}
