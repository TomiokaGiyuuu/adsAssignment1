public class MyLinkedList<T extends Comparable<T>> implements MyList<T>{

    ///////////////////////////////////////
    private static class MyNode<T>{
        T data;
        MyNode<T> next, prev;

        MyNode(T data) {this.data = data;}
    }
    ///////////////////////////////////////

    private int length = 0;
    private MyNode<T> head, tail;

    @Override
    public void add(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    @Override
    public void add(T item, int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException("index should be positive and less than size");

        MyNode<T> newNode = new MyNode<>(item);
        MyNode<T> schitalka = head;

        for(int i = 0; i < index; i++){
            schitalka = schitalka.next;
        }
        schitalka.prev.next = newNode;
        newNode.next = schitalka;
        length++;
    }

    @Override
    public boolean remove(T item) {
        if(isEmpty())
            throw new IndexOutOfBoundsException("Empty");
        MyNode<T> schitalka = head;

        while(schitalka!=null){
            if(schitalka.data.equals(item)){
                delete(schitalka);
                return true;
            }
            schitalka=schitalka.next;
        }

        return false;
    }

    @Override
    public T remove(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException("index should be positive and less than size");
        MyNode<T> newNode = head;
        for(int i = 0; i < index; i++){
            newNode = newNode.next;
        }
        delete(newNode);
        return newNode.data;
    }

    public T removeLast() {
        MyNode<T> newNode = tail;
        delete(newNode);
        return newNode.data;
    }

    @Override
    public void clear() {
        head = null;
        length = 0;
    }

    @Override
    public T get(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException("index should be positive and less than size");

        MyNode<T> temp = head;

        while (index != 0) {
            temp = temp.next;
            index--;
        }

        return temp.data;
    }

    @Override
    public int indexOf(Object o) {
        MyNode<T> temp = head;

        int index = 0;
        while (index != length - 1) {
            if(temp.data == o){
                return index;
            }
            temp = temp.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        MyNode<T> temp = head;

        int index = length - 1;
        while (index != 0) {
            if(temp.data == o){
                return index;
            }
            temp = temp.next;
            index--;
        }
        return 0;
    }

    @Override
    public void sort() {
        for (int i = 0; i < length - 1; i++){
            for (int j = 0; j < length - i - 1; j++){
                if (get(j).compareTo(get(j + 1)) > 0) {
                    T temp = getTemp(j).data;
                    getTemp(j).data = getTemp(j + 1).data;
                    getTemp(j + 1).data = temp;
                }
            }
        }
    }

    public int size() {
        return length;
    }

    @Override
    public boolean contains(Object object) {
        MyNode<T> temp = head;

        int index = length - 1;
        while (index != 0) {
            if(temp.data == object){
                return true;
            }
            temp = temp.next;
            index--;
        }
        return false;
    }

    //Methods
    private void delete(MyNode<T> node){
        MyNode<T> nextNode = node.next;
        MyNode<T> prevNode = node.prev;
        if(prevNode == null) {
            head = nextNode;
            tail = nextNode;
        } else if(nextNode == null) {
            tail = node.prev;
        } else {
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }
        length--;
    }

    private MyNode<T> getTemp(int index) {
        if (index >= length || index < 0)
            throw new IndexOutOfBoundsException("index should be positive and less than size");

        MyNode<T> temp = head;

        while (index != 0) {
            temp = temp.next;
            index--;
        }

        return temp;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public T getLast(){
        if (isEmpty())
            return null;

        return tail.data;
    }
}
