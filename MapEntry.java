/**
 * Class representing a key-value pair, used as an entry in the TreeMap.
 * @param <K> the type of keys maintained by this map entry
 * @param <V> the type of mapped values
 */
public class MapEntry<K, V> {
    private K key;
    private V value;

    /**
     * Constructs a new map entry with the specified key and value.
     * @param key the key associated with this entry
     * @param value the value associated with this entry
     */
    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the key associated with this map entry.
     * @return the key associated with this map entry
     */
    public K getKey() {
        return key;
    }

    /**
     * Returns the value associated with this map entry.
     * @return the value associated with this map entry
     */
    public V getValue() {
        return value;
    }

    /**
     * Replaces the value associated with this map entry with the specified value.
     * @param value the new value to be associated with this map entry
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Returns a string representation of this map entry. The string representation consists of the key and value separated by a comma and enclosed in parentheses.
     * @return a string representation of this map entry
     */
    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }
}
