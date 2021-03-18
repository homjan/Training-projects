package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    private List<Entry> list;

    public CustomTree() {
        this.root = new Entry<>("0");
        this.list = new ArrayList<>();
        list.add(root);
    }

    @Override
    public boolean add(String elementName) {
        Entry entry = new Entry(elementName);

        for(int i = 0; i < list.size(); i++) {
            Entry nextEntry = list.get(i);
            if(nextEntry.isAvailableToAddChildren()) {
                entry.parent = nextEntry;

                if(nextEntry.availableToAddLeftChildren) {
                    nextEntry.leftChild = entry;
                    nextEntry.availableToAddLeftChildren = false;

                } else {
                    nextEntry.rightChild = entry;
                    nextEntry.availableToAddRightChildren = false;
                }

                list.add(entry);
                break;
            }
        }

        return true;
    }

    @Override
    public int size() {
        return list.size() - 1;
    }

    public String getParent(String s) {
        Entry top = root;
        String result="no element";
        Queue<Entry> queue = new LinkedList<>();
        queue.add(top);
        do{
            if (top.elementName !=null)
            {
                if (top.elementName.equals(s))
                {
                    result = top.parent.elementName;
                    break;
                }
            }
            if (top.leftChild!=null) queue.add(top.leftChild);
            if (top.rightChild!=null) queue.add(top.rightChild);
            if (!queue.isEmpty()) top=queue.poll();
        }while (!queue.isEmpty());
        return result;
    }


    @Override
    public boolean remove(Object o) {
        if(!(o instanceof String))
            throw new UnsupportedOperationException();

        String elementName = (String) o;

        //Перебираем лист, пропуская root
        for(int i = 1; i < list.size(); i++) {

            Entry nextEntry = list.get(i);
            //Находим необходимую для удаления запись
            if(nextEntry.elementName.equals(elementName)) {

                //Удаляем найденную запись и ее детей
                removeChilds(nextEntry);
                list.remove(i);

                //восстанавливаем возможность добавить левого или правого ребенка
                Entry parent = nextEntry.parent;

                if(parent.leftChild.elementName == elementName) {
                    parent.availableToAddLeftChildren = true;
                    parent.leftChild = null;
                } else {
                    parent.availableToAddRightChildren = true;
                    parent.rightChild = null;
                }
            }
        }


        return true;
    }

    private void removeChilds(Entry entry) {
        List<Entry> childs = new ArrayList<>();
        for(int i = 0;  i < list.size(); i++) {
            Entry e = list.get(i);
            if(e.elementName == entry.elementName) {
                Entry leftChild = e.leftChild;
                Entry rightChild = e.rightChild;

                if(leftChild != null) {
                    removeChilds(leftChild);
                    childs.add(leftChild);
                }

                if(rightChild != null) {
                    removeChilds(rightChild);
                    childs.add(rightChild);
                }
            }
        }
        list.removeAll(childs);
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }


    @Override
       public String set(int index, String element){
           throw new UnsupportedOperationException();
       }

    @Override
    public void add(int index, String element){

        throw new UnsupportedOperationException();
    }
    @Override
    public String remove(int index){
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c){
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {

        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren(){

            boolean a = availableToAddLeftChildren || availableToAddRightChildren;
            return a;
        }
    }


}
