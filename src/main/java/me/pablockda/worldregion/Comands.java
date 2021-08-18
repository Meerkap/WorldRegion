package me.pablockda.worldregion;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pablockda.worldregion.utils.Mensajes;


public class Comands implements CommandExecutor {
	
	WorldRegion plugin;

	public Comands (WorldRegion plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) {

		if ( !(sender instanceof Player)  ) {

			Bukkit.getConsoleSender().sendMessage((Mensajes.ChatOnColor( "&6[ WorldRegions ] &8>> &4&lYou cant execute commands from the console")));
			return false;
			
		} else {
			
			Player p = (Player) sender;
			
			if (args.length > 0) {
				
				String subCommando = args[0].toLowerCase();
				
				switch (subCommando) {
					
					default:
						p.sendMessage(Mensajes.ChatOnColor( plugin.getConfig().getString("Lang.Msg_Help") ) );
						break;
				}
				
			}else {
				p.sendMessage(Mensajes.ChatOnColor(  plugin.getConfig().getString("Lang.Msg_Help")  ));
				return false;
			} 
		}


		return false;
	}	
	
	
}
