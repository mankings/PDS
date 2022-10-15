import java.util.Iterator;
import java.util.ListIterator;

public class ex1 {
    public static void main(String[] args) {
        VectorGeneric<Integer> vector1 = new VectorGeneric<>();
        VectorGeneric<Character> vector2 = new VectorGeneric<>();

        for (int i = 0; i < 5; i++) vector1.addElem(i);
        for (int i = 0; i < 5; i++) vector2.addElem ((char) ('a' + i));

        System.out.println("---------Regular iterator test---------\n");
        System.out.println("Integers:");
        Iterator<Integer> iterator1 = vector1.Iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
        System.out.println("\nCharacters:");
        Iterator<Character> iterator2 = vector2.Iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }

        //List Iterator
        System.out.println("---------List iterator test---------\n");
        System.out.println("Integers:");
        ListIterator<Integer> listIter = vector1.listIterator();
        System.out.println("Using hasNext():");
        while (listIter.hasNext()) {
            System.out.printf("Index %d: %d\n",listIter.nextIndex(), listIter.next());
        }
        System.out.println("Using hasPrevious():");
        while (listIter.hasPrevious()) {
            System.out.printf("Index %d: %d\n",listIter.previousIndex()+1, listIter.previous());
        }
        System.out.println("(Doesn't print the last index due to how we handle them while printing)");

        System.out.println("\nCharacters:");
        ListIterator<Character> listIter2 = vector2.listIterator();
        System.out.println("Using hasNext():");
        while (listIter2.hasNext()) {
            System.out.printf("Index %d: %c\n",listIter2.nextIndex(), listIter2.next());
        }
        System.out.println("Using hasPrevious():");
        while (listIter2.hasPrevious()) {
            System.out.printf("Index %d: %c\n",listIter2.previousIndex()+1, listIter2.previous());
        }
        System.out.println("(Doesn't print the last index due to how we handle them while printing)");
    }
}