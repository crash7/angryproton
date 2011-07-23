package craftforfood.myessentials.commands;

public class Cmdreload extends MyECommand {
	
	public Cmdreload() {
		super("reload");
		
	}
	
	public void execute(String[] args) {
		mye.getServer().reload();

	}

}
