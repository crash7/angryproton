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
				for(int xdim = Math.min(a.getX(), b.getX()); xdim <= Math.max(a.getX(), b.getX()); xdim++) {
					for(int ydim = Math.min(a.getY(), b.getY()); ydim <= Math.max(a.getY(), b.getY()); ydim++) {
						for(int zdim = Math.min(a.getZ(), b.getZ()); zdim <= Math.max(a.getZ(), b.getZ()); zdim++) {
							blocks++;
        					
						}
						
					}
					
				}
				
				
				if(mye.getMaxBlocks() == 0 || blocks <= mye.getMaxBlocks()) {
					int xb = Math.max(a.getX(), b.getX()) - Math.min(a.getX(), b.getX()) + 1;
					int yb = Math.max(a.getY(), b.getY()) - Math.min(a.getY(), b.getY()) + 1;
					int zb = Math.max(a.getZ(), b.getZ()) - Math.min(a.getZ(), b.getZ()) + 1;
					
					Block[][][] selectedblocks = new Block[xb][yb][zb];
					for(int xdim = 0, xpos = Math.min(a.getX(), b.getX()); xpos <= Math.max(a.getX(), b.getX()); xpos++, xdim++) {
						for(int ydim = 0, ypos = Math.min(a.getY(), b.getY()); ypos <= Math.max(a.getY(), b.getY()); ypos++, ydim++) {
							for(int zdim = 0, zpos = Math.min(a.getZ(), b.getZ()); zpos <= Math.max(a.getZ(), b.getZ()); zpos++, zdim++) {
								selectedblocks[xdim][ydim][zdim] = player.getWorld().getBlockAt(xpos, ypos, zpos);
								
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