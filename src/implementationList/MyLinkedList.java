package implementationList;

import java.util.NoSuchElementException;

public class MyLinkedList <E extends Object> {
    
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
    
    public MyLinkedList(){
        head = null;
    }
    
    public void add(E item) {
        addLast(item);
    }
    public void add(E data, int index) {
        MyNode tmp = new MyNode(data);
        MyNode nodeCurrent = head;       
        if (nodeCurrent != null) {
            for (int i = 0; i < index && nodeCurrent.getNext() != null; i++) {
                nodeCurrent = nodeCurrent.getNext();
            }
        }       
        tmp.setNext(nodeCurrent.getNext());
        nodeCurrent.setNext(tmp);
        
    } 
    public void addFirst(E item){
        head = new MyNode<E>(item, head);
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
        }
    }

    public void clear(){
        head = null;
    }
    public  MyLinkedList<E> clone(){
        MyLinkedList<E> twin = new MyLinkedList<E>();
        MyNode<E> tmp = head;
        while(tmp != null){
            twin.addLast(tmp.getData());
            tmp = tmp.getNext();
        }
        return twin;
    }
    public boolean contains(Object o){
        if(head != null){
            MyNode<E> tmp = head;
            while(tmp.getNext() != null){
                if(tmp.equals(o)){
                    return true;
                }
                tmp = tmp.getNext();        
            }
        }  
        return false;
   }
    
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
    
    public boolean isEmpty(){
        return head == null;
    }
    public E removeFirst(){
        E tmp = getFirst();
        head = head.getNext();
        return tmp;
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
    
}
