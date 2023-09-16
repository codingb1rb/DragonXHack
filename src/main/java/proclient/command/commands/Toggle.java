package proclient.command.commands;

import proclient.Dragon;
import proclient.command.Command;

public class Toggle extends Command {
	
	public Toggle() {
		super("Toggle", "toggles a module by name.", "Toggle <name>", "t");
		
	}

	@Override
	public void onCommand(String[] args, String command) {
		try {
			String moduleName = args[0];
			
			boolean foundModule = false;
			
			for(proclient.module.Module module : Dragon.moduleManager.mods){
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.toggle();
					
					Dragon.moduleManager.addChatMessage((module.isToggled() ? "Enabled" : "Disabled") + " " + module.name);
					
					foundModule = true;
					break;
					
				}
			}
			if(!foundModule) {
				Dragon.moduleManager.addChatMessage("Could not find module.");
			}
		} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
			Dragon.moduleManager.addChatMessage("Usage: Toggle/t <module>");
		}
		
	}

}