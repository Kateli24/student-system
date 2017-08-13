package dao;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * A collection class that manages one-to-many data. It has a similar interface
 * to java.util.Map.
 *
 * @author Mark George <mark.george@otago.ac.nz>
 * @license FreeBSD
 *
 * @param <Key> The key.
 * @param <Value> The value.
 */
public class MultiMap<Key, Value extends Comparable<Value>> {

	private final Map<Key, Set<Value>> map = new TreeMap<>();

	/**
	 * Adds a value to the collection.
	 *
	 * @param key The key that the value is associated with.
	 * @param value The value.
	 */
	public void put(Key key, Value value) {

		if (map.containsKey(key)) {
			// key already exists so get the list and add value to it
			map.get(key).add(value);
		} else {
			// key doesn't exist yet so create set
			Set<Value> set = new TreeSet<>();

			// add the value to the list
			set.add(value);

			// and put set into the map
			map.put(key, set);
		}
	}

	/**
	 * Returns a list of values for a given key.
	 *
	 * @param key The key.
	 * @return A list of values that are associated with that key.
	 */
	public Collection<Value> get(Key key) {
		return map.get(key);
	}

	/**
	 * Returns true if the map contains the given key.
	 *
	 * @param key
	 * @return
	 */
	public boolean containsKey(Key key) {
		return map.containsKey(key);
	}

	/**
	 * Returns the keys.
	 *
	 * @return
	 */
	public Set<Key> keySet() {
		return map.keySet();
	}

	/**
	 * Removes a value from a key.
	 *
	 * @param key
	 * @param value
	 */
	public void remove(Key key, Value value) {
		map.get(key).remove(value);
	}

	/**
	 * Find the number of keys in the map.
	 *
	 * @return the number of keys
	 */
	public int size() {
		return map.size();
	}

	/**
	 * Find the number of elements in a given key.
	 *
	 * @param key the key to check the size of
	 * @return the number of keys
	 */
	public int sizeOfKey(Key key) {
		return map.get(key).size();
	}

}
