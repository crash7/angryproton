package craftforfood.myessentials.commands;

public class Cmdmye extends MyECommand {
	
	public Cmdmye() {
		super("mye");
				
	}
	
	public boolean execute(String[] args) {
		player.sendMessage("Current " + mye.getBuildTool());
		
		return true;
		
	}
	
}
