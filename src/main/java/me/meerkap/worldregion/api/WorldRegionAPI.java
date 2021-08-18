package me.meerkap.worldregion.api;

import me.meerkap.worldregion.WorldRegion;
import me.meerkap.worldregion.managers.FileManager;
import me.meerkap.worldregion.objects.WR;
import me.meerkap.worldregion.utils.Config;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.*;

public class WorldRegionAPI {

    private final HashMap<String, WR> regions;
    private final Config config;

    public WorldRegionAPI(WorldRegion plugin) {

        config = new Config(plugin, "config");
        regions = new FileManager().loadRegions(config);

    }



    /**
     * Adds a Region with the following parameters:
     * @param region
     * @param world
     */
    public void addRegion(String region, String world) {

        if( regions.containsKey(region) ) {
            return;
        }

        regions.put( region, new WR( world ) );

        config.set("Regions."+region + ".Status", WR.Status.OFF.toString() );
        config.set("Regions."+region + ".World", world );
        config.set("Regions."+region + ".A", "null,null" );
        config.set("Regions."+region + ".B", "null,null" );
        config.save();

    }

    /**
     * Adds a Region with the following parameters:
     * @param region
     * @param world
     * @param ax
     * @param ay
     * @param az
     * @param bx
     * @param by
     * @param bz
     */
    public void addRegion(String region, String world, Double ax, Double ay, Double az, Double bx, Double by, Double bz) {

        if( regions.containsKey(region) ) {
            return;
        }

        regions.put( region, new WR( world, ax, ay, az, bx, by, bz ) );
        config.set("Regions."+region + ".Status", WR.Status.ON.toString() );
        config.set("Regions."+region + ".World", world );
        config.set("Regions."+region + ".A", Math.floor(ax) + "," + Math.floor(ay) + "," + Math.floor(az)  );
        config.set("Regions."+region + ".B", Math.floor(bx) + "," + Math.floor(by) + "," + Math.floor(bz)  );
        config.save();

    }


    /**
     * Adds a Region with the following parameters:
     * @param region
     * @param A
     * @param B
     */
    public void addRegion(String region, Location A, Location B) {

        if( regions.containsKey(region) ) {
            return;
        }

        if(!A.getWorld().getName().equals(B.getWorld().getName())) {
            return;
        }

        regions.put( region, new WR( A.getWorld().getName(), A.getX(), A.getY(), A.getZ(), B.getX(), B.getY(), B.getZ() ) );
        config.set("Regions."+region + ".Status", WR.Status.ON.toString() );
        config.set("Regions."+region + ".World", A.getWorld().getName() );
        config.set("Regions."+region + ".A", Math.floor(A.getX()) + "," + Math.floor(A.getY()) + "," + Math.floor(A.getZ())  );
        config.set("Regions."+region + ".B", Math.floor(B.getX()) + "," + Math.floor(B.getY()) + "," + Math.floor(B.getZ())  );
        config.save();

    }






    /**
     * Deletes a Region by name if exists
     * @param name
     */
    public void deleteRegion(String name) {

        if( !regions.containsKey(name) )
            return;

        regions.remove(name);
        config.set("Regions."+name, null);
        config.save();
    }




    /**
     * Sets the existing location as the point A
     * @param name
     * @param l
     */
    public void setPointA(String name, Location l) {

        if(!regions.containsKey(name))
            return;

        WR region = regions.get(name);

        if( !l.getWorld().getName().equals(region.getWorld()) ) {
            return;
        }

        region.setX1( l.getX() );
        region.setY1( l.getY() );
        region.setZ1( l.getZ() );

        config.set("Regions."+name + ".A", Math.floor(l.getX()) + "," + Math.floor(l.getY()) + "," + Math.floor(l.getZ())  );
        config.save();

        if( (Double)region.getX1()!=null && (Double)region.getX2()!=null ) {
            region.setStatus(WR.Status.ON);
        }

    }

    /**
     * Sets the existing location as the point B
     * @param name
     * @param l
     */
    public void setPointB(String name, Location l) {

        if(!regions.containsKey(name)) {
            return;
        }

        WR region = regions.get(name);

        if( !l.getWorld().getName().equals(region.getWorld()) ) {
            return;
        }

        region.setX2( l.getX() );
        region.setY2( l.getY() );
        region.setZ2( l.getZ() );

        config.set("Regions."+name + ".B", Math.floor(l.getX()) +  "," + Math.floor(l.getY()) + "," + Math.floor(l.getZ())  );
        config.save();

        if( (Double)region.getX1()!=null && (Double)region.getX2()!=null ) {
            region.setStatus(WR.Status.ON);
        }

    }


    /**
     * Change the status of the region if exist
     * It switch from ON to OFF and vice versa
     * @param name
     */
    public void changeStatus(String name) {

        if(!regions.containsKey(name)) {
            return;
        }

        WR object = regions.get(name);

        if( object.getX1() == null || object.getX2() == null ) {
            return;
        }

        if(object.getStatus() == WR.Status.OFF) {
            object.setStatus(WR.Status.ON);
            config.set("Regions."+name + ".Status", "ON" );
        }else {
            object.setStatus(WR.Status.OFF);
            config.set("Regions."+name + ".Status", "OFF" );
        }
        config.save();

    }


    /**
     * Tells you if the region is active
     * Active: True, Non Active: False
     * @param name
     * @return Value
     */
    public boolean isActive(String name) {

        if( !regions.containsKey(name) ) {
            return false;
        }

        boolean sino = regions.get(name).getStatus() == WR.Status.ON ? true : false;
        return sino;
    }


    /**
     * Returns list of the existing regions
     * Empty List if there are no regions
     * @return List
     */
    public ArrayList<String> getRegionList() {

        if(regions.isEmpty()) { return new ArrayList<>(); }

        ArrayList<String> list = new ArrayList<String>() ;

        for (  Map.Entry<String, WR> WR : regions.entrySet()) {
            list.add( WR.getKey() );
        }

        return list;
    }


    /**
     * Tells you if the region has 3 Dimensions
     * @param l
     * @return Value
     */
    public boolean isRegion3D(Location l) {

        if( regions.isEmpty() ) { return true; }

        boolean check = true;
        String world = l.getWorld().getName();

        for (  Map.Entry<String, WR> WG : regions.entrySet()) {

            if( WG.getValue().getStatus() == WR.Status.ON && WG.getValue().getWorld().equals(world) ) {

                check = false;

                double[] dim = new double[2];
                dim[0] = WG.getValue().getX1();
                dim[1] = WG.getValue().getX2();
                Arrays.sort(dim);

                double[] dim2 = new double[2];
                dim2[0] = WG.getValue().getY1();
                dim2[1] = WG.getValue().getY2();
                Arrays.sort(dim2);

                double[] dim3 = new double[2];
                dim3[0] = WG.getValue().getZ1();
                dim3[1] = WG.getValue().getZ2();
                Arrays.sort(dim3);

                if(  l.getX() >= dim[0] && l.getX() <= dim[1]  && l.getY() >= dim2[0] && l.getY() <= dim2[1]  &&  l.getZ() >= dim3[0] && l.getZ() <= dim3[1] ) {
                    return true;
                }
            }else if( WG.getValue().getStatus() == WR.Status.ON  ) {
                check = false;
            }
        }

        return check;
    }


    /**
     * Tells you if the region has 2 Dimensions
     * @param l
     * @return Value
     */
    public boolean isRegion2D(Location l) {

        if( regions.isEmpty() ) { return true; }

        boolean check = true;
        String world = l.getWorld().getName();

        for (  Map.Entry<String, WR> WG : regions.entrySet()) {

            if( WG.getValue().getStatus() == WR.Status.ON && WG.getValue().getWorld().equals(world) ) {

                check = false;

                double[] dim = new double[2];
                dim[0] = WG.getValue().getX1();
                dim[1] = WG.getValue().getX2();
                Arrays.sort(dim);

                double[] dim3 = new double[2];
                dim3[0] = WG.getValue().getZ1();
                dim3[1] = WG.getValue().getZ2();
                Arrays.sort(dim3);

                if(  l.getX() >= dim[0] && l.getX() <= dim[1]  && l.getZ() >= dim3[0] && l.getZ() <= dim3[1] ) {
                    return true;
                }
            }else if( WG.getValue().getStatus() == WR.Status.ON  ) {
                check = false;
            }
        }
        return check;
    }


    /**
     * Gives you a list with the regions in
     * the given range, empty if no regions
     * @param l
     * @param range
     * @return
     */
    public List<String> getRegionsInRange(Block l, int range) {

        List<String> list = new ArrayList<>();

        if(!isNumericPositiveOnly( String.valueOf(range) )) { return list;	}

        if( regions.isEmpty() ) { return list; }

        String world = l.getWorld().getName();

        double minX=  l.getX() + range;
        double maxX =  l.getX() - range;

        double minZ=  l.getZ() + range;
        double maxZ =  l.getZ() - range;

        for (  Map.Entry<String, WR> WG : regions.entrySet()) {

            if( WG.getValue().getWorld().equals(world) ) {

                double[] dim = new double[2];
                dim[0] = WG.getValue().getX1();
                dim[1] = WG.getValue().getX2();
                Arrays.sort(dim);

                double[] dim3 = new double[2];
                dim3[0] = WG.getValue().getZ1();
                dim3[1] = WG.getValue().getZ2();
                Arrays.sort(dim3);

                if( dim[0] >= maxX && dim[0] <= minX &&
                        dim[1] >= maxX && dim[1] <= minX &&
                        dim3[0] >= maxZ && dim3[0] <= minZ &&
                        dim3[1] >= maxZ && dim3[1] <= minZ   ) {
                    list.add(WG.getKey());

                }
            }
        }

        return list;
    }


    /**
     * Gives you the distance between 3D Points
     * @param aX
     * @param aY
     * @param aZ
     * @param bX
     * @param bY
     * @param bZ
     * @return
     */
    public double getDistanceBetween3DPoints( double aX, double aY, double aZ, double bX, double bY, double bZ) {
        return Math.sqrt( Math.pow( ( bX - aX) , 2 ) + Math.pow( (bY - aY), 2 ) + Math.pow( (bZ - aZ), 2 ) );
    }


    /**
     * Gives you the distance between 3D Points
     * @param aX
     * @param aZ
     * @param bX
     * @param bZ
     * @return
     */
    public double getDistanceBetween2DPoints( double aX, double aZ, double bX, double bZ) {
        return Math.sqrt( Math.pow( ( bX - aX) , 2 ) + Math.pow( (bZ - aZ), 2 ) );
    }

    private boolean isNumericPositiveOnly(String str){
        return str.matches("[+]?\\d*?");
    }


}
