package implementationList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList <E> implements List<E>{
    
    private static class MyNode<E>{
        private E data;
        private MyNode<E> next;
        
        public MyNode(E data) {
            this.data = data;
            this.next = null;
        }
        public MyNode(E data, MyNode<E> next){
            this.data = data;
            this.next = next;
        }
        public E getData() {
            return data;
        }
        public void setData(E data) {
            this.data = data;
        }
        public MyNode<E> getNext() {
            return next;
        }
        public void setNext(MyNode next) {
            this.next = next;
        }
    }
    
    private MyNode<E> head;
    private int size;
    
    public MyLinkedList(){
        head = null;
    }
    public MyLinkedList(Collection<? extends E> c){
        this();
        addAll(c);
    }
    
    @Override
    public boolean add(E item) {
        addLast(item);
        return true;
    }
    @Override
    public void add(int index, E element) {
        MyNode tmp = new MyNode(element);
        MyNode nodeCurrent = head;       
        if (nodeCurrent != null) {
            for (int i = 0; i < index && nodeCurrent.getNext() != null; i++) {
                nodeCurrent = nodeCurrent.getNext();
            }
        }       
        tmp.setNext(nodeCurrent.getNext());
        nodeCurrent.setNext(tmp);
        size++;
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (Object obj : c) {
            add(size,(E)obj);
            size++;
        }
        return true;
    }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (c.isEmpty()) {
            return false;
        }
        for (Object obj : c) {
            add(index,(E)obj);
            index++;
        }
        size += c.size();
        return true;
    }
    public void addFirst(E item){
        head = new MyNode<E>(item, head);
        size++;
    }   
    public void addLast(E item){
        if( head == null){
            addFirst(item);
        } 
        else
        {
            MyNode<E> tmp = head;
            while(tmp.getNext() != null){
                tmp = tmp.getNext();
            }
            tmp.setNext(new MyNode<E>(item));
            size++;
        }
    }
    @Override
    public void clear(){
        head = null;
        size = 0;
    }
    @Override
    public boolean contains(Object o){
        return indexOf(o) > -1;
   }
    @Override
    public E get(int pos){
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        MyNode<E> tmp = head;
        for (int k = 0; k < pos; k++) {
            tmp = tmp.getNext();
        }
        if( tmp == null) {
            throw new IndexOutOfBoundsException();
        }
        return tmp.getData();
    }
    public E getFirst(){
        if(head == null) {
            throw new NoSuchElementException();
        }
        return head.getData();
    }
    public E getLast(){
        if(head == null) {
            throw new NoSuchElementException();
        }
        MyNode<E> tmp = head;
        while(tmp.getNext() != null) {
            tmp = tmp.getNext();
        }
        return tmp.getData();
    }
    @Override
    public int indexOf(Object o) {
        int index = 0;
        MyNode<E> tmp = head;
        if (o == null) {
            while (tmp.getNext() != null) {
                tmp = tmp.getNext();
                if (tmp.getData()== null) {
                    return index;
                }
                index++;
            }
        } else {
            while (tmp != null) {
                tmp = tmp.getNext();
                if (o.equals(tmp.getData())) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }
    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        MyNode<E> tmp = head;
        if (o == null) {
            for(int i = 0; i < size && tmp.getNext() != null; i++){
                if (tmp.getData() == null) {
                    index = i;
                }
                return index;
            }
        } else {
            for(int i = 0; i < size && tmp.getNext() != null; i++){
                if (o.equals(tmp.getData())) {
                    index = i;
                }
            }
            return index;
        }
        return -1;
    }
    
    public boolean offer(E e) {
        return offerLast(e);
    }
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }
    public E peek() {
        return (head == null) ? null : head.getData();
    }
    public E peekFirst() {
        return peek();
    }
    public E peekLast() {
        if (head == null) {
            return null;
        }
        return getLast();
    }
    
    private MyNode<E> getNode(int pos){
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        MyNode<E> tmp = head;
        for (int k = 0; k < pos; k++) {
            tmp = tmp.getNext();
        }
        if( tmp == null) {
            throw new IndexOutOfBoundsException();
        }
        return tmp;
    }
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            if(head.getData() == null){
                removeFirst();
                
                return true;
            }
            for (int i = 1; i < size; i++) {
                if(get(i) == null){
                    getNode(i-1).setNext(getNode(i).getNext());
                    size--;
                }
            }
        } else {
            for (int i = 1; i < size; i++) {
                if(get(i).equals(o)){
                    getNode(i-1).setNext(getNode(i).getNext());
                    size--;
                }
            }
        }
        return false;
    }
    @Override
    public E remove(int index) {
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }
        if(index == 1){
            return removeFirst();
        }else{
            E tmp = get(index);
            getNode(index-1).setNext(getNode(index).getNext());
            size--;
            return tmp;
        }
    }
    public E removeFirst(){
        if (head == null) {
            return null;
        }
        E tmp = getFirst();
        head = head.getNext();
        size--;
        return tmp;
    }  
    @Override
    public boolean isEmpty(){
        return head == null;
    }
    public MyLinkedList<E> reverse(){
        MyLinkedList<E> list = new MyLinkedList<E>();
        MyNode<E> tmp = head;
        
        while(tmp != null){
            list.addFirst( tmp.getData() );
            tmp = tmp.getNext();
        }
        return list;
    }
    @Override
    public String toString(){
        String output = "";
        if (head != null) {
            MyNode node = head.getNext();
            while (node != null) {
                output += "[" + node.getData().toString() + "]";
                node = node.getNext();
            }
        }
        return output;
    }
    public void insertAfter(E key, E toInsert){
        MyNode<E> tmp = head;
        while(tmp != null && !tmp.getData().equals(key)) {
            tmp = tmp.getNext();
        }
        if(tmp != null){
            tmp.next = new MyNode<E>(toInsert, tmp.getNext());
        }
    } 
    public void insertBefore(E key, E toInsert){
        if(head == null){
            return;
        }
        if(head.getData().equals(key)){
            addFirst(toInsert);
            return;
        }
        MyNode<E> prev = null;
        MyNode<E> cur = head;
        
        while(cur != null && !cur.getData().equals(key)){
            prev = cur;
            cur = cur.getNext();
        }
        if(cur != null){
            prev.setNext(new MyNode<E>(toInsert, cur));
        }
    }
    public E pop() {
        return removeFirst();
    }
    public void push(E e) {
        addFirst(e);
    }
    public E poll() {
        return pollFirst();
    }
    public E pollFirst() {
        if (head == null) {
            return null;
        }
        MyNode<E> h = head;
        remove(0);
        return head.getData();
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    @Override
    public Object[] toArray() {
        MyNode<E> tmp = head;
        Object[] array = new Object[size];
        int i = 0;
        while (tmp != null) {
            array[i] = tmp.getData();
            i++;
            tmp = tmp.getNext();
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            throw new IndexOutOfBoundsException();
        }
        MyNode nodeCurrent = head;  
       
        for (int i = 0; i < size && nodeCurrent.getNext() != null; i++) {
            ((Object[]) a)[i] = nodeCurrent.getData();
        }
        return a;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection can't be null!");
        }
        boolean flaf = false;
        for (Object o : c) {
            while (contains(o)) {
                remove(o);
                flaf = true;
            }
        }
        return flaf;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection can't be null!");
        }
        MyLinkedList<E> help = new MyLinkedList<>();
        help.addAll(this);
        help.removeAll(c);
        return removeAll(help);
    }

    @Override
    public E set(int index, E element) {
        if (index > (size - 1)) {
            throw new IndexOutOfBoundsException();
        }
        MyNode<E> x = getNode(index);
        E oldItem = x.getData();
        x.setData((E)element);
        return oldItem;
    }
    
    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); 
        
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); 
        
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        MyLinkedList<E> subList = new MyLinkedList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(get(i));
        }
        return subList;
    }
    
    private class MyLinkedListIterator implements Iterator<E> {
        int cursor = 0;
        @Override
        public boolean hasNext() {
            return cursor != size();
        }
        @Override
        public E next() {
            int i = cursor;
            E next = get(i);
            cursor = i + 1;
            return next;
        }
    }
    
    
    
}
