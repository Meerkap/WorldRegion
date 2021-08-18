package me.pablockda.worldregion.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Mensajes {
	
	public static void cargaPlugin(String name, String version) {
		
		Bukkit.getConsoleSender().sendMessage(ChatOnColor( "&4[&6WorldRegion&4] &8>> &eEnabled | Running version &a" + version ));
		Bukkit.getConsoleSender().sendMessage(ChatOnColor( "&4[&6WorldRegion&4] &8>> &eThanks for using my plugin! {PablockDA}" ));
		
	}
	
	
	public static void descargaPlugin(String name, String version) {
		
		Bukkit.getConsoleSender().sendMessage(ChatOnColor( "&4[&6WorldRegion&4] &8>> &EDisabled | Running version &a" + version) );
		Bukkit.getConsoleSender().sendMessage(ChatOnColor( "&4[&6WorldRegion&4] &8>> &eThanks for using my plugin! {PablockDA}" ));
	}
	
	
	public static String ChatOnColor(String mensaje) {
		if(mensaje==null) {
			return mensaje;
		}else {
			return ChatColor.translateAlternateColorCodes('&',mensaje);
		}
	}
	
	public static boolean isNumericPositiveOnly(String str){
        return str.matches("[+]?\\d*?");
    }
	
	
	public static boolean isNewerVersion( int version) {
		String packageName = Bukkit.getBukkitVersion().split("-")[0];
		String f = packageName.split("\\D")[0];
		f += packageName.split("\\D")[1];
		//packageName = packageName.replaceAll("\\D", "");
		int num = Integer.valueOf(f);
		return num <= version ? false : true;
	}

	
}




