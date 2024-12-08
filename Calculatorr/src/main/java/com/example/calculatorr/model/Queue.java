package com.example.calculatorr.model;

import java.awt.dnd.InvalidDnDOperationException;

public class Queue<T>
{
    private T[] items;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public Queue(int capacity)
    {
        this.capacity = capacity;
        items = (T[]) new Object[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    public void enqueue(T item)
    {
        if (isFull())
            resize(capacity*2);

        rear = (rear + 1) % capacity;
        items[rear] = item;
        count++;
    }

    public T dequeue() throws InvalidDnDOperationException
    {
        if (isEmpty())
            throw new InvalidDnDOperationException("Queue is empty");

        T item = items[front];
        front = (front + 1) % capacity;
        count--;
        return item;
    }

    public T peek() throws InvalidDnDOperationException
    {
        if (isEmpty())
            throw new InvalidDnDOperationException("Queue is empty");

        return items[front];
    }

    private void resize(int newCapacity){
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(items, 0, newArray, 0, count);
        items = newArray;
        capacity = newCapacity;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    public int count() {
        return count;
    }
}