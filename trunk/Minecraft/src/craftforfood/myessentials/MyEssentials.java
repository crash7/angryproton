package craftforfood.myessentials;

import java.util.logging.Logger;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import craftforfood.myessentials.commands.MyECommand;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
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
	private Block pointA;
	private Block pointB;
	
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		MyEssentials.cLog.info("MyEssentials (version: " + pdfFile.getVersion() + ") by CraftForFood team desactivated!");
	}

	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		MyEssentials.cLog.info("MyEssentials (version: " + pdfFile.getVersion() + ") by CraftForFood team activated!");
		
		// Events registration
		
		
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
	 * @param pointA the pointA to set
	 */
	public void setPointA(Block pointA) {
		this.pointA = pointA;
	}

	/**
	 * @return the pointA
	 */
	public Block getPointA() {
		return pointA;
		
	}
	
	/**
	 * @param pointB the pointB to set
	 */
	public void setPointB(Block pointB) {
		this.pointB = pointB;
	}

	/**
	 * @return the pointB
	 */
	public Block getPointB() {
		return pointB;
		
	}

}
