package me.meerkap.worldregion.managers;

import me.meerkap.worldregion.objects.WR;
import me.meerkap.worldregion.utils.Config;
import me.meerkap.worldregion.utils.Mensajes;
import org.bukkit.Bukkit;

import java.util.HashMap;

public class FileManager {

    Mensajes m = new Mensajes();

    public HashMap<String, WR>  loadRegions(Config config )  {

        HashMap<String, WR> regions = null ;

        try  {

            if( config.contains("Regions.") ) {

                for(String key : config.getConfigurationSection("Regions.").getKeys(false)){

                    WR.Status s = config.getString("Regions." + key + ".Status").equals("OFF") ? WR.Status.OFF : WR.Status.ON;
                    String world =  config.getString("Regions." + key + ".World") ;

                    String A[] = config.getString("Regions." + key + ".A").split(",");

                    Double ax = A[0].equals("null") ? null : Double.valueOf(A[0]) ;
                    Double ay = A[1].equals("null") ? null : Double.valueOf(A[1]) ;
                    Double az = A[1].equals("null") ? null : Double.valueOf(A[2]) ;

                    String B[] = config.getString("Regions." + key + ".B").split(",");

                    Double bx = B[0].equals("null") ? null : Double.valueOf(B[0]) ;
                    Double by = B[1].equals("null") ? null : Double.valueOf(B[1]) ;
                    Double bz = B[1].equals("null") ? null : Double.valueOf(B[2]) ;

                    regions.put( key, new WR( s, world, ax, ay, az, bx, by, bz ) );
                }
            }

            Bukkit.getConsoleSender().sendMessage( m.ChatOnColor("&4[&6WorldRegion&4] &8>> &eRegions loaded correctly"));

        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage( m.ChatOnColor("&4[&6WorldRegion&4] &8>> &4Error: &eLoading regions"));
            e.printStackTrace();
        }

        return regions;

    }


}
