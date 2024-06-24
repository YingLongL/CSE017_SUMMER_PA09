import java.util.Comparator;

/**
 * A TreeMap implementation that uses a binary search tree where the nodes have
 * a value of type MapEntry. The tree map is sorted according to the natural
 * ordering of its keys, or by a Comparator provided at map creation time.
 * 
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class TreeMap<K extends Comparable<K>, V> {
    private TreeNode root;
    private int size;
    private Comparator<K> comp;

    /**
     * Inner class representing a node in the TreeMap.
     */
    private class TreeNode {
        MapEntry<K, V> entry;
        TreeNode left;
        TreeNode right;

        /**
         * Constructs a TreeNode with the given key and value.
         * 
         * @param key the key associated with this node
         * @param value the value associated with this node
         */
        TreeNode(K key, V value) {
            this.entry = new MapEntry<>(key, value);
        }
    }

    /**
     * Constructs an empty TreeMap with natural ordering of keys.
     */
    public TreeMap() {
        this(null);
    }

    /**
     * Constructs an empty TreeMap with the specified comparator.
     * 
     * @param comp the comparator that will be used to order this map
     */
    public TreeMap(Comparator<K> comp) {
        this.comp = comp;
        this.root = null;
        this.size = 0;
    }

    /**
     * Compares two keys using the comparator if provided, or natural ordering
     * otherwise.
     * 
     * @param k1 the first key to compare
     * @param k2 the second key to compare
     * @return a negative integer, zero, or a positive integer as the first key is
     *         less than, equal to, or greater than the second key
     */
    private int compare(K k1, K k2) {
        return (comp == null) ? k1.compareTo(k2) : comp.compare(k1, k2);
    }

    /**
     * Returns the number of key-value mappings in this map.
     * 
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     * 
     * @return true if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Removes all of the mappings from this map. The map will be empty after this
     * call returns.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * 
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    public boolean contains(K key) {
        TreeNode node = root;
        while (node != null) {
            int cmp = compare(key, node.entry.getKey());
            if (cmp < 0)
                node = node.left;
            else if (cmp > 0)
                node = node.right;
            else
                return true;
        }
        return false;
    }

    /**
     * Associates the specified value with the specified key in this map. If the map
     * previously contained a mapping for the key, the old value is replaced.
     * 
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return true if the key was not already present in the map
     */
    public boolean add(K key, V value) {
        if (root == null) {
            root = new TreeNode(key, value);
            size++;
            return true;
        }
        TreeNode parent = null;
        TreeNode node = root;
        while (node != null) {
            parent = node;
            int cmp = compare(key, node.entry.getKey());
            if (cmp < 0)
                node = node.left;
            else if (cmp > 0)
                node = node.right;
            else {
                node.entry.setValue(value); // Update value if key already exists
                return false;
            }
        }
        TreeNode newNode = new TreeNode(key, value);
        int cmp = compare(key, parent.entry.getKey());
        if (cmp < 0)
            parent.left = newNode;
        else
            parent.right = newNode;
        size++;
        return true;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * 
     * @param key the key whose mapping is to be removed from the map
     * @return true if the key was found and removed, false if the key was not found
     */
    public boolean remove(K key) {
        TreeNode parent = null;
        TreeNode node = root;
        while (node != null && compare(key, node.entry.getKey()) != 0) {
            parent = node;
            node = compare(key, node.entry.getKey()) < 0 ? node.left : node.right;
        }
        if (node == null)
            return false;
        if (node.left == null && node.right == null) {
            if (parent == null)
                root = null;
            else if (parent.left == node)
                parent.left = null;
            else
                parent.right = null;
        } else if (node.left == null) {
            if (parent == null)
                root = node.right;
            else if (parent.left == node)
                parent.left = node.right;
            else
                parent.right = node.right;
        } else if (node.right == null) {
            if (parent == null)
                root = node.left;
            else if (parent.left == node)
                parent.left = node.left;
            else
                parent.right = node.left;
        } else {
            TreeNode successorParent = node;
            TreeNode successor = node.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            node.entry = successor.entry;
            if (successorParent.left == successor)
                successorParent.left = successor.right;
            else
                successorParent.right = successor.right;
        }
        size--;
        return true;
    }

    /**
     * Returns the first (lowest) key currently in this map.
     * 
     * @return the first (lowest) key currently in this map, or null if the map is
     *         empty
     */
    public MapEntry<K, V> first() {
        if (root == null)
            return null;
        TreeNode node = root;
        while (node.left != null)
            node = node.left;
        return node.entry;
    }

    /**
     * Returns the last (highest) key currently in this map.
     * 
     * @return the last (highest) key currently in this map, or null if the map is
     *         empty
     */
    public MapEntry<K, V> last() {
        if (root == null)
            return null;
        TreeNode node = root;
        while (node.right != null)
            node = node.right;
        return node.entry;
    }

    /**
     * Returns the least key greater than or equal to the given key, or null if there
     * is no such key.
     * 
     * @param key the key
     * @return the least key greater than or equal to the given key, or null if there
     *         is no such key
     */
    public MapEntry<K, V> ceiling(K key) {
        TreeNode node = root;
        TreeNode result = null;
        while (node != null) {
            int cmp = compare(key, node.entry.getKey());
            if (cmp == 0)
                return node.entry;
            if (cmp < 0) {
                result = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return result == null ? null : result.entry;
    }

    /**
     * Returns the greatest key less than or equal to the given key, or null if there
     * is no such key.
     * 
     * @param key the key
     * @return the greatest key less than or equal to the given key, or null if there
     *         is no such key
     */
    public MapEntry<K, V> floor(K key) {
        TreeNode node = root;
        TreeNode result = null;
        while (node != null) {
            int cmp = compare(key, node.entry.getKey());
            if (cmp == 0)
                return node.entry;
            if (cmp > 0) {
                result = node;
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return result == null ? null : result.entry;
    }

    /**
     * Performs an inorder traversal of the tree, printing each entry.
     */
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    /**
     * Helper method to perform an inorder traversal starting from a given node.
     * 
     * @param node the node to start the traversal from
     */
    private void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.entry + " ");
            inorder(node.right);
        }
    }

    /**
     * Performs a preorder traversal of the tree, printing each entry.
     */
    public void preorder() {
        preorder(root);
        System.out.println();
    }

    /**
     * Helper method to perform a preorder traversal starting from a given node.
     * 
     * @param node the node to start the traversal from
     */
    private void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.entry + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    /**
     * Performs a postorder traversal of the tree, printing each entry.
     */
    public void postorder() {
        postorder(root);
        System.out.println();
    }

    /**
     * Helper method to perform a postorder traversal starting from a given node.
     * 
     * @param node the node to start the traversal from
     */
    private void postorder(TreeNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.entry + " ");
        }
    }
}
