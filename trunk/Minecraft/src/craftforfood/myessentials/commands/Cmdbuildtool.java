package craftforfood.myessentials.commands;

import org.bukkit.Material;

public class Cmdbuildtool extends MyECommand {
	
	public Cmdbuildtool() {
		super("build");
		
	}
	
	public void execute(String[] args) {
		player.sendMessage("§7Build tool is " + Material.getMaterial(mye.getBuildTool()).name().toLowerCase().replace("_", " ") + "!");
		
	}

}
