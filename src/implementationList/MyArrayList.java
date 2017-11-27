package implementationList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList <E> implements List<E>{
    private static final int INITIAL_CAPACITY = 10;
    private int size = 0;
    private Object elementData[] = {};
    int cursor;
    
    private void checkSize() {
        if (size == elementData.length) {
            int newIncreasedCapacity = elementData.length * 2;
            elementData = Arrays.copyOf(elementData, newIncreasedCapacity);
        }
    }
    private boolean checkIndex(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }else {
            return true;
        }
    }
    private boolean checkArgumentPositive(int arg){
        if(arg >= 0){
            return true;
        }else {
            throw new IllegalArgumentException("Illegal argument" 
                    + arg);
        }
    }
    
    public MyArrayList(){
        this.elementData = new Object[INITIAL_CAPACITY];
    }   
    public MyArrayList(int initialCapacity){
        if(checkArgumentPositive(initialCapacity)){
            this.elementData = new Object[initialCapacity];
        }
    }   
    public MyArrayList(Collection<? extends E> c) {
        this.elementData = c.toArray();
        this.size = elementData.length;
    }
    private MyArrayList(Object[] array) {
        this.elementData = array;
        this.size = elementData.length;
    }
    
    @Override
    public boolean add(E e){
        checkSize();
        elementData[size++] = e; 
        return true;
    }
    @Override
    public void add(int index, E element) {
        if(checkArgumentPositive(index)){
            checkSize();
            System.arraycopy(elementData, index, 
                    elementData, index + 1, size - index);
            elementData[index] = element;
            size++;
        }
        
    }   
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() > 0) {
            Object[] tmp = c.toArray();
            while ((c.size() + this.size) > elementData.length) {
                checkSize();
            }
            System.arraycopy(tmp, 0, elementData, size, c.size());
            size += c.size();
            return true;
        }
        return false;
    }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);
        if (c.size() > 0 && checkArgumentPositive(index)) {
            Object[] a = c.toArray();
            while ((c.size() + this.size) > elementData.length) {
                checkSize();
            }
            if (size - index > 0) {
                System.arraycopy(elementData, index, elementData, index + c.size(), size - index);
            }
            System.arraycopy(a, 0, elementData, index, c.size());
            size += c.size();
            return true;
        }
        return false;
    }
    @Override
    public void clear() {
        if (size > 0) {
            elementData = new Object[INITIAL_CAPACITY];
        }
    }
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public E get(int index){
        checkArgumentPositive(index); 
        checkIndex(index);
        return (E) elementData[index]; 
    }
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }

        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator<>();
    }
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = elementData.length - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public E remove(int index){
        checkIndex(index);
        if(index > 0){
            E removedElement = (E)elementData[index];
            for (int i = index; i < size - 1; i++) {
                elementData[i] = elementData[i + 1];
            }
            size--;
            return removedElement;
        }else{
            throw new IndexOutOfBoundsException("Index: " + index + 
                    ", size: " + size);
        }
        
    }
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (elementData[index] == null) {
                    remove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(elementData[index])) {
                    remove(index);
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        checkArgumentPositive(index);
        E oldElement = (E) elementData[index];
        elementData[index] = element;
        return oldElement;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex < 0 || fromIndex > size - 1 
                || toIndex > size || toIndex < fromIndex) {
            throw new ArrayIndexOutOfBoundsException("From index: " + fromIndex 
                    + ", toIndex: " + toIndex + ", size: " + size);
        }
        Object[] array = new Object[toIndex - fromIndex];
        System.arraycopy(elementData, fromIndex, array, 0, toIndex - fromIndex + 1);
        return new MyArrayList(array);
    }
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elementData, size);
    }
    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }  
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     class MyArrayListIterator<E> implements Iterator<E> {
        int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }
        @Override
        public E next() {
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] elementData = MyArrayList.this.elementData;
            cursor = i + 1;
            return (E) elementData[i];
        }

    }
}
