public class Array<E> {
    private int size;
    private E[] data;

    public Array(int capacity){
        size = 0;
        data = (E[])new Object[capacity];
    }

    public Array(){
        this(10);
    }

    public int getSize() {
        return size;
    }


    public boolean isEmpty(){
        return this.size == 0;
    }

    public void addLast(E e){
//        if(size == data.length){
//            throw new IllegalArgumentException("array is full");
//        }
//        this.data[size] = e;
//        size++;

        add(size,e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void add(int index,E e){

        if(index > size || index < 0){
            throw new IllegalArgumentException("Add failed. Require index >=0 and index < size");
        }

        if(size == data.length){
            resize(2 * data.length);
        }
        for(int i = size - 1; i >= index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public E get(int index){
        if(index > size || index < 0){
            throw new IllegalArgumentException("Add failed. Require index >=0 and index < size");
        }
        return data[index];
    }

    public void set(int index,E e){
        if(index > size || index < 0){
            throw new IllegalArgumentException("Add failed. Require index >=0 and index < size");
        }
        data[index] = e;
    }

    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    public int find(E e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public E remove(int index){
        if(index > size || index < 0){
            throw new IllegalArgumentException("Add failed. Require index >=0 and index < size");
        }
        E ret = data[index];
        for (int i = index + 1; i < size ; i++) {
            data[i-1] = data[i];
        }
        size--;
        // lazy
        if(size == data.length / 4 && data.length / 2 != 0){
            resize(data.length/2);
        }
        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    public void removeElement(E e){
        int index = find(e);
        remove(index);
    }

    //public void removeAllElement(int e)

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("array size=%d, capacity=%d\n",size,data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if(i != size - 1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public int getCapacity(){
        return data.length;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }
}
