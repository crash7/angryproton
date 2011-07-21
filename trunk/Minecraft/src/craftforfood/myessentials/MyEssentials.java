package craftforfood.myessentials;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import craftforfood.myessentials.commands.MyECommand;
import craftforfood.myessentials.listeners.BuilderPlayerListener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.config.Configuration;

/**
 * MyEssentials for Bukkit (Minecraft)
 * @author Crash
 * @author Juan
 */ 
public class MyEssentials extends JavaPlugin {
	private static PermissionHandler pHandler;
	private static final Logger cLog = Logger.getLogger("Minecraft");
	
	// Config!
	private int buildTool; 
	
	// Useful
	private HashMap<String, Block[]> points = new HashMap<String, Block[]>();
	public static final int MAXPOINTS = 2;
		
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		MyEssentials.cLog.info("MyEssentials (version: " + pdfFile.getVersion() + ") by CraftForFood team desactivated!");
	}

	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		MyEssentials.cLog.info("MyEssentials (version: " + pdfFile.getVersion() + ") by CraftForFood team activated!");
		
		// Events registration
		PluginManager pm = getServer().getPluginManager();
	    pm.registerEvent(Event.Type.PLAYER_INTERACT, new BuilderPlayerListener(this), Event.Priority.Normal, this);
		
		// Permissions
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    if (permissionsPlugin == null) {
	    	MyEssentials.cLog.info("Permissions plugin not detected, defaulting to OP!");
	        return;
	    }
	    pHandler = ((Permissions) permissionsPlugin).getHandler();
	    MyEssentials.cLog.info("Using Permissions plugin");
		
		// Config
		MyEssentials.cLog.info("MyEssentials by CraftForFood team config stuff");
		Configuration pCfg = getConfiguration();
		// Builder		
		buildTool = pCfg.getInt("build-tool", 280);
		
		pCfg.save();
		// END Config
				
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(getCommand(commandLabel) != null) {
				MyECommand cmd;
				try {
					cmd = (MyECommand) getClassLoader()
							.loadClass("craftforfood.myessentials.commands.Cmd" + command.getName().toLowerCase()).newInstance(); 
					
					if(hasPermission(player, "myessentials." + cmd.getNode())) {
						cmd.setMyEssentials(this);
						cmd.setPlayer(player);
						
						return cmd.execute(args);
																		
					} else {
						player.sendMessage("Don't try to mess with me, " + player.getDisplayName());
						
					}
									
				} catch(Exception e) {
					player.sendMessage("We got and exception!");
					player.sendMessage(e.getMessage());
					return true;
					
				}
				
			}
			
		} else {
			MyEssentials.cLog.info("MyEssentials said that you can't use that in the console");
			
		}
		
		return true;
		
	}
	
	public boolean hasPermission(Player player, String node) {
		return (MyEssentials.pHandler != null) ? MyEssentials.pHandler.has(player, node) : player.isOp();
		
	}
	
	/**
	 * Devuelve el numero de la herramienta que se usa para construir
	 * @return int
	 */
	public int getBuildTool() {
		return buildTool;
		
	}
	
	/**
	 * 
	 * @param p Player
	 * @param point Block
	 */
	public void setPointX(Player p, int xpoint, Block point) {
		Block[] blocks = points.get(p.getName());

		if(blocks == null) {
			blocks = new Block[MyEssentials.MAXPOINTS];
			
		}
			
		if(xpoint >= 0 && xpoint < blocks.length) {
			blocks[xpoint] = point;
			
		}
		
		points.put(p.getName(), blocks);
		
	}

	/**
	 * 
	 * @param pointindex
	 * @param player
	 * @return Block
	 */
	public Block getPoint(int pointindex, Player player) {
		Block[] blocks = points.get(player.getName());
		if(blocks != null) {
			if(pointindex >= 0 && pointindex < blocks.length) {
				return blocks[pointindex];
				
			}
			
		}
				
		return null;
		
	}
	
}
