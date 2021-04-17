package Uebungen.u03;

import java.util.ArrayList;

public class MyDeque<T> {

    private Node<T> first;
    private Node<T> tail;

    public MyDeque(){
        this.first = null;
    }

    static class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private T data;

        private Node(T data){
            this.data = data;
        }
    }

    public void addFirst(T data){
        Node<T> node = new Node<>(data);
        if(this.isFistNull()) {
            this.first = node;
            this.tail = this.first;
            return;
        }
        this.first.prev = node;
        node.next = this.first;
        this.first = node;
    }

    public void addLast(T data){
        Node<T> node = new Node<>(data);
        if(this.isFistNull()) {
            this.first = node;
            this.tail = this.first;
            return;
        }
        this.tail.next = node;
        node.prev = this.tail;
        this.tail = node;
    }

    public T removeFirst(){
        Node<T> first = this.first;
        this.first.next.prev = null;
        this.first.next = null;
        //this.first = this.first.next;
        if(this.isFistNull()) {
            this.first = null;
            this.tail = null;
        }
        return first.data;
    }

    public T removeLast(){
        Node<T> tail = this.tail;
        this.tail.prev.next = null;
        this.tail.prev = null;
        if(this.isFistNull()) {
            this.first = null;
            this.tail = null;
        }
        return tail.data;
    }

    public ArrayList<T> toArrayList(){
        if(this.isFistNull())
            return null;
        ArrayList<T> list = new ArrayList<>();
        Node<T> tmp = this.first;
        while(tmp.next != null){
            list.add(tmp.data);
            tmp = tmp.next;
        }
        list.add(tmp.data);
        return list;
    }

    public ArrayList<T> toReverseArrayList(){
        if(this.isFistNull())
            return null;
        ArrayList<T> list = new ArrayList<>();
        Node<T> tmp = this.tail;
        while(tmp.prev != null){
            list.add(tmp.data);
            tmp = tmp.prev;
        }
        list.add(tmp.data);
        return list;
    }

    private boolean isFistNull(){
        return this.first == null;
    }

    public static void main(String[] args) {
        MyDeque<Integer> test = new MyDeque<>();
        test.addFirst(3);
        test.addFirst(2);
        test.addFirst(1);
        for(int i = 4; i < 20; i++)
            test.addLast(i);
        System.out.println(test.removeFirst());
        System.out.println(test.removeLast());
        System.out.println(test.toArrayList().toString());
        System.out.println(test.toReverseArrayList().toString());
    }
}
