package craftforfood.myessentials.commands;

import org.bukkit.entity.Player;

import craftforfood.myessentials.MyEssentials;

public abstract class MyECommand {
	private String node;
	protected MyEssentials mye;
	protected Player player;
		
	public MyECommand(String node) {
		this.node = node;
		
	}

	public String getNode() {
		return node;
		
	}
	
	public void setMyEssentials(MyEssentials mye) {
		this.mye = mye;
		
	}
	
	public void setPlayer(Player player) {
		this.player = player;
		
	}
	
	public abstract boolean execute(String[] args);

}
