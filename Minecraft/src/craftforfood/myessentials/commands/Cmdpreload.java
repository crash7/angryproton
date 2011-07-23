package craftforfood.myessentials.commands;

public class Cmdpreload extends MyECommand {
	
	public Cmdpreload() {
		super("preload");
		
	}
	
	public void execute(String[] args) {
		mye.getServer().reload();

	}

}
