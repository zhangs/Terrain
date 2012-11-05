package unca.edu.szhang.Terrain;

import java.util.logging.Logger;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Terrain extends JavaPlugin {

	private Logger log = Logger.getLogger("Minecraft");
	
    @Override
    public void onEnable() {
    	this.logMessage("Custom Terrain Enabled");
        //saveDefaultConfig();
        
        //new TerrainListener(this);
        
        //this.getCommand("sample").setExecutor(new TerrainCommandExecutor(this));
    }

    @Override
    public void onDisable() {
    	this.logMessage("Custom Terrain Disabled");
    }
    
    public void logMessage(String msg) {
    	PluginDescriptionFile pdfile = this.getDescription();
    	this.log.info(pdfile.getName() + " " + pdfile.getVersion() + " " + msg);    	
    }

    public ChunkGenerator getDefaultWorldGenerator (String worldName, String uid) {
    	return new TerrainGenerator(this);
    }
}
