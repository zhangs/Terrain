package unca.edu.szhang.Terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class TerrainGenerator extends ChunkGenerator {
	private Terrain plugin;
	
	// constructor
	public TerrainGenerator (Terrain instance) {
		this.plugin = instance;
	}
	
	// Generating blocks in small area
	public List<BlockPopulator> getDefaultPopulators (World world) {
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();
		populators.add(new TerrainPopulator());
		
		return populators;
	}
		
	// Sets coordinates to locations
	private int coordsToInt(int x, int y, int z) {
		return (x * 16 + z) * 128  + y;
	}
	
	@Override
	public byte[] generate (World world, Random rng, int chunkx, int chunkz) {
		byte[] blocks = new byte[32768];
		int x, y, z;
		
		Random rand = new Random(world.getSeed());		
		SimplexOctaveGenerator octave = new SimplexOctaveGenerator(rand, 8);		
		octave.setScale(1/32.0);
		
		for (x = 0; x < 16; ++x) {
			for (z = 0; z < 16; ++x) {
				double noise = octave.noise(x + chunkx, z + chunkz, .5, .5);
				
				// sets the ground
				blocks[this.coordsToInt(x, 0, z)] = (byte) Material.BEDROCK.getId();

				for (y = 1; y < 20; ++y) {
					blocks[this.coordsToInt(x, y, z)] = (byte) Material.DIRT.getId();					
				}

				blocks[this.coordsToInt(x, 20, z)] = (byte) Material.GRASS.getId();
				
				// what is above the ground is decided here...
				Random element = new Random();
				int decided = element.nextInt(5);
				
				for (y = 21; y < 23; y++) {
					// territory of Fire
					if (decided == 0) {
						if (x == 0 || z == 0 || x == 15 || z == 15) {
							blocks[this.coordsToInt(x, y, z)] = (byte) Material.FIRE.getId();						
						}
						else {
							blocks[this.coordsToInt(x, y, z)] = (byte) Material.LAVA.getId();					
						}
					}
					// territory of water
					else if (decided == 1) {
						if (x == 0 || z == 0 || x == 15 || z == 15) {
							blocks[this.coordsToInt(x, y, z)] = (byte) Material.WATER.getId();						
						}
						else {
							blocks[this.coordsToInt(x, y, z)] = (byte) Material.ICE.getId();					
						}
					}	
					// territory of earth
					else if (decided == 2) {
						if (x == 0 || z == 0 || x == 15 || z == 15) {
							blocks[this.coordsToInt(x, y, z)] = (byte) Material.GRASS.getId();						
						}
						else {
							blocks[this.coordsToInt(x, y, z)] = (byte) Material.MOSSY_COBBLESTONE.getId();					
						}
					}	
					// territory of wind
					else if (decided == 3) {
						if (x == 0 || z == 0 || x == 15 || z == 15) {
							blocks[this.coordsToInt(x, y, z)] = (byte) Material.SAND.getId();						
						}
						else {
							blocks[this.coordsToInt(x, y, z)] = (byte) Material.SOUL_SAND.getId();					
						}
					}	
					// territory of normal
					else if (decided == 4) {
						if (x == 0 || z == 0 || x == 15 || z == 15) {
							blocks[this.coordsToInt(x, y, z)] = (byte) Material.DIAMOND.getId();					
						}
						else {
							blocks[this.coordsToInt(x, y, z)] = (byte) Material.OBSIDIAN.getId();				
						}
					}
				}
				
				// after creating floor, create a hill-type setting
				if (x > 3 && z > 3 && x < 13 && z < 13) {
				    for (y = 23; y < 50 + noise; y++) {
				    	if (decided == 0) {
				    		blocks[this.coordsToInt(x, y, z)] = (byte) Material.LAVA.getId();
				    	}
				    	if (decided == 1) {
				    		blocks[this.coordsToInt(x, y, z)] = (byte) Material.ICE.getId();
				    	}
				    	if (decided == 2) {
				    		blocks[this.coordsToInt(x, y, z)] = (byte) Material.MOSSY_COBBLESTONE.getId();
				    	}
				    	if (decided == 3) {
				    		blocks[this.coordsToInt(x, y, z)] = (byte) Material.SOUL_SAND.getId();
				    	}
				    	if (decided == 4) {
				    		blocks[this.coordsToInt(x, y, z)] = (byte) Material.OBSIDIAN.getId();
				    	}				    	
				    }
				}
			}
		}
		
		return blocks;			
	}
}
