package proclient.command.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import proclient.command.Command;


public class Enchant extends Command {
	
	public Enchant() {
		super("Enchant", "Enchants item with 32k level enchantments", "Enchant <level>", "enchant");
	}

	@Override
	public void onCommand(String[] args, String command) {
		for(Enchantment enchs : Enchantment.enchantmentsList) {
			if(enchs != null) {
				Minecraft.getMinecraft().thePlayer.getHeldItem().addEnchantment(enchs, Integer.valueOf(args[0]));
				}
				
			}
		}
	}