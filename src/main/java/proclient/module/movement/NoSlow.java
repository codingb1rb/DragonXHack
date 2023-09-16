package proclient.module.movement;

import java.time.chrono.Era;

import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.minecraft.block.BlockRedstoneComparator.Mode;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import proclient.event.Event;
import proclient.event.events.EventMotion;
import proclient.event.events.EventSlowDown;
import proclient.module.Category;
import proclient.module.Module;

public class NoSlow extends Module {
    public NoSlow() {
        super("NoSlow", KeyboardConstants.KEY_NONE, Category.MOVEMENT);
    }

    public void onEvent(Event e) {
        if(e instanceof EventMotion) {
            if (mc.thePlayer.isBlocking()) {
            if (e.isPre()) {
                mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging());
            } else {
                mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement());
            }
        }

        }
    }


}
