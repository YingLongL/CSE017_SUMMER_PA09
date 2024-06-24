/**
 * Generic Class to implement a Binary Search Tree
 * @param <E> the type of elements maintained by this tree
 * @author Yinglong Lin
 * @version Java 11 / VSCode
 * @since 2024-6-24 (date of last revision)
 */
public class BST<E extends Comparable<E>> {
    // Data members
    private TreeNode root;
    private int size;

    /**
     * Inner class TreeNode
     */
    private class TreeNode {
        E value;
        TreeNode left;
        TreeNode right;

        /**
         * Constructor for TreeNode
         * @param val the value to be stored in the node
         */
        TreeNode(E val) {
            value = val;
            left = right = null;
        }
    }

    /**
     * Default Constructor
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Method size
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method isEmpty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Method to clear the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Search method
     * @param value being searched
     * @return true if value is found in the tree, false otherwise
     */
    public boolean contains(E value) {
        if (root == null) {
            return false;
        }
        TreeNode node = root;
        while (node != null) {
            if (value.compareTo(node.value) < 0)
                node = node.left; // go left
            else if (value.compareTo(node.value) > 0)
                node = node.right; // go right
            else
                return true;
        }
        return false;
    }

    /**
     * Method add to add a new node to the tree
     * @param value to be added to the tree
     * @return true if value is not found in the tree and a new node added, false if value already exists in the tree
     */
    public boolean add(E value) {
        if (root == null)
            root = new TreeNode(value);
        else {
            TreeNode parent, node;
            parent = null;
            node = root;
            while (node != null) {
                parent = node;
                if (value.compareTo(node.value) < 0) {
                    node = node.left;
                } else if (value.compareTo(node.value) > 0) {
                    node = node.right;
                } else
                    return false; // duplicates are not allowed
            }
            if (value.compareTo(parent.value) < 0)
                parent.left = new TreeNode(value);
            else
                parent.right = new TreeNode(value);
        }
        size++;
        return true;
    }

    /**
     * Method remove to remove a value from the tree
     * @param value to be removed if found in the tree
     * @return true if value was found and removed, false if value was not found
     */
    public boolean remove(E value) {
        TreeNode parent, node;
        parent = null;
        node = root;
        // Find value first
        while (node != null) {
            if (value.compareTo(node.value) < 0) {
                parent = node;
                node = node.left;
            } else if (value.compareTo(node.value) > 0) {
                parent = node;
                node = node.right;
            } else
                break;
        }
        if (node == null) // value not in the tree
            return false;
        // Case 1: node has no children
        if (node.left == null && node.right == null) {
            if (parent == null) {
                root = null;
                size = 0;
            } else if (parent.left == node)
                parent.left = null;
            else
                parent.right = null;
        } else if (node.left == null) {
            // case 2: node has one right child
            if (parent == null)
                root = node.right;
            else if (parent.left == node)
                parent.left = node.right;
            else
                parent.right = node.right;
        } else if (node.right == null) {
            // case 2: node has one left child
            if (parent == null)
                root = node.left;
            else if (parent.left == node)
                parent.left = node.left;
            else
                parent.right = node.left;
        } else {
            // case 3: node has two children
            TreeNode rightMostParent = node;
            TreeNode rightMost = node.left;
            while (rightMost.right != null) {
                rightMostParent = rightMost;
                rightMost = rightMost.right;
            }
            node.value = rightMost.value;
            if (rightMostParent.left == rightMost)
                rightMostParent.left = rightMost.left;
            else
                rightMostParent.right = rightMost.left;
        }
        size--;
        return true;
    }

    /**
     * Inorder Traversal Method
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder Traversal Recursive Helper Method
     * @param node where the recursive method starts
     */
    private void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }

    /**
     * Preorder Traversal Method
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder Traversal Recursive Helper Method
     * @param node where the recursive method starts
     */
    private void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    /**
     * Postorder Traversal Method
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder Traversal Recursive Helper Method
     * @param node where the recursive method starts
     */
    private void postorder(TreeNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }
}
