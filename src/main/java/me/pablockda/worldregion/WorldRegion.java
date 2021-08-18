package me.pablockda.worldregion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import me.pablockda.worldregion.api.WorldRegionAPI;
import me.pablockda.worldregion.utils.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import me.pablockda.worldregion.utils.Mensajes;


public class WorldRegion extends JavaPlugin{

	private PluginDescriptionFile pdfFile = getDescription();
	private String version = this.pdfFile.getVersion();
	private Mensajes m = new Mensajes();

	private WorldRegionAPI worldRegionAPI;

	public void onEnable() {

		worldRegionAPI = new WorldRegionAPI(this);

		new MetricsLite(this);

		m.cargaPlugin(this.getDescription().getName(), this.getDescription().getVersion());
		
		updateChecker();
		
	}

	// Descarga del Plugin
	public void onDisable() {
		m.descargaPlugin(this.getDescription().getName(), this.getDescription().getVersion());
	}
	
	private void updateChecker() {
	    try {
	      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(
	        "https://api.spigotmc.org/legacy/update.php?resource=69426").openConnection();
	      int i = 1250;
	      localHttpURLConnection.setConnectTimeout(i);
	      localHttpURLConnection.setReadTimeout(i);
	      String latestversion = new BufferedReader(new InputStreamReader(localHttpURLConnection.getInputStream())).readLine();
	      if ((latestversion.length() <= 7) &&
	        (!this.version.equals(latestversion)))
	      {
	        Bukkit.getConsoleSender().sendMessage(m.ChatOnColor("&4[&6WorldRegion&4] &8>> &cVersion &e(" + latestversion + "&e) &cis available."));
	        Bukkit.getConsoleSender().sendMessage(m.ChatOnColor("&4[&6WorldRegion&4] &8>> &cYou can download it at: &ehttps://www.spigotmc.org/resources/69426/"));
	      }
	    }
	    catch (Exception localException) {
	      Bukkit.getConsoleSender().sendMessage(m.ChatOnColor("&4[&6WorldRegion&4] &8>> &cError while checking update."));
	    }
	}


	public WorldRegionAPI getAPI(){
		return this.worldRegionAPI;
	}


}