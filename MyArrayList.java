public class MyArrayList<T extends Comparable<T>> implements MyList<T> {
    private Object[] arr;
    private int length = 0;
    private int capacity = 3;

    public MyArrayList() {
        arr = new Object[capacity];
    }

    @Override
    public void add(T item) {
        if (length == capacity)
            increaseCapacity();

        arr[length++] = item;
    }

    @Override
    public void add(T item, int index) {
        length++;
        if (length == capacity)
            increaseCapacity();
        for(int i = length; i > index; i--){
            arr[i] = arr[i - 1];
        }
        arr[index] = item;
    }

    @Override
    public boolean remove(T item) {
        for(int i = indexOf(item); i < length - 1; i++){
            arr[i] = arr[i + 1];
        }
        arr[length - 1] = null;
        length--;
        return false;
    }

    @Override
    public T remove(int index) {
        for(int i = index; i < length - 1; i++){
            arr[i] = arr[i + 1];
        }
        arr[length - 1] = null;
        length--;
        return null;
    }

    @Override
    public void clear() {
        for(int i = 0; i < length; i++){
            arr[i] = null;
        }
        length = 0;
        capacity = 3;
    }

    private void increaseCapacity() {
        capacity = 2 * capacity;
        Object[] old = arr;
        arr = new Object[capacity];

        for (int i = 0; i < old.length; i++)
            arr[i] = old[i];
    }

    public T get(int index) {
        return (T)arr[index];
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < length; i++){
            if(o == arr[i]){
                return i;
            }
        }
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = length - 1; i > 0;i++){
            if(o == arr[i]){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sort() {
        for (int i = 0; i < length - 1; i++){
            for (int j = 0; j < length - i - 1; j++){
                if (get(j).compareTo(get(j + 1)) > 0) {
                    T temp = get(j + 1);
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public int size() {
        return length;
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < length; i++){
            if(o == arr[i]){
                return true;
            }
        }
        return false;
    }



}
