package craftforfood.myessentials.listeners;

import java.util.List;
import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerTeleportEvent;

import craftforfood.myessentials.MyEssentials;

public class TeleportPlayerListener extends PlayerListener {
	private MyEssentials mye;
		
	public TeleportPlayerListener(MyEssentials mye) {
		this.mye = mye;
		
	}
	
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		List<Player> players = event.getPlayer().getWorld().getPlayers();
		Iterator<Player> i = players.iterator();
		
		Player temp;
		while(i.hasNext()) {
			temp = i.next();
			if(temp.isOnline() && !temp.equals(event.getPlayer()) && mye.hasPermission(temp, "alerts.teleport")) {
				if(temp.getLocation().equals(event.getTo())) {
					temp.sendMessage("§7" + event.getPlayer().getName() + "has teleported near you..");
					
				}
				
			}
			
		}
				
	}
	
}
