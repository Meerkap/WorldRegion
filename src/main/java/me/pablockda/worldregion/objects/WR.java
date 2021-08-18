package me.pablockda.worldregion.objects;

public class WR {

	private Double x1=null, x2=null, y1=null, y2=null, z1=null, z2=null;
	private String world;
	private Status status;
	
	public enum Status{
		ON,OFF;
	}
	
	public WR(String world) {
		super();
		this.world = world;
		this.status = Status.OFF;
	}
	
	
	public WR( String world, Double x1, Double y1, Double z1, Double x2, Double y2, Double z2 ) {
		super();
		this.status = Status.ON;
		this.world = world;
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}
	
	
	


	public WR( Status status, String world, Double x1, Double y1, Double z1, Double x2, Double y2, Double z2) {
		super();
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.z1 = z1;
		this.z2 = z2;
		this.world = world;
		this.status = status;
	}


	public Double getX1() {
		return x1;
	}


	public void setX1(Double x1) {
		this.x1 = x1;
	}


	public Double getX2() {
		return x2;
	}


	public void setX2(Double x2) {
		this.x2 = x2;
	}


	public Double getY1() {
		return y1;
	}


	public void setY1(Double y1) {
		this.y1 = y1;
	}


	public Double getY2() {
		return y2;
	}


	public void setY2(Double y2) {
		this.y2 = y2;
	}


	public Double getZ1() {
		return z1;
	}


	public void setZ1(Double z1) {
		this.z1 = z1;
	}


	public Double getZ2() {
		return z2;
	}


	public void setZ2(Double z2) {
		this.z2 = z2;
	}


	public String getWorld() {
		return world;
	}


	public void setWorld(String world) {
		this.world = world;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
