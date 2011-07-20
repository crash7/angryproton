package craftforfood.myessentials.commands;

import org.bukkit.block.Block;

import java.lang.Math;
import java.util.Stack;

public class Cmdsphere extends MyECommand {
		private double mCubes; //maximum number of cubes
		private double radius; //radius of the sphere
		private Stack slices;  //number of slices to build the sphere
		private Block b;
		private int r;
		private int c; 
		private int id;
		
        public Cmdsphere() {
        	super("build.sphere");
        	
        }
        
        public boolean execute(String[] args) {
        	player.sendMessage("waaaa");	
        	slices = new Stack();
				b = mye.getPoint(0, player);
				
				if (b == null)
					player.sendMessage("seteame el punto, o no te construyo la esfera!");
				else{
					player.sendMessage("esfera");
					r = Integer.parseInt(args[0]);
					c = Integer.parseInt(args[1]);
					id = 20;
					
					if (c==0)
						mCubes = Math.ceil(r - 1 / 2) * 2 - 1;
					else
						mCubes = Math.ceil(r) * 2;
					
					for (double z = -mCubes / 2, i=0; z <= mCubes / 2; z++, i++)
						slices.push(new Double(Math.sqrt(Math.pow(r,2) - Math.pow(z,2))));
					
					while (slices.size() > 0){
						radius = ((Double) (slices.pop())).doubleValue();  			
						for (double y = -mCubes / 2, cY = 1; y <= mCubes / 2; y++, cY++){
							for (double x = -mCubes / 2, cX = 1; x <= mCubes / 2; x++, cX++){
								if ((Math.sqrt(Math.pow(y,2) + Math.pow(x,2))) > radius){
									player.getWorld().getBlockAt((int)(b.getX()+cX),(int)(b.getZ()+slices.size()),(int)(b.getY()+cY)).setTypeId(0);
								}else{
									player.getWorld().getBlockAt((int)(b.getX()+cX),(int)(b.getZ()+slices.size()),(int)(b.getY()+cY)).setTypeId(id);
								}
							}
						}
					}				
				}
				return true;
        }
}
