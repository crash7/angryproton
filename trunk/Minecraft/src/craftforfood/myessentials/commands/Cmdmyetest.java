package craftforfood.myessentials.commands;

public class Cmdmyetest extends MyECommand {
	
	public Cmdmyetest() {
		super("myetest");
				
	}
	
	public boolean execute(String[] args) {
		player.sendMessage(args.toString());
		
		return true;
		
	}
	
}