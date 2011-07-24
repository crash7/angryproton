package craftforfood.myessentials.commands;

import org.bukkit.block.Block;

public class Cmdzoneselection extends MyECommand {

	public Cmdzoneselection() {
		super("build.zoneselection");

	}

	public void execute(String[] args) {
		if(args.length == 0) {
			
			Block a = mye.getPoint(0, player);
			Block b = mye.getPoint(1, player);
			
			if(a != null && b != null) {
				int blocks = 0;
				int xb = 0;
				int yb = 0;
				int zb = 0;
				for(int xdim = Math.min(a.getX(), b.getX()); xdim <= Math.max(a.getX(), b.getX()); xdim++) {
					for(int ydim = Math.min(a.getY(), b.getY()); ydim <= Math.max(a.getY(), b.getY()); ydim++) {
						for(int zdim = Math.min(a.getZ(), b.getZ()); zdim <= Math.max(a.getZ(), b.getZ()); zdim++) {
							blocks++;
							zb++;
        					
						}
						yb++;
						
					}
					xb++;
					
				}
				
				if(mye.getMaxBlocks() == 0 || blocks <= mye.getMaxBlocks()) {
					Block[][][] selectedblocks = new Block[xb][yb][zb];
					for(int xdim = Math.min(a.getX(), b.getX()); xdim <= Math.max(a.getX(), b.getX()); xdim++) {
						for(int ydim = Math.min(a.getY(), b.getY()); ydim <= Math.max(a.getY(), b.getY()); ydim++) {
							for(int zdim = Math.min(a.getZ(), b.getZ()); zdim <= Math.max(a.getZ(), b.getZ()); zdim++) {
								selectedblocks[xdim][ydim][zdim] = player.getWorld().getBlockAt(xdim,ydim,zdim);
								
							}
							
						}
						
					}
					
					mye.setClipboard(selectedblocks, player);
					
					player.sendMessage("§7You have selected " + blocks + " blocks");
					
				} else {
					player.sendMessage("§cOver blocks limit (" + mye.getMaxBlocks() + ")");
					
				}
				
			} else {
				player.sendMessage("§cNot enough points. You need the two first points");
				
			}
				
		} else {
			player.sendMessage("§c" + mye.getCommand("zoneselection").getUsage());
			
		}
		
	}	

}