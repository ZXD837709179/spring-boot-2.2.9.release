package com.ke.basic.myHashMap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther: Xudong Zhang
 * @create: 2020/5/17 18:13
 * @description:
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {

        //要在初始化的时候就要设置为true,这样就会按照使用的顺序排序，否则只是放入的顺序排序
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(2,0.75f,true);
        map.put("苹果","apple");
        map.put("香蕉","banana");
        map.put("梨子","pear");
        map.put("香蕉","banana2");
        //String i = map.get("香蕉");
        //System.out.println(i);
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
