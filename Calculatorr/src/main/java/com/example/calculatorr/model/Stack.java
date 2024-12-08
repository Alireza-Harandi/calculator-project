package com.example.calculatorr.model;

import java.awt.dnd.InvalidDnDOperationException;
import java.util.InvalidPropertiesFormatException;

public class Stack<T> {
    private T[] elements;
    private int top;
    private int capacity;
    private int count;

    public Stack()
    {
        capacity = 16;
        elements = (T[]) new Object[capacity];
        top = -1;
        count = 0;
    }

    public void push(T item)
    {
        if (isFull())
            resize(capacity *2);
        elements[++top] = item;
        count++;
    }

    public T pop() throws InvalidPropertiesFormatException {
        if (isEmpty())
            throw new InvalidPropertiesFormatException("Stack is empty");
        count--;
        return elements[top--];

    }

    public T peek() throws InvalidDnDOperationException {
        if (isEmpty())
            throw new InvalidDnDOperationException("Stack is empty");
        return elements[top];
    }

    public int count()
    {
        return count;
    }

    private boolean isEmpty()
    {
        return top == -1;
    }

    private boolean isFull()
    {
        return top == capacity - 1;
    }

    public int size()
    {
        return top + 1;
    }

    private void resize(int newCapacity)
    {
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, count);
        elements = newArray;
        capacity = newCapacity;
    }
}
