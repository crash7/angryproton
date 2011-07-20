package craftforfood.myessentials.listeners;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerInteractEvent;
import craftforfood.myessentials.MyEssentials;

public class BuilderPlayerListener extends PlayerListener {
	private MyEssentials mye;
	
	public BuilderPlayerListener(MyEssentials mye) {
		this.mye = mye;
		
	}
	
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
				&& event.getPlayer().getItemInHand().getTypeId() == mye.getBuildTool()
				&& mye.hasPermission(event.getPlayer(), "myessentials.build.select")) {
			
			mye.setPointX(event.getPlayer(), 0, event.getClickedBlock());
			event.getPlayer().sendMessage("Punto " + event.getClickedBlock().getX());
			
		}
				
	}

}
