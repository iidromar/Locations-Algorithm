class BSTNode<K extends Comparable<K>, T>{
	public K key;
	public T data;
	public BSTNode<K, T> left, right;
	public BSTNode(K key, T data) {
		this.key = key;
		this.data = data;
		left = right = null;
	}
}
public class BST<K extends Comparable<K>, T> implements Map<K, T>{
	private BSTNode<K, T> root , current;
	
	public BST() {
		current = root = null;
	}

	@Override
	public boolean empty() {
		return root == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public T retrieve() {
		return current.data;
	}

	@Override
	public void update(T e) {
		current.data = e;
	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		int counter =0;
		if(key == null) return new Pair<Boolean, Integer>(false, counter);
		BSTNode<K, T> st = root;
		while(st != null) {
			int tester = key.compareTo(st.key);
			counter++;
			if(tester == 0) {
				current = st;
				return new Pair<Boolean, Integer>(true, counter);
			}
			else if(tester>0)
				st= st.right;
			else
				st= st.left;
		}
		return new Pair<Boolean, Integer>(false, counter);
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		int counter = 0;
		if(key == null) return new Pair<Boolean, Integer>(false,counter);
		if(root == null) {
			current = root = new BSTNode<K, T>(key, data);
			return new Pair<Boolean, Integer>(true, counter);
		}
		BSTNode<K, T> p = null;
		BSTNode<K, T> q = root;
		
		while(q != null) {
			int tester = key.compareTo(q.key);
			counter++;
			if(tester == 0) {
				return new Pair<Boolean, Integer>(false, counter);
			}
			else {
			p =q;
			if(tester>0)
				q=q.right;
			else
				q=q.left;
			}
		}
		BSTNode<K, T> item = new BSTNode<K, T>(key, data);
		if(key.compareTo(p.key) > 0)
			p.right = item;
		else
			p.left = item;
		current = item;
		return new Pair<Boolean, Integer>(true, counter);
	}
	@Override
	public Pair<Boolean, Integer> remove(K key) {
		int counter = 0;
		Pair<Boolean, Integer> checked = new Pair<Boolean, Integer>(false,counter);
		if(key == null)return checked;
		current = root = recRemove(root, key, checked);
		return checked;
	}
	private BSTNode<K, T> recRemove(BSTNode<K, T> p, K key, Pair<Boolean, Integer> checked){
		BSTNode<K, T> q = null;
		if(p == null) return p;
		if(key.compareTo(p.key)<0) {
			checked.second++;
			p.left = recRemove(p.left, key, checked);
		}
		else if(key.compareTo(p.key)>0) {
			checked.second++;
			p.right = recRemove(p.right, key, checked);
		}
		else {
			checked.first = true;
			checked.second++;
			if(p.left != null && p.right != null) {
				q = minValue(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = recRemove(p.right,q.key, checked);
			}else {
			if(p.left == null)
				return p.right;
			else if(p.right == null)
				return p.left;
			}
		}
		return p;
	}
	private BSTNode<K, T> minValue(BSTNode<K, T> p){
		if(p == null) return null;
		while(p.left != null) {
			p = p.left;
		}
		return p;
	}

	@Override
	public List<K> getAll() {
		List<K> returnedList = new LinkedList<K>();
		priGetAll(root, returnedList);
		return returnedList;
	}
	private void priGetAll(BSTNode<K, T> searchable, List<K> addedList) {
		if(searchable == null)return;
		else {
			priGetAll(searchable.left, addedList);
			addedList.insert(searchable.key);
			priGetAll(searchable.right, addedList);
		}
	}
	
}
