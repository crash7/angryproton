package craftforfood.myessentials.commands;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class Cmdzonepaste extends MyECommand {

	public Cmdzonepaste() {
		super("build.zonepaste");

	}

	public void execute(String[] args) {
		if(args.length == 1) {
			boolean cut = args[0].equals("cut");
			Block a = mye.getPoint(0, player);
						
			if(a != null) {
				Block[][][] selectedblocks = mye.getClipboard(player);
				if(selectedblocks != null) {
					int blocks = 0;
					
					for(int xdim = 0, xpos = a.getX(); xdim < selectedblocks.length; xdim++, xpos++) {
						player.sendMessage(xdim + " " + xpos);
						for(int ydim = 0, ypos = a.getY(); ydim < selectedblocks[xdim].length; ydim++, ypos++) {
							player.sendMessage(ydim + " " + ypos);
							for(int zdim = 0, zpos = a.getZ(); zdim < selectedblocks[xdim][ydim].length; zdim++, zpos++) {
								player.sendMessage(zdim + " " + zpos);
								player.getWorld().getBlockAt(xpos,ypos,zpos).setType(selectedblocks[xdim][ydim][zdim].getType());
								player.getWorld().getBlockAt(xpos,ypos,zpos).setData(selectedblocks[xdim][ydim][zdim].getData());
								
								if(cut) {
									selectedblocks[xdim][ydim][zdim].setType(Material.AIR);
									
								}
								
								blocks++;
								
							}
							
						}
						
					}
					
					player.sendMessage("§7You have " + ((cut) ? "cut" : "copy") + " a selection of " + blocks + " blocks");
					
				} else {
					player.sendMessage("§cYou need a selection first");
					
				}
				
			} else {
				player.sendMessage("§cNot enough points. You need the first point");
				
			}
				
		} else {
			player.sendMessage("§c" + mye.getCommand("zonepaste").getUsage());
			
		}
		
	}	

}