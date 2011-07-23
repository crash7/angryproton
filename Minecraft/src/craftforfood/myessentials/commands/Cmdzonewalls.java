package craftforfood.myessentials.commands;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class Cmdzonewalls extends MyECommand {

	public Cmdzonewalls() {
		super("build.zonewalls");

	}

	public void execute(String[] args) {
		if(args.length == 1) {
			try {
				int id = 0;
				int data = 0;
				int blocks = 0;
				if(args[0].contains(":")) {
					args = args[0].split(":");
					data = Integer.parseInt(args[1]);
				}
				id = Integer.parseInt(args[0]);
				
				if(mye.isAvailable(id, player)) {
					Block a = mye.getPoint(0, player);
					Block b = mye.getPoint(1, player);
					
					if(a != null && b != null) {
						for(int xdim = Math.min(a.getX(), b.getX()); xdim <= Math.max(a.getX(), b.getX()); xdim++) {
							for(int ydim = Math.min(a.getY(), b.getY()); ydim <= Math.max(a.getY(), b.getY()); ydim++) {
								for(int zdim = Math.min(a.getZ(), b.getZ()); zdim <= Math.max(a.getZ(), b.getZ()); zdim++) {
									if(xdim == a.getX() || xdim == b.getX() || zdim == a.getZ() || zdim == b.getZ()) {
										blocks++;
									}
                					
								}
								
							}
							
						}
						
						if(mye.getMaxBlocks() == 0 || blocks <= mye.getMaxBlocks()) {
							for(int xdim = Math.min(a.getX(), b.getX()); xdim <= Math.max(a.getX(), b.getX()); xdim++) {
								for(int ydim = Math.min(a.getY(), b.getY()); ydim <= Math.max(a.getY(), b.getY()); ydim++) {
									for(int zdim = Math.min(a.getZ(), b.getZ()); zdim <= Math.max(a.getZ(), b.getZ()); zdim++) {
										if(xdim == a.getX() || xdim == b.getX() || zdim == a.getZ() || zdim == b.getZ()) {
											player.getWorld().getBlockAt(xdim,ydim,zdim).setData((byte) data);
											player.getWorld().getBlockAt(xdim,ydim,zdim).setTypeId(id);
											
										}
										
									}
									
								}
								
							}
							
						} else {
							player.sendMessage("§cOver blocks limit (" + mye.getMaxBlocks() + ")");
							
						}
						
					} else {
						player.sendMessage("§cNot enough points. You need the two first points");
						
					}
					
				} else {
					player.sendMessage("§cYou can't use " + Material.getMaterial(id).name().toLowerCase().replace("_", " ") + "!");
					
				}
			} catch(NumberFormatException e) {
				player.sendMessage("§cUnknown block");
				
			}
			
			
		} else {
			player.sendMessage("§c" + mye.getCommand("zoneid").getUsage());
			
		}
		
	}	

}