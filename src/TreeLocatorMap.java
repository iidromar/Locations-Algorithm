
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	
	private Map<K, Location> binaryST = new BST<K, Location>();
	private Locator<K> locy = new TreeLocator<K>();
	
	@Override
	public Map<K, Location> getMap() {
		return binaryST;
	}

	@Override
	public Locator<K> getLocator() {
		return locy;
	}
	
	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		Pair<Boolean,Integer> mm = binaryST.insert(k, loc);
		if(mm.first)
			locy.add(k, loc);
		return mm; 
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		if(loc == null || k == null) return new Pair<Boolean, Integer>(false, 0);
		boolean check = getMap().find(k).first;
		if(check == false) return new Pair<Boolean, Integer>(false, getMap().find(k).second);
		locy.remove(k, binaryST.retrieve());
		locy.add(k, loc);
		binaryST.update(loc);
		return new Pair<Boolean, Integer>(true, binaryST.find(k).second);
	}
	
	
	@Override
	public Pair<Location, Integer> getLoc(K k) {
		Pair<Boolean,Integer> io =binaryST.find(k);
		if(io.first == false)return new Pair<Location, Integer>(null, io.second);
			return new Pair<Location, Integer>(binaryST.retrieve(), io.second);
		
	}
	
	@Override
	public Pair<Boolean, Integer> remove(K k) {
		Pair<Boolean, Integer> ii = binaryST.find(k);
		if(ii.first == false) return new Pair<Boolean, Integer>(false, ii.second);
		locy.remove(k, binaryST.retrieve());
		binaryST.remove(k);
		return new Pair<Boolean, Integer>(true, ii.second);
	}

	@Override
	public List<K> getAll() {
		return binaryST.getAll();
	}
	
	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		if(lowerLeft == null || upperRight == null) return new Pair<List<K>, Integer>(null,0);
		Pair<List<Pair<Location, List<K>>>, Integer> ps = locy.inRange(lowerLeft, upperRight);
		List<K> keys = new LinkedList<K>();
		List<K> on = new LinkedList<K>();
		List<Pair<Location, List<K>>> mm = ps.first;
		mm.findFirst();
		if (mm.empty()) return new Pair<List<K>, Integer>(null,0);
		while(!mm.last()) {
			on = mm.retrieve().second;
			on.findFirst();
			while(on.empty()) {
				mm.findNext();
				on = mm.retrieve().second;
				on.findFirst();
			}
			while(!on.last()) {
				keys.insert(on.retrieve());
				on.findNext();
			}
			keys.insert(on.retrieve());
			mm.findNext();
		}
		on = mm.retrieve().second;
		on.findFirst();
		while(!on.last()) {
			keys.insert(on.retrieve());
			on.findNext();
		}
		keys.insert(on.retrieve());
		
		int counter = ps.second;
		return new Pair<List<K>, Integer>(keys, counter);
	}
}
