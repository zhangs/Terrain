package unca.edu.szhang.Terrain;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class TerrainPopulator extends BlockPopulator {

	public void populate(World world, Random random, Chunk chunk) {
		int x, y, z;
		Block block;
		
		// Territory chunk surroundings and one block of content are skipped, starting at 2 instead of 0		
		for (x = 2; x < 16; ++x) {
			for (z = 2; z < 16; ++z) {
				if (random.nextInt(100) <= 25) {
					for (y = 40; chunk.getBlock(x, y, z).getType() == Material.AIR; --y);
										
					block = chunk.getBlock(x, y + 1, z);
					if (block.getType() == Material.LAVA) {
						block.setType(Material.GOLD_ORE);
					}
					if (block.getType() == Material.ICE) {
						block.setType(Material.LAPIS_BLOCK);
					}
					if (block.getType() == Material.MOSSY_COBBLESTONE) {
						block.setType(Material.RED_MUSHROOM);
					}
					if (block.getType() == Material.SOUL_SAND) {
						block.setType(Material.DEAD_BUSH);
					}
					if (block.getType() == Material.OBSIDIAN) {
						block.setType(Material.SNOW);
					}
				}
			}
		}
	}
	
}
