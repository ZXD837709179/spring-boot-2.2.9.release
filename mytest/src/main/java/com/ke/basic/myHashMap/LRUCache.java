package com.ke.basic.myHashMap;

import jdk.nashorn.internal.runtime.PrototypeObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther: Xudong Zhang
 * @create: 2020/5/17 18:14
 * @description:
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache c = new LRUCache(2);
        c.put(1,1);
        c.put(2,2);
        // c.put(2,4);
        System.out.println(c.get(2));
        int i = c.get(1);
        c.put(3,3);
        System.out.println(c.get(2));
        System.out.println(c.map.size());


    }

    LinkedHashMap<Integer,Integer> map=null;
    int size = 0;
    public LRUCache(int capacity) {
        size = capacity;
        map = new LinkedHashMap(capacity,0.75f,true);
    }

    public int get(int key) {
        Object o = map.get(key);
        if(o==null){
            return -1;
        }
        return (int)o;

    }

    public void put(int key, int value) {
        if(map.size()>=size){
            Iterator<Map.Entry<Integer,Integer>> it = map.entrySet().iterator();
            it.next();
            it.remove();
        }
        map.put(key,value);
    }
}
