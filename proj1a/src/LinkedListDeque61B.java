import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private IntNode sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    @Override
    public void addFirst(T x) {
        IntNode p = new IntNode(sentinel, x, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size++;
    }

    @Override
    public void addLast(T x) {
        IntNode p = new IntNode(sentinel.prev, x, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        IntNode p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
            returnList.add(p.item);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next.equals(sentinel);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        IntNode firstNode = sentinel.next;
        T item = firstNode.item;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        firstNode.next = null;
        firstNode.prev = null;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        IntNode lastNode = sentinel.prev;
        T item = lastNode.item;
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;
        lastNode.next = null;
        lastNode.prev = null;
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        IntNode p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(IntNode p, int index){
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next, index - 1);
    }
}
