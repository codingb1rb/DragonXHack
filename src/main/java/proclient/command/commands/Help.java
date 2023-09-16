package proclient.command.commands;

import proclient.Dragon;
import proclient.command.Command;
import net.minecraft.client.Minecraft;

public class Help extends Command {
    public Help() {
        super("Help", "Shows all commands", "Help", "help");
    }

	@Override
	public void onCommand(String[] args, String command) {
			for(Command c : Dragon.cmdManager.getCommands()) {
				Dragon.moduleManager.addChatMessage("§a" + c.getSyntax() + " §7- " + c.getDescription());
			}
		}
	}
