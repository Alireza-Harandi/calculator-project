package com.example.calculatorr.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoublyLinkedList<T> implements Iterable<T>
{
    @Getter
    @Setter
    public class Node<T>{
        private T data;
        private Node next;
        private Node prev;

        public Node(T data)
        {
            this.data = data;
            next = null;
            prev = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
    }

    public boolean remove(T value) {
        if (head == null)
            return false;

        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                if (current == head) {
                    head = head.next;

                    if (head != null)
                        head.prev = null;
                }
                else if (current == tail) {
                    tail = tail.prev;

                    if (tail != null)
                        tail.next = null;
                }
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }

                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (T current : this){
            result.append(current.toString()).append("\n");
        }

        return result.toString().trim();
    }

    public boolean contains(T value) {
        for (T current : this){
            if (current.equals(value))
                return true;
        }

        return false;
    }


}
