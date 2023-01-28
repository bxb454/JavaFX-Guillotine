import java.util.NoSuchElementException;

/**
 * This is the updated LinkedList class with added methods for the Guillotine project.
 * @author Boris Brondz
 */
public class LinkedList<T> implements Iterable<T> {
    /** the first node of the list, or null if the list is empty */
    private LLNode<T> firstNode;

    /**
     * Creates an initially empty linked list
     */
    public LinkedList() {
        firstNode = null;
    }

    /**
     * Returns the first node.
     */
    protected LLNode<T> getFirstNode() {
        return firstNode;
    }
    /**
     * Changes the front node.
     * @param node  the node that will be the first node of the new linked list
     */
    protected void setFirstNode(LLNode<T> node) {
        this.firstNode = node;
    }
    /**
     * Return whether the list is empty
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return (getFirstNode() == null);
    }

    /**
     * Add an element to the front of the linked list
     * @param element the element to be added to the front of the linked list
     */
    public void addToFront(T element) {
        setFirstNode(new LLNode<T>(element, getFirstNode()));
    }

    /**
     * Removes and returns the element at the front of the linked list
     * @return the element removed from the front of the linked list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            T save = getFirstNode().getElement();
            setFirstNode(getFirstNode().getNext());
            return save;
        }
    }

    /**
     * This method reverses the LinkedList elements (their order).
     */
    public void reverseList() {

        if(isEmpty()) throw new NoSuchElementException();
        LLNode<T> nodeptr = getFirstNode();
        while(nodeptr.getNext() != null) {
            addToFront(nodeptr.getNext().getElement());
            nodeptr.setNext(nodeptr.getNext().getNext());
        }

    }

    /**
     * This method moves the first element of the LinkedList to the very end.
     */
    public void moveFirstToLast() {
        addToEnd(this.getFirstNode().getElement());
        removeFromFront();
    }

    /**
     * This method moves the last element of the LinkedList to the very front.
     */
    public void moveLastToFirst() {
        LLNode<T> nodeptr = getFirstNode();
        while (nodeptr.getNext() != null) {
            nodeptr = nodeptr.getNext();
        }
        addToFront(nodeptr.getElement());
    }

    /**
     * This method moves an element from the front of a LinkedList back a certain amount of places.
     * @param n amount of elements to move the first element back.
     */
    public void moveBack(int n) {
    if(n > this.length() - 1 || n <= 0) throw new UnsupportedOperationException();
    int index = 0;
    T save = this.getFirstNode().getElement();
    LLNode<T> nodeptr = this.getFirstNode();
    while(index < n) {
        nodeptr = nodeptr.getNext();
        index++;
    }
    nodeptr.insertAfter(save);
    removeFromFront();

    }

    /**
     * This method reverses the first k elements of the LinkedList (reverse their order).
     * @param k how many elements to reverse.
     */
    public void reverseFirstK(int k) {
    if(k > this.length()) return;

    LLNode<T> nodeptr = getFirstNode();

    for(int i = 1; i<k; i++) {
        addToFront(nodeptr.getNext().getElement());
        nodeptr.setNext(nodeptr.getNext().getNext());
    }
    }

    /**
     * Returns the length of the linked list.
     * @return the number of nodes in the list.
     */
    public int length() {
        int count = 0;
        LLNode<T> nodeptr = getFirstNode();
        while (nodeptr != null) {
            count++;
            nodeptr = nodeptr.getNext();
        }
        return count;
    }

    /**
     * Adds an element to the end of the linked list
     * @param element the element to insert at the end of the linked list.
     */
    public void addToEnd(T element) {
        if (isEmpty())
            addToFront(element);
        else {
            LLNode<T> nodeptr = getFirstNode();
            while (nodeptr.getNext() != null)
                nodeptr = nodeptr.getNext();
            nodeptr.setNext(new LLNode<T>(element, null));
        }
    }

    /**
     * Return an iterator for this list.
     * @return the iterator for the list.
     */
    @Override
    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<T>(getFirstNode());
    }


    /**
     * Prints the contents of a list to System.out.
     * @param list the list to print
     */
    public static <S> void printList(LinkedList<S> list) {
        for (S element : list) {
            System.out.print(element);
            System.out.print(" ");
         }
        System.out.println();
     }
    }

