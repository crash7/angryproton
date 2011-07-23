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
			
			int lastpoint = 0;
			while(mye.getPoint(lastpoint, event.getPlayer()) != null && lastpoint < MyEssentials.MAXPOINTS) {
				lastpoint++;
				
			}
			
			if(lastpoint == MyEssentials.MAXPOINTS) {
				lastpoint = 0;
				for(int i = lastpoint+1; i < MyEssentials.MAXPOINTS; i++) {
					mye.setPointX(event.getPlayer(), i, null);
					
				}
								
			}
			
			mye.setPointX(event.getPlayer(), lastpoint, event.getClickedBlock());
			lastpoint++;
			event.getPlayer().sendMessage("§7You have set the point " + lastpoint + 
					  					" at X[" + event.getClickedBlock().getX() + "] Y[" + event.getClickedBlock().getY() + 
					  					"] Z[" + event.getClickedBlock().getZ() + "]");
			
		}
				
	}

}
