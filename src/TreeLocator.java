class TreeLocatorNode<T>{
	public T data;
	List<T> elements = new LinkedList<T>();
	public Location address;
	public TreeLocatorNode<T> red, green, blue, yellow;
	
	public TreeLocatorNode(T data, Location n) {
		elements.insert(data);
		address = n;
		red = green = blue = yellow = null;
	}
	
}
public class TreeLocator<T> implements Locator<T>{
	private TreeLocatorNode<T> root, current ;
	
	public TreeLocator() {
		root = current = null;
	}

	@Override
	public int add(T e, Location loc) {
		int counter =0;
		if(e == null || loc == null) return counter;
		if(root == null) {
			root = current = new TreeLocatorNode<T>(e,loc);
			return counter;
		}
		
		TreeLocatorNode<T> p = null;
		TreeLocatorNode<T> q = root;
		while(q != null) {
			if((loc.x == q.address.x) && (loc.y == q.address.y)) {
				q.elements.insert(e);
				counter++;
				return counter;
			}
			p=q;
			if(loc.x < q.address.x && loc.y <= q.address.y) {
				counter++;
				if(q.red == null) {
					q.red = new TreeLocatorNode(e,loc);
					return counter;
				}
			q = q.red;
			}
			else if(loc.x <= q.address.x && loc.y > q.address.y) {
				counter++;
				if(q.green == null) {
					q.green = new TreeLocatorNode(e,loc);
					return counter;
				}
			q = q.green;
			}
			else if(loc.x > q.address.x && loc.y >= q.address.y) {
				counter++;
				if(q.blue == null) {
					q.blue = new TreeLocatorNode(e,loc);
					return counter;
				}
			q = q.blue;
			}
			else if(loc.x >= q.address.x && loc.y < q.address.y) {
				counter++;
				if(q.yellow == null) {
					q.yellow = new TreeLocatorNode(e,loc);
					return counter;
				}
			q = q.yellow;
			}
		}
		return counter;
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		int counter = 0;
		TreeLocatorNode<T> q = root;
		while(q != null) {
			if((loc.x == q.address.x) && (loc.y == q.address.y)) {
				counter++;
				return new Pair<List<T>, Integer>(q.elements,counter);
			}
			if(loc.x < q.address.x && loc.y <= q.address.y) {
				counter++;
				q=q.red;
			}
			else if(loc.x <= q.address.x && loc.y > q.address.y) {
				counter++;
				q=q.green;
			}
			else if(loc.x > q.address.x && loc.y >= q.address.y) {
				counter++;
				q=q.blue;
			}
			else if(loc.x >= q.address.x && loc.y < q.address.y) {
				counter++;
				q=q.yellow;
			}
		}
		List<T> empty = new LinkedList<T>();
		return new Pair<List<T>, Integer>(empty,counter);
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		int counter = 0;
		boolean tested = false;
		if(e == null || loc == null) return new Pair<Boolean, Integer>(tested,counter);
		TreeLocatorNode<T> q = root;
		while(q != null) {
			if((loc.x == q.address.x) && (loc.y == q.address.y)) {
				counter++;
				if(q.elements.empty()) return new Pair<Boolean, Integer>(tested,counter);
				q.elements.findFirst();
				while(!q.elements.last()) {
				if(q.elements.retrieve().equals(e)) {
					tested = true;
				q.elements.remove();
				}
				else
					q.elements.findNext();
				}
				if(q.elements.retrieve().equals(e)) {
					tested = true;
					q.elements.remove();
				}
				return new Pair<Boolean, Integer>(tested,counter);
			}
			if(loc.x < q.address.x && loc.y <= q.address.y) {
				counter++;
				q=q.red;
			}
			else if(loc.x <= q.address.x && loc.y > q.address.y) {
				counter++;
				q=q.green;
			}
			else if(loc.x > q.address.x && loc.y >= q.address.y) {
				counter++;
				q=q.blue;
			}
			else if(loc.x >= q.address.x && loc.y < q.address.y) {
				counter++;
				q=q.yellow;
			}
		}
		return new Pair<Boolean, Integer>(tested,counter);
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> all = new LinkedList<Pair<Location, List<T>>>();
		recGetAll(root,all);
		return all;
	}
	private void recGetAll(TreeLocatorNode<T> bb ,List<Pair<Location, List<T>>> a) {
		if(bb == null) return;
		else {
			a.insert(new Pair<Location, List<T>>(bb.address,bb.elements));
			recGetAll(bb.red, a);
			recGetAll(bb.green, a);
			recGetAll(bb.blue, a);
			recGetAll(bb.yellow, a);
		}
	}
	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		int counter = 0;
        List<Pair<Location, List<T>>> listPairs = new LinkedList<Pair<Location, List<T>>>();
		
		Pair<List<Pair<Location, List<T>>>, Integer> pairs = new Pair<List<Pair<Location, List<T>>>, Integer>(listPairs, counter);
		
		if(lowerLeft == null || upperRight == null) return pairs;
		
		Location upperLeft = new Location(lowerLeft.x, upperRight.y);
		Location lowerRight = new Location(upperRight.x,lowerLeft.y);
		
		
		TreeLocatorNode<T> q = root;
		recInRange(q, pairs, lowerLeft, upperRight, upperLeft, lowerRight);
		
		return pairs;
	}
	
	private void recInRange(TreeLocatorNode<T> node ,Pair<List<Pair<Location, List<T>>>, Integer> pa,
			Location lower, Location upper, Location ul, Location lr) {
		if(node == null) return;
		pa.second++;
		if(lower.x == upper.x && lower.y == upper.y) {
			if(((node.address.x == upper.x) && (node.address.y == upper.y))
					&&((node.address.x == lower.x) && (node.address.y == lower.y))) {
			pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
			return;
		}
			else if(node.address.x < lower.x && node.address.y <= lower.y) {
				recInRange(node.blue, pa, lower, upper, ul, lr);	
			}
			else if(node.address.x >= lower.x && node.address.y < lower.y) {
				recInRange(node.green, pa, lower, upper, ul, lr);
			}
			else if(node.address.x > lower.x && node.address.y >= lower.y) {
				recInRange(node.red, pa, lower, upper, ul, lr);
			}
			else if(node.address.x <= lower.x && node.address.y > lower.y) {
				recInRange(node.yellow, pa, lower, upper, ul, lr);
			}
			}
		else if(((node.address.x <= upper.x) && (node.address.y <= upper.y))
				&&((node.address.x >= lower.x) && (node.address.y >= lower.y))) {
			if(lower.x == upper.x && lower.y == upper.y) {
				pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
				return;
			}
			else if(node.address.x == lower.x && node.address.y == lower.y) {
				pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
				recInRange(node.green, pa, lower, upper, ul, lr);
				recInRange(node.blue, pa, lower, upper, ul, lr);
			}
			else if(node.address.x == lr.x && node.address.y == lr.y) {
				pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
	        	recInRange(node.red, pa, lower, upper, ul, lr);
				recInRange(node.green, pa, lower, upper, ul, lr);
			}
			else if(node.address.x == ul.x && node.address.y == ul.y) {
				pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
				recInRange(node.blue, pa, lower, upper, ul, lr);
	        	recInRange(node.yellow, pa, lower, upper, ul, lr);
			}
			else if(node.address.x == upper.x && node.address.y == upper.y) {
				pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
	        	recInRange(node.red, pa, lower, upper, ul, lr);
				recInRange(node.yellow, pa, lower, upper, ul, lr);
	        }
			else if((node.address.x > lower.x && node.address.x < lr.x) && (node.address.y == lower.y)) {
				pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
				recInRange(node.red, pa, lower, upper, ul, lr);
				recInRange(node.green, pa, lower, upper, ul, lr);
				recInRange(node.blue, pa, lower, upper, ul, lr);
			}
			else if((node.address.x == lr.x)&& (node.address.y > lower.y && node.address.y < upper.y)) {
				pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
				recInRange(node.red, pa, lower, upper, ul, lr);
				recInRange(node.green, pa, lower, upper, ul, lr);
				recInRange(node.yellow, pa, lower, upper, ul, lr);
			}
			else if((node.address.x == lower.x) && (node.address.y > lower.y && node.address.y < upper.y)) {
				pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
				recInRange(node.green, pa, lower, upper, ul, lr);
				recInRange(node.blue, pa, lower, upper, ul, lr);
				recInRange(node.yellow, pa, lower, upper, ul, lr);
			}
			else if((node.address.x > lower.x && node.address.x < lr.x)&& (node.address.y == upper.y)) {
				pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
				recInRange(node.red, pa, lower, upper, ul, lr);
				recInRange(node.blue, pa, lower, upper, ul, lr);
				recInRange(node.yellow, pa, lower, upper, ul, lr);
			}
			else {
			pa.first.insert(new Pair<Location, List<T>>(node.address, node.elements));
			recInRange(node.red, pa, lower, upper, ul, lr);
			recInRange(node.green, pa, lower, upper, ul, lr);
			recInRange(node.blue, pa, lower, upper, ul, lr);
			recInRange(node.yellow, pa, lower, upper, ul, lr);
		}}
		//1
		else if((node.address.x < lower.x && node.address.y <= lower.y)) {
		recInRange(node.blue, pa, lower, upper, ul, lr);
		}
		//2
		else if((node.address.x >= lower.x && node.address.x < lr.x) && node.address.y < lower.y) {
		recInRange(node.green, pa, lower, upper, ul, lr);
		recInRange(node.blue, pa, lower, upper, ul, lr);
		}
		//3
		else if((node.address.x >= lr.x) && node.address.y < lower.y) {
			recInRange(node.green, pa, lower, upper, ul, lr);
		}
		//4
		else if(node.address.x < lower.x && (node.address.y > lower.y && node.address.y <= ul.y)) {
			recInRange(node.blue, pa, lower, upper, ul, lr);
			recInRange(node.yellow, pa, lower, upper, ul, lr);
			}
		//5
		else if((node.address.x > lr.x)&& (node.address.y >= lr.y && node.address.y <upper.y)) {
			recInRange(node.red, pa, lower, upper, ul, lr);
			recInRange(node.green, pa, lower, upper, ul, lr);
		}
		//6
		else if((node.address.x <= lower.x) && (node.address.y > upper.y)) {
			recInRange(node.yellow, pa, lower, upper, ul, lr);
		}
		//7
		else if((node.address.x > lower.x && node.address.x <= lr.x) && node.address.y > upper.y) {
			recInRange(node.red, pa, lower, upper, ul, lr);
			recInRange(node.yellow, pa, lower, upper, ul, lr);
		}
		//8
		else if(node.address.x > lr.x && node.address.y >= upper.y) {
			recInRange(node.red, pa, lower, upper, ul, lr);
		}
		
		/////extra cases
		else if((node.address.x > lower.x && node.address.x < lr.x) && (node.address.y == lower.y)) {
			recInRange(node.red, pa, lower, upper, ul, lr);
			recInRange(node.green, pa, lower, upper, ul, lr);
			recInRange(node.blue, pa, lower, upper, ul, lr);
		}
		else if((node.address.x == lr.x)&& (node.address.y > lower.y && node.address.y < upper.y)) {
			recInRange(node.red, pa, lower, upper, ul, lr);
			recInRange(node.green, pa, lower, upper, ul, lr);
			recInRange(node.yellow, pa, lower, upper, ul, lr);
		}
		else if((node.address.x == lower.x) && (node.address.y > lower.y && node.address.y < upper.y)) {
			recInRange(node.green, pa, lower, upper, ul, lr);
			recInRange(node.blue, pa, lower, upper, ul, lr);
			recInRange(node.yellow, pa, lower, upper, ul, lr);
		}
		else if((node.address.x > lower.x && node.address.x < lr.x)&& (node.address.y == upper.y)) {
			recInRange(node.red, pa, lower, upper, ul, lr);
			recInRange(node.blue, pa, lower, upper, ul, lr);
			recInRange(node.yellow, pa, lower, upper, ul, lr);
		}
		else if(node.address.x == lower.x && node.address.y == lower.y) {
			recInRange(node.green, pa, lower, upper, ul, lr);
			recInRange(node.blue, pa, lower, upper, ul, lr);
		}
		else if(node.address.x == lr.x && node.address.y == lr.y) {
        	recInRange(node.red, pa, lower, upper, ul, lr);
			recInRange(node.green, pa, lower, upper, ul, lr);
		}
		else if(node.address.x == ul.x && node.address.y == ul.y) {
			recInRange(node.blue, pa, lower, upper, ul, lr);
        	recInRange(node.yellow, pa, lower, upper, ul, lr);
		}
		else if(node.address.x == upper.x && node.address.y == upper.y) {
        	recInRange(node.red, pa, lower, upper, ul, lr);
			recInRange(node.yellow, pa, lower, upper, ul, lr);
        }
		
		
		}
		
		
	}



