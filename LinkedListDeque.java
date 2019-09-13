/** Project 1A: Data Structures
 *LinkListDeque implementation
 */
public class LinkedListDeque<T> {
    private class TNode {
        private T item;
        private TNode next;
        private TNode prev;

        TNode(T i, TNode n, TNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    // The first item (if it exists) is at sentinel.next.
    private TNode sentinel;
    private int size;
    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(null, new TNode(null, null, null), new TNode(null, null, null));
    }

    //Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (isEmpty()) {
            sentinel.next = new TNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new TNode(item, sentinel.next, sentinel);
            sentinel.next.next.prev = sentinel.next;
        }
        size += 1;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if (isEmpty()) {
            sentinel.next = new TNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev = new TNode(item, sentinel, sentinel.prev);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size += 1;
    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("null");
            return;
        }
        TNode t = sentinel;
        while (t.next.item != null) {
            System.out.print(t.next.item + " ");
            t = t.next;
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T amount = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return amount;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T amount = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return amount;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (isEmpty() || (size - 1) < index) {
            return null;
        }

        TNode t = sentinel;
        while (index >= 0) {
            index -= 1;
            t = t.next;
        }

        return t.item;
    }

    //Creates a deep copy of other
    public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinel = new TNode(null, new TNode(null, null, null), new TNode(null, null, null));
        for (int i = 0; i < other.size(); i += 1) {
            addLast((T) other.get(i));
        }
    }

    //Same as get, but uses recursion
    public T getRecursive(int index) {
        if (isEmpty() || (size - 1) < index) {
            return null;
        } else {
            return getRecursive(index, sentinel);
        }
    }

    private T getRecursive(int index, TNode deque) {
        if (index == 0) {
            return deque.next.item;
        } else {
            return getRecursive(index - 1, deque.next);
        }
    }
}



