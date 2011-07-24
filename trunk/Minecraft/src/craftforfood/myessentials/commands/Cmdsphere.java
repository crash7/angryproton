package craftforfood.myessentials.commands;

import org.bukkit.block.Block; 
import java.lang.Math;
import java.util.Stack;

public class Cmdsphere extends MyECommand {
	private double mCubes;			//maximum number of cubes
	private double radius;			//radius of the sphere
	private Stack<Double> slices;	//number of slices to build the sphere
	private Block b;				//block that contains the axis, where will be placed  the sphere
	private int r;					//variable to store the radius of the sphere to create
	private int idI;				//variable to store the material inside the sphere
	private int idS;				//variable to store the material of the sphere
	private int cSlices;	
		
	public Cmdsphere() {
		super("build.sphere");		
    }
        
    public void execute(String[] args) {
		slices = new Stack<Double>();
		b = mye.getPoint(0, player);
		if (b == null){
			player.sendMessage("seteame el punto, o no te construyo la esfera!");
			return;
		}
		else{
			if (args.length == 0){
				player.sendMessage("§c" + mye.getCommand("sphere").getUsage());
				return;
			}else{
				if (args.length != 3){
					player.sendMessage("§c" + mye.getCommand("sphere").getUsage());
					return;
				}
				try{
					r = Integer.parseInt(args[0]);
					idS = Integer.parseInt(args[1]);
					idI = Integer.parseInt(args[2]);
					mCubes = Math.ceil(r - 1 / 2) * 2 - 1;					
					if ((!(mye.isAvailable(idS, player)))||(!(mye.isAvailable(idI, player))))
						return;
				}
				catch (NumberFormatException e){
					player.sendMessage("§c" + mye.getCommand("sphere").getUsage());
					return;
				}
			}			
			if (r > mye.getMaxRadius()){
				player.sendMessage("§c" + "El radio máximo para construir una esfera es: " + mye.getMaxRadius());
				return;
			}				
			if ((int)(b.getY() + mCubes) <= 128){
				for (double z = -mCubes / 2; z <= mCubes / 2; z++)
					slices.push(new Double(Math.sqrt(Math.pow(r,2) - Math.pow(z,2))));			
				cSlices = slices.size();
				while (slices.size() > 0){
					radius = ((Double) (slices.pop())).doubleValue();                       
					for (double y = -mCubes / 2, cY = 1; y <= mCubes / 2; y++, cY++)
						for (double x = -mCubes / 2, cX = 1; x <= mCubes / 2; x++, cX++)
							if ((Math.sqrt(Math.pow(y,2) + Math.pow(x,2))) > radius){
								// por si hubiera out
							}else
								if ((slices.size() == 1)||(slices.size() == (cSlices - 2))||(slices.size() == 0)||(slices.size() == (cSlices - 1)))
									player.getWorld().getBlockAt((int)(b.getX()+cX-r),(int)(b.getY()+cY),(int)(b.getZ()+slices.size()-r)).setTypeId(idS); //shell
								else
									if ((Math.sqrt(Math.pow(y,2) + Math.pow(x,2))) > (radius - 3))
										player.getWorld().getBlockAt((int)(b.getX()+cX-r),(int)(b.getY()+cY),(int)(b.getZ()+slices.size()-r)).setTypeId(idS); //shell
									else
										player.getWorld().getBlockAt((int)(b.getX()+cX-r),(int)(b.getY()+cY),(int)(b.getZ()+slices.size()-r)).setTypeId(idI); //in
				}
			}else{
				player.sendMessage("§c" + "no da la altura del mapa para hacer una esfera con radio " + r);
				return;
			}
		}
		return;
	}
}