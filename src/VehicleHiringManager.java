
public class VehicleHiringManager {

	private LocatorMap<String> locMap = new TreeLocatorMap();
	public VehicleHiringManager() {
	}

	
	public LocatorMap<String> getLocatorMap() {
		return locMap;
	}

	public void setLocatorMap(LocatorMap<String> locatorMap) {
		locMap = locatorMap;
	}

	
	public boolean addVehicle(String id, Location loc) {
		return locMap.add(id, loc).first;
	}

	
	public boolean moveVehicle(String id, Location loc) {
		return locMap.move(id, loc).first;
	}

	
	public boolean removeVehicle(String id) {
		return locMap.remove(id).first;
	}

	
	public Location getVehicleLoc(String id) {
		if(locMap.getLoc(id) == null) return null;
		return locMap.getLoc(id).first;
	}

	
	public List<String> getVehiclesInRange(Location loc, int r) {
		if(loc == null) return null;
		int plusX = loc.x +r;
		int plusY = loc.y +r;
		int subX = loc.x - r;
		int subY = loc.y - r;
		
		Location lowerLeft = new Location(subX, subY);
		Location upperRight = new Location(plusX, plusY);
		return locMap.getInRange(lowerLeft, upperRight).first;
	}
}
