package proclient.module.combat;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomUtils;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import proclient.module.Category;
import proclient.module.Module;
import proclient.settings.Setting;

public class KillAura extends Module{
    boolean blocking;
    private Setting swingitem = new Setting("Swing", this, true);
    private Setting block = new Setting("Block", this, true);
    private Setting distance = new Setting("Distance", this, 2.8, 1.0, 10.0, false);
    private Setting blockdistance = new Setting("BlockDistance", this, 2.8, 1.0, 10.0, false);
    public KillAura() {
        super("Killaura", KeyboardConstants.KEY_K, Category.COMBAT);
        addAll(distance, blockdistance, block, swingitem);
    }

       public void onUpdate() {
        if(!this.isToggled())
            return;
        for(Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
            Object theObject = entities.next();
            if(theObject instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) theObject;
                if(entity instanceof EntityPlayerSP) continue;
                if(mc.thePlayer.getDistanceToEntity(entity) <= this.distance.getValDouble()) {
                    if(entity.isEntityAlive()) {
                        mc.playerController.attackEntity(mc.thePlayer, entity);
                        if(this.swingitem.isEnabled()) {
                        mc.thePlayer.swingItem();
                        }
                    }
                }
                if(this.block.isEnabled()) {
                    if(mc.thePlayer.getDistanceToEntity(entity) <= this.blockdistance.getValDouble()){
                        mc.gameSettings.keyBindUseItem.pressed = true;
                    }
            }
        }
        super.onUpdate();
    }
}
}