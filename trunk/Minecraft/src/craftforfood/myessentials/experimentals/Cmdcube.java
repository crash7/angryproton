package craftforfood.myessentials.experimentals;

import org.bukkit.block.Block; 

import craftforfood.myessentials.commands.MyECommand;

public class Cmdcube extends MyECommand{
	private Block b;	//block that contains the axis, where will be placed the cube
	private int in;		//variable to store the material inside the cube
	private int s;		//variable to store the material of the cube
	private int l;		//variable to store the side of the cube to create

	public Cmdcube() {
		super("build.cube");
	}

	public boolean execute(String[] args){
		b = mye.getPoint(0, player);

		if (b == null)
			player.sendMessage("seteame el punto, o no te construyo el cubo!");
		else{
			player.sendMessage("cubo");
			if ((args.length != 3 ))
				player.sendMessage("El comando para crear cubos tiene 3 parámetros!!!");
			else{
				l = Integer.parseInt(args[0]);
				s = Integer.parseInt(args[1]);
				in = Integer.parseInt(args[2]);
				if ((b.getY() + l) <= 128){
					for (int z=0; z<l; z++)
						for (int y=0; y<l; y++)
							for (int x=0; x<l; x++)
								if ((i == 0) || (i == (l - 1)))
									player.getWorld().getBlockAt((b.getX()+x),(b.getY()+z),(b.getZ()+y)).setTypeId(s); //shell
								else
									if ((k == 0) || (k == (l - 1)) || (j == 0) || (j == (l - 1)))
										player.getWorld().getBlockAt((b.getX()+x),(b.getY()+z),(b.getZ()+y)).setTypeId(s); //shell
									else
										player.getWorld().getBlockAt((b.getX()+x),(b.getY()+z),(b.getZ()+y)).setTypeId(in); //in
				}else
					player.sendMessage("no da la altura del mapa para hacer un cubo de lado" + l);
			}
		}
		return true;
	}
}