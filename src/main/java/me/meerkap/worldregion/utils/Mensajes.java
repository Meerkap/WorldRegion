package me.meerkap.worldregion.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Mensajes {
	
	public void cargaPlugin(String name, String version) {
		
		Bukkit.getConsoleSender().sendMessage(ChatOnColor( "&4[&6WorldRegion&4] &8>> &eEnabled | Running version &a" + version ));
		Bukkit.getConsoleSender().sendMessage(ChatOnColor( "&4[&6WorldRegion&4] &8>> &eThanks for using my plugin! {PablockDA}" ));
		
	}
	
	
	public void descargaPlugin(String name, String version) {
		
		Bukkit.getConsoleSender().sendMessage(ChatOnColor( "&4[&6WorldRegion&4] &8>> &EDisabled | Running version &a" + version) );
		Bukkit.getConsoleSender().sendMessage(ChatOnColor( "&4[&6WorldRegion&4] &8>> &eThanks for using my plugin! {PablockDA}" ));
	}
	
	
	public String ChatOnColor(String mensaje) {
		if(mensaje==null) {
			return mensaje;
		}else {
			return ChatColor.translateAlternateColorCodes('&',mensaje);
		}
	}
	


	
}




