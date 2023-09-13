package proclient.module.combat;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import proclient.module.Category;
import proclient.module.Module;

public class Criticals extends Module {
    public Criticals() {
        super("Criticals", KeyboardConstants.KEY_NONE, Category.COMBAT);
    }

    public void onUpdate() {
        if(this.isToggled()) {
        if (mc.objectMouseOver != null && mc.objectMouseOver.entityHit instanceof EntityLivingBase)
		doJumpCriticals();
        }
    }

    public void doJumpCriticals() {
        if (mc.thePlayer.isInWater() && !mc.thePlayer.isInsideOfMaterial(Material.lava) && mc.thePlayer.onGround) {
            mc.thePlayer.motionY = 0.1f;
            mc.thePlayer.fallDistance = 0.1f;
            mc.thePlayer.onGround = false;
        }
    }
}