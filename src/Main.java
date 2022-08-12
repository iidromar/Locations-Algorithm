
public class Main {

	public static void main(String[] args) {
		System.out.println("**********************");
		//testVHM();
		System.out.println("**********************");
		//testLocator();
		//testBST();
		//testTL_All_except_inRange();
		//testTL_inRange();
		//testTLM_all_except_getInRange();
		testTLM_getInRange();
	}
	public static void testTLM_getInRange() {
		TreeLocatorMap<String> tlm=new TreeLocatorMap<String>();
		tlm.add("F", new Location(4,7));
		tlm.add("V", new Location(5,7));
		tlm.add("K", new Location(6,1));
		tlm.add("D", new Location(4,3));
		tlm.add("O", new Location(4,8));
		tlm.add("U", new Location(8,4));
		tlm.add("V", new Location(8,2));
		tlm.add("Y", new Location(2,2));
		tlm.add("S", new Location(6,1));
		tlm.add("B", new Location(6,3));
		
		System.out.println("-----Testing getInRange-----");
		Pair<List<String>, Integer> pa2=tlm.getInRange(new Location(0, 0),new Location(9, 9));
		System.out.print("All,"+pa2.second+": ");
		print(pa2.first);
		
		pa2=tlm.getInRange(null,null);
		System.out.print("null case,"+pa2.second+": ");
		print(pa2.first);
		
		pa2=tlm.getInRange(new Location(0, 3),new Location(3,9));
		System.out.print("Yellow,"+pa2.second+": ");
		print(pa2.first);
		
		pa2=tlm.getInRange(new Location(0, 2),new Location(9, 4));
		System.out.print("Dark blue,"+pa2.second+": ");
		print(pa2.first);
		
		pa2=tlm.getInRange(new Location(4,1),new Location(6, 8));
		System.out.print("Red,"+pa2.second+": ");
		print(pa2.first);
		
		pa2=tlm.getInRange(new Location(6,3),new Location(8, 4));
		System.out.print("Green,"+pa2.second+": ");
		print(pa2.first);
		
		pa2=tlm.getInRange(new Location(4,7),new Location(5, 8));
		System.out.print("Sky blue,"+pa2.second+": ");
		print(pa2.first);
		
		System.out.println("----after removed S,K----");
		tlm.remove("S");
		tlm.remove("K");
		
		pa2=tlm.getInRange(new Location(4,1),new Location(6, 8));
		System.out.print("Red,"+pa2.second+": ");
		print(pa2.first);
		
}

	public static void testTLM_all_except_getInRange(){
		TreeLocatorMap<String> tlm=new TreeLocatorMap<String>();
		Pair<Boolean, Integer> pa;
		tlm.add("F", new Location(4,7));
		tlm.add("V", new Location(5,7));
		tlm.add("K", new Location(6,1));
		tlm.add("D", new Location(4,3));
		tlm.add("O", new Location(4,8));
		tlm.add("U", new Location(8,4));
		pa=tlm.add("V", new Location(8,2));
		tlm.add("Y", new Location(2,2));
		tlm.add("S", new Location(6,1));
		tlm.add("B", new Location(6,3));
		
		
		Map<String, Location> bst;
		Locator<String> TL;
		bst=tlm.getMap();
		TL=tlm.getLocator();
		
		
		System.out.println("-----show all TLM-----");
		((BST<String, Location>)bst).print();
		((TreeLocator<String>)TL).print();
		
		System.out.println();
		System.out.println("-----Testing add-----");
		System.out.println(pa.first+": "+pa.second);
		
		pa=tlm.add(null, null);
		System.out.println("null case-> "+pa.first+": "+pa.second);
		
		System.out.println();
		System.out.println("-----Testing move-----");
		pa=tlm.move("S", new Location(1, 1));
		System.out.println("moving S-> "+pa.first+": "+pa.second);
		pa=tlm.move("B", new Location(1, 1));
		System.out.println("moving B-> "+pa.first+": "+pa.second);
		//moving a not exits element
		pa=tlm.move("R", new Location(1, 1));
		System.out.println("moving R-> "+pa.first+": "+pa.second);
		//give a wrong location
		pa=tlm.move("F",null);
		System.out.println("null case-> "+pa.first+": "+pa.second);

		((BST<String, Location>)bst).print();
		((TreeLocator<String>)TL).print();

		System.out.println();
		System.out.println("-----Testing getLoc-----");
		Pair<Location, Integer> pa1;
		pa1=tlm.getLoc("F");
		System.out.println("F-> "+pa1.first+": "+pa1.second);
		pa1=tlm.getLoc("S");
		System.out.println("S-> "+pa1.first+": "+pa1.second);
		pa1=tlm.getLoc(null);
		System.out.println("null case-> "+pa1.first+": "+pa1.second);
		pa1=tlm.getLoc("R");
		System.out.println("R-> "+pa1.first+": "+pa1.second);
		
		System.out.println();
		System.out.println("-----Testing remove-----");
		
		pa=tlm.remove("V");
		System.out.println("V-> "+pa.first+": "+pa.second);
		
		pa=tlm.remove(null);
		System.out.println("null case-> "+pa.first+": "+pa.second);
		
		pa=tlm.remove("R");
		System.out.println("R-> "+pa.first+": "+pa.second);
		
		
		((BST<String, Location>)bst).print();
		((TreeLocator<String>)TL).print();
	
		System.out.println();
		System.out.println("-----Testing getAll-----");
		List<String> l=tlm.getAll();
		print(l);
			
	}
	/////////////////////remove this part///////////////////////////////
	public static void testTL_inRange() {
		TreeLocator<String> tl=new TreeLocator<String>();
		tl.add("F", new Location(4,7));
		tl.add("V", new Location(5,7));
		tl.add("K", new Location(6,1));
		tl.add("D", new Location(4,3));
		tl.add("O", new Location(4,8));
		tl.add("U", new Location(8,4));
		tl.add("V", new Location(8,2));
		tl.add("Y", new Location(2,2));
		tl.add("S", new Location(6,1));
		tl.add("B", new Location(6,3));
		List<Pair<Location, List<String>>> l=tl.getAll();
		printListOfPairs(l);
		
		Pair<List<Pair<Location, List<String>>>, Integer> inR;
		
		System.out.println("------------------------");
		inR=tl.inRange(new Location(4, 7), new Location(4,7));
		l=inR.first;
		System.out.println("Number of comparisons="+inR.second);
		printListOfPairs(l);
		
		System.out.println("------------------------");
		inR=tl.inRange(new Location(0, 0), new Location(9,9));
		l=inR.first;
		System.out.println("Number of comparisons="+inR.second);
		printListOfPairs(l);
		
		System.out.println("------------------------");
		inR=tl.inRange(new Location(0, 2), new Location(9,4));
		l=inR.first;
		System.out.println("Number of comparisons="+inR.second);
		printListOfPairs(l);
		
		System.out.println("------------------------");
		inR=tl.inRange(new Location(2, 5), new Location(6,9));
		l=inR.first;
		System.out.println("Number of comparisons="+inR.second);
		printListOfPairs(l);
		
		tl.add("R", new Location(2, 5));
		tl.add("A", new Location(1, 5)); 
		
		
		System.out.println("------------------------");
		inR=tl.inRange(new Location(2, 5), new Location(6,9));
		l=inR.first;
		System.out.println("Number of comparisons="+inR.second);
		printListOfPairs(l);
		
		System.out.println("------------------------");
		inR=tl.inRange(new Location(5, 0), new Location(8,2));
		l=inR.first;
		System.out.println("Number of comparisons="+inR.second);
		printListOfPairs(l);
		
		System.out.println("------------------------");
		inR=tl.inRange(new Location(2, 5), new Location(6,9));
		l=inR.first;
		System.out.println("Number of comparisons="+inR.second);
		printListOfPairs(l);
		
		System.out.println("------------------------");
		inR=tl.inRange(new Location(2,2), new Location(8,4));
		l=inR.first;
		System.out.println("Number of comparisons="+inR.second);
		printListOfPairs(l);
		
		System.out.println("------------------------");
		inR=tl.inRange(null,null);
		l=inR.first;
		System.out.println("Number of comparisons="+inR.second);
		printListOfPairs(l);
		
		System.out.println("------------------------");
		inR=tl.inRange(new Location(0,0), new Location(1,1));
		l=inR.first;
		System.out.println("Number of comparisons="+inR.second);
		printListOfPairs(l);
		
		
	}

	
	
	public static void testTL_All_except_inRange() {
		TreeLocator<String> tl1=new TreeLocator<String>();
		int num;
		num=tl1.add("F", new Location(4,7));
		System.out.println("F="+num);
		
		tl1.add("V", new Location(5,7));
		num=tl1.add("K", new Location(6,1));
		System.out.println("K="+num);
		
		tl1.add("D", new Location(4,3));
		tl1.add("O", new Location(4,8));
		
		num=tl1.add("U", new Location(8,4));
		System.out.println("U="+num);
		
		tl1.add("V", new Location(8,2));
		tl1.add("Y", new Location(2,2));
		num=tl1.add("S", new Location(6,1));
		System.out.println("S="+num);
		num=tl1.add("B", new Location(6,3));
		System.out.println("B="+num);
		
		num=tl1.add("R", new Location(7,6));
		System.out.println("R="+num);
		
		num=tl1.add(null,null);
		System.out.println("null="+num);
		
		num=tl1.add("A", new Location(6,1));
		System.out.println("A="+num);
		
		tl1.print();

		Pair<List<String>, Integer> pa=tl1.get( new Location(6,1));
		System.out.print(pa.second+" :(6.1)->");
		print(pa.first);

		pa=tl1.get( new Location(0,0));
		System.out.print(pa.second+" :(0.0)->");//comparison=2
		print(pa.first);
		
		pa=tl1.get( new Location(7,6));
		System.out.print(pa.second+" :(7.6)->");//comparison=4
		print(pa.first);
		tl1.add("A", new Location(7,6));
		pa=tl1.get( new Location(7,6));
		System.out.print(pa.second+" :(7.6)->");//comparison=4
		print(pa.first);
		pa=tl1.get( new Location(7,5));
		System.out.print(pa.second+" :(7.5)->");//comparison=4
		print(pa.first);
		pa=tl1.get( new Location(4,7));
		System.out.print(pa.second+" :(4.7)->");//comparison=1
		print(pa.first);
		
		tl1.add("U",new Location(8 ,4));
		tl1.add("N",new Location(8 ,4));
		tl1.add("U",new Location(8 ,4));
		tl1.add("U",new Location(8 ,4));
		System.out.println("-----testing getAll------");
		List<Pair<Location, List<String>>> l=tl1.getAll();
		printListOfPairs(l);
		
		System.out.println("-----testing remove-----");
		Pair<Boolean,Integer> pa2;
		pa2=tl1.remove("U",new Location(8 ,4));
		System.out.println(pa2.first+", #comparisons= "+pa2.second);
		
		l=tl1.getAll();
		printListOfPairs(l);
		
		
		pa2=tl1.remove("O",new Location(8 ,4));//false , 3
		System.out.println(pa2.first+", #comparisons= "+pa2.second);
		
		pa2=tl1.remove("O",new Location(0 ,0));//false , 2
		System.out.println(pa2.first+", #comparisons= "+pa2.second);
		
		pa2=tl1.remove(null,new Location(8 ,4));//false , 0
		System.out.println(pa2.first+", #comparisons= "+pa2.second);
		
		pa2=tl1.remove("O",null);//false , 0
		System.out.println(pa2.first+", #comparisons= "+pa2.second);
		
		pa2=tl1.remove("V",new Location(8 ,2));
		System.out.println(pa2.first+", #comparisons= "+pa2.second);
		
		System.out.println("------testing 'reomve for empty loc'------");
		pa2=tl1.remove("V",new Location(8 ,2));
		System.out.println(pa2.first+", #comparisons= "+pa2.second);
		System.out.println("-----------------------");
		tl1.remove("D", new Location(4,3));
		printListOfPairs(tl1.getAll());
		System.out.println("-----------testing add on exist loc and see the #of comp------------");
		
		num=tl1.add("D",new Location(4,3));
		System.out.println("# of coparesions="+num);
	
	
	}
	
	public static <T> void printListOfPairs(List<Pair<Location, List<T>>> l) {
		if(l.empty()) {
			System.out.println("[empty]");
			return;
			}
		l.findFirst();
		while (!l.last()) {
			System.out.print(l.retrieve().first + "-->");
			List<T> data = l.retrieve().second;
			if (!data.empty()) {
				data.findFirst();
				System.out.print("[");
				while (!data.last()) {
					System.out.print(data.retrieve() + ", ");
					data.findNext();
				}
				System.out.println(data.retrieve() + "]");
			} else
				System.out.println("[empty]");
			l.findNext();
		}
		System.out.print(l.retrieve().first + "-->");
		List<T> data = l.retrieve().second;
		if (!data.empty()) {
			data.findFirst();
			System.out.print("[");
			while (!data.last()) {
				System.out.print(data.retrieve() + ", ");
				data.findNext();
			}
			System.out.println(data.retrieve() + "]");
		} else
			System.out.println("[empty]");
		
	}

	public static void testBST() {
		BST<Integer,String> bst1=new BST<Integer,String>();
		Pair pa;
		pa=bst1.insert(50, "Rakan");
		System.out.println(pa.first);//true
		System.out.println(pa.second);//0	
		bst1.print();
		
		pa=bst1.insert(null,null);
		System.out.println(pa.first);//false
		System.out.println(pa.second);//0	
		bst1.print();
		
		
		pa=bst1.insert(50, "Rakan");
		System.out.println(pa.first);//false
		System.out.println(pa.second);//1
		bst1.print();
		
		pa=bst1.insert(30, "Khaled");
		System.out.println(pa.first);//true
		System.out.println(pa.second);//2
		bst1.print();
		
		pa=bst1.insert(40, "Omer");
		System.out.println(pa.first);//true
		System.out.println(pa.second);//3
		bst1.print();
		
		pa=bst1.insert(70, "Naif");
		System.out.println(pa.first);//true
		System.out.println(pa.second);//2
		bst1.print();
		
		pa=bst1.insert(60, "Ali");
		System.out.println(pa.first);//true
		System.out.println(pa.second);//3
		bst1.print();
		
		pa=bst1.insert(60, "Ali");
		System.out.println(pa.first);//false
		System.out.println(pa.second);//3
		bst1.print();
		
		System.out.println("current on "+"'"+bst1.retrieve()+"'");//Ali
		bst1.find(40);
		System.out.println("current on "+"'"+bst1.retrieve()+"'");//Omer
		
		bst1.find(50);
		System.out.println("current on "+"'"+bst1.retrieve()+"'");//Rakan
		pa=bst1.insert(60, "Ali");
		System.out.println(pa.first);//false
		System.out.println(pa.second);//3
		bst1.print();
		System.out.println("current on "+"'"+bst1.retrieve()+"'");//Rakan
		
		bst1.find(60);
		bst1.update("Faisal");//update Ail to Fasial
		bst1.print();	
		
		pa=bst1.find(0);//current unchanged 
		pa=bst1.find(null);//current unchanged 
		System.out.println("current on "+"'"+bst1.retrieve()+"'");//Fasial
		System.out.println(pa.first);//false
		System.out.println(pa.second);//2
		
		bst1.insert(100, "Rayan");
		bst1.insert(90, "Turki");
		bst1.insert(120, "Fahad");
		bst1.insert(95, "Majed");
		
		System.out.println("-----test remove-----");
		pa=bst1.remove(70);//removing Naif
		System.out.println("current on "+"'"+bst1.retrieve()+"'");
		System.out.println(pa.first);//true
		System.out.println(pa.second);//4
		bst1.print();
		
		pa=bst1.remove(95);//removing Majed
		System.out.println("current on "+"'"+bst1.retrieve()+"'");
		System.out.println(pa.first);//true
		System.out.println(pa.second);//4
		bst1.print();
		
		pa=bst1.remove(120);//removing Fahed
		System.out.println("current on "+"'"+bst1.retrieve()+"'");
		System.out.println(pa.first);//true
		System.out.println(pa.second);//4
		bst1.print();
		
		pa=bst1.remove(120);//not there
		System.out.println("current on "+"'"+bst1.retrieve()+"'");
		System.out.println(pa.first);//false
		System.out.println(pa.second);//3
		bst1.print();
		
		pa=bst1.remove(null);//null case
		System.out.println("current on "+"'"+bst1.retrieve()+"'");
		System.out.println(pa.first);//false
		System.out.println(pa.second);//0
		bst1.print();
		
		List<Integer>l=bst1.getAll();
		print(l);
}	
	
	/////////////////////remove this part///////////////////////////////
	
	
	private static void testVHM() {
		VehicleHiringManager vhm = new VehicleHiringManager();
		vhm.addVehicle("F", new Location(4, 7));
		vhm.addVehicle("V", new Location(5, 7));
		vhm.addVehicle("K", new Location(6, 1));
		vhm.addVehicle("D", new Location(4, 3));
		vhm.addVehicle("O", new Location(4, 8));
		vhm.addVehicle("U", new Location(8, 4));
		vhm.addVehicle("V", new Location(8, 2));
		vhm.addVehicle("Y", new Location(2, 2));
		vhm.addVehicle("S", new Location(6, 1));
		vhm.addVehicle("B", new Location(6, 3));

		System.out.println("----------------------");
		System.out.println(vhm.getVehicleLoc("E"));
		System.out.println(vhm.getVehicleLoc("S"));
		System.out.println(vhm.getVehicleLoc("F"));

		System.out.println("----------------------");
		print(vhm.getVehiclesInRange(new Location(5, 5), 2));
		print(vhm.getVehiclesInRange(new Location(3, 3), 1));
		print(vhm.getVehiclesInRange(new Location(0, 0), 10));

		System.out.println("----------------------");
		Map<String, Location> map = vhm.getLocatorMap().getMap();
		System.out.println(map.find("U"));
		System.out.println(map.find("K"));
		System.out.println(map.find("X"));

		System.out.println("----------------------");
		Locator<String> locator = vhm.getLocatorMap().getLocator();
		System.out.println(locator.get(new Location(3, 5)).second);
		System.out.println(locator.get(new Location(2, 6)).second);
		System.out.println(locator.get(new Location(8, 3)).second);
		System.out.println(locator.get(new Location(5, 1)).second);
		System.out.println(locator.get(new Location(4, 2)).second);
		System.out.println(locator.get(new Location(0, 0)).second);
		System.out.println(locator.inRange(new Location(2, 2), new Location(8, 8)).second);
		System.out.println(locator.inRange(new Location(4, 4), new Location(6, 6)).second);
		System.out.println(locator.inRange(new Location(0, 0), new Location(2, 3)).second);
	}

	private static void testLocator() {
		TreeLocator<String> locator = new TreeLocator<String>();
		locator.add("F", new Location(4, 7));
		locator.add("V", new Location(5, 7));
		locator.add("K", new Location(6, 1));
		locator.add("D", new Location(4, 3));
		locator.add("O", new Location(4, 8));
		locator.add("U", new Location(8, 4));
		locator.add("V", new Location(8, 2));
		locator.add("Y", new Location(2, 2));
		locator.add("S", new Location(6, 1));
		locator.add("B", new Location(6, 3));

		System.out.println(locator.get(new Location(3, 5)).second);
		System.out.println(locator.get(new Location(2, 6)).second);
		System.out.println(locator.get(new Location(8, 3)).second);
		System.out.println(locator.get(new Location(5, 1)).second);
		System.out.println(locator.get(new Location(4, 2)).second);
		System.out.println(locator.get(new Location(0, 0)).second);
		System.out.println(locator.inRange(new Location(2, 2), new Location(8, 8)).second);
		System.out.println(locator.inRange(new Location(4, 4), new Location(6, 6)).second);
		System.out.println(locator.inRange(new Location(0, 0), new Location(2, 3)).second);
	}

	private static <T> void print(List<T> l) {
		if (l == null) {
			System.out.println("[null]");
			return;
		}
		if (l.empty()) {
			System.out.println("[empty]");
			return;
		}
		l.findFirst();
		while (!l.last()) {
			System.out.print(l.retrieve() + ", ");
			l.findNext();
		}
		System.out.println(l.retrieve());
	}

}
