/** Project 1A: Data Structures
 *ArrayDeque implementation
 */
public class ArrayDeque<T> {
    private T[] items;
    private int first;
    private int nextFirst;
    private int nextLast;
    private int size;
    private final int finalLength = 8;
    //Create an empty list
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = (items.length - size) / 2;
        nextLast = nextFirst + 1;
        first = nextFirst;
    }

    //Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (sizeIsLength()) {
            resize(size * 2);
        }
        first = nextFirst;
        items[nextFirst] = item;
        nextFirst = subtractOne(nextFirst);
        size += 1;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if (sizeIsLength()) {
            resize(size * 2);
        }
        items[nextLast] = item;
        if (items[first] == null) {
            first = nextLast;
        }
        nextLast = addOne(nextLast);
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
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = items[first];
        items[first] = null;
        first = addOne(first);
        nextFirst = addOne(nextFirst);
        size -= 1;
        checkResize();
        return item;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = subtractOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        checkResize();
        return item;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null.
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        } else if (first + index < items.length) {
            return items[first + index];
        } else {
            return items[first + index - items.length];
        }
    }

    //Resizes the underlying array to the target capacity.
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int tempLength = items.length - first;
        System.arraycopy(items, first, a, (a.length / 2) - 1, tempLength);
        if (tempLength != items.length) {
            System.arraycopy(items, 0, a, ((a.length / 2) - 1) + tempLength, first);
        }
        items = a;
        nextFirst = subtractOne((a.length / 2) - 1);
        first = addOne(nextFirst);
        nextLast = addOne(nextFirst + size);
    }

    //Resizes the underlying array to down to the target capacity
    private void resizeDown(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (capacity < finalLength) {
            return;
        }
        if (first + size > items.length) {
            int left = (first + size) - items.length;
            int tempLength = items.length - first;
            System.arraycopy(items, first, a, (a.length / 2) - 1, tempLength);
            System.arraycopy(items, nextLast - 1, a, ((a.length / 2) - 1) + tempLength, left);
        } else {
            System.arraycopy(items, first, a, (a.length / 2) - 1, size);
        }
        items = a;
        nextFirst = subtractOne((a.length / 2) - 1);
        first = addOne(nextFirst);
        nextLast = addOne(nextFirst + size);
    }

    //Checks whether the array is full and needs to be resized.
    private boolean sizeIsLength() {
        return size == items.length;
    }

    private void checkResize() {
        if ((double) size / items.length < 0.25) {
            resizeDown(items.length / 2);
        }
    }

    //Subtracts one from a given x. Used to clean up code.
    private int subtractOne(int x) {
        if (x == 0) {
            return items.length - 1;
        }
        return x - 1;
    }

    //Adds one to a given x. Used go clean up code.
    private int addOne(int x) {
        if (x == items.length - 1) {
            return 0;
        }
        return x + 1;
    }

    //Creates a deep copy of other
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[8];
        nextFirst = (items.length - size) / 2;
        nextLast = nextFirst + 1;
        first = nextFirst;
        for (int i = 0; i < other.size(); i += 1) {
            addLast((T) other.get(i));
        }
    }
}


