package proclient.module.hud;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import proclient.module.Category;
import proclient.module.RenderModule;

public class ComboCounter extends RenderModule {
    public ComboCounter() {
        super("ComboCounter", KeyboardConstants.KEY_NONE, Category.HUD, 4, 14, 30, 10);
    }
    public static boolean attacked = false;
    public static int combo = 0;

    public void onAttack(Entity e) {
        if (this.isToggled()) {
            attacked = true;
        }
    }

    public void onEntityHit(S19PacketEntityStatus event) {
        if (this.isToggled() && attacked && event.logicOpcode == 2) {
            combo++;
            attacked = false;
        }
    }

    public int getWidth() {
        return Minecraft.getMinecraft().fontRendererObj.getStringWidth(getText()) + 4;
    }

    public int getHeight() {
        return Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 4;
    }

    private String getText() {
        return "[" + combo + " Combo]";
    }

    @Override
    public void draw() {
        if (Minecraft.getMinecraft().thePlayer.hurtTime > 3 && this.isToggled()) {
            combo = 0;
        }

        Minecraft.getMinecraft().fontRendererObj.drawString("[" + combo + " Combo]", this.x + 2, this.y + 2, -1);
    }
}
