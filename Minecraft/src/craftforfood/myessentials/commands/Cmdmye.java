package craftforfood.myessentials.commands;

public class Cmdmye extends MyECommand {
	
	public Cmdmye() {
		super("mye");
				
	}
	
	public void execute(String[] args) {
		player.sendMessage("§1MyEssentials is running");
				
	}
	
}
