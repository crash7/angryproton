package craftforfood.myessentials.commands;

import org.bukkit.block.Block; 
import java.lang.Math;
import java.util.Stack;

public class Cmdsphere extends MyECommand {
	private double mCubes; 	//maximum number of cubes
	private double radius; 	//radius of the sphere
	private Stack<Double> slices;  	//number of slices to build the sphere
	private Block b;		//block that contains the axis, where will be placed  the sphere
	private int r;			//variable to store the radius of the sphere to create
	private int c; 			//variable to store if the sphere is centered or not
	private int idI;		//variable to store the material inside the sphere
	private int idS;		//variable to store the material of the sphere
	private int idO;		//variable to store the material the sphere
	private int cSlices;	
		
		
	public Cmdsphere() {
		super("build.sphere");
		
    }
        
    public void execute(String[] args) {
		slices = new Stack<Double>();
		b = mye.getPoint(0, player);
		idO = 0;

		if (b == null)
			player.sendMessage("seteame el punto, o no te construyo la esfera!");
		else{
			player.sendMessage("esfera");
			if ((args.length != 3 ) && (args.length != 4 ))
				player.sendMessage("§c" + mye.getCommand("sphere").getUsage());
			
			
			r = Integer.parseInt(args[0]);
			
			idS = Integer.parseInt(args[1]);
			if (!(mye.isAvailable(idS)))
				return;
			
			idI = Integer.parseInt(args[2]);
			
			if (!(mye.isAvailable(idI)))
				return;
			
			if (args.length == 4)
				c = Integer.parseInt(args[3]);
			else
				c = 1
				
			if (c == 0)
				mCubes = Math.ceil(r - 1 / 2) * 2 - 1;
			else
				mCubes = Math.ceil(r) * 2;
			
			if ((int)(b.getY() + mCubes) <= 128){
				for (double z = -mCubes / 2, i=0; z <= mCubes / 2; z++, i++)
					slices.push(new Double(Math.sqrt(Math.pow(r,2) - Math.pow(z,2))));
			
				cSlices = slices.size();
				while (slices.size() > 0){
					radius = ((Double) (slices.pop())).doubleValue();                       
					for (double y = -mCubes / 2, cY = 1; y <= mCubes / 2; y++, cY++){
						for (double x = -mCubes / 2, cX = 1; x <= mCubes / 2; x++, cX++){
							if ((Math.sqrt(Math.pow(y,2) + Math.pow(x,2))) > radius){
								//player.getWorld().getBlockAt((int)(b.getX()+cX),(int)(b.getY()+slices.size()),(int)(b.getZ()+cY)).setTypeId(0); //out
							}else{
								if (c==1){
									if (((slices.size() == 1)||(slices.size() == (cSlices - 2)))){
										player.getWorld().getBlockAt((int)(b.getX()+cX-r),(int)(b.getY()+slices.size()-r),(int)(b.getZ()+cY-r)).setTypeId(idS); //shell
									}else{
										if ((Math.sqrt(Math.pow(y,2) + Math.pow(x,2))) > (radius - 2.1)){
											player.getWorld().getBlockAt((int)(b.getX()+cX-r),(int)(b.getY()+slices.size()-r),(int)(b.getZ()+cY-r)).setTypeId(idS); //shell
										}else{
											//player.getWorld().getBlockAt((int)(b.getX()+cX),(int)(b.getY()+slices.size()),(int)(b.getZ()+cY)).setTypeId(idO); //out
											player.getWorld().getBlockAt((int)(b.getX()+cX-r),(int)(b.getY()+slices.size()-r),(int)(b.getZ()+cY-r)).setTypeId(idI); //in
										}
									}
								}else{
									if ((slices.size() == 1)||(slices.size() == (cSlices - 2))||(slices.size() == 0)||(slices.size() == (cSlices - 1))){
										player.getWorld().getBlockAt((int)(b.getX()+cX-r),(int)(b.getY()+slices.size()-r),(int)(b.getZ()+cY-r)).setTypeId(idS); //shell
									}else{
										if ((Math.sqrt(Math.pow(y,2) + Math.pow(x,2))) > (radius - 2.1)){
											player.getWorld().getBlockAt((int)(b.getX()+cX-r),(int)(b.getY()+slices.size()-r),(int)(b.getZ()+cY-r)).setTypeId(idS); //shell
										}else{
											player.getWorld().getBlockAt((int)(b.getX()+cX-r),(int)(b.getY()+slices.size()-r),(int)(b.getZ()+cY-r)).setTypeId(idI); //in
										}
									}
								}
							}							
						}
					}
				}
			}else
				player.sendMessage("no da la altura del mapa para hacer una esfera con radio " + r);
		}
		return;
	}
}