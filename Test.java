import java.util.Comparator;

/**
 * This is a test class for the TreeMap implementation. It demonstrates the usage
 * of TreeMap with both natural ordering and a custom comparator.
 */
public class Test {
    /**
     * The main method to test the TreeMap functionality.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("\nTreeMap with natural ordering");
        TreeMap<String, Integer> tree = new TreeMap<>();
        tree.add("Monday", 65);
        tree.add("Tuesday", 71);
        tree.add("Wednesday", 68);
        tree.add("Thursday", 66);
        tree.add("Friday", 64);
        tree.add("Saturday", 70);
        tree.add("Sunday", 75);
        System.out.print("\tInorder Traversal:\n\t");
        tree.inorder();
        System.out.println();

        System.out.println("\tTree contains \"Thursday\"? " + tree.contains("Thursday"));
        System.out.println("\tTree contains \"March\"? " + tree.contains("March"));

        System.out.println("\t\"Thursday\" removed? " + tree.remove("Thursday"));
        System.out.println("\t\"March\" removed? " + tree.remove("March"));

        System.out.println("\tFirst: " + tree.first());
        System.out.println("\tLast : " + tree.last());

        System.out.println("\tCeiling(\"Monday\") : " + tree.ceiling("Monday"));
        System.out.println("\tCeiling(\"October\") : " + tree.ceiling("October"));

        System.out.println("\tfloor(\"Thursday\") : " + tree.floor("Thursday"));
        System.out.println("\tfloor(\"October\") : " + tree.floor("October"));

        System.out.println("\nTreeMap with a comparator");
        tree = new TreeMap<>(Comparator.comparing(String::toString));
        tree.add("Monday", 65);
        tree.add("Tuesday", 71);
        tree.add("Wednesday", 68);
        tree.add("Thursday", 66);
        tree.add("Friday", 64);
        tree.add("Saturday", 70);
        tree.add("Sunday", 75);
        System.out.print("\tInorder Traversal:\n\t");
        tree.inorder();
        System.out.println();

        System.out.println("\tTree contains \"Thursday\"? " + tree.contains("Thursday"));
        System.out.println("\tTree contains \"March\"? " + tree.contains("March"));

        System.out.println("\t\"Thursday\" removed? " + tree.remove("Thursday"));
        System.out.println("\t\"March\" removed? " + tree.remove("March"));

        System.out.println("\tFirst: " + tree.first());
        System.out.println("\tLast : " + tree.last());

        System.out.println("\tCeiling(\"Monday\") : " + tree.ceiling("Monday"));
        System.out.println("\tCeiling(\"October\") : " + tree.ceiling("October"));

        System.out.println("\tfloor(\"Thursday\") : " + tree.floor("Thursday"));
        System.out.println("\tfloor(\"October\") : " + tree.floor("October"));
    }
}
