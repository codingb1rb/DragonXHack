package proclient.module.hud;

import java.util.ArrayList;
import java.util.List;
import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.Minecraft;
import proclient.module.Category;
import proclient.module.RenderModule;
import proclient.util.shit.FuncUtils;

public class CPS extends RenderModule {
    public CPS() {
        super("CPS", KeyboardConstants.KEY_NONE, Category.HUD, 4, 26, 20, 5);
    }
    public boolean wasPressed;
    public long lastPressed;
    private List<Long> clicks = new ArrayList<>();

    public int getHeight() {
        return mc.fontRendererObj.FONT_HEIGHT + 4;
    }

    public void draw() {
        boolean pressed = Mouse.isButtonDown(0) || Mouse.isButtonDown(1);

        if (pressed != wasPressed) {
            lastPressed = System.currentTimeMillis();
            wasPressed = pressed;
            if (pressed) {
                this.clicks.add(lastPressed);
            }
        }

        final long time = System.currentTimeMillis();
        FuncUtils.removeIf(clicks, sinceLast -> sinceLast + 1000 < time);

        Minecraft.getMinecraft().fontRendererObj.drawString("[CPS: " + clicks.size() + "]", this.x + 2, this.y + 2, -1);
    }
}