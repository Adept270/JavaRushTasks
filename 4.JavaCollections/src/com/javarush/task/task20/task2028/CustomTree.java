package com.javarush.task.task20.task2028;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
https://gist.github.com/senichek/5b3e0fe8bfd5df6f7204c3d6c3f71e14

*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root = new Entry<>("0");


    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));

//            throw new UnsupportedOperationException();
        }
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int count = 0;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Entry<String> element = queue.poll();
            element.checkChildren();

            if (element.leftChild != null) {
                count++;
                queue.offer(element.leftChild);
            }
            if (element.rightChild != null) {
                count++;
                queue.offer(element.rightChild);
            }
        }
        return count;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String element) {

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            currentNode.checkChildren();
            if (currentNode.isAvailableToAddChildren()) {
                if (currentNode.availableToAddLeftChildren){
                    currentNode.leftChild = new Entry<>(element);
                    currentNode.leftChild.parent = currentNode;
                    return true;
                } else if (currentNode.availableToAddRightChildren) {
                    currentNode.rightChild = new Entry<>(element);
                    currentNode.rightChild.parent = currentNode;
                    return true;
                }
            } else {
                if (currentNode.leftChild != null){
                    queue.offer(currentNode.leftChild);
                }
                if (currentNode.rightChild != null){
                    queue.offer(currentNode.rightChild);
                }
            }
        }
        return false;
    }

    public String getParent(String s) {
        //возвращает имя родителя элемента дерева, имя которого было полученного в качестве параметра.
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            if (currentNode.elementName.equals(s)){
                return currentNode.parent.elementName;
            } else {
                if (currentNode.leftChild != null) {
                    queue.offer(currentNode.leftChild);
                }
                if (currentNode.rightChild != null) {
                    queue.offer(currentNode.rightChild);
                }
            }
        }
        return null;
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        String s = (String) o;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Entry<String> currentNode = queue.poll();
            if (currentNode.leftChild != null) {
                if (currentNode.leftChild.elementName.equals(s)){
                    currentNode.leftChild = null;
                    return true;
                }
                queue.offer(currentNode.leftChild);
            }
            if (currentNode.rightChild != null) {
                if (currentNode.rightChild.elementName.equals(s)){
                    currentNode.rightChild = null;
                    return true;
                }
                queue.offer(currentNode.rightChild);
            }
        }
        return false;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            /*Реализуй метод void checkChildren, устанавливающий поле availableToAddLeftChildren в false,
             если leftChild не равен null и availableToAddRightChildren в false,
             если rightChild не равен null.*/

            if (leftChild != null) {
                availableToAddLeftChildren = false;
            }
            if (rightChild != null) {
                availableToAddRightChildren = false;
            }
        }

        boolean isAvailableToAddChildren() {
        /*Реализуй метод boolean isAvailableToAddChildren, возвращающий
         дизъюнкцию полей availableToAddLeftChildren и availableToAddRightChildren.*/
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }


}
