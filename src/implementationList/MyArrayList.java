package implementationList;

import java.util.Arrays;

public class MyArrayList <E extends Object> {
    private static final int INITIAL_CAPACITY = 10;
    private int size = 0;
    private Object elementData[] = {};
    
    public MyArrayList(){
        elementData = new Object[INITIAL_CAPACITY];
    }
    
    public MyArrayList(int capacity){
        elementData = new Object[capacity];
    }
    
    public boolean add(E e){
        if (size == elementData.length) {
            if(!ensureCapacity()){
                return false;
            } 
        }
        elementData[size++] = e; 
        return true;
    }
    
    private boolean ensureCapacity() {
        int newIncreasedCapacity = elementData.length * 2;
        elementData = Arrays.copyOf(elementData, newIncreasedCapacity);
        return true;
    }
    
    public E get(int index) throws Exception{
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " 
                    + index + ", Size " + index);
        }
        return (E) elementData[index]; 
    }
    
    public Object remove(int index) throws Exception{
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " 
                    + index + ", Size " + index);
        }
        Object removedElement = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return removedElement;
    }
    
    


}
